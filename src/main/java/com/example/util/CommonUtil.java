package com.example.util;





import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.coyote.ProtocolException;
import org.apache.log4j.Logger;



import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.*;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;


/**
 * Created by hhy on 16-11-24.
 */
public class CommonUtil {

    public static JSONObject getxml(int orderid, double totalPrice, String url, String openid, String desc, Long time) throws Exception {

        //获取prepayid
        Map<String, String> map = new HashMap<String, String>();
//            WeiXinConfig wcf=weiXinBaseService.getWeiXinConfig();
        String nonceStr = UUID.randomUUID().toString().substring(0, 32);
        String ip = null;
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

//            oauthService.shareFactory(request);
        String appid = "wxe1b0f22089fadce1";
        long timestamp = time;
        map.put("appid", appid);
        map.put("mch_id", "1324493901");
        map.put("nonce_str", nonceStr);
        map.put("body", desc);
        map.put("out_trade_no", "hya" + orderid);
        map.put("total_fee", "1");
        map.put("spbill_create_ip", ip);
        map.put("notify_url", url);
        map.put("trade_type", "JSAPI");
        map.put("openid", openid);
        String paySign = null;
        String secret = "QMNERAJTMSGIZZIBBHYQEJISWCSRSGED";
        try {
            paySign = getPayCustomSign(map, secret);
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("sign", paySign);
        String xml = CommonUtil.ArrayToXml(map);
        String s = CommonUtil.httpsPost("https://api.mch.weixin.qq.com/pay/unifiedorder", xml);
        JSONObject result = CommonUtil.xml2JSON(s);

        JSONObject json = new JSONObject();
        if (result.get("result_code").equals("SUCCESS")) {
            //封装h5页面调用参数
            Map<String, String> signMap = new HashMap<String, String>();
            String prepayid = result.getString("prepay_id");
            signMap.put("appId", appid);
            signMap.put("timeStamp", timestamp + "");
            signMap.put("package", "prepay_id=" + prepayid);
            signMap.put("signType", "MD5");
            signMap.put("nonceStr", nonceStr);

            json.put("appId", appid);
            json.put("timeStamp", timestamp);
            json.put("package", "prepay_id=" + prepayid);
            json.put("nonceStr", nonceStr);
            json.put("signType", "MD5");
            String paySign2 = getPayCustomSign(signMap, secret);
            json.put("paySign", paySign2);
            json.put("ok", true);
        } else {
            json.put("error", result.getString("err_code_des"));
        }
        return json;
    }

    @SuppressWarnings("unchecked")
    public static JSONObject xml2JSON(String xml) {
        JSONObject obj = new JSONObject();
        try {
            InputStream is = new ByteArrayInputStream(xml.getBytes("utf-8"));
            SAXBuilder sb = new SAXBuilder();
            Document doc = sb.build(is);
            Element root = doc.getRootElement();
            List jiedian = root.getChildren();
            Element et = null;
            for (int i = 0; i < jiedian.size(); i++) {
                et = (Element) jiedian.get(i);
                obj.put(et.getName(), et.getValue());
            }
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 一个迭代方法
     *
     * @param element
     * : org.jdom.Element
     * @return java.util.Map 实例
     */
    @SuppressWarnings("unchecked")

    private static Logger log = Logger.getLogger(CommonUtil.class);

    public static JSONObject httpsRequestToJsonObject(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        try {
            StringBuffer buffer = httpsRequest(requestUrl, requestMethod);
            jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (ConnectException ce) {
            log.error("连接超时：" + ce.getMessage());
        } catch (Exception e) {
            log.error("https请求异常：" + e.getMessage());
        }
        return jsonObject;
    }

    public static String httpsPost(String requestUrl, String data) throws IOException {

        URL url = new URL(requestUrl);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setUseCaches(false);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        out.writeBytes(data); //写入请求的字符串
        out.flush();
        out.close();
        String result = null;
        if (connection.getResponseCode() == 200) {
            System.out.println("yes++");
            //请求返回的数据
            InputStream in = connection.getInputStream();

            try {
                byte[] data1 = new byte[in.available()];
                in.read(data1);
                //转成字符串
                result = new String(data1);

            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } else {
            result = "error";
        }


        return result;
    }

    private static StringBuffer httpsRequest(String requestUrl, String requestMethod)
            throws NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException, MalformedURLException,
            IOException, ProtocolException, UnsupportedEncodingException {
        URL url = new URL(requestUrl);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setUseCaches(false);
        connection.setRequestMethod(requestMethod);


//        connection.
        // 从输入流读取返回内容
        InputStream inputStream = connection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String str = null;
        StringBuffer buffer = new StringBuffer();
        while ((str = bufferedReader.readLine()) != null) {
            buffer.append(str);
        }
        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();
        inputStream = null;
        connection.disconnect();
        return buffer;
    }

    public static String getPayCustomSign(Map<String, String> bizObj, String key) throws Exception {

        String bizString = CommonUtil.FormatBizQueryParaMap(bizObj,
                false);
//        logger.info(bizString);
        return MD5SignUtil.sign(bizString, key);
    }

    public static String FormatBizQueryParaMap(Map<String, String> paraMap,
                                               boolean urlencode) throws Exception {

        String buff = "";
        try {
            List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(
                    paraMap.entrySet());

            Collections.sort(infoIds,
                    new Comparator<Map.Entry<String, String>>() {
                        public int compare(Map.Entry<String, String> o1,
                                           Map.Entry<String, String> o2) {
                            return (o1.getKey()).toString().compareTo(
                                    o2.getKey());
                        }
                    });

            for (int i = 0; i < infoIds.size(); i++) {
                Map.Entry<String, String> item = infoIds.get(i);
                //System.out.println(item.getKey());
                if (item.getKey() != "") {

                    String key = item.getKey();
                    String val = item.getValue();
                    if (urlencode) {
                        val = URLEncoder.encode(val, "utf-8");

                    }
                    buff += key + "=" + val + "&";

                }
            }

            if (buff.isEmpty() == false) {
                buff = buff.substring(0, buff.length() - 1);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return buff;
    }

    public static String ArrayToXml(Map<String, String> arr) {
        String xml = "<xml>";

        Iterator<Map.Entry<String, String>> iter = arr.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, String> entry = iter.next();
            String key = entry.getKey();
            String val = entry.getValue();
            if (IsNumeric(val)) {
                xml += "<" + key + ">" + val + "</" + key + ">";

            } else
                xml += "<" + key + "><![CDATA[" + val + "]]></" + key + ">";
        }

        xml += "</xml>";
        return xml;
    }

    public static boolean IsNumeric(String str) {
        if (str.matches("\\d *")) {
            return true;
        } else {
            return false;
        }
    }
}