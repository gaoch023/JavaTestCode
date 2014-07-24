package RSA;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;


/**
 * 非对称算法测试例子
 * @author Administrator
 *
 */
public class RSACoderTest {
	//公钥
	private byte[] publicKey;
	
	//私钥
	private byte[] privateKey;
	
	@Before
	public void initKey() throws NoSuchAlgorithmException{
		//初始化密钥
		Map<String, Object> keyMap = RSACoder.initKey();
		publicKey = RSACoder.getPublicKey(keyMap);
		privateKey = RSACoder.getPrivateKey(keyMap);
		
		System.out.println("私钥：" + Base64.encodeBase64String(privateKey));
		System.out.println("公钥：" + Base64.encodeBase64String(publicKey));
	}
	
	/**
	 * 校验
	 * @throws Exception 
	 */
	@Test
	public void test() throws Exception{
		System.out.println("开始测试");
		
		String inputStr1 = "RSA加密算法";
		
		byte[] data1 = inputStr1.getBytes();
		System.out.println("原文：" + data1);
		
		//用私钥加密操作
		byte[] encodeData1 = RSACoder.encryptByPrivate(data1, privateKey);
		
		//用公钥解密
		byte[] decodeData1 = RSACoder.decryptByPublicKey(encodeData1, publicKey);
		
		String outputStr1 = new String(decodeData1);
		
		//校验
		org.junit.Assert.assertEquals(inputStr1, outputStr1);
		
		String inputStr2 = "RSA Encypt Algorithm";
		
		byte[] data2 = inputStr2.getBytes();
		System.out.println("原文：" + data2);
		
		//用公钥加密
		byte[] encodedData2 = RSACoder.encryptByPublicKey(data2, publicKey);
		
		//用私钥解密
		byte[] decodeData2 = RSACoder.decryptByPrivateKey(encodedData2, privateKey);
		
		String outputStr2 = new String(decodeData2);
		
		org.junit.Assert.assertEquals(inputStr2, outputStr2);
	}
}
