import java.util.List;

/*
 * for most cases with >= 10 empty, 4 is the most allowed depth, 5 result in out of memory exception, the nodes are more than 150,000
 * for most cases with 6~9 empty, 5 is the most allowed depth
 * for most cases with <= 5 empty, no limited depth, but it's usually down on depth 5 or 6
 * beside the empty number, the position itself also affect the depth, for example, with 7 empty, some only can do 5 depth, some can do 6+
 * to be safe and simple, we only give depth via empty number
 */
public class MorrisPhaseStrategyOpening {
	private static final int SEARCH_DEP_LIMIT_DEFAULT = 4;	//according practice, 4 can limit most run under 30s
	private static final int HIGH_TOP = 23;
	private static final int HIGH_BOTTOM = 10;
	private static final int MED_TOP = 9;
	private static final int MED_BOTTOM = 5;
	private static final int LOW_TOP = 4;
	private static final int LOW_BOTTOM = 1;

	public static Estimation calculateEstimation(MorrisBoard bd) {
		Estimation est = new IntegerEstimation();
		int numWhite = bd.countWhite();
		int numBlack = bd.countBlack();
		est.setValue(numWhite - numBlack); 
		return est;
	}

	public static List<MorrisBoard> generateAction(MorrisBoard bd) {
		return MorrisBase.generateAdd(bd);
	}

	/*
	 * the depth is related with the number of empty position.
	 * the more the empty is, the more the possible children of each node, so the less the appropriate depth 
	 */
	public static int determinApproporiateDepth(MorrisBoard bd) {
		
		int depth = SEARCH_DEP_LIMIT_DEFAULT;
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
		
		return depth;
	}

	private static int depthForLow(int numEmpty) {
		int depth = 23;	//it's open, but it's usually done on 5 or 6, so give a big number
		return depth;
	}

	private static int depthForMed(int numEmpty) {
		int depth = SEARCH_DEP_LIMIT_DEFAULT + 1;
		return depth;
	}
	
	private static int depthForHigh(int numEmpty) {
		int depth = SEARCH_DEP_LIMIT_DEFAULT;
		return depth;
	}

}
