package ExecutorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * �̳߳ز���, ���Է����̱߳�ѭ�����������������߳�ȴ���������ģ�������ExecutorService�����ġ�
 * @author Administrator
 *
 */
public class Client {
	public static void main(String[] args) throws InterruptedException{
		TestRunnable runnable;
		// ����һ���̶���С���̳߳�
        ExecutorService service = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            System.out.println("�����߳�" + i);
            
            runnable = new TestRunnable("" + i);
            // ��δ��ĳ��ʱ��ִ�и���������
            service.execute(runnable);
        }
        // �ر������߳�
        service.shutdown();
        
        // �ȴ����߳̽������ټ���ִ������Ĵ���
        service.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        System.out.println("all thread complete");

	}
	
	static class TestRunnable implements Runnable{
		private String name;
		
		public TestRunnable(String name){
			this.name = name;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("�߳�" + name  + "��ʼ����");
		}
		
	}
}
