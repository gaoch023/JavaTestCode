package DumpSort;

/**
 * �������㷨
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
		
		//���ö������㷨
		heapSort(A, A.length -1);
		
		for (int i : A) {
			System.out.print(i + "\n");
		}
	}

	/**
	 * �������㷨
	 * 
	 * @param heap
	 *            ԭʼ������
	 * @param index
	 *            ҪŲ���Ķ�λ���±�
	 * @param N
	 *            �ѵĳ���
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
	 * ������,�ҳ����е����Ԫ��
	 * 
	 * @param heap
	 *            ԭʼ������
	 * @param N
	 *            �� �Ĵ�С
	 */
	public static void makeHeap(int[] heap, int N) {
		for (int i = N / 2; i >= 1; i--) {
			shiftDown(heap, i, N);
		}

		// �������
		System.out.print("��ǰ�ѵ�����Ԫ��Ϊ��" + heap[1] + "\n");
	}

	/**
	 * �������㷨
	 * 
	 * @param heap
	 *            ԭʼ������
	 * @param N
	 *            �Ѵ�С������
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
