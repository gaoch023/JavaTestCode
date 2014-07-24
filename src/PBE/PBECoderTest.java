package PBE;

import junit.framework.Assert;

import org.junit.Test;

/**
 * PBE校验
 * @author Administrator
 *
 */
public class PBECoderTest {
	
	/**
	 * 测试例子
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception{
		String inputStr = "PBE基于口令的加密算法";
		
		System.out.println("原文：" + inputStr);
		
		byte[] input = inputStr.getBytes();
		
		String pwd = "snowolf@zlex.org";
		
		System.out.println("密码：" + pwd);
		
		//初始化盐
		byte[] salt = PBECoder.initSalt();
		
		//加密操作
		byte[] data = PBECoder.encrypt(input, pwd, salt);
		
		//解密操作
		byte[] output = PBECoder.decrypt(data, pwd, salt);
		
		String outputStr = new String(output);
		
		Assert.assertEquals(inputStr, outputStr);
	}
}
