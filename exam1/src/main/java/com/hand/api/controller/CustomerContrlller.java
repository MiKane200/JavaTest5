package com.hand.api.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hand.api.service.CustomerService;
import com.hand.domain.entity.Customer;
import com.hand.domain.entity.Film;
import com.hand.domain.entity.Page;
import org.apache.ibatis.annotations.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerContrlller {

    private static Map<String,String> cookieMap = new HashMap<>();

    @Autowired
    private CustomerService customerService;


    @PostMapping(value = "/login")
    public String login( @RequestBody Customer customer,HttpServletResponse response) {
        if(customerService.login(customer.getFirstName(),customer.getLastName())){
            Cookie cookie = new Cookie("loginStatus", "login");
            response.addCookie(cookie);
            return "login successful!";
        }else{
            Cookie cookie = new Cookie("loginStatus", "logout");
            response.addCookie(cookie);
        }
        return "login failed,wrong name or password!";
    }

    @GetMapping("/")
    public List<Film> list(Page page){
        List<Film> films = customerService.getFilms(page);
        return films;
    }

    @PutMapping(value = "/save")
    public String save(@RequestBody Customer customer,HttpServletRequest request,HttpServletResponse response) {
        String address = "47 MySakila Drive";
        //address = String.valueOf(request.getParameter("address"));
        String cId = String.valueOf(customerService.save(customer,address));
        if(customerService.save(customer,address) > 0){
            Cookie cookie = new Cookie("cId", cId);
            response.addCookie(cookie);
            cookieMap.put("cId",cId);
            return cId;
        }
        return "wrong address!";
    }

    @DeleteMapping(value = "/delete")
    public String delete(HttpServletRequest httpServletRequest){
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null || cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cId")) {
                    String cId = cookie.getValue();
                    return (customerService.delete(cId)) ? "delete successful" : "delete failed";
                }
            }
        }
        return "success";
    }

    @PostMapping(value = "/update")
    public Customer update(@RequestBody Customer customer) {
        String cId = cookieMap.get("cId");
        customer.setCustomerId(Short.valueOf(cId));
        customerService.update(customer);
        return customer;
    }


}
