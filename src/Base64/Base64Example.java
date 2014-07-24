package Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
 
/**
 * 用普通方法对明文base64位加密
 * @author Administrator
 *
 */
public class Base64Example {
	public static void main(String[] args) throws UnsupportedEncodingException{
		String inputStr = "java加密与解密的艺术";
		
		bouncyCastleWay(inputStr);
		
		commonCodecWay(inputStr);
	}
	
	
	/**
	 * bouncyCastle方式进行Base64 编码
	 * @param inputStr
	 * @throws UnsupportedEncodingException 
	 */
	public static  void bouncyCastleWay(String inputStr) throws UnsupportedEncodingException{
		System.out.println("原文： " + inputStr);
		
		//进行Base64编码
		String code = UrlBase64Coder.enCode(inputStr);
		System.out.println("编码后： " + code);
		
		//进行Base64解码
		code = UrlBase64Coder.deCode(code);
		System.out.println("解码后： " + code);
	}
	
	/**
	 * commonCodec方式进行Base 64编码
	 * @param inputStr
	 * @throws UnsupportedEncodingException 
	 */
	public static void commonCodecWay(String inputStr) throws UnsupportedEncodingException{
		System.out.println("原文： " + inputStr);
		
		//进行Base64编码
		String code = CBase64Coder.enCode(inputStr);
		System.out.println("编码后： " + code);
		
		//进行Base64解码
		code = CBase64Coder.deCode(code);
		System.out.println("解码后： " + code);
	}
	
	/**
	 * 使用BouncyCastled的MD4的加密方法
	 * @param data
	 */
	public void bouncyCastleMD4(byte[] data){
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD4");
			
			md.update(data);
			
			md.digest();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
