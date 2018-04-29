package com.ecar.ecarnetfream.publics.util;


import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * 3DES加密工具类
 */
public class EncryptionUtil {
	// 密钥
	private final static String secretKey = getKey();
	// 向量
	private final static String iv = "01234567";
	// 加解密统一使用的编码方式
	private final static String encoding = "utf-8";

	/**
	 * 3DES加密
	 * 
	 * @param plainText
	 *            普通文本
	 * @return
	 * @throws Exception
	 */
	public static String encode(String plainText) throws Exception {
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);

		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
		cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
		byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));
		return Base64.encode(encryptData);
	}

	/**
	 * 3DES解密
	 * 
	 * @param encryptText
	 *            加密文本
	 * @return
	 * @throws Exception
	 */
	public static String decode(String encryptText) throws Exception {
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);
		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
		cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
		byte[] decryptData = cipher.doFinal(Base64.decode(encryptText));
		return new String(decryptData, encoding);
	}

	private static String getKey() {
		String secretKey = binaryStrToStr(Constants.SIGNKEY);
		while (secretKey.length() < 24) {
			secretKey = secretKey.concat(binaryStrToStr(Constants.SIGNKEY));
		}
		return secretKey.substring(0, 24);
	}

	// 将字符串转换成二进制字符串，以空格相隔
	public static String strToBinarystr(String str) {
		char[] strChar = str.toCharArray();
		StringBuilder result = new StringBuilder();
		for (int i = 0, len = strChar.length; i < len; i++) {
			result.append(Integer.toBinaryString(strChar[i]) + " ");
		}
		return result.toString();
	}

	// 将二进制字符串转换成Unicode字符串
	public static String binaryStrToStr(String binStr) {
		String[] tempStr = StringsUtil.strToStrArray(binStr);
		char[] tempChar = new char[tempStr.length];
		for (int i = 0; i < tempStr.length; i++) {
			tempChar[i] = StringsUtil.binaryStrToChar(tempStr[i]);
		}
		return String.valueOf(tempChar);
	}
}
