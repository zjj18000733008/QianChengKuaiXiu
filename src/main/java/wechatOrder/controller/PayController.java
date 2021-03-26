package wechatOrder.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wechatOrder.config.WxConfig;
import wechatOrder.dao.OrderMapper;
import wechatOrder.exception.OpenidIsNullException;
import wechatOrder.po.Order;
import wechatOrder.po.Orderitem;
import wechatOrder.po.User;
import wechatOrder.service.OrderService;
import wechatOrder.util.LoginUtils;
import wechatOrder.util.SecurityUtils;
import wechatOrder.util.UrlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Signature;
import java.security.interfaces.RSAKey;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JJ
 * @date 2019/12/7 - 19:14
 */
@RestController
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderService orderService;
    private static WxConfig wxConfig = new WxConfig();
    private static final Logger log = LoggerFactory.getLogger(PayController.class);

    @RequestMapping("/order")
    public ResponseEntity order(String orderId, HttpServletRequest req) throws Exception {

//        HttpSession session = req.getSession();
//        User user = (User) session.getAttribute("user");
//        if (user == null) {
//            return new ResponseEntity("请先登录", HttpStatus.UNAUTHORIZED);
//        }
        User user = (User) SecurityUtils.getCurrentUser();

        if (orderId == null) {
            return new ResponseEntity("orderId不能为空", HttpStatus.PRECONDITION_REQUIRED);
        }

        String openid = user.getOpenid();
//        String openid="oXAT_4-kbibmSDEyUsLcDOVed0Kw";//为了方便测试,这里将openid设为定值
        log.info("orderId>>>" + orderId);

        //判断该订单是否处于未支付状态,只有订单处于未支付状态才可以支付,防止多次支付
        Order order1 = orderMapper.selectByPrimaryKey(orderId);
        String state = order1.getState();
        if (!state.equals("0")) {//如果该订单不处于未支付状态
            return new ResponseEntity("orderId为" + orderId + "的订单不处于未支付状态", HttpStatus.FORBIDDEN);
        } else if (state.equals("1")) {
            return new ResponseEntity("此订单已支付过", HttpStatus.BAD_REQUEST);
        }

//        //用沙盒测试
//        //获取沙盒环境验收密钥
//        HashMap<String, String> mapSandbox = new HashMap<String, String>();
//        mapSandbox.put("mch_id", "1567380971");
//        mapSandbox.put("nonce_str", WXPayUtil.generateNonceStr());
//        String signSandbox = WXPayUtil.generateSignature(mapSandbox, wxConfig.getKey());
//        mapSandbox.put("sign", signSandbox);
//        String xmlSandbox = requestWithoutCert("https://api.mch.weixin.qq.com/sandboxnew/pay/getsignkey", mapSandbox, 8000, 10000);
//        Map<String, String> resultMapSandbox = WXPayUtil.xmlToMap(xmlSandbox);
//        //获得沙盒环境验收密钥
//        String sandbox_signkey = resultMapSandbox.get("sandbox_signkey");
//        wxConfig.setKey(sandbox_signkey);

//        WXPay wxPay = new WXPay(wxConfig, WXPayConstants.SignType.MD5, true);//沙盒测试
        WXPay wxPay = new WXPay(wxConfig, WXPayConstants.SignType.MD5);//正式上线使用
        log.info("开始获取订单信息...");
        Order order = orderMapper.getOrderById(orderId);//获取订单信息
        log.info("获取ip...");
        //获取ip
        String ip = req.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getRemoteAddr();
        }
        if (ip.indexOf(",") != -1) {
            String[] ips = ip.split(",");
            ip = ips[0].trim();
        }
        log.info("ip>>>" + ip);


        try {
            //拼接统一下单地址参数
            Map rawData = new HashMap();
            log.info("拼接统一下单地址参数...");
            rawData.put("appid", wxConfig.getAppID());
            rawData.put("mch_id", wxConfig.getMchID());
            rawData.put("nonce_str", WXPayUtil.generateNonceStr());
            rawData.put("body", "这是支付的body");
            rawData.put("out_trade_no", orderId);
            Double v = order.getActualAmount() * 100;
            int i = v.intValue();
            rawData.put("total_fee", Integer.valueOf(i).toString());
//            rawData.put("total_fee", "101");//沙盒测试时,支付金额是写死的,见https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=23_13最下面的公众号支付用例
            rawData.put("openid", openid);
            rawData.put("spbill_create_ip", ip);
            rawData.put("trade_type", "JSAPI");
            rawData.put("notify_url", "https://wexin.xiangyata.net.cn/qiancheng/pay/callback");
//            rawData.put("notify_url", "http://2u854q4254.zicp.vip/pay/callback");
            String sign = WXPayUtil.generateSignature(rawData, wxConfig.getKey());//生成签名
            rawData.put("sign", sign);
            String xml = WXPayUtil.mapToXml(rawData);
            log.info("调用微信统一下单接口...");
            Map map = wxPay.unifiedOrder(rawData);//调用微信统一下单接口并得到返回的数据
            String prepay_id = (String) map.get("prepay_id");
            if (prepay_id == null) {
                log.error("prepay_id为空值");
                return new ResponseEntity("调用微信统一下单接口失败", HttpStatus.NOT_ACCEPTABLE);
            }
            Map<String, String> payMap = new HashMap<String, String>();
            payMap.put("appId", wxConfig.getAppID());
            payMap.put("timeStamp", System.currentTimeMillis() + "");
            payMap.put("nonceStr", WXPayUtil.generateNonceStr());
            payMap.put("signType", "MD5");
            payMap.put("package", "prepay_id=" + prepay_id);
            String paySign = WXPayUtil.generateSignature(payMap, wxConfig.getKey());
//            String paySign = WXPayUtil.generateSignature(payMap, "1f1bdd8c46cc28e217130e5372f76e72");//这里为了测试,使用sandbox_signkey
            payMap.put("paySign", paySign);
            return new ResponseEntity(payMap, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("拼接地址参数时发生异常:" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * 支付结果通知接口,notify_url设置的接口
     * 不能携带参数,必须使用外网接口地址,不能使用本地调试地址
     * <p>
     * 支付完成后，微信会把相关支付结果及用户信息通过数据流的形式发送给商户，
     * 商户需要接收处理，并按文档规范返回应答。
     *
     * @return
     */
    @RequestMapping("/callback")
    public void callback(HttpServletRequest req, HttpServletResponse res) {

        String resXml = "";
        HashMap<String, String> resMap = new HashMap<String, String>();
        InputStream inStream;
        Order order = null;


        try {

            inStream = req.getInputStream();
            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }

            log.info("wxnotify:微信支付----start----");

            // 获取 微信调用我们的notify_url 返回的信息
            String result = new String(outSteam.toByteArray(), "utf-8");
            log.info("wxnotify:微信支付----result----=" + result);

            // 关闭流
            outSteam.close();
            inStream.close();

            // xml转换为map
            Map<String, String> resultMap = WXPayUtil.xmlToMap(result);
            boolean isSuccess = false;
            if (WXPayConstants.SUCCESS.equalsIgnoreCase(resultMap.get("return_code"))) {

                log.info("wxnotify:微信支付----返回成功");

                if (WXPayUtil.isSignatureValid(resultMap, wxConfig.getKey())) {

                    log.info("wxnotify:微信支付----验证签名成功");
                    //为了方便测试,这里就不进行订单金额的校验

                    // 订单处理 操作 ordercontroller 的回写操作
                    order = orderMapper.getOrderById(resultMap.get("out_trade_no"));//从数据库中查出该订单
                    Double actualAmount = order.getActualAmount();//数据库中该订单的实际支付金额
                    //校验返回的订单金额是否与商户侧的订单金额一致
                    int merchantSideAmount=(int)(actualAmount*100);
                    int returnAmount=Integer.valueOf(resultMap.get("total_fee"));
                    if(merchantSideAmount!=returnAmount){
                        log.error("wxnotify:支付失败,错误信息：订单金额与商户侧的订单金额不一致" );
                        resMap.put("return_code","FAIL");
                        resMap.put("return_msg","订单金额与商户侧的订单金额不一致");
                        resXml = WXPayUtil.mapToXml(resMap);
                    }else{
                        // 通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
                        resMap.put("return_code","SUCCESS");
                        resMap.put("return_msg","OK");
                        resXml = WXPayUtil.mapToXml(resMap);
                        isSuccess=true;
                    }
                    resMap.put("return_code", "SUCCESS");
                    resMap.put("return_msg", "OK");
                    resXml = WXPayUtil.mapToXml(resMap);
                    isSuccess = true;

                } else {
                    log.error("wxnotify:微信支付----判断签名错误");
                    resMap.put("return_code", "FAIL");
                    resMap.put("return_msg", "验证签名错误");
                    resXml = WXPayUtil.mapToXml(resMap);
                }

            } else {
                log.error("wxnotify:支付失败,错误信息：" + resultMap.get("return_msg"));
                resMap.put("return_code", "FAIL");
                resMap.put("return_msg", resultMap.get("return_msg"));
                resXml = WXPayUtil.mapToXml(resMap);
            }

            // 付款记录修改 & 记录付款日志

            if (isSuccess == true) {
                // 回调方法，处理业务 - 修改订单状态
                log.info("wxnotify:微信支付回调：修改的订单===>" + resultMap.get("out_trade_no"));

                //因为微信官方可能会多次调用此回调接口,所以为了避免多次调用此接口导致重复修改订单状态,需要以下判断
                //如果订单状态为未支付,则修改订单状态
                if ("0".equals(order.getState())) {
                    order.setState("1");
                    Date date = new Date(System.currentTimeMillis());
                    String currentTimeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
                    log.info("订单支付时间为" +currentTimeStr);
                    order.setPayTime(currentTimeStr);//设置订单支付时间
                    int updateResult = orderMapper.updateOrderById(order);//更新订单状态
                    //判断是否更新成功
                    if (updateResult > 0) {
                        log.info("wxnotify:微信支付回调：修改订单支付状态成功");
                    } else {
                        log.info("wxnotify:微信支付回调：修改订单支付状态失败");
                    }
//                    //删除购物车中相应规格的商品
//                    List<Orderitem> orderitems = orderService.queryOrderItemsByOrderId(order.getId());
//                    Integer[] specificationIds = new Integer[orderitems.size()];
//                    for (int i = 0; i < orderitems.size(); i++) {
//                        specificationIds[i]=orderitems.get(i).getSpecificationId();
//                    }
//                    HashMap<String, Integer[]> map = new HashMap<String, Integer[]>();
//                    map.put("specificationIds",specificationIds);
//                    UrlUtils.sendPost("localhost/cart/deleteCartItemsBySpecificationId",map);
                }

            }

        } catch (Exception e) {
            log.error("wxnotify:支付回调发布异常：" + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                // 处理业务完毕
                BufferedOutputStream out = new BufferedOutputStream(res.getOutputStream());
                out.write(resXml.getBytes());
                out.flush();
                out.close();
            } catch (IOException e) {
                System.out.println("wxnotify:支付回调发布异常: " + e.getMessage());
                e.printStackTrace();
            }
        }

    }

    //无证书的请求
    public String requestWithoutCert(String strUrl, Map<String, String> reqData,
                                     int connectTimeoutMs, int readTimeoutMs) throws Exception {
        String UTF8 = "UTF-8";
        String reqBody = WXPayUtil.mapToXml(reqData);
        URL httpUrl = new URL(strUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection) httpUrl.openConnection();
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setConnectTimeout(connectTimeoutMs);
        httpURLConnection.setReadTimeout(readTimeoutMs);
        httpURLConnection.connect();
        OutputStream outputStream = httpURLConnection.getOutputStream();
        outputStream.write(reqBody.getBytes(UTF8));

        // if (httpURLConnection.getResponseCode()!= 200) {
        //     throw new Exception(String.format("HTTP response code is %d, not 200", httpURLConnection.getResponseCode()));
        // }

        //获取内容
        InputStream inputStream = httpURLConnection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, UTF8));
        final StringBuffer stringBuffer = new StringBuffer();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuffer.append(line);
        }
        String resp = stringBuffer.toString();
        if (stringBuffer != null) {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // if (httpURLConnection!=null) {
        //     httpURLConnection.disconnect();
        // }

        return resp;
    }
}
