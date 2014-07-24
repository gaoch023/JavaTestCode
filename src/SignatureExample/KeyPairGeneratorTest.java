package SignatureExample;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.text.MessageFormat;

/**
 * 密钥对生成扩展类
 * @author Administrator
 *
 */
public class KeyPairGeneratorTest {
	/**
	 * 随机数生成种子
	 * @param seed
	 * @return
	 */
	public KeyPair generateKeyPair(long seed){
		KeyPairGenerator keyPairGenerator;
		
		SecureRandom rng;
		try {
			keyPairGenerator = KeyPairGenerator.getInstance("DSA");
			rng = SecureRandom.getInstance("SHA1PRNG", "SUN");
			
			rng.setSeed(seed);
			
			keyPairGenerator.initialize(1024, rng);
			
			return keyPairGenerator.generateKeyPair();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void main(String[] args){
		KeyPairGeneratorTest kpge = new KeyPairGeneratorTest();
		
		KeyPair kp = kpge.generateKeyPair(717);
		System.out.println("----PUBLIC KEY ---");
		PublicKey pubKey = kp.getPublic();
		System.out.println(MessageFormat.format("Algorithm={0}, Encoded={1}, Format={2}", pubKey.getAlgorithm(), pubKey.getEncoded(), pubKey.getFormat()));
		
		System.out.println("----PRIVARE KEY ---");
		PrivateKey privtaeKey = kp.getPrivate();
		System.out.println(MessageFormat.format("Algorithm={0}, Encoded={1}, Format={2}", privtaeKey.getAlgorithm(), privtaeKey.getEncoded(), privtaeKey.getFormat()));
	}
}
