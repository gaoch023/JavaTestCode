package PBE;

import java.security.Key;
import java.security.SecureRandom;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.bouncycastle.asn1.cms.KEKIdentifier;

/**
 * 基于口令加密
 * @author Administrator
 *
 */
public class PBECoder {
	/**
	 * PBE支持的算法
	 */
	public static final String ALGORITHM = "PBEWITHMD5andDES";
	
	/**
	 * 迭代次数
	 */
	public static final int ITERATION_COUNT = 10;
	
	/**
	 * 盐初始化操作
	 * @return
	 */
	public static byte[] initSalt(){
		//实例化安全随机数
		SecureRandom random = new SecureRandom();
		
		//产出盐
		return random.generateSeed(8);
	}
	
	/**
	 * 转化密钥
	 * @param password
	 * @return
	 */
	private static Key toKey(String password) throws Exception{
		//密钥材料转换
		PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
		
		//实例化
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
		
		//生成密钥
		SecretKey secretKey = keyFactory.generateSecret(keySpec);
		
		return secretKey;
	}
	
	/**
	 * 加密操作
	 * @param data
	 * 待加密数据
	 * @param password
	 * 密码
	 * @param salt
	 * 盐
	 * @return
	 */
	public static byte[] encrypt(byte[] data, String password, byte[] salt) throws Exception{
		//转换密钥
		Key key = toKey(password);
		
		//实例化PBE参考资料
		PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, ITERATION_COUNT);
		
		//实例化
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		
		//初始化
		cipher.init(Cipher.ENCRYPT_MODE, key, parameterSpec);
		
		//执行操作
		return cipher.doFinal(data);
	}
	
	/**
	 * 解密操作
	 * @param data
	 * 待解密数据
	 * @param password
	 * 密码
	 * @param salt
	 * 盐
	 * @return
	 */
	public static byte[] decrypt(byte[] data, String password, byte[] salt) throws Exception{
		//转换密钥
		Key key = toKey(password);
		
		//实例化PBE参数材料
		PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, ITERATION_COUNT);
		
		//实例化
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		
		//初始化
		cipher.init(Cipher.DECRYPT_MODE, key, parameterSpec);
		
		//执行操作
		return cipher.doFinal(data);
	}
}
