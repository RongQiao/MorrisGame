import java.util.List;


public class MorrisTree {
	private static MorrisTree instance = null;
	private Tree<MorrisNodeData> tree;
	
	private MorrisTree(String mPositionStr) {
		MorrisNodeData rootData = new MorrisNodeData();
		rootData.setBoard(new MorrisBoard(mPositionStr));
		tree = new Tree<MorrisNodeData> (rootData);
	}
	
	//input posStr, means there is new position, so always create a new instance
	public static MorrisTree getInstance(String posStr) {
		instance = new MorrisTree(posStr);
		return instance;
	}

	//no imput posStr, if no instance exists, create with empty position, otherwise use the exist one 
	public static MorrisTree getInstance() {
		if (instance == null) {
			instance = new MorrisTree("xxxxxxxxxxxxxxxxxxxxxxx");
		}
		return instance;
	}
	
	public MorrisBoard getBoard() {
		MorrisNodeData data = getRootData();
		return data.getBoard();
	}

	public Estimation getEstimation() {
		MorrisNodeData data = getRootData();
		return data.getEstim();
	}
	
	private MorrisNodeData getRootData() {
		Node<MorrisNodeData> root = tree.getRoot();
		MorrisNodeData data = root.getData();
		return data;
	}

	public int totalDepth() {
		return tree.countDepth();
	}

	public int countLeaf() {
		List<Node<MorrisNodeData>> leaves = tree.getLeaves();
		return leaves.size();
	}

	public Node<MorrisNodeData> getRoot() {
		return tree.getRoot();
	}



}
