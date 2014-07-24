package CipherExample;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/**
 * Cipher进行加密解密例子
 * 
 * @author Administrator
 * 
 */
public class CipherExample {
	private Cipher mEncrypter;
	private Cipher mDecrypter;

	/**
	 * 初始化操作
	 */
	public void init(SecretKey key) {
		// for CBC ;must be 8bytes
		byte[] initVector = new byte[] { 0x10, 0x10, 0x01, 0x04, 0x01, 0x01,
				0x01, 0x02 };

		AlgorithmParameterSpec algParameterSpec = new IvParameterSpec(
				initVector);

		try {
			mEncrypter = Cipher.getInstance("DES/CBC/PKCS5Paddind");

			mDecrypter = Cipher.getInstance("DES/CBC/PKCS5Paddind");

			mEncrypter.init(Cipher.ENCRYPT_MODE, key, algParameterSpec);
			mDecrypter.init(Cipher.DECRYPT_MODE, key, algParameterSpec);

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 写入到输出流中
	 * 
	 * @param bytes
	 *            待输入数据
	 * @param out
	 *            输出地址
	 */
	public void write(byte[] bytes, OutputStream out) {
		// 加密之后写入到输出流中
		CipherOutputStream cos = new CipherOutputStream(out, mEncrypter);

		try {
			cos.write(bytes, 0, bytes.length);
			cos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 从输入流中读取书籍
	 * 
	 * @param bytes
	 *            读入到目标字节数组中
	 * @param in
	 *            源数据地址
	 */
	public void read(byte[] bytes, InputStream in) {
		// 解密之后读入数据
		CipherInputStream cis = new CipherInputStream(in, mDecrypter);

		int pos = 0;
		int intValue;

		try {
			while ((intValue = cis.read()) != -1) {
				bytes[pos] = (byte) intValue;
				pos++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 加密数据
	 * 
	 * @param input
	 *            输入的待加密数据
	 * @return
	 */
	public byte[] encrypt(byte[] input) {
		try {
			return mEncrypter.doFinal(input);
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 解密数据
	 * 
	 * @param input
	 *            待解密数据
	 * @return
	 */
	public byte[] decrypt(byte[] input) {
		try {
			return mDecrypter.doFinal(input);
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public static void main(String[] agrs) {
		CipherExample ce  = new CipherExample();
		
		SecretKey key;
		try {
			key = KeyGenerator.getInstance("DES").generateKey();
			ce.init(key);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Testing encrypt/decrypt of bytes");
		
		//源数据，待加密数据
		byte[] clearText = new byte[]{65, 73, 82, 68, 65, 78, 67, 69};
		byte[] encryptedText = ce.encrypt(clearText);
		byte[] decryptedText = ce.decrypt(encryptedText);
		
		String clearTextString = new String(clearText);
		String encryptedTextString = new String(encryptedText);
		String decryptedTextSting = new String(decryptedText);
		
		System.out.println("源数据：" + clearTextString);
		System.out.println("加密后的数据：" + encryptedTextString);
		System.out.println("解密后的数据：" + decryptedTextSting);
	}
}
