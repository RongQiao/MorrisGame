import java.util.List;


public class MiniMaxGame extends MorrisMiniMax implements IMorrisPhaseStrategy{
	public static void main(String[] args) {
		boolean argsValid = checkArgs(args);
		if (argsValid) {
			String inFN = args[0];
			String outFN = args[1];
			String depthStr = args[2];
			String inputPosStr = UIController.readFromFile(inFN);
			MiniMaxGame MMG = new MiniMaxGame(inputPosStr);
			
			int depth = Integer.parseInt(depthStr);
			if (MMG.checkDepth(depth)) {
				MMG.play(depth);
				String playResult = MMG.getResultStr();
				UIController.writeToFile(outFN, playResult);
				MMG.showPlayResult(MMG.orgPlayResultResponse());
			}
			else {
				System.out.println("search depth is too much.");
			}
		}
		if (!argsValid) {
			System.out.println("invalid args.");
		}
	}

	public MiniMaxGame(String inputPosStr) {
		mTree = MorrisTree.getInstance(inputPosStr);
	}

	@Override
	public Estimation calculateEstimation(MorrisBoard board) {
		return MorrisPhaseStrategyGame.calculateEstimation(board);
	}

	@Override
	public List<MorrisBoard> generateAction(MorrisBoard board) {
		return MorrisPhaseStrategyGame.generateAction(board);
	}

	@Override
	public int getSearchDepthLimit() {
		return MorrisPhaseStrategyGame.SEARCH_DEP_LIMIT;
	}

}
