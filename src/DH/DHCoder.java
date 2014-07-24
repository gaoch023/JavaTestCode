package DH;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;

/**
 * ��Կ�����㷨
 * @author Administrator
 *
 */
public class DHCoder {
	/**
	 * �ǶԳƼ�����Կ�㷨
	 */
	public static final String KEY_ALGORTHM = "DH";
	
	/**
	 * ������Կ�㷨
	 */
	public static final String SECRET_ALGORITHM = "AES";
	
	/**
	 * ��Կ����
	 */
	private static final int KEY_SIZE = 512;
	
	/**
	 * ��Կ
	 */
	private static final String PUBLIC_KEY = "DHPublicKey";
	
	/**
	 * ˽Կ
	 */
	private static final String PRIVATE_KEY = "DHPrivate";
	
	/**
	 * ��ʼ���׷���Կ
	 * @return
	 * �׷���ԿMap
	 * @throws Exception
	 */
	public static Map<String, Object> initKey() throws Exception{
		//ʵ������Կ��������
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORTHM);
		
		//��ʼ����Կ��������
		keyPairGenerator.initialize(KEY_SIZE);
		
		//������Կ��
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		
		//�׷���Կ
		DHPublicKey publicKey = (DHPublicKey)keyPair.getPublic();
		
		//�׷�˽Կ
		DHPrivateKey privateKey = (DHPrivateKey)keyPair.getPrivate();
		
		//����Կ�Դ洢��Map��
		Map<String, Object> keyMap = new HashMap<String, Object>(2);
		keyMap.put(PRIVATE_KEY, privateKey);
		keyMap.put(PUBLIC_KEY, publicKey);
		
		return keyMap;
	}
	
	/**
	 * ��ʼ���ҷ���Կ
	 * @param key
	 * �׷���Կ
	 * @return
	 */
	public static Map<String, Object> initKey(byte[] key) throws Exception{
		//�����׷���Կ,ת����Կ����
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
		
		//ʵ������Կ����
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORTHM);
		
		//������Կ
		PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);
		
		//�ɼ׷���Կ�����ҷ���Կ
		DHParameterSpec dhParameterSpec = ((DHPublicKey)pubKey).getParams();
		
		//ʵ������Կ��������
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(keyFactory.getAlgorithm());
		
		//��ʼ����Կ��������
		keyPairGenerator.initialize(dhParameterSpec);
		
		//������Կ��
		KeyPair keyPair = keyPairGenerator.genKeyPair();
		
		//�ҷ���Կ
		DHPublicKey publicKey = (DHPublicKey)keyPair.getPublic();
		
		//�ҷ�˽Կ
		DHPrivateKey privateKey = (DHPrivateKey)keyPair.getPrivate();
		
		//����Կ�Դ洢��Map��
		Map<String, Object> keyMap = new HashMap<String, Object>(2);
		keyMap.put(PRIVATE_KEY, privateKey);
		keyMap.put(PUBLIC_KEY, publicKey);
		
		return keyMap;
	}
}
