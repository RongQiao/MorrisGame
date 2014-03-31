import java.util.List;


public class MiniMaxOpening extends MorrisMiniMax implements IMorrisPhaseStrategy{
	public static void main(String[] args) {
		boolean argsValid = checkArgs(args);
		if (argsValid) {
			String inFN = args[0];
			String outFN = args[1];
			String depthStr = args[2];
			int depth = Integer.parseInt(depthStr);
			
			String inputPosStr = UIController.readFromFile(inFN);
			MiniMaxOpening MMO = new MiniMaxOpening(inputPosStr);
			MMO.play(depth);
			String playResult = MMO.getResultStr();
			UIController.writeToFile(outFN, playResult);
			MMO.showPlayResult();
		}
		if (!argsValid) {
			System.out.println("invalid args.");
		}
	}

	public MiniMaxOpening(String posStr) {
		mTree = MorrisTree.getInstance(posStr);
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
