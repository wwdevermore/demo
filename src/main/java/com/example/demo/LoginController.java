package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by wangw on 2017/7/24.
 */
@Controller
public class LoginController {
    @RequestMapping(value = "/login",method= RequestMethod.POST)
    public String post(@RequestParam("user")  int userId, @RequestParam("pass") String password, HttpServletResponse){
        boolean loginSuccess;
        String name="";
        return "";
    }

}
