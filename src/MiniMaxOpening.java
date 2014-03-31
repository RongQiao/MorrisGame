import java.util.List;


public class MiniMaxOpening extends MorrisMiniMax implements IMorrisPhaseStrategy{
	public static void main(String[] args) {
		boolean argsValid = checkArgs(args);
		if (argsValid) {
			String inFN = args[0];
			String outFN = args[1];
			String depthStr = args[2];
			String inputPosStr = UIController.readFromFile(inFN);
			MiniMaxOpening MMO = new MiniMaxOpening(inputPosStr);
			
			int depth = Integer.parseInt(depthStr);		
			if (MMO.checkDepth(depth)) {
				MMO.play(depth);
				String playResult = MMO.getResultStr();
				UIController.writeToFile(outFN, playResult);
				MMO.showPlayResult(MMO.orgPlayResultResponse());				
			}
			else {
				System.out.println("search depth is too much.");
			}
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

	@Override
	public int getSearchDepthLimit() {
		return MorrisPhaseStrategyOpening.SEARCH_DEP_LIMIT;	
	}


	
}
