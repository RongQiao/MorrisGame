import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;


public class testPhaseStrategy {

	/*
	 * with input position xxxxxBxWWxWxBBBBxxxxxxx, for opening
	 * estimation is 3-5 = -2
	 */
	@Test
	public void testEstimationOpening() {
		String posStr = "xxxxxBxWWxWxBBBBxxxxxxx";
		MorrisBoard bd = new MorrisBoard(posStr);
		Estimation est = MorrisPhaseStrategyOpening.calculateEstimation(bd);
		assertTrue(est.getValue() == -2);
	}
	
	@Test
	public void testGenerateActionOpening() {
		String posStr = "xxxxxBxWWWWWBBBBxxxxxxx";
		MorrisBoard input = new MorrisBoard(posStr);
		List<MorrisBoard> output = MorrisPhaseStrategyOpening.generateAction(input);
		assertTrue(output.size() == 17);
	}
	
	/*
	 * with input position xxxxxWxBBxBxWWWWxxxxxxx, for game
	 * the possible black move is 49, refer to testGenerateHopping
	 * estimation is 1000 * (5 - 3) - 49 = 1951
	 */
	@Test
	public void testEstimationGame() {
		String posStr = "xxxxxWxBBxBxWWWWxxxxxxx";
		MorrisBoard bd = new MorrisBoard(posStr);
		Estimation est = MorrisPhaseStrategyGame.calculateEstimation(bd);
		assertTrue(est.getValue() == 1951);
	}
	
	@Test
	public void testGenerateActionGame4Hopping() {
		String posStr = "xxxxxBxWWxWxBBBBxxxxxxx";
		MorrisBoard input = new MorrisBoard(posStr);
		List<MorrisBoard> output = MorrisPhaseStrategyGame.generateAction(input);
		assertTrue(output.size() == 49);
	}
	
	@Test
	public void testGenerateActionGame4Move() {
		String posStr = "xxxxxBxWWWWWBBBBxxxxxxx";
		MorrisBoard input = new MorrisBoard(posStr);
		List<MorrisBoard> output = MorrisPhaseStrategyGame.generateAction(input);
		assertTrue(output.size() == 7);
	}

}
