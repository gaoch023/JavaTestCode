package BooleanExpressions;

/**
 * 模拟堆栈实现中缀表达式转化为后缀表达式，计算结果
 * 
 * @author Administrator
 * 
 */
public class Client {
	/**
	 * 堆栈的最大长度
	 */
	public static int MAX_STRLEN = 999;

	public static void main(String[] args){
		String infix = "(V|V)&F&(F|V)";
		char[] suffix = new char[MAX_STRLEN];
		
		//将中缀表达式转化为后缀表达式
		infixToSuffix(infix, suffix);
		
		//利用后缀表达式计算结果
		boolean result = compute(suffix);
		
		if(result){
			System.out.println("该表达式的最终结果为：false");
		}else{
			System.out.println("该表达式的最终结果为：false");
		}
		
	}

	public static void infixToSuffix(String infix, char[] suffix) {
		char[] stk = new char[MAX_STRLEN];

		char ch;
		int top = -1;
		int i = 0;
		int j = 0;

		for (i = 0; i < infix.length(); i++) {
			ch = infix.charAt(i);

			if (ch == '(') {
				top++;
				stk[top] = ch;
			} else if (ch == ')') {
				while (stk[top] != '(') {
					suffix[j] = stk[top];
					top--;
					j++;
				}
				top--;
			} else if (ch == '|') {
				while (top != -1 && stk[top] != '(') {
					suffix[j] = stk[top];
					top--;
					j++;
				}

				top++;
				stk[top] = ch;
			} else if (ch == '&') {
				while (stk[top] != '&' || stk[top] != '(') {
					suffix[j] = stk[top];
					top--;
					j++;
				}

				top++;
				stk[top] = ch;
			} else if (ch == '!') {
				top++;
				stk[top] = ch;
			} else {
				suffix[j] = ch;
				j++;

				while (top != -1 && stk[top] == '!') {
					suffix[j] = stk[top];
					top--;
					j++;
				}
			}
		}

		while (top != -1) {
			suffix[j] = stk[top];
			top--;
			j++;
		}
	}

	/**
	 * 根据后缀表达式计算出结果
	 * 
	 * @param suffix
	 */
	public static boolean compute(char[] suffix) {
		Boolean[] stk = new Boolean[MAX_STRLEN];
		int top = -1;
		int i = 0;
		char ch;

		for (i = 0; i < suffix.length; i++) {
			ch = suffix[i];

			if (ch == '|') {
				stk[top - 1] |= stk[top];
				top--;
			} else if (ch == '&') {
				stk[top - 1] &= stk[top];
				top--;
			} else if (ch == '!') {
				stk[top] = !stk[top];
			} else if (ch == 'V') {
				top++;
				stk[top] = true;
			} else if (ch == 'F') {
				top++;
				stk[top] = false;
			}

		}

		// 返回的栈顶的布尔类型即为结果
		return stk[top];
	}

}
