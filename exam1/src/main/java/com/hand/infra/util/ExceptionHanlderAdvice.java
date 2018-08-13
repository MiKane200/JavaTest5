package com.hand.infra.util;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHanlderAdvice {

    @ExceptionHandler(value = Exception.class)
    public ModelAndView exception(Exception exception, WebRequest request){
        ModelAndView modelAndView = new ModelAndView("error"); //一个叫error的错误页面
        modelAndView.addObject("errorMsg",exception.getMessage());//这个是放在错误页面上的信息 ${errorMessage}
        return modelAndView;
    }

    @ModelAttribute
    //使用这个属性将键值对添加到全局，所以注解了@Rquestmapping的方法都可以获得这个键值对
    public void addAttribute(Model model){
        model.addAttribute("msg","this is extra message");//这是需要添加的一些格外信息
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.setDisallowedFields("languageId"); //忽略request的languageId参数
    }
}
