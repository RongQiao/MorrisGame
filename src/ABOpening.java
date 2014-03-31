import java.util.List;


public class ABOpening extends MorrisAlphaBeta implements IMorrisPhaseStrategy{
	public static void main(String[] args) {
		boolean argsValid = checkArgs(args);
		if (argsValid) {
			String inFN = args[0];
			String outFN = args[1];
			String depthStr = args[2];
			
			String inputPosStr = UIController.readFromFile(inFN);
			ABOpening ABO = new ABOpening(inputPosStr);
			int depth = Integer.parseInt(depthStr);
			if (ABO.checkDepth(depth)) {
				ABO.play(depth);
				String playResult = ABO.getResultStr();
				UIController.writeToFile(outFN, playResult);
				ABO.showPlayResult(ABO.orgPlayResultResponse());				
			}
			else {
				System.out.println("search depth is too much.");
			}
		}
		if (!argsValid) {
			System.out.println("invalid args.");
		}
	}

	public ABOpening(String inputPosStr) {
		mTree = MorrisTree.getInstance(inputPosStr);
	}

	@Override
	public Estimation calculateEstimation(MorrisBoard board) {
		return MorrisPhaseStrategyOpening.calculateEstimation(board);
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
