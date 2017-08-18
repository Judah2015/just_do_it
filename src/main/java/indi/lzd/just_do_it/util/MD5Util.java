package indi.lzd.just_do_it.util;

import java.security.MessageDigest;

public class MD5Util {

    public static String getMD5(String password) {
        String result = "";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");

            //将password改成byte数组，一个数字或字母一个byte？
            byte[] pswByte = password.getBytes();

            byte[] resultByte = md5.digest(pswByte);

            result = byteToHexString(resultByte);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String byteToHexString(byte[] resultByte) {
        StringBuffer result = new StringBuffer();
        int digital;
        for (int i = 0; i < resultByte.length; i++) {
            //digital是10进制，一个byte(8位)的10进制范围为0-255
            digital = resultByte[i];
            //怎么会小于0呢
            if (digital < 0) {
                digital += 256;
            }
            //???
            if (digital < 16) {
                result.append("0");
            }
            result.append(Integer.toHexString(digital));
        }
        return result.toString();
    }
}
