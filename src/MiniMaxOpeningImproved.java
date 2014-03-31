import java.util.List;


public class MiniMaxOpeningImproved extends MorrisMiniMax implements IMorrisPhaseStrategy{
	public static void main(String[] args) {
		boolean argsValid = checkArgs(args);
		if (argsValid) {
			String inFN = args[0];
			String outFN = args[1];
			String depthStr = args[2];
			int depth = Integer.parseInt(depthStr);
			
			String inputPosStr = UIController.readFromFile(inFN);
			MiniMaxOpeningImproved MMOI = new MiniMaxOpeningImproved(inputPosStr);
			MMOI.play(depth);
			String playResult = MMOI.getResultStr();
			UIController.writeToFile(outFN, playResult);
			MMOI.showPlayResult(MMOI.orgPlayResultResponse());
		}
		if (!argsValid) {
			System.out.println("invalid args.");
		}
	}

	public MiniMaxOpeningImproved(String posStr) {
		mTree = MorrisTree.getInstance(posStr);
	}

	/*
	 * check the empty neighbors, for white, the more is the better
	 * for example, with "WxWWxWWWWWBBBBBBBBxxxxx", move a3 to a6(get 4 empty) is better than move b1 to d1(get 3 empty)
	 * for the two move, the numbers of pieces are same, but the empty can show previous one is better 
	 */
	@Override
	public Estimation calculateEstimation(MorrisBoard board) {
		Estimation est = new IntegerEstimation();
		int numWhite = board.countWhite();
		int numBlack = board.countBlack();
		int numEmptyNeighbor4White = board.countEmptyNeighbor4White();
		int numEmptyNeighbor4Black = board.countEmptyNeighbor4Black();
		est.setValue((numWhite - numBlack) + (numEmptyNeighbor4White - numEmptyNeighbor4Black)); 
		return est;
	}

	@Override
	public List<MorrisBoard> generateAction(MorrisBoard board) {
		return MorrisPhaseStrategyOpening.generateAction(board);
	}

	@Override
	public int getSearchDepthLimit(MorrisBoard board) {
		return MorrisPhaseStrategyOpening.determinApproporiateDepth(board);
	}

}
