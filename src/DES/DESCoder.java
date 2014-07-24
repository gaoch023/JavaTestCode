package DES;

import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * 对称加密算法DES
 * 
 * @author Administrator
 * 
 */
public class DESCoder {
	/**
	 * DES加密算法
	 */
	public static final String KEY_ALGORITHM = "DES";

	/**
	 * 加解密算法/工作模式/填充方式
	 */
	public static final String CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";

	/**
	 * 生成密钥
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static byte[] initKey() throws NoSuchAlgorithmException {
		KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);

		kg.init(56);

		// 生成私密密钥
		SecretKey secretKey = kg.generateKey();

		// 获得密钥的二进制编码形式
		return secretKey.getEncoded();
	}

	/**
	 * 解密操作
	 * 
	 * @param data
	 *            待解密数据
	 * @param key
	 *            密钥
	 * @return
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] data, byte[] key) throws Exception{
		// 还原密钥
		Key k = toKey(key);

		// 实例化
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);

		// 初始化，设置为解密模式
		cipher.init(Cipher.DECRYPT_MODE, k);

		// 执行操作
		return cipher.doFinal(data);
	}
	
	/**
	 * 加密数据
	 * @param data
	 * 待加密数据
	 * @param key
	 * 密钥
	 * @return
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data, byte[] key) throws Exception{
		//还原密钥
		Key k = toKey(key);

		// 实例化
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);

		// 初始化，设置为解密模式
		cipher.init(Cipher.ENCRYPT_MODE, k);

		// 执行操作
		return cipher.doFinal(data);
	}
	
	/**
	 * 转化密钥由二进制密钥转为Key类型的密钥
	 * @param key
	 * 二进制密钥
	 * @return
	 * @throws Exception
	 */
	private static Key toKey(byte[] key) throws Exception{
		//实例化DES密钥材料
		DESKeySpec dks = new DESKeySpec(key);
		
		//实例化私密密钥工厂
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
		
		//生成私密密钥
		SecretKey secretKey = keyFactory.generateSecret(dks);
		
		return secretKey;
	}
}
