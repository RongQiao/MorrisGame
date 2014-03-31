
public class EvaluateAlgorithmAlphaBeta extends EvaluateAlgorithmMorris{
	
	public static void applyAlgorithm(Node<MorrisNodeData> root) {
		Node<MorrisNodeData> crtNode = root;
		if (!crtNode.hasChildren()) {
			evaluateLeaf(crtNode);
		}
		else {
			Node<MorrisNodeData> child = crtNode.getFirstChild();
			while (child != null) {
				applyAlgorithm(child);		
				//check if there is conflict between crtNode and crtNode.parent, if it's true, the rest children don't need be evaluated
				if (isConflictWithParent(crtNode)) {
					break;
				}
				child = child.next();
			}
			evaluateInterm(crtNode);
		}
	}
	
	private static boolean isConflictWithParent(Node<MorrisNodeData> crtNode) {
		boolean ret = false;
		if (crtNode.hasParent()) {
			Node<MorrisNodeData> prNode = crtNode.getParent();
			Estimation prEst = prNode.getData().getEstim();
			Estimation crtEst = crtNode.getData().getEstim();
			if ((crtEst != null) && (prEst != null)) {
				ret = crtEst.isConflict(prEst);
			}
		}
		
		return ret;
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
			/*int optType = IntegerEstimation.LESS_EQUAL;
			if (!child.hasNext()) {
				optType = IntegerEstimation.EQUAL;
			}*/
			prEst.setOperType(IntegerEstimation.LESS_EQUAL);
		}
		if (!child.hasNext()) {
			prEst.setOperType(IntegerEstimation.EQUAL);
		}
	}

	private static void calculateMax(Node<MorrisNodeData> child, Node<MorrisNodeData> parent) {
		Estimation prEst = getOrCreateEstimation(parent, INFINITE_NEG);
		Estimation chEst = child.getData().getEstim();
		if (chEst.isGreatThan(prEst)) {
			prEst.setValue(chEst.getValue());
			/*int optType = IntegerEstimation.GREAT_EQUAL;
			if (!child.hasNext()) {
				optType = IntegerEstimation.EQUAL;
			}*/
			prEst.setOperType(IntegerEstimation.GREAT_EQUAL);
		}
		if (!child.hasNext()) {
			prEst.setOperType(IntegerEstimation.EQUAL);
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
