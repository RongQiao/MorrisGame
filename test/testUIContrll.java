import static org.junit.Assert.*;

import org.junit.Test;


public class testUIContrll {

	@Test
	public void testFileRead() {
		String posStr = UIController.readFromFile("white.txt");
		assertTrue(posStr.length() == 23);
	}
	
	@Test
	public void testFileWrite() {
		String fileName = "black.txt";
		String posStr = "xxxxxBxWWWWWBBBBxxxxxxx";
		UIController.writeToFile(fileName, posStr);
		String checkStr = UIController.readFromFile(fileName);
		assertTrue(checkStr.length() == posStr.length());
		for (int i = 0; i < checkStr.length(); i++) {
			assertTrue(checkStr.charAt(i) == posStr.charAt(i));
		}
	}

}
