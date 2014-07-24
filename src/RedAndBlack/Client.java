package RedAndBlack;

/**
 * 递归算法解决红黑瓷砖问题
 * 
 * @author Administrator
 * 
 */
public class Client {
	/**
	 * 经过的黑瓷砖的总数
	 */
	public static int count = 0;

	// ‘.’代表红瓷砖，‘#’代表黑瓷砖
	public static char[][] grid = new char[][] {
			{ '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#' },
			{ '#', '.', '.', '#', '.', '.', '#', '.', '.', '#', '.', '.', '#' },
			{ '#', '.', '.', '#', '.', '.', '#', '.', '.', '#', '.', '.', '#' },
			{ '#', '.', '.', '#', '.', '.', '#', '.', '.', '#', '#', '.', '#' },
			{ '#', '.', '.', '#', '.', '.', '#', '.', '.', '#', '#', '.', '#' },
			{ '#', '.', '.', '#', '.', '.', '#', '.', '.', '#', '.', '.', '#' },
			{ '#', '.', '.', '#', '.', '.', '#', '.', '.', '#', '.', '.', '#' },
			{ '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#' } };

	public static void main(String[] args) {

		int startX = 10;
		int startY = 5;

		find(startX, startY);

		System.out.print("路过的黑瓷砖的总数为：" + count);
	}

	/**
	 * 行坐标
	 * 
	 * @param i
	 *            列坐标
	 * @param j
	 */
	public static void find(int i, int j) {
		count++;
		grid[i][j] = '.';

		if (grid[i - 1][j] == '.') {
			find(i - 1, j);
		}

		if (grid[i][j - 1] == '.') {
			find(i, j - 1);
		}

		if (grid[i + 1][j] == '.') {
			find(i + 1, j);
		}

		if (grid[i][j + 1] == '.') {
			find(i, j + 1);
		}
	}

}
