package BooleanExpressions;

/**
 * ģ���ջʵ����׺���ʽת��Ϊ��׺���ʽ��������
 * 
 * @author Administrator
 * 
 */
public class Client {
	/**
	 * ��ջ����󳤶�
	 */
	public static int MAX_STRLEN = 999;

	public static void main(String[] args){
		String infix = "(V|V)&F&(F|V)";
		char[] suffix = new char[MAX_STRLEN];
		
		//����׺���ʽת��Ϊ��׺���ʽ
		infixToSuffix(infix, suffix);
		
		//���ú�׺���ʽ������
		boolean result = compute(suffix);
		
		if(result){
			System.out.println("�ñ��ʽ�����ս��Ϊ��false");
		}else{
			System.out.println("�ñ��ʽ�����ս��Ϊ��false");
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
	 * ���ݺ�׺���ʽ��������
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

		// ���ص�ջ���Ĳ������ͼ�Ϊ���
		return stk[top];
	}

}
