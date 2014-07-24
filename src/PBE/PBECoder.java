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
 * ���ڿ������
 * @author Administrator
 *
 */
public class PBECoder {
	/**
	 * PBE֧�ֵ��㷨
	 */
	public static final String ALGORITHM = "PBEWITHMD5andDES";
	
	/**
	 * ��������
	 */
	public static final int ITERATION_COUNT = 10;
	
	/**
	 * �γ�ʼ������
	 * @return
	 */
	public static byte[] initSalt(){
		//ʵ������ȫ�����
		SecureRandom random = new SecureRandom();
		
		//������
		return random.generateSeed(8);
	}
	
	/**
	 * ת����Կ
	 * @param password
	 * @return
	 */
	private static Key toKey(String password) throws Exception{
		//��Կ����ת��
		PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
		
		//ʵ����
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
		
		//������Կ
		SecretKey secretKey = keyFactory.generateSecret(keySpec);
		
		return secretKey;
	}
	
	/**
	 * ���ܲ���
	 * @param data
	 * ����������
	 * @param password
	 * ����
	 * @param salt
	 * ��
	 * @return
	 */
	public static byte[] encrypt(byte[] data, String password, byte[] salt) throws Exception{
		//ת����Կ
		Key key = toKey(password);
		
		//ʵ����PBE�ο�����
		PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, ITERATION_COUNT);
		
		//ʵ����
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		
		//��ʼ��
		cipher.init(Cipher.ENCRYPT_MODE, key, parameterSpec);
		
		//ִ�в���
		return cipher.doFinal(data);
	}
	
	/**
	 * ���ܲ���
	 * @param data
	 * ����������
	 * @param password
	 * ����
	 * @param salt
	 * ��
	 * @return
	 */
	public static byte[] decrypt(byte[] data, String password, byte[] salt) throws Exception{
		//ת����Կ
		Key key = toKey(password);
		
		//ʵ����PBE��������
		PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, ITERATION_COUNT);
		
		//ʵ����
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		
		//��ʼ��
		cipher.init(Cipher.DECRYPT_MODE, key, parameterSpec);
		
		//ִ�в���
		return cipher.doFinal(data);
	}
}
