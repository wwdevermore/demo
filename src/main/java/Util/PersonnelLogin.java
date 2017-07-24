package Util;

import com.alibaba.fastjson.JSONObject;


public class PersonnelLogin {

    public static JSONObject login(int id, String password) {
        JSONObject param = new JSONObject();
        param.put("UserNo", String.valueOf(id));
        param.put("Password", password);
        String responseString = HttpUtils.post(Constant.LOGIN_URL, null, param.toJSONString());
        if (responseString == null) {
            JSONObject result = new JSONObject();
            result.put("IsSuccess", false);
            return result;
        } else {
            return JSONObject.parseObject(responseString);
        }
    }

}
