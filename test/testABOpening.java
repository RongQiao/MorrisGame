import static org.junit.Assert.*;

import org.junit.Test;


public class testABOpening {
	@Test
	public void testLine() {
		System.out.println();
		System.out.println("testABOpening");
		System.out.println();		
	}
	
	/*
	 * with input "xxxxxBxWWWWWBBBBxxxxxxx", expand 2 depth
	 * the best output should have e4=W
	 */
	@Test
	public void testMainDep1() {
		String outPosStr = testMain("white.txt", "black.txt", 1);
		
		assertTrue(outPosStr.charAt(16) == 'W'); 
	}

	/*
	 * with input "xxxxxBxWWWWWBBBBxxxxxxx", expand 2 depth
	 * the best output should have e4=W
	 */
	@Test
	public void testMainDep2() {
		String outPosStr = testMain("white.txt", "black.txt", 2);
		
		assertTrue(outPosStr.charAt(16) == 'W'); 
	}
	
	/*
	 * with input "xxxxxBxWWWWWBBBBxxxxxxx", expand 2 depth
	 * the best output should have e4=W
	 */
	@Test
	public void testMainDep3() {
		String outPosStr = testMain("white.txt", "black.txt", 3);
	}

	private String testMain(String inFN, String outFN, int depth) {
		String inputPosStr = UIController.readFromFile(inFN);
		ABOpening ABO = new ABOpening(inputPosStr);
		ABO.play(depth);
		String playResult = ABO.getResultStr();
		UIController.writeToFile(outFN, playResult);
		ABO.showPlayResult(ABO.orgPlayResultResponse());
		
		//check
		String checkResult = UIController.readFromFile(outFN);
		assertTrue(checkResult.length() == playResult.length());
		for (int i = 0; i < checkResult.length(); i++) {
			assertTrue(checkResult.charAt(i) == playResult.charAt(i));
		}
		
		return playResult;
	}


}
