package CRCTest;

import java.util.zip.CRC32;

import org.junit.Test;

/**
 * CRC—≠ª∑»ﬂ”‡–£—È
 * @author Administrator
 *
 */
public class CRCTest {
	/**
	 * ≤‚ ‘CRC-32
	 * @throws Exception
	 */
	@Test
	public void testCRC32() throws Exception{
		String str = "≤‚ ‘CRC-32";
		
		CRC32 crc32 = new CRC32();
		crc32.update(str.getBytes());
		
		String hex = Long.toHexString(crc32.getValue());
		
		System.out.println("CRC-32 " + hex);
	}
}
