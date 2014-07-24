package ExecutorService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * futureģʽ�̳߳ز���
 * 
 * @author Administrator
 * 
 */
public class Client2 {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		List<Future<String>> resultList = new ArrayList<Future<String>>();
		Future<String> future;
		
		//����10������ִ��
		for(int i=0; i<10; i++){
			future = executorService.submit(new TaskWithResult(i));
			//�����񼯴洢��List��
			resultList.add(future);
		}
		
		executorService.shutdown();
		System.out.println("all thread end");
		
		for (Future<String> fs : resultList) {
			try {
				System.out.println(fs.get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	static class TaskWithResult implements Callable<String> {
		/**
		 * ��ʾID
		 */
		private int id;

		public TaskWithResult(int id) {
			this.id = id;
		}

		/**
		 * ����ľ�����̣�һ�����񴫸�ExecutorService��submit��������÷����Զ���һ���߳���ִ�С�
		 * 
		 * @return
		 * @throws Exception
		 */
		public String call() throws Exception {
			System.out.println("call()�������Զ�����,�ɻ����             "
					+ Thread.currentThread().getName());
			// һ��ģ���ʱ�Ĳ���
			for (int i = 999999999; i > 0; i--);
			
			return "call()�������Զ����ã�����Ľ���ǣ�" + id + "    "
					+ Thread.currentThread().getName();
		}
	}
}
