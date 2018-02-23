package common.util;

import java.security.Key;
import java.security.MessageDigest;
import java.security.SecureRandom;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * String 加解密帮助类
 *
 * @author yanghp1984
 * @version 1.0.0 2017-12-18
 */
public class StringEncrypter {
    private static Logger logger = LogManager.getLogger(StringEncrypter.class);

    /**
     * SHA-1 算法
     */
    public static final String HASH_TYPE_SHA1 = "SHA-1";

    /**
     * SHA-256 算法
     */
    public static final String HASH_TYPE_SHA256 = "SHA-256";

    /**
     * MD5 算法
     */
    public static final String HASH_TYPE_MD5 = "MD5";

    /**
     * DES 算法
     */
    public static final String ENCRYPT_TYPE_DES = "DES";

    /**
     * AES 算法
     */
    public static final String ENCRYPT_TYPE_AES = "AES";

    /**
     * 对称加密密钥
     */
    private static final String ENCRYPT_KEY_DEFAULT = "myskey0123456789";

    /**
     * CBC模式的向量，用于增加ASE加息算法的强度
     */
    private static final String AES_IV_PARAMETER = "1234560123456789";

    /**
     * 获取字符串的散列值
     *
     * @param str      字符串
     * @param hashType 散列算法，只支持 SHA-256, SHA-1, MD5
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
     * @param str 字符串
     * @return SHA-1 散列值
     */
    public static String getSHAValue(String str) {
        return getHashValue(str, HASH_TYPE_SHA1);
    }

    /**
     * 获取字符串的 SHA-256 散列值
     *
     * @param str 字符串
     * @return SHA-256 散列值
     */
    public static String getSHA256Value(String str) {
        return getHashValue(str, HASH_TYPE_SHA256);
    }

    /**
     * 获取字符串的 MD5 散列值
     *
     * @param str 字符串
     * @return MD5 散列值
     */
    public static String getMD5Value(String str) {
        return getHashValue(str, HASH_TYPE_MD5);
    }

    /**
     * 获取字符串的加密值（密文采取Base64编码）
     *
     * @param source      字符串明文
     * @param skey        加密密钥
     * @param encryptType 加密算法
     * @return 字符串密文
     */
    public static String getEncryptValue(String source, String skey, String encryptType) {
        String encryptedStr = null;
        if (StringUtils.isNotEmpty(source)) {
            try {
                Cipher cipher = getCipher(skey.getBytes(), true, encryptType);
                byte[] encryptBytes = cipher.doFinal(source.getBytes("UTF-8"));
                BASE64Encoder base64Encoder = new BASE64Encoder();
                encryptedStr = base64Encoder.encode(encryptBytes);
            } catch (Exception e) {
                logger.error("getEncryptedValue ERROR:" + e.getMessage());
            }
        }
        return encryptedStr;
    }

    /**
     * 获取字符串的加密值（默认加密密钥，AES加密算法）。
     *
     * @param source 字符串明文
     * @return 字符串密文
     */
    public static String getEncryptValue(String source) {
        return getEncryptValue(source, ENCRYPT_KEY_DEFAULT, ENCRYPT_TYPE_AES);
    }

    /**
     * 获取字符串的解密值（密文采取Base64编码）
     *
     * @param source      字符串密文
     * @param skey        加密密钥
     * @param encryptType 加密算法
     * @return 字符串明文
     */
    public static String getDecryptValue(String source, String skey, String encryptType) {
        String decryptedStr = null;
        if (StringUtils.isNotEmpty(source)) {
            try {
                BASE64Decoder base64Decoder = new BASE64Decoder();
                Cipher cipher = getCipher(skey.getBytes(), false, encryptType);
                byte[] decryptBytes = cipher.doFinal(base64Decoder.decodeBuffer(source));
                decryptedStr = new String(decryptBytes, "UTF-8");
            } catch (Exception e) {
                logger.error("getEncryptedValue ERROR:" + e.getMessage());
            }
        }
        return decryptedStr;
    }

    /**
     * 获取字符串的解密值（默认加密密钥，AES加密算法）。
     *
     * @param source 字符串密文
     * @return 字符串明文
     */
    public static String getDecryptValue(String source) {
        return getDecryptValue(source, ENCRYPT_KEY_DEFAULT, ENCRYPT_TYPE_AES);
    }

