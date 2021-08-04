package projects;

import java.util.ArrayList;
import java.util.Scanner;

class pair1<T, V, S> {
	T min;
	V max;
	S isBST;
}

class TreeNode<T> {
	T data;
	ArrayList<TreeNode<T>> children;

	public TreeNode() {
		children = new ArrayList<TreeNode<T>>();
	}

	public void setData(T data) {
		this.data = data;
	}

	public T getData() {
		return this.data;
	}

	// We created TreeNode class as generic but the utility functions are of
	// "Integer" type, because for different type of inputs
	// we need different types of scanner input functions hence for simplicity we
	// made it Integer...
	public int countNodesGenericTree(TreeNode<Integer> root) {
		int ans = 0;
		for (int i = 0; i < root.children.size(); i++) {
			ans += countNodesGenericTree(root.children.get(i));
		}
		return ans + 1;
	}

	public int getHeightGenericTree(TreeNode<Integer> root) {
		// according to edges
		// if according nodes height is required then initialize max with 0...
		int ans = 0;
		int max = 0;
		for (int i = 0; i < root.children.size(); i++) {
			ans = getHeightGenericTree(root.children.get(i));
			if (ans > max) {
				max = ans;
			}
		}
		return 1 + max;
	}

	public TreeNode<Integer> takeInputGenericTree() {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter root data: ");
		int rootData = input.nextInt();
		QueueUsingLinkedList<TreeNode<Integer>> pendingNodes = new QueueUsingLinkedList<TreeNode<Integer>>();
		TreeNode<Integer> root = null;
		root = new TreeNode<Integer>();
		root.setData(rootData);
		pendingNodes.enqueue(root);
		while (!pendingNodes.isEmpty()) {
			TreeNode<Integer> frontNode;
			try {
				frontNode = pendingNodes.dequeue();
				System.out.println("Enter number of childs of " + frontNode.data);
				int childCount = input.nextInt();
				for (int i = 0; i < childCount; i++) {
					System.out.println("Enter " + (i + 1) + "th child data");
					int childData = input.nextInt();
					TreeNode<Integer> child = new TreeNode<Integer>();
					child.setData(childData);
					frontNode.children.add(child);
					pendingNodes.enqueue(child);
				}
			} catch (EmptyQueueException e) {
				// TODO Auto-generated catch block
				// we will never reach here...
			}
		}
		return root;
	}

	public void printGeneriTree(TreeNode<Integer> root) {
		if (root == null) {
			return;
		}
		QueueUsingLinkedList<TreeNode<Integer>> pendingNodes = new QueueUsingLinkedList<TreeNode<Integer>>();
		pendingNodes.enqueue(root);
		while (!pendingNodes.isEmpty()) {
			TreeNode<Integer> frontNode;
			try {
				frontNode = pendingNodes.dequeue();
				String s = frontNode.data + ":";
				for (int i = 0; i < frontNode.children.size(); i++) {
					TreeNode<Integer> temp = frontNode.children.get(i);
					if (i == frontNode.children.size() - 1) {
						s = s + temp.data;
					} else {
						s = s + temp.data + ",";
					}
					pendingNodes.enqueue(temp);
				}
				System.out.println(s);
			} catch (EmptyQueueException e) {
				// TODO Auto-generated catch block
				// we will never reach here...
			}
		}
	}
}

class BinaryTreeNode<T> {
	T data;
	BinaryTreeNode<T> left;
	BinaryTreeNode<T> right;

	public BinaryTreeNode() {
		this.left = null;
		this.right = null;
	}

	public void setData(T data) {
		this.data = data;
	}

	public T getData() {
		return this.data;
	}

	public int countNodesBinaryTree(BinaryTreeNode<Integer> root) {
		if (root == null) {
			return 0;
		}
		int left = countNodesBinaryTree(root.left);
		int right = countNodesBinaryTree(root.right);
		return left + right + 1;
	}

	public int getHeightBinaryTree(BinaryTreeNode<Integer> root) {
		if (root == null) {
			return 0;
		}
		int leftHeight = getHeightBinaryTree(root.left);
		int rightHeight = getHeightBinaryTree(root.right);
		return 1 + Math.max(leftHeight, rightHeight);
	}

