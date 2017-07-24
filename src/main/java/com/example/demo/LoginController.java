package com.example.demo;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;

/**
 * Created by wangw on 2017/7/24.
 */
@Controller
@Log4j
public class LoginController {


//    @RequestMapping(value = "/login",method= RequestMethod.POST)
//    public String post(@RequestParam("user")  int userId, @RequestParam("pass") String password, HttpServletResponse){
//        boolean loginSuccess;
//        String name="";
//        JSONObject result= PersonnelLogin.login(userId,password);
//        loginSuccess=(boolean)result.get("IsSuccess");
//        if (loginSuccess) {
//            JSONObject data = (JSONObject) result.get("Data");
//            name = (String) data.get("Name");
//        }
//        Map<String, Object> response = new HashMap<>();
//        if(loginSuccess){
//            String encryptedId;
//            try{
//                byte[] bytes= DesUtils.ehaiEncode(userId);
//                encryptedId= Base64Util.encryptBASE64(bytes);
//            }catch (GeneralSecurityException e){
//                log.error(e);
//                response.put("success",false);
//                return JSON.toJSONString(response);
//            }


}
