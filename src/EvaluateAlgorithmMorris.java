
public abstract class EvaluateAlgorithmMorris {
	public static final int INFINITE_NEG = -10000;
	public static final int INFINITE_POS = 10000;
	private static int cntEvaluated = 0;
	
	public static boolean isMax(Node<MorrisNodeData> node) {
		int dep = node.getDepth();
		if ((dep % 2) == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean isMin(Node<MorrisNodeData> node) {
		return (!isMax(node));
	}
		
	public static void clearEvaluateCnt() {
		cntEvaluated = 0;
	}
	
	public static int getEvaluateCnt() {
		return cntEvaluated;
	}
	
	public static void increaseEvaluateCnt(int number) {
		cntEvaluated += number;
	}
	
	//if parent.estimation exist, get it, otherwise create a new one with the given value
	public static Estimation getOrCreateEstimation(Node<MorrisNodeData> node, int createValue) {
		MorrisNodeData nodeData = node.getData();		
		Estimation est = nodeData.getEstim();
		if (est == null) {
			est = new IntegerEstimation(createValue);
			nodeData.setEstim(est);
		}
		return est;
	}


}
