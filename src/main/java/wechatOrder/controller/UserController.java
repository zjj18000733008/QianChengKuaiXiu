package wechatOrder.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.wxpay.sdk.WXPayUtil;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import wechatOrder.crypto.AES;
import wechatOrder.crypto.WXCore;
import wechatOrder.exception.BussinessRuntimeException;
import wechatOrder.exception.OpenidIsNullException;
import wechatOrder.exception.SomeParamIsNullException;
import wechatOrder.po.User;
import wechatOrder.service.UserService;
import wechatOrder.util.UrlUtils;

import javax.annotation.Resource;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.transform.Result;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.spec.AlgorithmParameterSpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static  wechatOrder.util.LoginUtils.getUserWXLoginInfo;

/**
 * @author JJ
 * @date 2019/12/1 - 13:47
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource(name = "userServiceImpl")
    UserService userService;

    @Value("#{prop.appid}")
    public String appid;
    @Value("#{prop.secret}")
    public String secret;
    @Value("#{prop.grant_type}")
    public String grant_type;


    /**
     * 这个接口用于小程序端的用户登录
     *
     * @param wxCode  调用wx.login()所得到的code
     * @param rawData 用户的基本信息
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public Map userLogin(String wxCode, String rawData, String signature, String encrypteData, String iv, HttpServletRequest req, HttpServletResponse res) throws Exception {
        //先预声明一些变量
        HttpSession session = req.getSession();
        System.out.println("sessionid>>>" + session.getId());
        Map map = new HashMap();
        User user1 = null;

        if (wxCode == null || "".equals(wxCode)) {
            throw new SomeParamIsNullException("wxCode不能为空值");
        }
        System.out.println("wxCode>>>" + wxCode);

        //调用微信的接口得到openid用户唯一标识和session_key
        JSONObject jsonObject = getUserWXLoginInfo(wxCode);

        if (jsonObject == null) {
            throw new Exception("jsonObject为空,有可能是调请求https://api.weixin.qq.com/sns/jscode2session超时所导致");
        }
        if (jsonObject.containsKey("errmsg")) {
            //如果微信平台返回的数据中不包含openid则抛出异常
            throw new BussinessRuntimeException((String) jsonObject.get("errmsg"));
        }
        System.out.println("jsonObject>>>>>" + jsonObject);
        String openid = (String) jsonObject.get("openid");
        String sessionKey = (String) jsonObject.get("session_key");


        //通过openid查询数据库是否有此用户
        User user = userService.getUserByOpenid(openid);

        if (user == null) {//用户不存在,即用户是第一次登陆

            if (rawData == null) {
                throw new SomeParamIsNullException("rawData是空值");
            }
            //将小程序传来的用户基本信息解析为json数据
            JSONObject rawDataJsonObject = JSON.parseObject(rawData);

            //填充用户数据
            user1 = new User();
            user1.setGender(String.valueOf(rawDataJsonObject.getIntValue("gender")));
            user1.setNickname(rawDataJsonObject.getString("nickName"));
            user1.setCity(rawDataJsonObject.getString("city"));
            user1.setProvince(rawDataJsonObject.getString("province"));
            user1.setCountry(rawDataJsonObject.getString("country"));
            user1.setRoleId(1);//默认用户的角色为普通用户
            user1.setUserAvatar(rawDataJsonObject.getString("avatarUrl"));
            user1.setSessionKey(sessionKey);
            user1.setOpenid(openid);

            //将用户数据存入数据库
            userService.saveUser(user1);//这时user的id会注入到user中

            //将用户信息存入session
            session.setAttribute("user", new User(user1));

            //使用的是springsecurity
            //小程序用户认证成功,手动将Authentication对象放入springsecurity中
            SimpleGrantedAuthority role_user = new SimpleGrantedAuthority("ROLE_USER");
            ArrayList<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<SimpleGrantedAuthority>();
            simpleGrantedAuthorities.add(role_user);
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(new User(user1),null,simpleGrantedAuthorities);
            authRequest.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
            SecurityContextHolder.getContext().setAuthentication(authRequest);
            //服务器不应当把sessionKey传到小程序客户端以及服务器之外的环境
            user1.setSessionKey(null);
            user1.setOpenid(null);
            //将用户信息返回给前端
            map.put("userInfo", user1);


        } else {//如果用户之前已经登录过小程序,即数据库中有该用户的信息

            //刷新数据库中的sessionKey
            user.setSessionKey(sessionKey);
            userService.updateUserByOpenid(user);

            //将用户信息存入session
            session.setAttribute("user", new User(user));

            //使用的是springsecurity
            //小程序用户认证成功,手动将Authentication对象放入springsecurity中
            SimpleGrantedAuthority role_user = new SimpleGrantedAuthority("ROLE_USER");
            ArrayList<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<SimpleGrantedAuthority>();
            simpleGrantedAuthorities.add(role_user);
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(new User(user),null,simpleGrantedAuthorities);
            authRequest.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
            SecurityContextHolder.getContext().setAuthentication(authRequest);

            //仍然是不能把sessionkey返回给小程序
            user.setSessionKey(null);
            user.setOpenid(null);
            //将用户信息返回给前端
            map.put("userInfo", user);


        }

        //将JSESSINID存入cookie中,并向小程序发送该cookie
        Cookie cookie = new Cookie("JSESSION", session.getId());
        res.addCookie(cookie);

        map.put("msg", "登录成功");
        return map;
    }

    /**
     * 用于获取用户手机号
     *
     * @param wxCode
     * @param encryptedData
     * @param iv
     * @return
     */
    @RequestMapping("/getUserPhone")
    @ResponseBody
    public ResponseEntity getUserPhone(HttpServletRequest req, @RequestParam String wxCode, String encryptedData,  String iv) throws Exception {
        String result = "";

        JSONObject jsonObject = getUserWXLoginInfo(wxCode);
        if(jsonObject==null){
            return new ResponseEntity("jsonObject为空,可能是服务器请求微信官方接口超时导致",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(jsonObject.containsKey("errmsg")){
            String errmsg = (String) jsonObject.get("errmsg");
            System.out.println(errmsg);
            return new ResponseEntity(errmsg,HttpStatus.PRECONDITION_FAILED);
        }

        //判断数据库中是否有该用户的相关信息(判断用户是否是第一次登录)
        User userInDB = userService.getUserByOpenid((String) jsonObject.get("openid"));
        //如果用户是第一次登录,那么获取了手机号也没用,没有用户的其他基本信息(如昵称,头像等)
        if(userInDB==null){
            return new ResponseEntity("新用户请先登录",HttpStatus.BAD_REQUEST);
        }else if(userInDB.getPhoneNumber()!=null && userInDB.getPhoneNumber().length()>0){//如果用户不是第一次登录,则查看数据库中是否有该用户的手机号,有则直接返回
            HashMap map = new HashMap();
            map.put("purePhoneNumber",userInDB.getPhoneNumber());
            return new ResponseEntity(map,HttpStatus.OK);
        }
        //用户不是第一次登录,且数据库中没有用户的手机号,则需要根据密文解密出手机号
        if(encryptedData==null || "".equals(encryptedData) || iv==null || "".equals(iv)){
            return new ResponseEntity("请传入密文和向量以解密出手机号",HttpStatus.PRECONDITION_REQUIRED);
        }

        //解密数据
        byte[] encData = Base64.decode(encryptedData);
        byte[] iv2=Base64.decode(iv);
        byte[] key = Base64.decode((String) jsonObject.get("session_key"));
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(iv2);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.DECRYPT_MODE,keySpec,ivSpec);
        result = new String(cipher.doFinal(encData), "UTF-8");//获取到解密后的数据
        JSONObject jsonResult = JSONObject.parseObject(result);//将解密后的数据解析成json格式

        if(jsonResult.containsKey("purePhoneNumber")){

            userInDB.setPhoneNumber(jsonResult.getString("purePhoneNumber"));
            userService.updateUserByOpenid(userInDB);
        }

        return new ResponseEntity(jsonResult, HttpStatus.OK);
    }



}
