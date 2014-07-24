package DES;

import org.junit.Assert;
import org.junit.Test;

/**
 * DES �����㷨��������
 * @author Administrator
 *
 */
public class DESCoderTest {
	
	@Test
	public final void test() throws Exception{
		String inputStr = "DES�ԳƼ����㷨";
		
		byte[] inputData = inputStr.getBytes();
		System.out.println("ԭ�ģ�" + inputStr);
		
		//��ʼ����Կ
		byte[] key = DESCoder.initKey();
		
		//���ܲ���
		inputData = DESCoder.encrypt(inputData, key);
		
		//���ܲ���
		byte[] outPutData = DESCoder.decrypt(inputData, key);
		
		String outputStr = new String(outPutData);
		
		Assert.assertEquals(inputStr, outputStr);
	}
}
