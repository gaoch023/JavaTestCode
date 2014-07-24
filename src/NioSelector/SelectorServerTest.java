package NioSelector;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * selector�����
 * 
 * @author Administrator
 * 
 */
public class SelectorServerTest {
	public static void main(String[] args) throws IOException,
			InterruptedException {
		Selector selector = Selector.open(); // ����Selector

		// ����һ�����ڽ������ӵ�ServerSocketChannel
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		InetSocketAddress address = new InetSocketAddress(
				InetAddress.getLocalHost(), 10000);
		serverSocketChannel.socket().bind(address);
		serverSocketChannel.configureBlocking(false);
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT); // ע��Accept�¼�
		
		while (true) {
			if (selector.select() > 0) { // ���ע����¼�����
				Set<SelectionKey> selectedKeys = selector.selectedKeys(); // ��ȡ�������¼�
				Iterator<SelectionKey> it = selectedKeys.iterator(); // ���ν��д���
				while (it.hasNext()) {
					SelectionKey selectionKey = it.next();
					if (selectionKey.isAcceptable()) { // �����Accept�¼�
						serverSocketChannel = ((ServerSocketChannel) selectionKey
								.channel());
						SocketChannel socketChannel = serverSocketChannel
								.accept();
						socketChannel.configureBlocking(false);
						socketChannel.register(selector, SelectionKey.OP_READ);
						System.out.println("Connected: "
								+ socketChannel.socket()
										.getRemoteSocketAddress());
					}else if (selectionKey.isReadable()) { // �����Read�¼�
						System.out.println("Read: ");
					}
					
					it.remove(); // ��Ҫ����������¼��Ƴ�
				}
			}
		}
	}
}
