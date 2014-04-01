import java.util.List;


public class MiniMaxGameImproved extends MorrisMiniMax implements IMorrisPhaseStrategy{

	public static void main(String[] args) {
		boolean argsValid = checkArgs(args);
		if (argsValid) {
			String inFN = args[0];
			String outFN = args[1];
			String depthStr = args[2];
			
			String inputPosStr = UIController.readFromFile(inFN);
			MiniMaxGameImproved MMGI = new MiniMaxGameImproved(inputPosStr);
			int depth = Integer.parseInt(depthStr);
			if (MMGI.checkDepth(depth)) {
				MMGI.play(depth);
				String playResult = MMGI.getResultStr();
				UIController.writeToFile(outFN, playResult);
				MMGI.showPlayResult(MMGI.orgPlayResultResponse());			
			}
			else {
				System.out.println("search depth is too much.");
			}
		}
		if (!argsValid) {
			System.out.println("invalid args.");
		}
	}
	
	public MiniMaxGameImproved(String inputPosStr) {
		mTree = MorrisTree.getInstance(inputPosStr);
	}

	@Override
	public Estimation calculateEstimation(MorrisBoard board) {
		Estimation est = new IntegerEstimation();
		int numWhite = board.countWhite();
		int numBlack = board.countBlack();
		
		MorrisBoard swapBoard = board.swap();
		List<MorrisBoard> blackMoves = generateAction(swapBoard);
		int numBlackMoves = blackMoves.size();
		
		int estValue = IntegerEstimation.NOTSET;
		if (numBlack <= 2) {
			estValue = IntegerEstimation.WIN;
		}
		else {
			if (numWhite <= 2) {
				estValue = IntegerEstimation.LOSE;
			}
			else {
				if (numBlackMoves == 0) {
					estValue = IntegerEstimation.WIN;
				}
				else {
					int numEmptyNeighbor4White = board.countEmptyNeighbor4White();
					int numEmptyNeighbor4Black = board.countEmptyNeighbor4Black();
					estValue = 1000 * ((numWhite - numBlack) + (numEmptyNeighbor4White - numEmptyNeighbor4Black)) 
							- numBlackMoves;
				}
			}
		}
		
		est.setValue(estValue); 
		return est;
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
