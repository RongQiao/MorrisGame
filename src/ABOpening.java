import java.util.List;


public class ABOpening extends MorrisAlphaBeta implements IMorrisPhaseStrategy{
	public static void main(String[] args) {
		boolean argsValid = checkArgs(args);
		if (argsValid) {
			String inFN = args[0];
			String outFN = args[1];
			String depthStr = args[2];
			int depth = Integer.parseInt(depthStr);
			
			String inputPosStr = UIController.readFromFile(inFN);
			ABOpening ABO = new ABOpening(inputPosStr);
			ABO.play(depth);
			String playResult = ABO.getResultStr();
			UIController.writeToFile(outFN, playResult);
			ABO.showPlayResult();
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

}
