package Random;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

/**
 * Ëæ»úÊý²âÊÔÀý×Ó
 * 
 * @author Administrator
 * 
 */
public class Client {
	public static void main(String[] args) throws NoSuchAlgorithmException {
		SecureRandom rng = SecureRandom.getInstance("SHA1PRNG");
		rng.setSeed(711);

		int num = 10;
		byte[] numArray = new byte[num];

		rng.nextBytes(numArray);

		System.out.println("---------SecureRandom--------");
		for (byte b : numArray) {
			System.out.println(b + " ");
		}
		
		Random random = new Random(711);
		random.nextBytes(numArray);
		System.out.println("---------Random--------");
		for (byte b : numArray) {
			System.out.println(b + " ");
		}
	}
}
