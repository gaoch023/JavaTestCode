package PBE;

import junit.framework.Assert;

import org.junit.Test;

/**
 * PBEУ��
 * @author Administrator
 *
 */
public class PBECoderTest {
	
	/**
	 * ��������
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception{
		String inputStr = "PBE���ڿ���ļ����㷨";
		
		System.out.println("ԭ�ģ�" + inputStr);
		
		byte[] input = inputStr.getBytes();
		
		String pwd = "snowolf@zlex.org";
		
		System.out.println("���룺" + pwd);
		
		//��ʼ����
		byte[] salt = PBECoder.initSalt();
		
		//���ܲ���
		byte[] data = PBECoder.encrypt(input, pwd, salt);
		
		//���ܲ���
		byte[] output = PBECoder.decrypt(data, pwd, salt);
		
		String outputStr = new String(output);
		
		Assert.assertEquals(inputStr, outputStr);
	}
}
