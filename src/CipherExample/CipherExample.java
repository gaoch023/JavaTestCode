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
 * Cipher���м��ܽ�������
 * 
 * @author Administrator
 * 
 */
public class CipherExample {
	private Cipher mEncrypter;
	private Cipher mDecrypter;

	/**
	 * ��ʼ������
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
	 * д�뵽�������
	 * 
	 * @param bytes
	 *            ����������
	 * @param out
	 *            �����ַ
	 */
	public void write(byte[] bytes, OutputStream out) {
		// ����֮��д�뵽�������
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
	 * ���������ж�ȡ�鼮
	 * 
	 * @param bytes
	 *            ���뵽Ŀ���ֽ�������
	 * @param in
	 *            Դ���ݵ�ַ
	 */
	public void read(byte[] bytes, InputStream in) {
		// ����֮���������
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
	 * ��������
	 * 
	 * @param input
	 *            ����Ĵ���������
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
	 * ��������
	 * 
	 * @param input
	 *            ����������
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
		
		//Դ���ݣ�����������
		byte[] clearText = new byte[]{65, 73, 82, 68, 65, 78, 67, 69};
		byte[] encryptedText = ce.encrypt(clearText);
		byte[] decryptedText = ce.decrypt(encryptedText);
		
		String clearTextString = new String(clearText);
		String encryptedTextString = new String(encryptedText);
		String decryptedTextSting = new String(decryptedText);
		
		System.out.println("Դ���ݣ�" + clearTextString);
		System.out.println("���ܺ�����ݣ�" + encryptedTextString);
		System.out.println("���ܺ�����ݣ�" + decryptedTextSting);
	}
}
