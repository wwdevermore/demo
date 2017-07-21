package com.example.demo;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wwd on 2017/7/20.
 */
@Controller
public class ChatController {
    Logger logger=Logger.getLogger(ChatController.class);
    @RequestMapping("/chat")
    public String chat(){
        logger.warn("[entered /chat..........]");
        return "chat";
    }
}
