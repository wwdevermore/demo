package com.example.demo;

import Util.*;
import model.Agent;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.GeneralSecurityException;
import java.util.regex.Pattern;

/**
 * Created by wangw on 2017/7/24.
 */

@Controller
public class LoginController {

        private String body;
        private String key;
        private String inputStr;

        @RequestMapping(value = "/login",method = RequestMethod.POST)
        public String post(@RequestParam("username")  int userId, @RequestParam("password") String password){
            String responseStr;
            HttpHeaders headers=new HttpHeaders();
            headers.add("Content-Type","text/xml; charset=utf-8");
            headers.add("SOAPAction","http://tempuri.org/LoginV2");
            inputStr= Constant.systemCode+"&"+ Constant.systemKey+"&"+userId+"&"+password;
            key= DesUtils.ehaiEncode(inputStr);
            body="<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                    "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                    "<soap:Body>\n" +
                    "<LoginV2 xmlns=\"http://tempuri.org/\">\n" +
                    "<key>"+key+"</key>\n" +
                    "<ip>192.168.5.151</ip><verifyIp>false</verifyIp></LoginV2></soap:Body></soap:Envelope>";
            responseStr= HttpUtils.post(Constant.loginUrl,headers.toSingleValueMap(),body);
            //正则验证
            String pattern=".*<Success>([true])+</Success>.*";
            boolean isSuccess= Pattern.matches(pattern,responseStr);
            if(isSuccess) {
                String encryptedId;
                try{
                    byte[] bytes=DesUtils.ehaiEncode(userId);
                    encryptedId= Base64Util.encryptBASE64(bytes);
                }catch (GeneralSecurityException ge){
                    //todo addjson response
                    return "login";
                }
                //WebSocketSession socketSession=new WebSocketClientSockJsSession();
                String session= UuidUtils.getRandomUUID();
                Agent agent=new Agent();
                agent.setServiceId(userId);
                agent.setUuid(session);

                //todo add into databases

                return "chat";
            }else {
                //todo addjson
                return "login";
            }

        }


}
