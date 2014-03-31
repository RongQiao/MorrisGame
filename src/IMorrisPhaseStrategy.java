import java.util.List;


public interface IMorrisPhaseStrategy {
	public Estimation calculateEstimation(MorrisBoard board);
	public List<MorrisBoard> generateAction(MorrisBoard board);
	public int getSearchDepthLimit();
}
