import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;


public class testMorrisBase {

	/*
	 * with input position xxxxxBxWWWWWBBBBxxxxxxx,
	 * possible add position for W: 
	 * empty = 23 - 5 - 5 = 13, put on E4 is Mill, result possible 5 remove of Black
	 * total = 13 - 1 + 5 = 17
	 */
	@Test
	public void testGenerateAdd() {
		String posStr = "xxxxxBxWWWWWBBBBxxxxxxx";
		MorrisBoard input = new MorrisBoard(posStr);
		List<MorrisBoard> output = MorrisBase.generateAdd(input);
		assertTrue(output.size() == 17);
	}
	
	/*
	 * with input position xxxxxBxWWWWWBBBBxxxxxxx,
	 * possible move position for W: 
	 * empty adjacent = 6, empty c2 has 2 possible move from other W 
	 * total = 6 - 1 + 1 * 2 = 7
	 */
	@Test
	public void testGenerateMove1() {
		String posStr = "xxxxxBxWWWWWBBBBxxxxxxx";
		MorrisBoard input = new MorrisBoard(posStr);
		List<MorrisBoard> output = MorrisBase.generateMove(input);
		assertTrue(output.size() == 7);
	}
	
	/*
	 * with input position WxWWxWWWWWBBBBBBBBxxxxx,
	 * possible move position for W: 
	 * empty adjacent = 3, empty d1 and d0 has 2 possible move from other W 
	 * total = 3 - 2 + 2 * 2 = 5
	 */
	@Test
	public void testGenerateMove2() {
		String posStr = "WxWWxWWWWWBBBBBBBBxxxxx";
		MorrisBoard input = new MorrisBoard(posStr);
		List<MorrisBoard> output = MorrisBase.generateMove(input);
		assertTrue(output.size() == 5);
	}

	/*
	 * with input position xxxxxBxWWxWxBBBBxxxxxxx,
	 * possible hopping position for W: 
	 * empty = 23 - 3 - 5 = 15, so the possible jump is 15 * 3 = 45, jump to b3 is Mill, result possible 5 remove of Black 
	 * total = 45 - 1 + 5 = 49
	 */
	@Test
	public void testGenerateHopping() {
		String posStr = "xxxxxBxWWxWxBBBBxxxxxxx";
		MorrisBoard input = new MorrisBoard(posStr);
		List<MorrisBoard> output = MorrisBase.generateHopping(input);
		assertTrue(output.size() == 49);
	}
}
