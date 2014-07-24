package SignatureExample;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

/**
 * 数字签名算法例子
 * 
 * @author Administrator
 * 
 */
public class SignatureExample {
	/**
	 * 对数据进行签名
	 * 
	 * @param data
	 *            待签名数据
	 * @param key
	 *            私密
	 * @return
	 */
	public byte[] signData(byte[] data, PrivateKey key) {
		Signature signer;
		try {
			signer = Signature.getInstance("SHA1withDSA");

			signer.initSign(key);
			signer.update(data);

			return (signer.sign());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * 验证数字签名
	 * @param data
	 * 待验证数据
	 * @param key
	 * 验证公钥
	 * @param sig
	 * 签名
	 * @return
	 */
	public boolean verifySign(byte[] data, PublicKey key, byte[] sig){
		Signature signer;
		try {
			signer = Signature.getInstance("SHA1withDSA");
			signer.initVerify(key);
			
			signer.update(data);
			
			return signer.verify(sig);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static void main(String[] args){
		SignatureExample sigEx = new SignatureExample();
		KeyPairGeneratorTest kpge = new KeyPairGeneratorTest();
		
		KeyPair keyPair = kpge.generateKeyPair(717);
		
		byte[] data = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74};
		byte[] digitalSignature = sigEx.signData(data, keyPair.getPrivate());
		
		boolean verified = false;
		
		verified = sigEx.verifySign(data, keyPair.getPublic(), digitalSignature);
		
		if(verified){
			System.out.println("verify Success");
		}else{
			System.out.println("verify failed");
		}
		
		//创建一个新的密钥对，验证将会失败
		keyPair = kpge.generateKeyPair(517);
		verified = sigEx.verifySign(data, keyPair.getPublic(), digitalSignature);
		
		if(verified){
			System.out.println("verify Success");
		}else{
			System.out.println("verify failed");
		}
	}
}
