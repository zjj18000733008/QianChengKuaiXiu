package wechatOrder.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

/**
 * @author JJ
 * @date 2019/12/7 - 11:13
 */
public class LoginUtils {

    @Value("#{prop.appid}")
    public static String appid;
    @Value("#{prop.secret}")
    public static String secret;
    @Value("#{prop.grant_type}")
    public static String grant_type;

    /**
     * 将code,appid,secret,grant_type发给微信平台的接口以换取openid
     * @param wxCode
     * @return
     */
    public static JSONObject getUserWXLoginInfo(String wxCode) {
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String,String> requestUrlParam = new HashMap<String,String>();
        requestUrlParam.put("appid", appid);	//开发者设置中的appId
        requestUrlParam.put("secret", secret);	//开发者设置中的appSecret
        requestUrlParam.put("js_code", wxCode);	//小程序调用wx.login返回的code
        requestUrlParam.put("grant_type",grant_type);	//默认参数
        //发送post请求读取调用微信 https://api.weixin.qq.com/sns/jscode2session 接口获取openid用户唯一标识
        JSONObject jsonObject = JSON.parseObject(UrlUtils.sendPost(requestUrl, requestUrlParam));
        return jsonObject;
    }
}
