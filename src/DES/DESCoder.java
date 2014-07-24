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
 * �ԳƼ����㷨DES
 * 
 * @author Administrator
 * 
 */
public class DESCoder {
	/**
	 * DES�����㷨
	 */
	public static final String KEY_ALGORITHM = "DES";

	/**
	 * �ӽ����㷨/����ģʽ/��䷽ʽ
	 */
	public static final String CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";

	/**
	 * ������Կ
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static byte[] initKey() throws NoSuchAlgorithmException {
		KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);

		kg.init(56);

		// ����˽����Կ
		SecretKey secretKey = kg.generateKey();

		// �����Կ�Ķ����Ʊ�����ʽ
		return secretKey.getEncoded();
	}

	/**
	 * ���ܲ���
	 * 
	 * @param data
	 *            ����������
	 * @param key
	 *            ��Կ
	 * @return
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] data, byte[] key) throws Exception{
		// ��ԭ��Կ
		Key k = toKey(key);

		// ʵ����
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);

		// ��ʼ��������Ϊ����ģʽ
		cipher.init(Cipher.DECRYPT_MODE, k);

		// ִ�в���
		return cipher.doFinal(data);
	}
	
	/**
	 * ��������
	 * @param data
	 * ����������
	 * @param key
	 * ��Կ
	 * @return
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data, byte[] key) throws Exception{
		//��ԭ��Կ
		Key k = toKey(key);

		// ʵ����
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);

		// ��ʼ��������Ϊ����ģʽ
		cipher.init(Cipher.ENCRYPT_MODE, k);

		// ִ�в���
		return cipher.doFinal(data);
	}
	
	/**
	 * ת����Կ�ɶ�������ԿתΪKey���͵���Կ
	 * @param key
	 * ��������Կ
	 * @return
	 * @throws Exception
	 */
	private static Key toKey(byte[] key) throws Exception{
		//ʵ����DES��Կ����
		DESKeySpec dks = new DESKeySpec(key);
		
		//ʵ����˽����Կ����
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
		
		//����˽����Կ
		SecretKey secretKey = keyFactory.generateSecret(dks);
		
		return secretKey;
	}
}
