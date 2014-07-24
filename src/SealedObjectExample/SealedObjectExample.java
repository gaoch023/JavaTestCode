package SealedObjectExample;

import java.io.IOException;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;

/**
 * 利用SealedObject实现加密Object对象
 * @author Administrator
 *
 */
public class SealedObjectExample {
	private SecretKey secretKey;
	private Cipher encrypter;
	private Cipher decrypter;
	
	public SealedObjectExample(){
		try {
			secretKey = KeyGenerator.getInstance("DES").generateKey();
			
			encrypter = Cipher.getInstance("DES");
			encrypter.init(Cipher.ENCRYPT_MODE, secretKey);
			
			decrypter = Cipher.getInstance("DES");
			decrypter.init(Cipher.DECRYPT_MODE, secretKey);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 加密序列化对象
	 * @param obj
	 * @return
	 */
	public SealedObject seal(Serializable obj){
		try {
			return new SealedObject(obj, encrypter);
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 解密对象
	 * @param obj
	 * 待解密对象
	 * @return
	 */
	public Object unSeal(SealedObject obj){
		try {
			String alg = obj.getAlgorithm();
			
			System.out.println("加密的算法是：" + alg);
			
			return obj.getObject(decrypter);
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void main(String[] args){
		CustomerData cust;
		CustomerData unSeal;
		SealedObject sealed;
		
		SealedObjectExample soe = new SealedObjectExample();
		
		//实例化实体
		cust = new CustomerData();
		cust.name = "Paul";
		cust.password = "password";
		
		//sealed it
		sealed = soe.seal(cust);
		
		//unSealed it
		unSeal = (CustomerData)soe.unSeal(sealed);
		System.out.println("NAME:" + unSeal.name);
		System.out.println("PASSWORD:" + unSeal.password);
	}
}

class CustomerData implements Serializable{
	public String name;
	public String password;
}
