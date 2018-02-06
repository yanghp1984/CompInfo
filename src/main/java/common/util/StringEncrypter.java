package common.util;

import java.security.MessageDigest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * String 加解密帮助类
 * 
 * @author yanghp1984
 * @version 1.0.0 2017-12-18
 */
public class StringEncrypter {
	private static Logger logger = LogManager.getLogger(StringEncrypter.class);

	/** SHA-1 算法 */
	public static final String HASH_TYPE_SHA1 = "SHA-1";

	/** SHA-256 算法 */
	public static final String HASH_TYPE_SHA256 = "SHA-256";

	/** MD5 算法 */
	public static final String HASH_TYPE_MD5 = "MD5";

	/**
	 * 获取字符串的散列值
	 * 
	 * @param str
	 *            字符串
	 * @param hashType
	 *            散列算法，只支持 SHA-256, SHA-1, MD5
	 * @return 散列值
	 */
	public static String getHashValue(String str, String hashType) {
		String strResult = null;
		if (str != null && str.length() > 0) {
			if (hashType == null || hashType.isEmpty()) {
				hashType = HASH_TYPE_SHA256;
			}

			try {
				MessageDigest md = MessageDigest.getInstance(hashType);
				md.update(str.getBytes());
				strResult = byteToHex(md.digest());
			} catch (Exception e) {
				logger.error("getHashValue ERROR: " + e.getMessage());
			}
		}
		return strResult;
	}

	/**
	 * 获取字符串的 SHA-1 散列值
	 * 
	 * @param str
	 *            字符串
	 * @return SHA-1 散列值
	 */
	public static String getSHAValue(String str) {
		return getHashValue(str, HASH_TYPE_SHA1);
	}

	/**
	 * 获取字符串的 SHA-256 散列值
	 * 
	 * @param str
	 *            字符串
	 * @return SHA-256 散列值
	 */
	public static String getSHA256Value(String str) {
		return getHashValue(str, HASH_TYPE_SHA256);
	}

	/**
	 * 获取字符串的 MD5 散列值
	 * 
	 * @param str
	 *            字符串
	 * @return MD5 散列值
	 */
	public static String getMD5Value(String str) {
		return getHashValue(str, HASH_TYPE_MD5);
	}

	/**
	 * 将 byte[] 数组转换为 16 进制字符串
	 * 
	 * @param bytes
	 *            byte[] 数组
	 * @return 16 进制字符串
	 */
	public static String byteToHex(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			String hex = Integer.toHexString(0xff & b);
			if (hex.length() == 1) {
				sb.append('0');
			}
			sb.append(hex);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		String str = "admin";
		System.out.println(str + ":");
		System.out.println("SHA-1: " + getSHAValue(str));
		System.out.println("SHA-256: " + getSHA256Value(str));
		System.out.println("MD5: " + getMD5Value(str));
		
		str = "yh";
		System.out.println(str + ":");
		System.out.println("SHA-1: " + getSHAValue(str));
		System.out.println("SHA-256: " + getSHA256Value(str));
		System.out.println("MD5: " + getMD5Value(str));
	}
}