    /**
     * 根据密钥和加密算法，获取加密设备。当前只支持AES和DES算法，默认采用AES算法。
     *
     * @param keyBytes    加密密钥
     * @param isEncrypt   True表示加密，False表示解密
     * @param encryptType 加密算法
     * @return 加密设备
     */
    private static Cipher getCipher(byte[] keyBytes, boolean isEncrypt, String encryptType) throws Exception {
        Cipher cipher = null;
        if (StringUtils.equals(encryptType, ENCRYPT_TYPE_DES)) {
            // DES加密算法
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            keyGenerator.init(new SecureRandom(keyBytes));
            Key key = keyGenerator.generateKey();
            cipher = Cipher.getInstance("DES");
            if (isEncrypt) {
                cipher.init(Cipher.ENCRYPT_MODE, key);
            } else {
                cipher.init(Cipher.DECRYPT_MODE, key);
            }
        } else {
            // AES加密算法
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(AES_IV_PARAMETER.getBytes());
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            if (isEncrypt) {
                cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParameterSpec);
            } else {
                cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParameterSpec);
            }
        }
        return cipher;
    }

    /**
     * 获取字符串的DES加密值
     *
     * @param source 字符串明文
     * @param skey   加密密钥
     * @return 字符串密文
     */
    public static String getDesEncryptValue(String source, String skey) {
        return getEncryptValue(source, skey, ENCRYPT_TYPE_DES);
    }

    /**
     * 获取字符串的DES加密值（使用默认加密密码）
     *
     * @param source 字符串明文
     * @return 字符串密文
     */
    public static String getDesEncryptValue(String source) {
        return getEncryptValue(source, ENCRYPT_KEY_DEFAULT, ENCRYPT_TYPE_DES);
    }

    /**
     * 获取字符串的DES解密值
     *
     * @param source 字符串密文
     * @param skey   加密密钥
     * @return 字符串明文
     */
    public static String getDesDecryptValue(String source, String skey) {
        return getDecryptValue(source, skey, ENCRYPT_TYPE_DES);
    }

    /**
     * 获取字符串的DES解密值
     *
     * @param source 字符串密文
     * @return 字符串明文
     */
    public static String getDesDecryptValue(String source) {
        return getDecryptValue(source, ENCRYPT_KEY_DEFAULT, ENCRYPT_TYPE_DES);
    }

    /**
     * 获取字符串的AES加密值，AES—128-CBC加密算法。
     *
     * @param source 字符串明文
     * @param skey   加密密钥
     * @return 字符串密文
     */
    public static String getAesEncryptValue(String source, String skey) {
        return getEncryptValue(source, skey, ENCRYPT_TYPE_AES);
    }

    /**
     * 获取字符串的AES加密值，AES—128-CBC加密算法。
     *
     * @param source 字符串明文
     * @return 字符串密文
     */
    public static String getAesEncryptValue(String source) {
        return getEncryptValue(source, ENCRYPT_KEY_DEFAULT, ENCRYPT_TYPE_AES);
    }

    /**
     * 获取字符串的AES加密值，AES—128-CBC加密算法。
     *
     * @param source 字符串密文
     * @param skey   加密密钥
     * @return 字符串明文
     */
    public static String getAesDecryptValue(String source, String skey) {
        return getDecryptValue(source, skey, ENCRYPT_TYPE_AES);
    }

    /**
     * 获取字符串的AES加密值，AES—128-CBC加密算法。
     *
     * @param source 字符串密文
     * @return 字符串明文
     */
    public static String getAesDecryptValue(String source) {
        return getDecryptValue(source, ENCRYPT_KEY_DEFAULT, ENCRYPT_TYPE_AES);
    }

    /**
     * 将 byte[] 数组转换为 16 进制字符串
     *
     * @param bytes byte[] 数组
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

        str = "scott";
        String desStr = getDesEncryptValue(str);
        System.out.println("\nSOUR STRING：" + str);
        System.out.println("DES ENCRYPT:" + desStr);
        System.out.println("DES DECRYPT:" + getDesDecryptValue(desStr));
        String aesStr = getAesEncryptValue(str);
        System.out.println("AES ENCRYPT:" + aesStr);
        System.out.println("AES DECRYPT:" + getAesDecryptValue(aesStr));

        str = "12345678";
        desStr = getDesEncryptValue(str);
        System.out.println("\nSOUR STRING：" + str);
        System.out.println("DES ENCRYPT:" + desStr);
        System.out.println("DES DECRYPT:" + getDesDecryptValue(desStr));
        aesStr = getAesEncryptValue(str);
        System.out.println("AES ENCRYPT:" + aesStr);
        System.out.println("AES DECRYPT:" + getAesDecryptValue(aesStr));
    }
}
