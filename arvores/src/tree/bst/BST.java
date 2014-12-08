package tree.bst;

public class BST {
	protected BST parent;
	protected int key;
	protected BST left;
	protected BST right;

	public BST() {
	}
	public BST(int aContent) {
		key = aContent;
	}
	public BST getParent() {
		return parent;
	}
	public void setParent(BST parent) {
		this.parent = parent;
	}
	public int getContent() {
		return key;
	}
	public void setContent(int content) {
		this.key = content;
	}
	public BST getLeft() {
		return left;
	}
	public BST getRight() {
		return right;
	}

	public BST find(int content) {
		BST node = this;
		while(node != null && node.key != content) {
			if(content < node.key) {
				node = node.left;
			} else {
				node = node.right;
			}
		}
		return node;
	}

	public BST getMin() {
		BST node = this;
		while(node.left != null) {
			node = node.left;
		}
		return node;
	}
	
	public BST getMax() {
		BST node = this;
		while(node.right != null) {
			node = node.right;
		}
		return node;
	}

	public BST getNext() {
		BST node = this;
		if(node.right != null) {
			return node.right.getMin();
		}
		BST x = node.parent;
		while(x != null && node != x.right){
			node = x;
			x = node.parent;
		}
		return x;
	}
	
	public BST getPrev() {
		BST node = this;
		if(node.left != null) {
			return node.left.getMax();
		}
		BST x = node.parent;
		while(x != null && node != x.left){
			node = x;
			x = node.parent;
		}
		return x;
	}

	public void insert(int newKey) {
		BST newNode = new BST(newKey);
		insert(newNode);
	}
	
	public void insert(BST newNode) {
		BST y = null;
		BST x = this;
		while(x != null) {
			y = x;
			if(newNode.key < x.key){
				x = x.left;
			} else {
				x = x.right;
			}
		}
		newNode.parent = y;
		if(newNode.key < y.key) {
			y.left = newNode;
		} else {
			y.right = newNode;
		}
	}
	
	public void remove() {
		if(this.left != null && this.right != null) {
			BST y = this.right.getMin();
		}
		if(this.left != null) {
			if (this.parent.left.key == this.key) {
				this.parent.left = this.left;
			} else {
				this.parent.right = this.left;
			}
		} else if(this.right != null) {
			if (this.parent.right.key == this.key) {
				this.parent.left = this.right;
			} else {
				this.parent.right = this.right;
			}
		} else {
			if (this.parent.left.key == this.key) {
				this.parent.left = null;
			} else {
				this.parent.right = null;
			}
		}
	}

	public int depth() {
		int dleft = left != null ? left.depth() : 0;
		int dright = right != null ? right.depth() : 0;
		int x = dleft > dright ? dleft+1 : dright+1;
	    return x;
	}
	
	public void copyFrom(BST node) {
		parent = node.parent;
		key = node.key;
		left = node.left;
		right = node.right;
	}
	
	public BST clone() {
		BST clone = new BST();
		clone.parent = parent;
		clone.key = key;
		clone.left = left;
		clone.right = right;
		return clone;
	}
	
	public void print() {
		String leftKey = left != null ? String.valueOf(left.key) : "";
		String rightKey = right != null ? String.valueOf(right.key) : "";
		System.out.println("N: " + key + " L: " + leftKey + " R: " + rightKey);
		if (left != null) left.print();
		if (right != null) right.print();
	}
	
	public void print_array() {
		if (left != null) left.print();
		System.out.print(key + " ");
		if (right != null) right.print();
	}
}
