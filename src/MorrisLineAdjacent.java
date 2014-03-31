
public class MorrisLineAdjacent {
	private MorrisIntersection primary;
	private MorrisIntersection adjacent1;
	private MorrisIntersection adjacent2;
	MorrisLineAdjacent(MorrisIntersection pm, MorrisIntersection adj1, MorrisIntersection adj2) {
		primary = pm;
		adjacent1 = adj1;
		adjacent2 = adj2;
	}
	
	public boolean isMill() {
		boolean ret = false;
		if ((primary != null) && (adjacent1 != null) && (adjacent2 != null)) {
			char pCh = primary.getLetter();
			if ((adjacent1.getLetter() == pCh) && (adjacent2.getLetter() == pCh)) {
				ret = true;
			}
		}
		return ret;
	}
}
