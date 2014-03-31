import java.util.ArrayList;
import java.util.List;

/*
 * for move or hopping, it takes more time to calculate the estimation
 * for most of cases, depth 7 will result in long long time(didn't show the out of memory)
 * so set the limit between 3 ~ 6
 */
public class MorrisPhaseStrategyGame {
	public static final int UNVALIED = 100;	//if input white <3, no meaning for play the game
	private static final int SEARCH_DEP_LIMIT_DEFAULT = 5;	//according practice, 6 can limit the run under 30s
	private static final int HIGH_TOP = 15;
	private static final int HIGH_BOTTOM = 10;
	private static final int MED_TOP = 9;
	private static final int MED_BOTTOM = 8;
	private static final int LOW_TOP = 7;
	private static final int LOW_BOTTOM = 1;

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

	/*
	 * the depth is related with the number of empty position(hopping) or empty neighbor(move).
	 * the more the empty is, the more the possible children of each node, so the less the appropriate depth 
	 */
	public static int determinApproporiateDepth(MorrisBoard bd) {		
		int depth = SEARCH_DEP_LIMIT_DEFAULT;
		int numWhite = bd.countWhite();
		if (numWhite < 3) {
			depth = UNVALIED;
		}
		else if (numWhite == 3) {
			depth = 3;
		}
		else {
			int numEmpty = bd.countEmpty();
			if ((numEmpty >= HIGH_BOTTOM) && (numEmpty <= HIGH_TOP)) {
				depth = depthForHigh(numEmpty);
			}
			else if ((numEmpty >= MED_BOTTOM) && (numEmpty <= MED_TOP)) {
				depth = depthForMed(numEmpty);
			}
			else if ((numEmpty >= LOW_BOTTOM) && (numEmpty <= LOW_TOP)) {
				depth = depthForLow(numEmpty);
			}			
		}

		
		return depth;
	}

	private static int depthForLow(int numEmpty) {
		int depth = SEARCH_DEP_LIMIT_DEFAULT + 1;
		return depth;
	}

	private static int depthForMed(int numEmpty) {
		int depth = SEARCH_DEP_LIMIT_DEFAULT;
		return depth;
	}
	
	private static int depthForHigh(int numEmpty) {
		int depth = SEARCH_DEP_LIMIT_DEFAULT;
		return depth;
	}

}
