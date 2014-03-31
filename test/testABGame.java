import static org.junit.Assert.*;

import org.junit.Test;


public class testABGame {

	@Test
	public void testLine() {
		System.out.println();
		System.out.println("testABGame");
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
		String outPosStr = testMain("white8w8b.txt", "black.txt", 3);
		
		boolean bW = ((outPosStr.charAt(20) == 'W')
				|| (outPosStr.charAt(1) == 'W')
				|| (outPosStr.charAt(4) == 'W'));
		assertTrue(bW); 
	}

	private String testMain(String inFN, String outFN, int depth) {
		String inputPosStr = UIController.readFromFile(inFN);
		ABGame ABG = new ABGame(inputPosStr);
		ABG.play(depth);
		String playResult = ABG.getResultStr();
		UIController.writeToFile(outFN, playResult);
		ABG.showPlayResult(ABG.orgPlayResultResponse());
		
		//check
		String checkResult = UIController.readFromFile(outFN);
		assertTrue(checkResult.length() == playResult.length());
		for (int i = 0; i < checkResult.length(); i++) {
			assertTrue(checkResult.charAt(i) == playResult.charAt(i));
		}
		
		return playResult;
	}

}
