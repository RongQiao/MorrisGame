
final public class EvaluateAlgorithmMiniMax extends EvaluateAlgorithmMorris{

	public static void applyAlgorithm(Node<MorrisNodeData> root) {
		Node<MorrisNodeData> crtNode = root;
		if (!crtNode.hasChildren()) {
			evaluateLeaf(crtNode);
		}
		else {
			Node<MorrisNodeData> child = crtNode.getFirstChild();
			while (child != null) {
				applyAlgorithm(child);				
				child = child.next();
			}
			evaluateInterm(crtNode);
		}
	}
	
	private static void evaluateNode(Node<MorrisNodeData> node) {
		Estimation estim = node.getData().getEstim();
		if ((estim != null) && node.hasParent()) {//it's valid to calculate the parent.estimation
			Node<MorrisNodeData> parent = node.getParent();
			
			if (isMax(parent)) {
				//get the maximum estimation of all children as parent.estimation
				calculateMax(node, parent);
			}
			else {
				//get the minimum estimation of all children as parent.estimation
				calculateMini(node, parent);
			}
		}
	}
	
	private static void calculateMini(Node<MorrisNodeData> child, Node<MorrisNodeData> parent) {
		Estimation prEst = getOrCreateEstimation(parent, INFINITE_POS);
		Estimation chEst = child.getData().getEstim();
		if (chEst.isLessThan(prEst)) {
			prEst.setValue(chEst.getValue());
		}
	}

	private static void calculateMax(Node<MorrisNodeData> child, Node<MorrisNodeData> parent) {
		Estimation prEst = getOrCreateEstimation(parent, INFINITE_NEG);
		Estimation chEst = child.getData().getEstim();
		if (chEst.isGreatThan(prEst)) {
			prEst.setValue(chEst.getValue());
		}
	}

	private static void evaluateInterm(Node<MorrisNodeData> node) {
		evaluateNode(node);
	}

	private static void evaluateLeaf(Node<MorrisNodeData> node) {
		evaluateNode(node);
		//evaluated leaf + 1
		EvaluateAlgorithmMorris.increaseEvaluateCnt(1);
	}


}
