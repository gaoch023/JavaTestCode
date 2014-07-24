package DumpSort;

/**
 * 堆排序算法
 * 
 * @author Administrator
 * 
 */
public class Client {
	public static void main(String[] args) {
		int[] A = new int[] { -1, 20, 17, 9, 10, 11, 4, 5, 3, 7, 5 };
		
		//int index = 2;
		//A[index] = 3;
		//shiftDown(A, 2, A.length - 1);
		
		//调用堆排序算法
		heapSort(A, A.length -1);
		
		for (int i : A) {
			System.out.print(i + "\n");
		}
	}

	/**
	 * 堆下移算法
	 * 
	 * @param heap
	 *            原始堆数据
	 * @param index
	 *            要挪动的堆位置下标
	 * @param N
	 *            堆的长度
	 */
	public static void shiftDown(int[] heap, int i, int N) {
		int t;
		if (2 * i > N) {
			return;
		}

		while (2 * i <= N) {
			i *= 2;

			if (i + 1 <= N && heap[i + 1] > heap[i]) {
				i++;
			}

			if (heap[i / 2] < heap[i]) {
				t = heap[i / 2];
				heap[i / 2] = heap[i];
				heap[i] = t;
			} else {
				break;
			}
		}
	}

	/**
	 * 堆排序,找出其中的最大元素
	 * 
	 * @param heap
	 *            原始堆数据
	 * @param N
	 *            堆 的大小
	 */
	public static void makeHeap(int[] heap, int N) {
		for (int i = N / 2; i >= 1; i--) {
			shiftDown(heap, i, N);
		}

		// 排序结束
		System.out.print("当前堆的最大的元素为：" + heap[1] + "\n");
	}

	/**
	 * 堆排序算法
	 * 
	 * @param heap
	 *            原始堆数据
	 * @param N
	 *            堆大小的总数
	 */
	public static void heapSort(int[] heap, int N) {
		makeHeap(heap, N);

		int t;
		int len = N;
		for (int i = N; i > 1; i--) {
			t = heap[i];
			heap[i] = heap[1];
			heap[1] = t;

			len--;
			shiftDown(heap, 1, len);
		}
	}
}
