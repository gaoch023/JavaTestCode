package Base64;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

/**
 * UrlBase64Url 编码
 * 
 * @author Administrator
 * 
 */
public class CBase64Coder {
	/**
	 * 字符编码方式
	 */
	public final static String ENCODING = "UTF-8";

	/**
	 * 执行编码
	 * 
	 * @param data
	 *            待编码数据
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String enCode(String data)
			throws UnsupportedEncodingException {
		byte[] b = Base64.encodeBase64URLSafe(data.getBytes(ENCODING));

		return new String(b, ENCODING);
	}

	/**
	 * 执行解码操作
	 * 
	 * @param data
	 *            待解码操作
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String deCode(String data)
			throws UnsupportedEncodingException {
		byte[] b = Base64.decodeBase64(data.getBytes(ENCODING));

		return new String(b, ENCODING);
	}
}
