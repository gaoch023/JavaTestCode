package Base64;

import java.io.UnsupportedEncodingException;

import org.bouncycastle.util.encoders.UrlBase64;

/**
 * UrlBase64Url ����
 * 
 * @author Administrator
 * 
 */
public class UrlBase64Coder {
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
		byte[] b = UrlBase64.encode(data.getBytes(ENCODING));

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
		byte[] b = UrlBase64.decode(data.getBytes(ENCODING));

		return new String(b, ENCODING);
	}
}
