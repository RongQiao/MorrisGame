
public abstract class MorrisAlphaBeta extends MorrisBase{

	@Override
	public void applyAlgorithm(Node<MorrisNodeData> root) {
		EvaluateAlgorithmAlphaBeta.clearEvaluateCnt();
		EvaluateAlgorithmAlphaBeta.applyAlgorithm(root);
	}

	@Override
	public int getEvaluatedCnt() {
		return EvaluateAlgorithmAlphaBeta.getEvaluateCnt();
	}
	
}
