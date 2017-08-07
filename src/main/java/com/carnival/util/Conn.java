
package com.carnival.util;

import java.security.MessageDigest;
import java.util.UUID;

/**
 * ********************************************************
 * @ClassName: Conn
 * @Description: 
 * @author DoDo
 * @date 2014-9-21 上午11:51:07
 *******************************************************
 */
public final class Conn {
	
	/**
	 * ********************************************************
	 * @Title: getUUID
	 * @Description: 获取长度为 16 位的UUID
	 * @return String
	 * @date 2014-9-21 上午11:52:36
	 ********************************************************
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-","");
	}
	
	/**
	 * ********************************************************
	 * @Title: encrypt
	 * @Description: 32位MD5加密
	 * @param s
	 * @return String
	 * @date 2014-9-21 上午11:44:06
	 ********************************************************
	 */
	public final static String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

	