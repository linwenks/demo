package com.hy.demo.common.base.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * AES加密工具
 * 
 * @author Tank
 *
 */
@Slf4j
public class AesUtil {

	// 算法
	private static final String ALGORITHM = "AES";

	// 加密方式
	public static final String AES_CBC_PKCS5PADDING = "AES/CBC/PKCS5PADDING";
	public static final String AES_128_CBC = "AES-128-CBC";

	// 编码方式
	private static final Charset CHARSET_NAME = StandardCharsets.UTF_8;

	public static Cipher initEncrypt(String cipherMethod, String iv, String secret) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException {
		// 创建AES密钥
		var key = new SecretKeySpec(secret.getBytes(), ALGORITHM);
		var ivParameterSpec = new IvParameterSpec(iv.getBytes());

		// 创建密码器
		var cipherEncrypt = Cipher.getInstance(cipherMethod);
		// 初始化加密器
		cipherEncrypt.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);
		return cipherEncrypt;
	}

	public static Cipher initDecrypt(String cipherMethod, String iv, String secret) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException {
		// 创建AES密钥
		var key = new SecretKeySpec(secret.getBytes(), ALGORITHM);
		var ivParameterSpec = new IvParameterSpec(iv.getBytes());
		// 创建密码器
		var cipherDecrypt = Cipher.getInstance(cipherMethod);
		// 初始化加密器
		cipherDecrypt.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
		return cipherDecrypt;
	}

	/**
	 * 加密
	 *
	 * @param content 加密内容
	 * @return 加密后的内容
	 */
	public static String encryptToStringBase64(Cipher cipher, String content) throws BadPaddingException, IllegalBlockSizeException {
		if (StringUtils.isNotBlank(content)) {
			return Base64.getEncoder().encodeToString(encrypt(cipher, content));
		}
		return null;
	}

	public static byte[] encrypt(Cipher cipher, String content) throws BadPaddingException, IllegalBlockSizeException {
		if (StringUtils.isNotBlank(content)) {
			return encrypt(cipher, content.getBytes(CHARSET_NAME));
		}
		return null;
	}

	public static byte[] encrypt(Cipher cipher, byte[] content) throws BadPaddingException, IllegalBlockSizeException {
		if (ArrayUtils.isNotEmpty(content)) {
			try {
				return cipher.doFinal(content);
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				log.error(" encrypt error ", e);
				throw e;
			}
		}
		return null;
	}



	/**
	 * 解密
	 *
	 * @param content 需要解密的内容
	 * @return 解密后的内容
	 */
	public static String decryptToStringBase64(Cipher cipher, String content) throws BadPaddingException, IllegalBlockSizeException {
		if (StringUtils.isNotBlank(content)) {
			return decryptToString(cipher, Base64.getDecoder().decode(content));
		}
		return null;
	}

	public static byte[] decrypt(Cipher cipher, String content) throws BadPaddingException, IllegalBlockSizeException {
		if (StringUtils.isNotBlank(content)) {
			return decrypt(cipher, content.getBytes(CHARSET_NAME));
		}
		return null;
	}

	public static String decryptToString(Cipher cipher, String content) throws BadPaddingException, IllegalBlockSizeException {
		if (StringUtils.isNotBlank(content)) {
			return decryptToString(cipher, content.getBytes(CHARSET_NAME));
		}
		return null;
	}

	public static byte[] decrypt(Cipher cipher, byte[] content) throws BadPaddingException, IllegalBlockSizeException {
		if (ArrayUtils.isNotEmpty(content)) {
			try {
				return cipher.doFinal(content);
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				log.error(" decrypt error ", e);
				throw e;
			}
		}
		return null;
	}

	public static String decryptToString(Cipher cipher, byte[] content) throws BadPaddingException, IllegalBlockSizeException {
		if (ArrayUtils.isNotEmpty(content)) {
			return new String(decrypt(cipher, content));
		}
		return null;
	}
}