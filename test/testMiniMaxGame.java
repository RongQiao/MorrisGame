import static org.junit.Assert.*;

import org.junit.Test;


public class testMiniMaxGame {

	@Test
	public void testLine() {
		System.out.println();
		System.out.println("testMiniMaxGame");
		System.out.println();		
	}
	
	/*
	 * with input "WxWWxWWWWWBBBBBBBBxxxxx", expand 1 depth
	 * the best output should have...
	 */
	@Test
	public void testMainDep1() {
		String outPosStr = testMain("white8w8b.txt", "black.txt", 1);
		
		boolean bW = ((outPosStr.charAt(20) == 'W')
				|| (outPosStr.charAt(1) == 'W')
				|| (outPosStr.charAt(4) == 'W'));
		assertTrue(bW); 
	}
	
	@Test
	public void testMainDep2() {
		String outPosStr = testMain("white8w8b.txt", "black.txt", 2);
		
		boolean bW = ((outPosStr.charAt(20) == 'W')
				|| (outPosStr.charAt(1) == 'W')
				|| (outPosStr.charAt(4) == 'W'));
		assertTrue(bW); 
	}
	
	@Test
	public void testMainDep3() {
		System.out.println("testMainDep3");
		String outPosStr = testMain("white8w8b.txt", "black.txt", 3);
		
		boolean bW = ((outPosStr.charAt(20) == 'W')
				|| (outPosStr.charAt(1) == 'W')
				|| (outPosStr.charAt(4) == 'W'));
		assertTrue(bW); 
	}
	
	@Test
	public void testMainDepHigh() {
		System.out.println("testMainDepHigh");
		String outPosStr = testMain("white8w8b.txt", "black.txt", 6);
		
		boolean bW = ((outPosStr.charAt(20) == 'W')
				|| (outPosStr.charAt(1) == 'W')
				|| (outPosStr.charAt(4) == 'W'));
		assertTrue(bW); 
	}
	
	/*
	 * test hopping
	 */
	@Test
	public void testMainHoppingD3() {
		System.out.println("testMainHoppingD3");
		testMain("white3w3b.txt", "black.txt", 3);
	}

	private String testMain(String inFN, String outFN, int depth) {
		String inputPosStr = UIController.readFromFile(inFN);
		MiniMaxGame MMG = new MiniMaxGame(inputPosStr);
		MMG.play(depth);
		String playResult = MMG.getResultStr();
		UIController.writeToFile(outFN, playResult);
		MMG.showPlayResult(MMG.orgPlayResultResponse());
		
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
	public void testBlackGameMainDep3() {
		System.out.println("testBlackGameMainDep3");
		String outPosStr = testBlackGameMain("white8w8bs.txt", "black.txt", 3);
	}

	private String testBlackGameMain(String inFN, String outFN, int depth) {
		String inputPosStr = UIController.readFromFile(inFN);
		String swapedPosStr = MorrisBase.swapPosStr(inputPosStr);
		MiniMaxGame MMG = new MiniMaxGame(swapedPosStr);
		MMG.play(depth);
		String playResult = MMG.getResultStr();
		swapedPosStr = MorrisBase.swapPosStr(playResult);
		UIController.writeToFile(outFN, swapedPosStr);
		String respStr[] = MMG.orgPlayResultResponse();
		respStr[0] = "Board Position: " + swapedPosStr;
		MMG.showPlayResult(respStr);
		
		//check
		String checkResult = UIController.readFromFile(outFN);
		assertTrue(checkResult.length() == swapedPosStr.length());
		for (int i = 0; i < checkResult.length(); i++) {
			assertTrue(checkResult.charAt(i) == swapedPosStr.charAt(i));
		}
		
		return playResult;
	}
	
	@Test
	public void testImproveMainDep3() {
		System.out.println("testImproveMainDep3");
		String outPosStr = testImproveMain("white8w8b.txt", "black.txt", 3);
		
		boolean bW = ((outPosStr.charAt(20) == 'W')
				|| (outPosStr.charAt(1) == 'W')
				|| (outPosStr.charAt(4) == 'W'));
		assertTrue(bW); 
	}

	private String testImproveMain(String inFN, String outFN, int depth) {
		String inputPosStr = UIController.readFromFile(inFN);
		MiniMaxGameImproved MMGI = new MiniMaxGameImproved(inputPosStr);
		MMGI.play(depth);
		String playResult = MMGI.getResultStr();
		UIController.writeToFile(outFN, playResult);
		MMGI.showPlayResult(MMGI.orgPlayResultResponse());
		
		//check
		String checkResult = UIController.readFromFile(outFN);
		assertTrue(checkResult.length() == playResult.length());
		for (int i = 0; i < checkResult.length(); i++) {
			assertTrue(checkResult.charAt(i) == playResult.charAt(i));
		}
		
		return playResult;
	}
	
}
