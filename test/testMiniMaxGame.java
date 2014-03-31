import static org.junit.Assert.*;

import org.junit.Test;


public class testMiniMaxGame {

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

	private String testMain(String inFN, String outFN, int depth) {
		String inputPosStr = UIController.readFromFile(inFN);
		MiniMaxGame MMG = new MiniMaxGame(inputPosStr);
		MMG.play(depth);
		String playResult = MMG.getResultStr();
		UIController.writeToFile(outFN, playResult);
		MMG.showPlayResult();
		
		//check
		String checkResult = UIController.readFromFile(outFN);
		assertTrue(checkResult.length() == playResult.length());
		for (int i = 0; i < checkResult.length(); i++) {
			assertTrue(checkResult.charAt(i) == playResult.charAt(i));
		}
		
		return playResult;
	}

}
