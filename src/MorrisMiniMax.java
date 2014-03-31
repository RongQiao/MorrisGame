
public abstract class MorrisMiniMax extends MorrisBase{

	@Override
	public void applyAlgorithm(Node<MorrisNodeData> root) {
		EvaluateAlgorithmMiniMax.clearEvaluateCnt();
		EvaluateAlgorithmMiniMax.applyAlgorithm(root);
	}

	@Override
	public int getEvaluatedCnt() {
		return EvaluateAlgorithmMiniMax.getEvaluateCnt();
	}

}
