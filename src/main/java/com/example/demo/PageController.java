package com.example.demo;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wwd on 2017/7/20.
 */
@Controller
public class PageController {
    Logger logger=Logger.getLogger(PageController.class);
    @RequestMapping("/chatPage")
    public String chat(){
        logger.warn("[entered /chat..........]");
        return "chat";
    }

    @RequestMapping("/loginPage")
    public String login(){
        return "logins";
    }
}
