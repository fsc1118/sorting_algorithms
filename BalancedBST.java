package sorting_algorithms;

import java.util.Random;

public class BalancedBST {
	
	public Node root;
	
	public BalancedBST() {
		root = null;
	}
	
	class Node {
		int val;
		Node left;
		Node right;
		Node parent;
		Node(int val) {
			this.val = val;
			left = null;
			right = null;
		}
	}
	
	private static int getBalanced(Node node) {
		return depth(node.left) - depth(node.right);
	}
	
	private enum leftOrRight {
		LEFT,
		RIGHT
	}
	private void insertion(Node root, int val) {
		if (val < root.val && root.left == null) {
			root.left = new Node(val);
			root.left.parent = root;
		} else if (val < root.val) {
			insertion(root.left, val);
		} else if (val >= root.val && root.right == null) {
			root.right = new Node(val);
			root.right.parent = root;
		} else {
			insertion(root.right, val);
		}
		int balance = getBalanced(root);
		leftOrRight firstLetter;
		leftOrRight secondLetter;
		if (balance > 1) {
			firstLetter = leftOrRight.LEFT;
			int secondBalance = getBalanced(root.left);
			if (secondBalance > 0) {
				secondLetter = leftOrRight.LEFT;
			} else {
				secondLetter = leftOrRight.RIGHT;
			}
		} else if (balance < -1) {
			firstLetter = leftOrRight.RIGHT;
			int secondBalance = getBalanced(root.right);
			if (secondBalance > 0) {
				secondLetter = leftOrRight.LEFT;
			} else {
				secondLetter = leftOrRight.RIGHT;
			}
		} else {
			return;
		}
		Node parent = root.parent;
		if (firstLetter == leftOrRight.LEFT && secondLetter == leftOrRight.LEFT) {
			Node newNode = rightTransformation(root);
			if (parent == null) {
				this.root = newNode;
				newNode.parent = null;
			} else {
				if (parent.val > newNode.val) {
					parent.left = newNode;
				} else {
					parent.right = newNode;
				}
				newNode.parent = parent;
			}
		} else if (firstLetter == leftOrRight.RIGHT && secondLetter == leftOrRight.RIGHT) {
			Node newNode = leftTransformation(root);
			if (parent == null) {
				this.root = newNode;
				newNode.parent = null;
			} else {
				if (parent.val > newNode.val) {
					parent.left = newNode;
				} else {
					parent.right = newNode;
				}
				newNode.parent = parent;
			}
		} else if (firstLetter == leftOrRight.LEFT && secondLetter == leftOrRight.RIGHT) {
			Node temp = leftTransformation(root.left);
			root.left = temp;
			Node newNode = rightTransformation(root);
			if (parent == null) {
				this.root = newNode;
				newNode.parent = null;
			} else {
				if (parent.val > newNode.val) {
					parent.left = newNode;
				} else {
					parent.right = newNode;
				}
				newNode.parent = parent;
			}
		} else if (firstLetter == leftOrRight.RIGHT && secondLetter == leftOrRight.LEFT) {
			Node temp = rightTransformation(root.right);
			root.right = temp;
			Node newNode = leftTransformation(root);
			if (parent == null) {
				this.root = newNode;
				newNode.parent = null;
			} else {
				if (parent.val > newNode.val) {
					parent.left = newNode;
				} else {
					parent.right = newNode;
				}
				newNode.parent = parent;
			}
		}
	}
	
	public void insert(int newVal) {
		if (root == null) {
			root = new Node(newVal);
			root.parent = null;
			return;
		}
		insertion(root, newVal);
	}
	
	private static Node leftTransformation(Node y) {
		Node x = y.right;
		y.right = x.left;
		if (x.left != null) {
			x.left.parent = y;
		}
		x.left = y;
		y.parent = x;
		return x;
	}
	
	private static Node rightTransformation(Node y) {
		Node x = y.left;
		y.left = x.right;
		if (x.right != null) {
			x.right.parent = y;
		}
		x.right = y;
		y.parent = x;
		return x;
	}
	
	public static void midIteration(Node root) {
		if (root == null) {
			return;
		}
		midIteration(root.left);
		System.out.print(root.val);
		System.out.print(",");
		midIteration(root.right);
	}
	
	private static int depth(Node root) {
		if (root == null) {
			return 0;
		} 
		return Math.max(depth(root.left), depth(root.right)) + 1;
	}
	
	public boolean isBalanced(Node root) {
		if (root == null) {
			return true;
		}
		if (Math.abs(depth(root.left) - depth(root.right)) > 1) {
			return false;
		}
		return isBalanced(root.left) && isBalanced(root.right);
	}
	
	public boolean isBST(Node root) {
		if (root == null) {
			return true;
		}
		int val1 = root.val;
		if (root.left != null && root.left.val > val1) {
			return false;
		}
		if (root.right != null && root.right.val < val1) {
			return false;
		}
		return isBST(root.left) && isBST(root.right);
	}
	public static void main(String[] args) {
		BalancedBST bbst = new BalancedBST();
		Random rand = new Random();
		for (int i = 0; i < 10000; i++) {
			//System.out.println(depth(bbst.root));
			int k = rand.nextInt();
			//System.out.println(k);
			bbst.insert(k);
			//System.out.println();
		}
		System.out.println(bbst.isBST(bbst.root));
		System.out.println(bbst.isBalanced(bbst.root));
	}
}
