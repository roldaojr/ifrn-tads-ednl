package tree.binary;

public class BinaryNode<T> {
	private BinaryNode<T> parent;
	private T content;
	private BinaryNode<T> left;
	private BinaryNode<T> right;

	public BinaryNode() {
	}
	public BinaryNode(T aContent) {
		content = aContent;
	}
	public BinaryNode<T> getParent() {
		return parent;
	}
	public void setParent(BinaryNode<T> parent) {
		this.parent = parent;
	}
	public Object getContent() {
		return content;
	}
	public void setContent(T content) {
		this.content = content;
	}
	public BinaryNode<T> getLeft() {
		return left;
	}
	public void setLeft(BinaryNode<T> left) {
		left.parent = this;
		this.left = left;
	}
	public BinaryNode<T> getRight() {
		return right;
	}
	public void setRight(BinaryNode<T> right) {
		right.parent = this;
		this.right = right;
	}
	
}
