import java.util.ArrayList;
import java.util.List;


public class Tree<T> {
	private Node<T> root;
	
	Tree (T rootData) {
		root = new Node<T> (rootData);
	}
	
	public Tree() {
		root = new Node<T> ();
	}

	public Node<T> getRoot() {
		return root;
	}

	public int countDepth() {
		return (root.countSubDepth());
	}

	public List<Node<T>> getLeaves() {
		List<Node<T>> leaves = new ArrayList<Node<T>>();
		root.getLeaves(leaves);		
		return leaves;
	}
	
	
}
