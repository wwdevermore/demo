package com.example.demo;

import com.alibaba.fastjson.JSON;
import model.ServiceMessage;
import org.apache.log4j.Logger;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangw on 2017/7/21.
 */
@Controller
public class RoomController {
    Logger log=Logger.getLogger(RoomController.class);
    //处理客户端STOMP消息,并处理指定目的地的消息
    @MessageMapping("/hello")
    @SendTo("/topic/users")
    public String messagesToService(ServiceMessage serviceMessage) throws Exception{
        log.info("receive messages ......");
        log.info("messages:  "+ serviceMessage.getMessage());
        Map map= new HashMap<String,String>();
        map.put("content",serviceMessage.getContent());
        return JSON.toJSONString(map);
    }
}
