import java.util.List;


public class ABGame extends MorrisAlphaBeta implements IMorrisPhaseStrategy{
	public static void main(String[] args) {
		boolean argsValid = checkArgs(args);
		if (argsValid) {
			String inFN = args[0];
			String outFN = args[1];
			String depthStr = args[2];
			int depth = Integer.parseInt(depthStr);
			
			String inputPosStr = UIController.readFromFile(inFN);
			ABGame ABG = new ABGame(inputPosStr);
			ABG.play(depth);
			String playResult = ABG.getResultStr();
			UIController.writeToFile(outFN, playResult);
			ABG.showPlayResult(ABG.orgPlayResultResponse());
		}
		if (!argsValid) {
			System.out.println("invalid args.");
		}
	}

	public ABGame(String inputPosStr) {
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
}
