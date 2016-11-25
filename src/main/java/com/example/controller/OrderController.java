package com.example.controller;

import com.example.entity.Order;
import com.example.entity.OrderList;
import com.example.service.OrderService;
import com.example.util.CommonUtil;
import net.sf.json.JSONObject;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by hhy on 11/19/16.
 */
@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;


    @RequestMapping(method = RequestMethod.POST)
    public HttpEntity createOrder(@RequestBody OrderList orderList) {
//      public HttpEntity createOrder(@RequestBody Map<String,Object> orderMap) {
//
//        Order order= (Order) orderMap.get("order");
//        List<OrderDetail> orderDetailList= (List<OrderDetail>) orderMap.get("orderDetailList");
        int id = orderService.createOrder(orderList);
       // JSONObject result=        orderService.prePaidOrder(id);
//    public static String getxml(int orderid,double totalPrice,String url,String openid) {

        JSONObject result=orderService.prePaidOrder(id);
        if (result.getString("ok")!=null) {
            result.remove("ok");
            return new ResponseEntity(result, HttpStatus.OK);
        }else
        {
            return new ResponseEntity(result, HttpStatus.EXPECTATION_FAILED);
        }

    }
    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity getOrder(@RequestParam(value="id",required = true) int id) {
//        @RequestParam(value = "name", required = true) String name,
//        @RequestParam(value = "pwd", required = true) String pwd)
//      public HttpEntity createOrder(@RequestBody Map<String,Object> orderMap) {
//
//        Order order= (Order) orderMap.get("order");
//        List<OrderDetail> orderDetailList= (List<OrderDetail>) orderMap.get("orderDetailList");

        Order order=orderService.findById(id);
        return new ResponseEntity(order,HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.POST,value = "/pay/{id}")
    public HttpEntity payOrder(@PathVariable("id")  int id) {
//        @RequestParam(value = "name", required = true) String name,
//        @RequestParam(value = "pwd", required = true) String pwd)
//      public HttpEntity createOrder(@RequestBody Map<String,Object> orderMap) {
//
//        Order order= (Order) orderMap.get("order");
//        List<OrderDetail> orderDetailList= (List<OrderDetail>) orderMap.get("orderDetailList");

       // JSONObject result=orderService.prePaidOrder(id);
       // String success=result.getString("result_code");
        JSONObject result=orderService.prePaidOrder(id);
        return new ResponseEntity("result",HttpStatus.CREATED);
    }



    @RequestMapping(value="/pay/wexinnotify/test.do")

    public String weixinNotify(HttpServletRequest request, HttpServletResponse response){
        System.out.println("fdasfasdfasdfsad");
        String out_trade_no=null;
        String return_code =null;
//
//        try {
//            InputStream inStream = request
//                    .getInputStream();
//            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
//            byte[] buffer = new byte[1024];
//            int len = 0;
//            while ((len = inStream.read(buffer)) != -1) {
//                outSteam.write(buffer, 0, len);
//            }
//            outSteam.close();
//            inStream.close();
//            String resultStr  = new String(outSteam.toByteArray(),"utf-8");
//
//            JSONObject resultMap = CommonUtil.xml2JSON(resultStr);
//            String result_code = (String) resultMap.get("result_code");
//            String is_subscribe = (String) resultMap.get("is_subscribe");
//            String transaction_id = (String) resultMap.get("transaction_id");
//            String sign = (String) resultMap.get("sign");
//            String time_end = (String) resultMap.get("time_end");
//            String bank_type = (String) resultMap.get("bank_type");
//
//            out_trade_no = (String) resultMap.get("out_trade_no");
//            return_code = (String) resultMap.get("return_code");
//
//            request.setAttribute("out_trade_no", out_trade_no);
//            //通知微信.异步确认成功.必写.不然微信会一直通知后台.八次之后就认为交易失败了.
//            System.err.println("aaaaaaaaaaa:"+out_trade_no);
//            response.getWriter().write("<xml>\n" +
//                    "  <return_code><![CDATA[SUCCESS]]></return_code>\n" +
//                    "  <return_msg><![CDATA[OK]]></return_msg>\n" +
//                    "</xml>");
//        }  catch (Exception e) {
//
//            try {
//                response.getWriter().write("<xml>\n" +
//                        "  <return_code><![CDATA[FAIL]]></return_code>\n" +
//                        "  <return_msg><![CDATA[ERROR]]></return_msg>\n" +
//                        "</xml>");
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }
//        }
//        if(return_code.equals("SUCCESS")){
//            String id=out_trade_no.substring(3);
//            orderService.prePaidOrder(Integer.parseInt(id));
//        }else{
//            //支付失败的业务逻辑
//        }
        return "SUCCESS";
    }
}
