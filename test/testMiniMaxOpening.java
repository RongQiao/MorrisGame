import static org.junit.Assert.*;

import org.junit.Test;


public class testMiniMaxOpening {
	@Test
	public void testLine() {
		System.out.println();
		System.out.println("testMiniMaxOpening");
		System.out.println();		
	}
	
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
		System.out.println("testMainDep1");
		String outPosStr = testMain("white.txt", "black.txt", 1);
		
		assertTrue(outPosStr.charAt(16) == 'W'); 
	}
	
	/*
	 * with input "xxxxxBxWWWWWBBBBxxxxxxx", expand 2 depth
	 * the best output should have 
	 */
	@Test
	public void testMainDep2() {
		System.out.println("testMainDep2");
		String outPosStr = testMain("white.txt", "black.txt", 2);
		
		assertTrue(outPosStr.charAt(16) == 'W'); 
	}
	
	/*
	 * with input "xxxxxBxWWWWWBBBBxxxxxxx", expand 3 depth
	 * the best output should have 
	 */
	@Test
	public void testMainDep3() {
		System.out.println("testMainDep3");
		String outPosStr = testMain("white.txt", "black.txt", 3);
	}

	private String testMain(String inFN, String outFN, int depth) {
		String inputPosStr = UIController.readFromFile(inFN);
		MiniMaxOpening MMO = new MiniMaxOpening(inputPosStr);
		MMO.play(depth);
		String playResult = MMO.getResultStr();
		UIController.writeToFile(outFN, playResult);
		MMO.showPlayResult(MMO.orgPlayResultResponse());
		
		//check
		String checkResult = UIController.readFromFile(outFN);
		assertTrue(checkResult.length() == playResult.length());
		for (int i = 0; i < checkResult.length(); i++) {
			assertTrue(checkResult.charAt(i) == playResult.charAt(i));
		}
		
		return playResult;
	}
	
	/*
	 * with input "xxxxxWxBBBBBWWWWxxxxxxx", expand 3 depth
	 * this should have same evaluated count and estimation with testMainDep3, as it's just swaped the color
	 * the output string is swaped color with testMainDep3
	 * the best output should have 
	 */
	@Test
	public void testBlackOpenMainDep3() {
		System.out.println("testBlackOpenMainDep3");
		String outPosStr = testBlackOpenMain("whites.txt", "black.txt", 3);
	}

	private String testBlackOpenMain(String inFN, String outFN, int depth) {
		String inputPosStr = UIController.readFromFile(inFN);
		String swapedPosStr = MorrisBase.swapPosStr(inputPosStr);
		MiniMaxOpening MMO = new MiniMaxOpening(swapedPosStr);
		MMO.play(depth);
		String playResult = MMO.getResultStr();
		swapedPosStr = MorrisBase.swapPosStr(playResult);
		UIController.writeToFile(outFN, swapedPosStr);
		String respStr[] = MMO.orgPlayResultResponse();
		respStr[0] = "Board Position: " + swapedPosStr;
		MMO.showPlayResult(respStr);
		
		//check
		String checkResult = UIController.readFromFile(outFN);
		assertTrue(checkResult.length() == swapedPosStr.length());
		for (int i = 0; i < checkResult.length(); i++) {
			assertTrue(checkResult.charAt(i) == swapedPosStr.charAt(i));
		}
		
		return swapedPosStr;
	}
	
	@Test
	public void testImproveDep3() {
		System.out.println("testImproveDep3");
		testImproveMain("white.txt", "black.txt", 3);
	}
	
	private String testImproveMain(String inFN, String outFN, int depth) {
		String inputPosStr = UIController.readFromFile(inFN);
		MiniMaxOpeningImproved MMOI = new MiniMaxOpeningImproved(inputPosStr);
		MMOI.play(depth);
		String playResult = MMOI.getResultStr();
		UIController.writeToFile(outFN, playResult);
		MMOI.showPlayResult(MMOI.orgPlayResultResponse());
		
		//check
		String checkResult = UIController.readFromFile(outFN);
		assertTrue(checkResult.length() == playResult.length());
		for (int i = 0; i < checkResult.length(); i++) {
			assertTrue(checkResult.charAt(i) == playResult.charAt(i));
		}
		
		return playResult;
	}

}
