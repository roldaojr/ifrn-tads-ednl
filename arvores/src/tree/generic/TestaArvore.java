package tree.generic;

public class TestaArvore {

	public static void main(String[] args) {
		Node arvore = new Node(1);
		arvore.getChildren().add(new Node(2));
		arvore.getChildren().add(new Node(3));

		Node subArvore1 = new Node(4);
		subArvore1.getChildren().add(new Node(5));
		subArvore1.getChildren().add(new Node(6));
		arvore.getChildren().add(subArvore1);
	}

}
