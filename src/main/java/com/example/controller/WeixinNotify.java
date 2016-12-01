package com.example.controller;

import com.example.service.OrderService;
import com.example.util.CommonUtil;
import net.sf.json.JSONObject;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by hhy on 16-12-1.
 */
//@RequestMapping(value = "/orders/pay/wexinnotify/test.do")
@WebServlet(urlPatterns="/orders/pay/wexinnotify/test.do", description="Servlet的说明")
public class WeixinNotify extends HttpServlet{

    @Autowired
    private OrderService orderService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.err.println("****************weixin Notify**************************");
        String out_trade_no=null;
        String return_code =null;
        try {
            InputStream inStream = req
                    .getInputStream();
            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
            outSteam.close();
            inStream.close();
            String resultStr  = new String(outSteam.toByteArray(),"utf-8");

            JSONObject resultMap = CommonUtil.xml2JSON(resultStr);
            System.err.println(resultMap);
            String result_code = (String) resultMap.get("result_code");
            String is_subscribe = (String) resultMap.get("is_subscribe");
            String transaction_id = (String) resultMap.get("transaction_id");
            String sign = (String) resultMap.get("sign");
            String time_end = (String) resultMap.get("time_end");
            String bank_type = (String) resultMap.get("bank_type");

            out_trade_no = (String) resultMap.get("out_trade_no");
            return_code = (String) resultMap.get("return_code");

            req.setAttribute("out_trade_no", out_trade_no);
            //通知微信.异步确认成功.必写.不然微信会一直通知后台.八次之后就认为交易失败了.

            String result="<xml>\n" +
                    "  <return_code><![CDATA["+result_code+"]]></return_code>\n" +
                    "  <return_msg><![CDATA["+result_code+"]]></return_msg>\n" +
                    "</xml>";
            if (result_code.equals("SUCCESS")){
                String s=out_trade_no.substring(3);
                System.out.println(s);
                orderService.paid(Integer.parseInt(s));
            }


            OutputStream outputStream = resp.getOutputStream();//获取OutputStream输出流
                  resp.setHeader("content-type", "text/html;charset=UTF-8");//通过设置响应头控制浏览器以UTF-8的编码显示数据，如果不加这句话，那么浏览器显示的将是乱码

                    byte[] dataByteArr = result.getBytes("UTF-8");//将字符转换成字节数组，指定以UTF-8编码进行转换
                    outputStream.write(dataByteArr);//使用OutputStream流向客户端输出字节数组

        }  catch (Exception e) {

            e.printStackTrace();
        }
//
//        if(return_code.equals("SUCCESS")){
//            return   "<xml>\n" +
//                    "  <return_code><![CDATA[SUCCESS]]></return_code>\n" +
//                    "  <return_msg><![CDATA[OK]]></return_msg>\n" +
//                    "</xml>";
//        }else{
//            //支付失败的业务逻辑
//            return "<xml>\n" +
//                    "  <return_code><![CDATA[FAIL]]></return_code>\n" +
//                    "  <return_msg><![CDATA[FAIL]]></return_msg>\n" +
//                    "</xml>";
//        }
    }
}


//    @RequestMapping(value="")
//
//    public String weixinNotify(HttpServletRequest request, HttpServletResponse response){
//
//
//    }