import static org.junit.Assert.*;

import org.junit.Test;


public class testMiniMaxOpening {
	
	/*
	 * with input "xxxxxBxWWWWWBBBBxxxxxxx", expand 1 depth
	 * get 17 possible leaves
	 */
	@Test
	public void testMorrisTreeExpandDep1() {
		String posStr = "xxxxxBxWWWWWBBBBxxxxxxx";
		testMorrisTreeExpand(posStr, 1);
		MorrisTree mTree = MorrisTree.getInstance();
		assertTrue(mTree.totalDepth() == 1);
		assertTrue(mTree.countLeaf() == 17);
	}
	
	/*
	 * with input "xxxxxBxWWWWWBBBBxxxxxxx", expand 2 depth
	 * get much more possible leaves
	 */
	@Test
	public void testMorrisTreeExpandDep2() {
		String posStr = "xxxxxBxWWWWWBBBBxxxxxxx";
		testMorrisTreeExpand(posStr, 2);
		MorrisTree mTree = MorrisTree.getInstance();
		System.out.println(mTree.totalDepth());
		System.out.println(mTree.countLeaf());
		assertTrue(mTree.totalDepth() == 2);
		assertTrue(mTree.countLeaf() > 200);	//not sure the exact number
	}
	
	private void testMorrisTreeExpand(String inputPosStr, int depth) {
		MiniMaxOpening MMO = new MiniMaxOpening(inputPosStr);
		MMO.expandTree(MorrisTree.getInstance().getRoot(), depth);
	}

	/*
	 * with input "xxxxxBxWWWWWBBBBxxxxxxx", expand 1 depth
	 * the best output should have e4=W
	 */
	@Test
	public void testMainDep1() {
		String outPosStr = testMain("white.txt", "black.txt", 1);
		
		assertTrue(outPosStr.charAt(16) == 'W'); 
	}
	
	/*
	 * with input "xxxxxBxWWWWWBBBBxxxxxxx", expand 2 depth
	 * the best output should have 
	 */
	@Test
	public void testMainDep2() {
		String outPosStr = testMain("white.txt", "black.txt", 2);
		
		assertTrue(outPosStr.charAt(16) == 'W'); 
	}
	
	/*
	 * with input "xxxxxBxWWWWWBBBBxxxxxxx", expand 3 depth
	 * the best output should have 
	 */
	@Test
	public void testMainDep3() {
		String outPosStr = testMain("white.txt", "black.txt", 3);
	}

	private String testMain(String inFN, String outFN, int depth) {
		String inputPosStr = UIController.readFromFile(inFN);
		MiniMaxOpening MMO = new MiniMaxOpening(inputPosStr);
		MMO.play(depth);
		String playResult = MMO.getResultStr();
		UIController.writeToFile(outFN, playResult);
		MMO.showPlayResult();
		
		//check
		String checkResult = UIController.readFromFile(outFN);
		assertTrue(checkResult.length() == playResult.length());
		for (int i = 0; i < checkResult.length(); i++) {
			assertTrue(checkResult.charAt(i) == playResult.charAt(i));
		}
		
		return playResult;
	}

}
