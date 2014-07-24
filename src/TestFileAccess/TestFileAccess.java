package TestFileAccess;

import java.io.RandomAccessFile;

/**
 * java安全模型测试
 * 
 * @author Administrator
 * 
 */
public class TestFileAccess {
	public static void main(String[] args) {
		try {

			RandomAccessFile raf = new RandomAccessFile(
					"d://test.txt", "rw");
			String ln = raf.readLine();

            System.out.println(ln);

            ln = '\n' + ln;

			raf.seek(raf.length());
			raf.write(ln.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
