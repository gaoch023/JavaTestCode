package Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
 
/**
 * ����ͨ����������base64λ����
 * @author Administrator
 *
 */
public class Base64Example {
	public static void main(String[] args) throws UnsupportedEncodingException{
		String inputStr = "java��������ܵ�����";
		
		bouncyCastleWay(inputStr);
		
		commonCodecWay(inputStr);
	}
	
	
	/**
	 * bouncyCastle��ʽ����Base64 ����
	 * @param inputStr
	 * @throws UnsupportedEncodingException 
	 */
	public static  void bouncyCastleWay(String inputStr) throws UnsupportedEncodingException{
		System.out.println("ԭ�ģ� " + inputStr);
		
		//����Base64����
		String code = UrlBase64Coder.enCode(inputStr);
		System.out.println("����� " + code);
		
		//����Base64����
		code = UrlBase64Coder.deCode(code);
		System.out.println("����� " + code);
	}
	
	/**
	 * commonCodec��ʽ����Base 64����
	 * @param inputStr
	 * @throws UnsupportedEncodingException 
	 */
	public static void commonCodecWay(String inputStr) throws UnsupportedEncodingException{
		System.out.println("ԭ�ģ� " + inputStr);
		
		//����Base64����
		String code = CBase64Coder.enCode(inputStr);
		System.out.println("����� " + code);
		
		//����Base64����
		code = CBase64Coder.deCode(code);
		System.out.println("����� " + code);
	}
	
	/**
	 * ʹ��BouncyCastled��MD4�ļ��ܷ���
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
