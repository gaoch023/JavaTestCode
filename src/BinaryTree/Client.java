package BinaryTree;

/**
 * 二叉树
 * 
 * @author Administrator
 * 
 */
public class Client {
	public static void main(String[] args) {
		TreeNode root = new TreeNode('0');
		TreeNode tree1 = new TreeNode('1');
		TreeNode tree2 = new TreeNode('2');
		TreeNode tree3 = new TreeNode('3');
		TreeNode tree4 = new TreeNode('4');
		TreeNode tree5 = new TreeNode('5');
		TreeNode tree6 = new TreeNode('6');
		TreeNode tree7 = new TreeNode('7');
		TreeNode tree8 = new TreeNode('8');

		root.setChildren(tree1, tree2);
		tree1.setChildren(tree3, tree4);
		tree2.setChildren(tree5, tree6);
		tree3.setChildren(tree7, tree8);

		System.out.println("前序遍历二叉树");
		binaryPreorder(root);

		System.out.println("后序遍历二叉树");
		binaryPostorder(root);

		System.out.println("中序遍历二叉树");
		binaryInOrder(root);

		System.out.println("欧拉路径遍历二叉树");
		eulerPathOrder(root);
	}

	/**
	 * 前序遍历二叉树,遍历顺序中-左-右
	 */
	public static void binaryPreorder(TreeNode v) {
		v.outPut();

		if (v.getLeftChild() != null) {
			binaryPreorder(v.getLeftChild());
			binaryPreorder(v.getRightChild());
		}
	}

	/**
	 * 后序遍历二叉树，遍历顺序左-右-中
	 * 
	 * @param v
	 */
	public static void binaryPostorder(TreeNode v) {
		if (v.getLeftChild() != null) {
			binaryPostorder(v.getLeftChild());
			binaryPostorder(v.getRightChild());
		}

		v.outPut();
	}

	/**
	 * 中序遍历，遍历顺序左-中-右
	 * 
	 * @param v
	 */
	public static void binaryInOrder(TreeNode v) {
		if (v.getLeftChild() != null) {
			binaryInOrder(v.getLeftChild());
		}

		v.outPut();

		if (v.getRightChild() != null) {
			binaryInOrder(v.getRightChild());
		}
	}

	/**
	 * 欧拉路径搜索
	 * 
	 * @param v
	 */
	public static void eulerPathOrder(TreeNode v) {
		v.outPut();

		if (v.getLeftChild() != null) {
			eulerPathOrder(v.getLeftChild());
		}

		if(v.getLeftChild() != null){
			v.outPut();
		}

		if (v.getRightChild() != null) {
			eulerPathOrder(v.getRightChild());
		}

		if(v.getRightChild() != null){
			v.outPut();
		}
	}

}
