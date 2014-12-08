package tree.bst;

public class BalancedBST extends BST {
	protected int balance = 0;

	public BalancedBST() {
	}
	public BalancedBST(int aContent) {
		key = aContent;
	}

	public void insert(int newKey) {
		BalancedBST newNode = new BalancedBST(newKey);
		insert(newNode);
	}
	
	public void insert(BalancedBST newNode) {
		super.insert(newNode);
		this.rebalance();
	}
	
	public void remove() {
		super.remove();
		this.rebalance();
		if(this.parent != null) {
			BalancedBST parent = (BalancedBST) this.parent;
			parent.rebalance();
		}
	}
	
	public int getBalance(){
		return balance;
	}
	
	public void rebalance() {
		int dleft = left != null ? left.depth() : 0;
		int dright = right != null ? right.depth() : 0;
		balance = dleft-dright;
		
		if(balance > 1) {
			BalancedBST child = (BalancedBST) (balance > 1 ? left : right);
			if(child.balance < 0) {
				rotate_left_right();
			} else {
				rotate_right();
			}
		} else if (balance < -1) {
			BalancedBST child = (BalancedBST) (balance > 1 ? left : right);
			if(child.balance > 1) {
				rotate_right_left();
			} else {
				rotate_left();
			}			
		}
	}
	
	protected void rotate_right() {
		// node é a raiz atual
		BST node = this.clone();
		
		// a raiz passa ser o nó a esquerda
		BST root = node.left;
		root.parent = node.parent;

		// colocar a direita do filho a esquerda na esquerda de node
		BST old_lr = node.left.right;
		node.left = old_lr;
		if(old_lr != null) old_lr.parent = node;

		// colocar node a direita na nova raiz
		root.right = node;
		node.parent = root;
		
		copyFrom(root);
	}
	protected void rotate_left() {
		// node é a raiz atual
		BST node = this.clone();
		
		// a raiz passa ser o nó a direita
		BST root = node.right;
		root.parent = node.parent;

		// colocar a esquerda do filho a direita na direita de node
		BST old_rl = node.right.left;
		node.right = old_rl;
		if(old_rl != null) old_rl.parent = node;

		// colocar node a esquerda na nova raiz
		root.left = node;
		node.parent = root;
		
		copyFrom(root);
	}
	protected void rotate_left_right() {
		BalancedBST node = (BalancedBST) left;
		node.rotate_left();
		rotate_right();
	}
	protected void rotate_right_left() {
		BalancedBST node = (BalancedBST) right;
		node.rotate_right();
		rotate_left();
	}
	
}
