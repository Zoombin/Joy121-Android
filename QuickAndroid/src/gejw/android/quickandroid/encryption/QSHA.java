package gejw.android.quickandroid.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class QSHA {
	/**
	 * SHA 加密 返回加密后的字符串
	 * 
	 * @param pwd
	 *            加密前的字符串
	 * @return 加密后的数字
	 */
	public static String EncryptSHA(String source) {
		if (source == null) {
			source = "";
		}
		String result = "";
		try {
			result = encrypt(source, "SHA");
		} catch (NoSuchAlgorithmException ex) {
			// this should never happen
			throw new RuntimeException(ex);
		}
		return result;
	}

	/**
	 * Encrypt string
	 */
	private final static String encrypt(String source, String algorithm)
			throws NoSuchAlgorithmException {
		byte[] resByteArray = encrypt(source.getBytes(), algorithm);
		return toHexString(resByteArray);
	}

	/**
	 * Encrypt byte array.
	 */
	private final static byte[] encrypt(byte[] source, String algorithm)
			throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance(algorithm);
		md.reset();
		md.update(source);
		return md.digest();
	}

	/**
	 * Get hex string from byte array
	 */
	private final static String toHexString(byte[] res) {
		StringBuffer sb = new StringBuffer(res.length << 1);
		for (int i = 0; i < res.length; i++) {
			String digit = Integer.toHexString(0xFF & res[i]);
			if (digit.length() == 1) {
				digit = '0' + digit;
			}
			sb.append(digit);
		}
		return sb.toString().toUpperCase();
	}
}
