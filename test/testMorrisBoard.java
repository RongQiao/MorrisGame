import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;


public class testMorrisBoard {

	//MorrisBoard.TOTALPOS = 23
	@Test
	public void testBoardInit() {
		MorrisBoard bd = new MorrisBoard();
		assertTrue(bd.countPosition() == 23);
		assertTrue(bd.countWhite() == 0);
		assertTrue(bd.countBlack() == 0);
		assertTrue(bd.countEmpty() == MorrisBoard.TOTALPOS);
	}
	
	@Test 
	public void testBoardInitStr() {
		String strPos = "xxxxxBxWWWWWBBBBxxxxxxx";
		MorrisBoard bd = new MorrisBoard(strPos);
		assertTrue(bd.countPosition() == 23);
		assertTrue(bd.countWhite() == 5);
		assertTrue(bd.countBlack() == 5);
		assertTrue(bd.countEmpty() == 13);
	}
	
	//test neighbor of an intersection
	@Test
	public void testIntersectionNeighbor() {
		String strPos = "xxxxxBxWWWWWBBBBxxxxxxx";
		MorrisBoard bd = new MorrisBoard(strPos);
		MorrisIntersection mi = bd.getIntersection(MorrisIntersection.C3);
		List<MorrisIntersection> neighbors = mi.getNeighbor();
		assertTrue(neighbors.size() == 3);
		assertTrue(mi.left().isWhite());
		assertTrue(mi.right() == null);
		assertTrue(mi.up().isBlack());
		assertTrue(mi.down().isEmpty());
		assertTrue(mi.skewLeftUp() == null);
		assertTrue(mi.skewLeftDown() == null);
		assertTrue(mi.skewRightUp() == null);
		assertTrue(mi.skewRightDown() == null);		
	}
	
	//test Mill
	@Test
	public void testBoardNewposCloseMill() {
		String strPos = "xxxxxBxWWWWWBBBBxxxxxxx";
		MorrisBoard bd = new MorrisBoard(strPos);
		bd.addWhite(MorrisIntersection.E4);
		assertTrue(bd.closeMill(MorrisIntersection.E4));
	}
}
