package Util;

import org.apache.log4j.Logger;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.Key;

public class DesUtils {
    
    private static Logger logger = Logger.getLogger(DesUtils.class);

    private static String ALGORITHM_DES = "DES/CBC/PKCS5Padding";
    
    public static String ehaiEncode(String originText) {
        byte[] data;
        try {
            data = originText.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error(e);
            return null;
        }
        byte[] encryptedData;
        try {
            encryptedData = encode(Constant.DES_KEY, data);
        } catch (GeneralSecurityException e) {
            logger.error(e);
            return null;
        }
        return Base64Util.encryptBASE64(encryptedData);
    }

    public static byte[] ehaiEncode(byte[] data) throws GeneralSecurityException {
        return encode(Constant.DES_KEY, data);
    }

    public static byte[] ehaiEncode(int data) throws GeneralSecurityException {
        return encode(Constant.DES_KEY, String.valueOf(data).getBytes());
    }

    public static byte[] ehaiDecode(byte[] data) throws GeneralSecurityException {
        return decode(Constant.DES_KEY, data);
    }
    
    public static String ehaiDecode(String encryptText) {
        String replacedData;
        if (encryptText != null) {
            replacedData = encryptText.replace("*", "=").replace("$", "+");
        } else {
            return null;
        }
        byte[] encryptData;
        try {
            encryptData = decode(Constant.DES_KEY, Base64Util.decryptBASE64(replacedData));
        } catch (GeneralSecurityException e) {
            logger.error(e);
            return null;
        }
        try {
            return new String(encryptData, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error(e);
            return null;
        }
    }

    private static byte[] encode(String key, byte[] data) throws GeneralSecurityException {
        DESKeySpec dks = new DESKeySpec(key.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        Key secretKey = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
        IvParameterSpec iv = new IvParameterSpec(Constant.DES_IV.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        return cipher.doFinal(data);
    }

    private static byte[] decode(String key, byte[] data) throws GeneralSecurityException {
        DESKeySpec dks = new DESKeySpec(key.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        Key secretKey = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
        IvParameterSpec iv = new IvParameterSpec(Constant.DES_IV.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        return cipher.doFinal(data);
    }

}
