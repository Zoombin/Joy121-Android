package gejw.android.quickandroid.encryption;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.Cipher;

public class QRsa {

	public static class RsaKey {
		public RSAPrivateKey privateKey;
		public RSAPublicKey publicKey;
	}

	public static RsaKey getRSAPublicKey(String key) {
		try {
			RsaKey rsaKey = new RsaKey();
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(key);
			keyPairGen.initialize(1024);
			KeyPair keyPair = keyPairGen.generateKeyPair();
			// Generate keys
			rsaKey.privateKey = (RSAPrivateKey) keyPair.getPrivate();
			rsaKey.publicKey = (RSAPublicKey) keyPair.getPublic();
			return rsaKey;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 加密
	 * 
	 * @param publicKey
	 * @param obj
	 * @return
	 */
	public static byte[] Encrypt(RSAPublicKey publicKey, byte[] obj) {
		if (publicKey != null) {
			try {
				Cipher cipher = Cipher.getInstance("RSA");
				cipher.init(Cipher.ENCRYPT_MODE, publicKey);
				return cipher.doFinal(obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 加密
	 * 
	 * @param publicKey
	 * @param obj
	 * @return
	 */
	public static String Encrypt(RSAPublicKey publicKey, String obj) {
		return new String(Encrypt(publicKey, obj));
	}

	/**
	 * 解密
	 * 
	 * @param privateKey
	 * @param obj
	 * @return
	 */
	public static byte[] Decrypt(RSAPrivateKey privateKey, byte[] obj) {
		if (privateKey != null) {
			try {
				Cipher cipher = Cipher.getInstance("RSA");

				cipher.init(Cipher.DECRYPT_MODE, privateKey);
				return cipher.doFinal(obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	/**
	 * 解密
	 * 
	 * @param privateKey
	 * @param obj
	 * @return
	 */
	public static String Decrypt(RSAPrivateKey privateKey, String obj) {
		byte[] b = Decrypt(privateKey, obj.getBytes());
		return bytesToString(b);
	}

	private static String bytesToString(byte[] encrytpByte) {
		String result = "";
		for (Byte bytes : encrytpByte) {
			result += (char) bytes.intValue();
		}
		return result;
	}
}
