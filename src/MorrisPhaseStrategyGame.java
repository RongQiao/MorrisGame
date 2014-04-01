import java.util.ArrayList;
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

	/*
	 * if black < 3, white win, doesn't need generate any action
	 * if white = 3, hopping; > 3, move; <3, lose, doesn't need generate any action
	 */
	public static List<MorrisBoard> generateAction(MorrisBoard bd) {
		List<MorrisBoard> possibleSubBoard = new ArrayList<MorrisBoard>();
		
		int numBlack = bd.countBlack();
		if (numBlack >= 3) {
			int numWhite = bd.countWhite();
			if (numWhite == 3) {
				possibleSubBoard = MorrisBase.generateHopping(bd);
			}
			else {
				if (numWhite > 3) {
					possibleSubBoard = MorrisBase.generateMove(bd);
				}
			}			
		}
		
		return possibleSubBoard;
	}

}
