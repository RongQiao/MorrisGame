import java.util.List;


public class MorrisPhaseStrategyGame {
	public static final int SEARCH_DEP_LIMIT = 6;	//according practice, 6 can limit the run under 30s

	public static Estimation calculateEstimation(MorrisBoard bd) {
		Estimation est = new IntegerEstimation();
		int numWhite = bd.countWhite();
		int numBlack = bd.countBlack();
		
		MorrisBoard swapBoard = bd.swap();
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
					estValue = 1000 * (numWhite - numBlack) - numBlackMoves;
				}
			}
		}
		
		est.setValue(estValue); 
		return est;
	}

	public static List<MorrisBoard> generateAction(MorrisBoard bd) {
		if (bd.countWhite() == 3) {
			return MorrisBase.generateHopping(bd);
		}
		else {
			return MorrisBase.generateMove(bd);
		}
	}

}
