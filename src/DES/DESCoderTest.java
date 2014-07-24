package DES;

import org.junit.Assert;
import org.junit.Test;

/**
 * DES 加密算法测试例子
 * @author Administrator
 *
 */
public class DESCoderTest {
	
	@Test
	public final void test() throws Exception{
		String inputStr = "DES对称加密算法";
		
		byte[] inputData = inputStr.getBytes();
		System.out.println("原文：" + inputStr);
		
		//初始化密钥
		byte[] key = DESCoder.initKey();
		
		//加密操作
		inputData = DESCoder.encrypt(inputData, key);
		
		//解密操作
		byte[] outPutData = DESCoder.decrypt(inputData, key);
		
		String outputStr = new String(outPutData);
		
		Assert.assertEquals(inputStr, outputStr);
	}
}
