package RSA;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

/**
 * �ǶԳƼ����㷨-RSA
 * 
 * @author Administrator
 * 
 */
public class RSACoder {
	/**
	 * �ǶԳƼ�����Կ�㷨
	 */
	public static final String KEY_ALGORITHM = "RSA";

	/**
	 * ��Կ
	 */
	private static final String PUBLIC_KEY = "RSAPublicKey";

	/**
	 * ˽Կ
	 */
	private static final String PRIVATE_KEY = "RSAPrivateKey";

	/**
	 * RSA��Կ���� Ĭ��Ϊ1024λ
	 */
	private static final int KEY_SIZE = 512;

	public static Map<String, Object> initKey() throws NoSuchAlgorithmException {
		// ʵ������Կ��������
		KeyPairGenerator keyPairGenerator = KeyPairGenerator
				.getInstance(KEY_ALGORITHM);

		// ��ʼ����Կ��������
		keyPairGenerator.initialize(KEY_SIZE);

		// ������Կ��
		KeyPair keyPair = keyPairGenerator.generateKeyPair();

		// ��Կ
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

		// ˽Կ
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

		// ��װ��Կ
		Map<String, Object> keyMap = new HashMap<String, Object>(2);

		keyMap.put(PRIVATE_KEY, privateKey);
		keyMap.put(PUBLIC_KEY, publicKey);

		return keyMap;
	}

	/**
	 * ��ȡ˽Կ
	 * 
	 * @param keyMap
	 * @return
	 */
	public static byte[] getPrivateKey(Map<String, Object> keyMap) {
		Key key = (Key) keyMap.get(PRIVATE_KEY);

		return key.getEncoded();
	}

	/**
	 * ��ȡ��Կ
	 * 
	 * @param map
	 * @return
	 */
	public static byte[] getPublicKey(Map<String, Object> keyMap) {
		Key key = (Key) keyMap.get(PUBLIC_KEY);

		return key.getEncoded();
	}

	/**
	 * ˽Կ����
	 * 
	 * @param data
	 *            ����������
	 * @param key
	 *            ˽Կ
	 * @return
	 * @throws InvalidKeySpecException
	 * @throws NoSuchAlgorithmException
	 */
	public static byte[] decryptByPrivateKey(byte[] data, byte[] key)
			throws Exception {
		// ȡ��˽Կ
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

		// ����˽Կ
		PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

		// �����ݽ���
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateKey);

		return cipher.doFinal(data);
	}

	/**
	 * ��Կ����
	 * 
	 * @param data
	 * @param key
	 * @return
	 */
	public static byte[] decryptByPublicKey(byte[] data, byte[] key)
			throws Exception {
		// ȡ�ù�Կ
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

		// ���ɹ�Կ
		PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);

		// �����ݽ���
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, publicKey);

		return cipher.doFinal(data);
	}

	/**
	 * ��Կ����
	 * 
	 * @param data
	 * @param key
	 * @return
	 */
	public static byte[] encryptByPublicKey(byte[] data, byte[] key)
			throws Exception {
		// ȡ�ù�Կ
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

		// ���ɹ�Կ
		PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);

		// �����ݼ���
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);

		return cipher.doFinal(data);
	}

	/**
	 * ˽Կ����
	 * 
	 * @param data
	 * @param key
	 * @return
	 */
	public static byte[] encryptByPrivate(byte[] data, byte[] key)
			throws Exception {
		// ȡ��˽Կ
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

		// ����˽Կ
		PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

		// �����ݼ���
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);

		return cipher.doFinal(data);
	}
}
