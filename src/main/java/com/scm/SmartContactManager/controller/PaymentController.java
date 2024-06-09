package com.scm.SmartContactManager.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scm.SmartContactManager.entities.Orders;
import com.scm.SmartContactManager.entities.User;
import com.scm.SmartContactManager.helper.GetLoggedInUserName;
import com.scm.SmartContactManager.service.IOrderService;
import com.scm.SmartContactManager.service.IUserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.Map;

import org.json.JSONObject;
import com.razorpay.*;

@Controller
@RequestMapping("/user/payment")
public class PaymentController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IOrderService orderService ;

    @GetMapping("/contribute")
    public String contribute(Map<String,Object> map,Authentication authentication)
    {
        String email = GetLoggedInUserName.getLoggedInUserEmail(authentication);
        User user = userService.getUserByEmail(email);
        map.put("user",user);
        return "user/contribute";
    }

    @PostMapping("/create_order")
    @ResponseBody
    public String createOrder(@RequestBody Map<String,Object> data,Authentication authentication) throws RazorpayException
    {
       String email = GetLoggedInUserName.getLoggedInUserEmail(authentication);
        User user = userService.getUserByEmail(email);
        System.out.println(user);
        System.out.println(data);

        int amount = Integer.parseInt(data.get("amount").toString());

        var client = new RazorpayClient("rzp_test_oyYGW2jJpcIAQn", "bZ2OYnCqmkJHM4f56f88LuuK");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("amount",amount*100);
        jsonObject.put("currency","INR");
        jsonObject.put("receipt","txn_279532");


        Order order = client.orders.create(jsonObject);
        System.out.println(order);

        Orders myOrder = new Orders();

        myOrder.setOrderId(order.get("id").toString());
        myOrder.setAmount(order.get("amount").toString());
        myOrder.setPaymentId(null);
        myOrder.setStatus("created");
        myOrder.setUser(user);
        myOrder.setReceipt(order.get("receipt"));

        orderService.saveOrder(myOrder);

        return order.toString();
    }

    @PostMapping("/update-order")
    public String updateOrder(@RequestBody Map<String,Object> data, Authentication authentication, Map<String,Object> map, HttpSession session)
    {
        String email = GetLoggedInUserName.getLoggedInUserEmail(authentication);
        User user = userService.getUserByEmail(email);
        System.out.println(user);
        System.out.println(data);

        Orders myOrder = orderService.getOrder(data.get("order_id").toString());
        myOrder.setPaymentId(data.get("payment_id").toString());
        myOrder.setStatus(data.get("statusx").toString());
        System.out.println(data);
        orderService.saveOrder(myOrder);
        System.out.println("order success");
        map.put("user",user);
        return "user/contribute";
    }
}