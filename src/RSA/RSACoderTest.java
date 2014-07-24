package RSA;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;


/**
 * �ǶԳ��㷨��������
 * @author Administrator
 *
 */
public class RSACoderTest {
	//��Կ
	private byte[] publicKey;
	
	//˽Կ
	private byte[] privateKey;
	
	@Before
	public void initKey() throws NoSuchAlgorithmException{
		//��ʼ����Կ
		Map<String, Object> keyMap = RSACoder.initKey();
		publicKey = RSACoder.getPublicKey(keyMap);
		privateKey = RSACoder.getPrivateKey(keyMap);
		
		System.out.println("˽Կ��" + Base64.encodeBase64String(privateKey));
		System.out.println("��Կ��" + Base64.encodeBase64String(publicKey));
	}
	
	/**
	 * У��
	 * @throws Exception 
	 */
	@Test
	public void test() throws Exception{
		System.out.println("��ʼ����");
		
		String inputStr1 = "RSA�����㷨";
		
		byte[] data1 = inputStr1.getBytes();
		System.out.println("ԭ�ģ�" + data1);
		
		//��˽Կ���ܲ���
		byte[] encodeData1 = RSACoder.encryptByPrivate(data1, privateKey);
		
		//�ù�Կ����
		byte[] decodeData1 = RSACoder.decryptByPublicKey(encodeData1, publicKey);
		
		String outputStr1 = new String(decodeData1);
		
		//У��
		org.junit.Assert.assertEquals(inputStr1, outputStr1);
		
		String inputStr2 = "RSA Encypt Algorithm";
		
		byte[] data2 = inputStr2.getBytes();
		System.out.println("ԭ�ģ�" + data2);
		
		//�ù�Կ����
		byte[] encodedData2 = RSACoder.encryptByPublicKey(data2, publicKey);
		
		//��˽Կ����
		byte[] decodeData2 = RSACoder.decryptByPrivateKey(encodedData2, privateKey);
		
		String outputStr2 = new String(decodeData2);
		
		org.junit.Assert.assertEquals(inputStr2, outputStr2);
	}
}
