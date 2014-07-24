package BinaryTree;

/**
 * 树的节点
 * @author Administrator
 *
 */
public class TreeNode {
	/**
	 * 节点的字符标记
	 */
	private char ch;
	
	/**
	 * 左子树
	 */
	private TreeNode leftChild;
	
	/**
	 * 右子树
	 */
	private TreeNode rightChild;
	
	public TreeNode getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(TreeNode leftChild) {
		this.leftChild = leftChild;
	}

	public TreeNode getRightChild() {
		return rightChild;
	}

	public void setRightChild(TreeNode rightChild) {
		this.rightChild = rightChild;
	}

	public TreeNode(char ch) {
		super();
		this.ch = ch;
	}

	public void setChildren(TreeNode leftNode, TreeNode rightNode){
		this.leftChild = leftNode;
		this.rightChild = rightNode;
	}
	
	public void outPut(){
		System.out.println(ch);
	}
}
