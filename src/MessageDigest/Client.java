package MessageDigest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 消息摘要算法例子
 * @author Administrator
 *
 */
public class Client {
	public static void main(String[] args){
		try{
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] data1 = {65, 66, 67, 68, 69};
			byte[] data2 = {70, 71, 72, 73, 74};
			
			sha.update(data1);
			sha.update(data2);
			
			byte[] msgDigest = sha.digest();
			System.out.println("Message Digest");
			for(int i=0; i<msgDigest.length; i++){
				System.out.println( msgDigest[i] + " ");
			}
			
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}
	}
}