	public BinaryTreeNode<Integer> takeInputBinaryTree() {
		Scanner input = new Scanner(System.in);
		// data value of TreeNode = "-1" is a break point...
		System.out.println("Enter Root data: ");
		int rootData = input.nextInt();
		BinaryTreeNode<Integer> root = null;
		if (rootData == -1) {
			return null;
		}
		root = new BinaryTreeNode<Integer>();
		root.setData(rootData);
		QueueUsingLinkedList<BinaryTreeNode<Integer>> pendingNodes = new QueueUsingLinkedList<BinaryTreeNode<Integer>>();
		if (root != null) {
			pendingNodes.enqueue(root);
		}
		while (!pendingNodes.isEmpty()) {
			try {
				BinaryTreeNode<Integer> frontNode = pendingNodes.dequeue();
				System.out.println("Enter left child of " + frontNode.data);
				int leftChild = input.nextInt();
				if (leftChild != -1) {
					BinaryTreeNode<Integer> leftNode = new BinaryTreeNode<Integer>();
					leftNode.setData(leftChild);
					pendingNodes.enqueue(leftNode);
					frontNode.left = leftNode;
				}
				System.out.println("Enter right child of " + frontNode.data);
				int rightChild = input.nextInt();
				if (rightChild != -1) {
					BinaryTreeNode<Integer> rightNode = new BinaryTreeNode<Integer>();
					rightNode.setData(rightChild);
					pendingNodes.enqueue(rightNode);
					frontNode.right = rightNode;
				}
			} catch (EmptyQueueException e) {
				// we will never reach here...
			}
		}
		return root;
	}

	private pair1<Integer, Integer, Boolean> isBSTHelper(BinaryTreeNode<Integer> root) {
		if (root == null) {
			pair1<Integer, Integer, Boolean> output = new pair1<Integer, Integer, Boolean>();
			output.min = Integer.MAX_VALUE;
			output.max = Integer.MIN_VALUE;
			output.isBST = true;
			return output;
		}

		pair1<Integer, Integer, Boolean> leftAns = isBSTHelper(root.left);
		pair1<Integer, Integer, Boolean> rightAns = isBSTHelper(root.right);

		pair1<Integer, Integer, Boolean> output = new pair1<Integer, Integer, Boolean>();

		if ((leftAns.isBST && rightAns.isBST) && (leftAns.max < root.data && rightAns.min >= root.data)) {
			if (leftAns != null && rightAns != null) {
				int min = Math.min(root.data, Math.min(leftAns.min, rightAns.min));
				int max = Math.max(root.data, Math.max(leftAns.max, rightAns.max));

				output.isBST = true;
				output.min = min;
				output.max = max;
			}
		} else {
			if (leftAns != null && rightAns != null) {
				int min = Math.min(root.data, Math.min(leftAns.min, rightAns.min));
				int max = Math.max(root.data, Math.max(leftAns.max, rightAns.max));
				output.isBST = false;
				output.min = min;
				output.max = max;
			}
		}
		return output;
	}

	public boolean isBST(BinaryTreeNode<Integer> root) {
		if (root == null) {
			return true;
		}
		pair1<Integer, Integer, Boolean> ans = isBSTHelper(root);
		return ans.isBST;
	}

	public void printBinayTree(BinaryTreeNode<Integer> root) {
		if (root == null) {
			return;
		}
		QueueUsingLinkedList<BinaryTreeNode<Integer>> pendingNodes = new QueueUsingLinkedList<BinaryTreeNode<Integer>>();
		pendingNodes.enqueue(root);
		while (!pendingNodes.isEmpty()) {
			try {
				BinaryTreeNode<Integer> frontNode = pendingNodes.dequeue();
				String s = frontNode.data + ":";

				if (frontNode.left != null) {
					s = s + "L:" + frontNode.left.data + ",";
					pendingNodes.enqueue(frontNode.left);
				}
				if (frontNode.right != null) {
					s = s + "R:" + frontNode.right.data;
					pendingNodes.enqueue(frontNode.right);
				}
				System.out.println(s);
			} catch (EmptyQueueException e) {
				// We will never reach here...
			}
		}
	}

}

public class LibraryForTrees {

	public static void main(String[] args) {

		BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>();
		BinaryTreeNode<Integer> Root = root.takeInputBinaryTree();
		System.out.println("height = " + root.getHeightBinaryTree(Root));
		System.out.println("isBST = " + root.isBST(Root));
		System.out.println("Number of Nodes = " + root.countNodesBinaryTree(Root));
		root.printBinayTree(Root);

	}

}