package Util;

import java.util.UUID;

public class UuidUtils {

    /**
     * 生成随机 UUID
     * @return 随机不含横线的 UUID 字符串
     * */
    public static String getRandomUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(0, 8)
                + uuid.substring(9, 13)
                + uuid.substring(14, 18)
                + uuid.substring(19, 23)
                + uuid.substring(24);
    }

}
