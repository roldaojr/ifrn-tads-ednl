package tree.generic;

import java.util.ArrayList;
import java.util.List;

public class Node {
	private Node parent;
	private Object content;
	private List<Node> children;
	public Node() {
		children = new ArrayList<Node>();
	}
	public Node(Object aContent) {
		children = new ArrayList<Node>();
		content = aContent;
	}
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public Object getContent() {
		return content;
	}
	public void setContent(Object content) {
		this.content = content;
	}
	public List<Node> getChildren() {
		return children;
	}
}
