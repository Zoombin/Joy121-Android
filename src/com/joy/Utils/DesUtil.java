package com.joy.Utils;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import com.baidu.android.bba.common.security.MD5Util;

import android.util.Base64;

public class DesUtil {

	private final static String DES = "DES";
	private final static String key = "Joy121sz";
	public final static String fujiakey = "wang!@#$%";

	public static void run() throws Exception {
		String data = "steven";
		System.err.println(encrypt(data + fujiakey));
		System.err.println(decrypt(encrypt(data + fujiakey)));

		
	}

	public static String encrypt(String message) throws Exception {
		Cipher cipher = Cipher.getInstance("DES");

		DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));

		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
		IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);

		return toHexString(cipher.doFinal(message.getBytes("UTF-8")));
	}

	public static String decrypt(String message) throws Exception {

		byte[] bytesrc = convertHexString(message);
		Cipher cipher = Cipher.getInstance("DES");
		DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
		IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));

		cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);

		byte[] retByte = cipher.doFinal(bytesrc);
		return new String(retByte);
	}

	public static byte[] convertHexString(String ss) {
		byte digest[] = new byte[ss.length() / 2];
		for (int i = 0; i < digest.length; i++) {
			String byteString = ss.substring(2 * i, 2 * i + 2);
			int byteValue = Integer.parseInt(byteString, 16);
			digest[i] = (byte) byteValue;
		}

		return digest;
	}

	public static String toHexString(byte b[]) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			String plainText = Integer.toHexString(0xff & b[i]);
			if (plainText.length() < 2)
				plainText = "0" + plainText;
			hexString.append(plainText);
		}

		return hexString.toString();
	}

//	/**
//	 * Description 根据键值进行加密
//	 * 
//	 * @param data
//	 * @param key
//	 *            加密键byte数组
//	 * @return
//	 * @throws Exception
//	 */
//	public static String encrypt(String data) throws Exception {
//		byte[] bt = encrypt(data.getBytes(), key.getBytes());
//		// String strs = new BASE64Encoder().encode(bt);
//		String strs = Base64.encodeToString(bt, Base64.DEFAULT);
//		strs = URLEncoder.encode(strs, "UTF-8");
//		return strs;
//	}
//
//	/**
//	 * Description 根据键值进行解密
//	 * 
//	 * @param data
//	 * @param key
//	 *            加密键byte数组
//	 * @return
//	 * @throws IOException
//	 * @throws Exception
//	 */
//	public static String decrypt(String data) throws IOException, Exception {
//		if (data == null)
//			return null;
//		// BASE64Decoder decoder = new BASE64Decoder();
//		// byte[] buf = decoder.decodeBuffer(data);
//		data = URLDecoder.decode(data, "UTF-8");
//		byte[] buf = Base64.decode(data, Base64.DEFAULT);
//		byte[] bt = decrypt(buf, key.getBytes());
//		return new String(bt);
//	}
//
//	/**
//	 * Description 根据键值进行加密
//	 * 
//	 * @param data
//	 * @param key
//	 *            加密键byte数组
//	 * @return
//	 * @throws Exception
//	 */
//	private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
//		// 生成一个可信任的随机数源
//		SecureRandom sr = new SecureRandom();
//
//		// 从原始密钥数据创建DESKeySpec对象
//		DESKeySpec dks = new DESKeySpec(key);
//
//		// 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
//		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
//		SecretKey securekey = keyFactory.generateSecret(dks);
//
//		// Cipher对象实际完成加密操作
//		Cipher cipher = Cipher.getInstance(DES);
//
//		// 用密钥初始化Cipher对象
//		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
//
//		return cipher.doFinal(data);
//	}
//
//	/**
//	 * Description 根据键值进行解密
//	 * 
//	 * @param data
//	 * @param key
//	 *            加密键byte数组
//	 * @return
//	 * @throws Exception
//	 */
//	private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
//		// 生成一个可信任的随机数源
//		SecureRandom sr = new SecureRandom();
//
//		// 从原始密钥数据创建DESKeySpec对象
//		DESKeySpec dks = new DESKeySpec(key);
//
//		// 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
//		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
//		SecretKey securekey = keyFactory.generateSecret(dks);
//
//		// Cipher对象实际完成解密操作
//		Cipher cipher = Cipher.getInstance(DES);
//
//		// 用密钥初始化Cipher对象
//		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
//
//		return cipher.doFinal(data);
//	}
}
