package NioSelector;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * selector�ͻ���
 * 
 * @author Administrator
 * 
 */
public class SelectorClientTest {
	public static void main(String[] args) throws IOException,
			InterruptedException {
		SocketChannel socketChannel = SocketChannel.open();
		// ����SocketChannel
		InetSocketAddress address = new InetSocketAddress(
				InetAddress.getLocalHost(), 10000);
		System.out.println("ip��ַ��" + InetAddress.getLocalHost());
		
		socketChannel.socket().connect(address);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		
		// ����Buffer
		while (true) {
			try {
				buffer.clear();
				String time = sdf.format(new Date());
				buffer.put(time.getBytes());
				buffer.flip();

				// ���óɶ�ȡ״̬
				socketChannel.write(buffer);
				// ��������
				Thread.sleep(5000);
			} catch (Exception e) {
				System.out.println("Connection Close");
				break;
			}
		}

	}
}
