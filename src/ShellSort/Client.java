package ShellSort;

/**
 * Ï£¶ûÅÅÐò
 * 
 * @author Administrator
 * 
 */
public class Client {
	public static void main(String[] args) {
		 int[]  array = new int[]{25,19,6,58,34,10,7,98,160,0};

         display(array, array.length);
         shellSort(array);
         display(array, array.length);
	}

	/**
	 * Ï£¶ûÅÅÐòËã·¨
	 * 
	 * @param array
	 */
	public static void shellSort(int[] array) {
		int d = array.length;
		int i = 0;
		int j = 0;
		int temp;

		while (d >= 1) {
			for (i = d; i < array.length; i++) {
				temp = array[i];
				j = i - d;
				while ((j > 0) && (temp < array[j])) {
					array[j + d] = array[j];
					j -= d;
				}
				array[j + d] = temp;
			}

			d /= 2;
		}
	}

	/**
	 * Êä³öÊý×é
	 * 
	 * @param array
	 * @param length
	 */
	public static void display(int array[], int length) {
		for (int i = 0; i < length; i++) {
			System.out.println(array[i] + ", ");
		}
		System.out.println("\n");
	}

}
