package TestAllocation;

/**
 * ��ʱ��Minor GC ����
 * @author Administrator
 *
 */
public class TestAllocation {
	private static final int _1MB = 1024 * 1024;
	
	public static void main(String[] args){
		byte[] allocation1;
		byte[] allocation2;
		byte[] allocation3;
		byte[] allocation4;
		
		allocation1 = new byte[_1MB * 2];
		allocation2 = new byte[_1MB * 2];
		allocation3 = new byte[_1MB * 2];
		
		//����minor gc
		allocation4 = new byte[_1MB * 4];
	}
}
