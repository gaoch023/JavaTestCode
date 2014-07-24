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
 * 密钥交换算法
 * @author Administrator
 *
 */
public class DHCoder {
	/**
	 * 非对称加密密钥算法
	 */
	public static final String KEY_ALGORTHM = "DH";
	
	/**
	 * 本地密钥算法
	 */
	public static final String SECRET_ALGORITHM = "AES";
	
	/**
	 * 密钥长度
	 */
	private static final int KEY_SIZE = 512;
	
	/**
	 * 公钥
	 */
	private static final String PUBLIC_KEY = "DHPublicKey";
	
	/**
	 * 私钥
	 */
	private static final String PRIVATE_KEY = "DHPrivate";
	
	/**
	 * 初始化甲方密钥
	 * @return
	 * 甲方密钥Map
	 * @throws Exception
	 */
	public static Map<String, Object> initKey() throws Exception{
		//实例化密钥对生成器
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORTHM);
		
		//初始化密钥对生成器
		keyPairGenerator.initialize(KEY_SIZE);
		
		//生成密钥对
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		
		//甲方公钥
		DHPublicKey publicKey = (DHPublicKey)keyPair.getPublic();
		
		//甲方私钥
		DHPrivateKey privateKey = (DHPrivateKey)keyPair.getPrivate();
		
		//将密钥对存储在Map中
		Map<String, Object> keyMap = new HashMap<String, Object>(2);
		keyMap.put(PRIVATE_KEY, privateKey);
		keyMap.put(PUBLIC_KEY, publicKey);
		
		return keyMap;
	}
	
	/**
	 * 初始化乙方密钥
	 * @param key
	 * 甲方公钥
	 * @return
	 */
	public static Map<String, Object> initKey(byte[] key) throws Exception{
		//解析甲方公钥,转换公钥材料
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
		
		//实例化密钥工厂
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORTHM);
		
		//产生公钥
		PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);
		
		//由甲方公钥构建乙方密钥
		DHParameterSpec dhParameterSpec = ((DHPublicKey)pubKey).getParams();
		
		//实例化密钥对生成器
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(keyFactory.getAlgorithm());
		
		//初始化密钥对生成器
		keyPairGenerator.initialize(dhParameterSpec);
		
		//产生密钥对
		KeyPair keyPair = keyPairGenerator.genKeyPair();
		
		//乙方公钥
		DHPublicKey publicKey = (DHPublicKey)keyPair.getPublic();
		
		//乙方私钥
		DHPrivateKey privateKey = (DHPrivateKey)keyPair.getPrivate();
		
		//将密钥对存储在Map中
		Map<String, Object> keyMap = new HashMap<String, Object>(2);
		keyMap.put(PRIVATE_KEY, privateKey);
		keyMap.put(PUBLIC_KEY, publicKey);
		
		return keyMap;
	}
}
