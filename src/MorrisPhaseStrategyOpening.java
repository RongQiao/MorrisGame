import java.util.List;


public class MorrisPhaseStrategyOpening {

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

}
