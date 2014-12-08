package tree.binary;

public class TreeTest {

	public static void main(String[] args) {
		BinaryNode<Integer> arvore = new BinaryNode<>(1);

		BinaryNode<Integer> subArvore1 = new BinaryNode<>(2);
		subArvore1.setLeft(new BinaryNode<>(4));
		subArvore1.setRight(new BinaryNode<>(5));
		arvore.setLeft(subArvore1);

		BinaryNode<Integer> subArvore2 = new BinaryNode<>(3);
		subArvore2.setLeft(new BinaryNode<>(6));
		subArvore2.setRight(new BinaryNode<>(7));
		arvore.setRight(subArvore2);

	}

}
