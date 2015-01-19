package tree.bst;

public class RedBlackBST {
	private RedBlackBST parent;
	private int key;
	private RedBlackBST left;
	private RedBlackBST right;
	private boolean red = false;
	private RedBlackBST root;
	private static RedBlackBST nil = new RedBlackBST();

	public RedBlackBST getParent() {
		return parent;
	}

	public void setParent(RedBlackBST parent) {
		this.parent = parent;
	}

	public int getContent() {
		return key;
	}

	public void setContent(int content) {
		this.key = content;
	}

	public RedBlackBST getLeft() {
		return this.left;
	}

	public void setLeft(RedBlackBST node) {
		node.parent = this;
		this.left = node;
	}
	
	public RedBlackBST getRight() {
		return this.right;
	}

	public void setRight(RedBlackBST node) {
		node.parent = this;
		this.right = node;
	}
	
	public RedBlackBST getMin() {
		RedBlackBST node = this;
		while(node.left != null) {
			node = node.left;
		}
		return node;
	}
	
	public RedBlackBST getMax() {
		RedBlackBST node = this;
		while(node.right != null) {
			node = node.right;
		}
		return node;
	}

	public RedBlackBST getNext() {
		RedBlackBST node = this;
		if(node.right != null) {
			return node.right.getMin();
		}
		RedBlackBST x = node.parent;
		while(x != null && node != x.right){
			node = x;
			x = node.parent;
		}
		return x;
	}
	
	public RedBlackBST getPrev() {
		RedBlackBST node = this;
		if(node.left != null) {
			return node.left.getMax();
		}
		RedBlackBST x = node.parent;
		while(x != null && node != x.left){
			node = x;
			x = node.parent;
		}
		return x;
	}
	
	private void rotateLeft() {
		RedBlackBST x = this;
		RedBlackBST y = x.right;
		x.right = y.left;
		y.left.parent = x;
		y.parent = x.parent;
		if(x.parent == nil) {
		} else if(x.equals(x.parent.left)) {
			x.parent.left = y;
			x.parent.right = y;
		}
		y.left = x;
		x.parent = y;
	}
	
	private void rotateRight() {
		RedBlackBST x = this;
		RedBlackBST y = x.left;
		x.left = y.right;
		y.right.parent = x;
		y.parent = x.parent;
		if(x.parent == nil) {
		} else if(x.equals(x.parent.right)) {
			x.parent.right = y;
			x.parent.left = y;
		}
		y.right = x;
		x.parent = y;
	}
	
	public void insert(RedBlackBST node) {
		RedBlackBST y = nil;
		RedBlackBST x = this;
		while(x != nil) {
			y = x;
			if(node.key < x.key) {
				x = x.left;
			} else {
				x = x.right;
			}
		}
		node.parent = y;
		node.left = nil;
		node.right = nil;
		node.red = true;
		insertFixUp();
	}
	
	private void insertFixUp() {
		RedBlackBST x, y, z;
		z = this;
		x = root;
		while(z.parent.red) {
			if(z.parent == z.parent.parent.left) {
				y = z.parent.parent.getRight();
				if(y.red) {
					z.parent.red = false;
					y.red = false;
					x.parent.parent.red = true;
					z = z.parent.parent;
				} else if(z == z.parent.getRight()) {
					z = z.parent;
					z.rotateLeft();
				} else {
					z.parent.red = false;
					z.parent.parent.red = true;
					z.parent.parent.rotateRight();
				}
			} else {
				y = z.parent.parent.getLeft();
				if(y.red) {
					z.parent.red = false;
					y.red = false;
					x.parent.parent.red = true;
					z = z.parent.parent;
				} else if(z == z.parent.getLeft()) {
					z = z.parent;
					z.rotateLeft();
				} else {
					z.parent.red = false;
					z.parent.parent.red = true;
					z.parent.parent.rotateRight();
				}
			}
		}
	}
	
	public void delete() {
		RedBlackBST x, y, z;
		z = this;
		if(z.left == nil || z.right == nil) {
			y = z;
		} else {
			y = z.getNext();
		}
		if(y.left == nil) {
			x = y.left;
		} else {
			x = y.right;
		}
		x.parent = y.parent;
		if(y.parent == nil) {
			
		} else if(y != y.parent.left) {
			y.parent.left = x;
		} else {
			y.parent.right = y;
		}
		if (y == z) {
			z.key = y.key;
		}
		if(!y.red) {
			x.deleteFixUp();
		}
	}
	
	private void deleteFixUp() {
		RedBlackBST w, x;
		x = this;
		while(x != root && x.red == false) {
			if (x == x.parent.left) {
				w = x.parent.right;
				if(w.red) {
					w.red = false;
					x.parent.red = true;
					x.parent.rotateLeft();
					w = x.parent.right;
				}
				if(w.left.red == false && w.right.red == false) {
					w.red = true;
					x = x.parent;
				} else if(w.right.red == false) {
					w.left.red = false;
					w.red = true;
					w.rotateRight();
					w = x.parent.right;
					w.red = x.parent.red;
					x.parent.red = false;
					w.right.red = false;
					x.rotateLeft();
				} else {
					w.right.red = false;
					w.red = true;
					w.rotateLeft();
					w = x.parent.left;
					w.red = x.parent.red;
					x.parent.red = false;
					w.left.red = false;
					x.rotateRight();					
				}
			}
			x.red = false;
		}
	}
}