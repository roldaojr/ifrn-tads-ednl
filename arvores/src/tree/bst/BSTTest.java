package tree.bst;

public class BSTTest {

	public static void main(String[] args) {
		BST arvore1 = new BST(0);
		arvore1.insert(1);
		arvore1.insert(2);
		arvore1.insert(3);
		arvore1.insert(4);
		arvore1.insert(5);
		arvore1.print();
		System.out.println(arvore1.depth());
		System.out.println("");

		BalancedBST arvore2 = new BalancedBST(0);
		arvore2.insert(1);
		arvore2.insert(2);
		arvore2.insert(3);
		arvore2.insert(4);
		arvore2.insert(5);
		arvore2.print();
		System.out.println("");
	}

}
