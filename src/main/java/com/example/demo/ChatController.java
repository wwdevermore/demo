package com.example.demo;

import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wwd on 2017/7/20.
 */
@Controller
public class ChatController {
    @RequestMapping("/chat")
    public String chat(){
        return "chat";
    }
    @RequestMapping("/hello")
    @SendTo("/topic/app")
    public String app()throws Exception{
        Thread.sleep(1000);
        return "fuck fuck fuck";

    }
}
