package Base64;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

/**
 * UrlBase64Url ����
 * 
 * @author Administrator
 * 
 */
public class CBase64Coder {
	/**
	 * �ַ����뷽ʽ
	 */
	public final static String ENCODING = "UTF-8";

	/**
	 * ִ�б���
	 * 
	 * @param data
	 *            ����������
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String enCode(String data)
			throws UnsupportedEncodingException {
		byte[] b = Base64.encodeBase64URLSafe(data.getBytes(ENCODING));

		return new String(b, ENCODING);
	}

	/**
	 * ִ�н������
	 * 
	 * @param data
	 *            ���������
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String deCode(String data)
			throws UnsupportedEncodingException {
		byte[] b = Base64.decodeBase64(data.getBytes(ENCODING));

		return new String(b, ENCODING);
	}
}
