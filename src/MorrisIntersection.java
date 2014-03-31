import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class MorrisIntersection {
	private static final int INVALID = MorrisBoard.TOTALPOS;
	public static final int A0 = 0;
	public static final int D0 = 1;
	public static final int G0 = 2;
	public static final int B1 = 3;
	public static final int D1 = 4;
	public static final int F1 = 5;
	public static final int C2 = 6;
	public static final int E2 = 7;
	public static final int A3 = 8;
	public static final int B3 = 9;
	public static final int C3 = 10;
	public static final int E3 = 11;
	public static final int F3 = 12;
	public static final int G3 = 13;
	public static final int C4 = 14;
	public static final int D4 = 15;
	public static final int E4 = 16;
	public static final int B5 = 17;
	public static final int D5 = 18;
	public static final int F5 = 19;
	public static final int A6 = 20;
	public static final int D6 = 21;
	public static final int G6 = 22;
	private MorrisBoard parentBD;
	private char letter;
	private int indexStr;
	private int indexLeft;
	private int indexRight;
	private int indexUp;
	private int indexDown;
	private int indexSkewLeftUp;
	private int indexSkewLeftDown;
	private int indexSkewRightUp;
	private int indexSkewRightDown;
	
	MorrisIntersection(int indexStr, MorrisBoard bd) {	
		setLetter('x');
		this.indexStr = indexStr;
		parentBD = bd;
		init();
	}

	public MorrisIntersection(int indexStr, char ch, MorrisBoard bd) {
		setLetter(ch);
		this.indexStr = indexStr;
		parentBD = bd;
		init();
	}
	

	public int getIndex() {
		return indexStr;
	}

	private void init() {
		switch (indexStr) {
		case A0: 
			initA0();
			break;
		case D0:
			initD0();
			break;
		case G0:
			initG0();
			break;
		case B1:
			initB1();
			break;
		case D1:
			initD1();
			break;
		case F1:
			initF1();
			break;
		case C2:
			initC2();
			break;
		case E2:
			initE2();
			break;
		case A3:
			initA3();
			break;
		case B3:
			initB3();
			break;
		case C3:
			initC3();
			break;
		case E3:
			initE3();
			break;
		case F3:
			initF3();
			break;
		case G3:
			initG3();
			break;
		case C4:
			initC4();
			break;
		case D4:
			initD4();
			break;
		case E4:
			initE4();
			break;
		case B5:
			initB5();
			break;
		case D5:
			initD5();
			break;
		case F5:
			initF5();
			break;
		case A6:
			initA6();
			break;
		case D6:
			initD6();
			break;
		case G6:
			initG6();
			break;
		default:
			break;
		}
	}

	/*
	 * 		indexLeft = INVALID;
		indexRight = INVALID;
		indexUp = INVALID;
		indexDown = INVALID;
		indexSkewLeftUp = INVALID;
		indexSkewLeftDown = INVALID;
		indexSkewRightUp = INVALID;
		indexSkewRightDown = INVALID;
	 */
	private void initG6() {
		indexLeft = D6;
		indexRight = INVALID;
		indexUp = INVALID;
		indexDown = G3;
		indexSkewLeftUp = INVALID;
		indexSkewLeftDown = F5;
		indexSkewRightUp = INVALID;
		indexSkewRightDown = INVALID;
	}

	private void initD6() {
		indexLeft = A6;
		indexRight = G6;
		indexUp = INVALID;
		indexDown = D5;
		indexSkewLeftUp = INVALID;
		indexSkewLeftDown = INVALID;
		indexSkewRightUp = INVALID;
		indexSkewRightDown = INVALID;
	}

	private void initA6() {
		indexLeft = INVALID;
		indexRight = D6;
		indexUp = INVALID;
		indexDown = A3;
		indexSkewLeftUp = INVALID;
		indexSkewLeftDown = INVALID;
		indexSkewRightUp = INVALID;
		indexSkewRightDown = B5;
	}

	private void initF5() {
		indexLeft = D5;
		indexRight = INVALID;
		indexUp = INVALID;
		indexDown = F3;
		indexSkewLeftUp = INVALID;
		indexSkewLeftDown = E4;
		indexSkewRightUp = G6;
		indexSkewRightDown = INVALID;
	}

	private void initD5() {
		indexLeft = B5;
		indexRight = F5;
		indexUp = D6;
		indexDown = D4;
		indexSkewLeftUp = INVALID;
		indexSkewLeftDown = INVALID;
		indexSkewRightUp = INVALID;
		indexSkewRightDown = INVALID;
	}

	private void initB5() {
		indexLeft = INVALID;
		indexRight = D5;
		indexUp = INVALID;
		indexDown = B3;
		indexSkewLeftUp = A6;
		indexSkewLeftDown = INVALID;
		indexSkewRightUp = INVALID;
		indexSkewRightDown = C4;
	}

	private void initE4() {
		indexLeft = D4;
		indexRight = INVALID;
		indexUp = INVALID;
		indexDown = E3;
		indexSkewLeftUp = INVALID;
		indexSkewLeftDown = INVALID;
		indexSkewRightUp = F5;
		indexSkewRightDown = INVALID;
	}

	private void initD4() {
		indexLeft = C4;
		indexRight = E4;
		indexUp = D5;
		indexDown = INVALID;
		indexSkewLeftUp = INVALID;
		indexSkewLeftDown = INVALID;
		indexSkewRightUp = INVALID;
		indexSkewRightDown = INVALID;
	}

	private void initC4() {
		indexLeft = INVALID;
		indexRight = D4;
		indexUp = INVALID;
		indexDown = C3;
		indexSkewLeftUp = B5;
		indexSkewLeftDown = INVALID;
		indexSkewRightUp = INVALID;
		indexSkewRightDown = INVALID;
	}

	private void initG3() {
		indexLeft = F3;
		indexRight = INVALID;
		indexUp = G6;
		indexDown = G0;
		indexSkewLeftUp = INVALID;
		indexSkewLeftDown = INVALID;
		indexSkewRightUp = INVALID;
		indexSkewRightDown = INVALID;
	}

	private void initF3() {
		indexLeft = E3;
		indexRight = G3;
		indexUp = F5;
		indexDown = F1;
		indexSkewLeftUp = INVALID;
		indexSkewLeftDown = INVALID;
		indexSkewRightUp = INVALID;
		indexSkewRightDown = INVALID;
	}

	private void initE3() {
		indexLeft = INVALID;
		indexRight = F3;
		indexUp = E4;
		indexDown = E2;
		indexSkewLeftUp = INVALID;
		indexSkewLeftDown = INVALID;
		indexSkewRightUp = INVALID;
		indexSkewRightDown = INVALID;
	}

	private void initC3() {
		indexLeft = B3;
		indexRight = INVALID;
		indexUp = C4;
		indexDown = C2;
		indexSkewLeftUp = INVALID;
		indexSkewLeftDown = INVALID;
		indexSkewRightUp = INVALID;
		indexSkewRightDown = INVALID;
	}

	private void initB3() {
		indexLeft = A3;
		indexRight = C3;
		indexUp = B5;
		indexDown = B1;
		indexSkewLeftUp = INVALID;
		indexSkewLeftDown = INVALID;
		indexSkewRightUp = INVALID;
		indexSkewRightDown = INVALID;
	}

	private void initA3() {
		indexLeft = INVALID;
		indexRight = B3;
		indexUp = A6;
		indexDown = A0;
		indexSkewLeftUp = INVALID;
		indexSkewLeftDown = INVALID;
		indexSkewRightUp = INVALID;
		indexSkewRightDown = INVALID;
	}

	private void initE2() {
		indexLeft = C2;
		indexRight = INVALID;
		indexUp = E3;
		indexDown = INVALID;
		indexSkewLeftUp = INVALID;
		indexSkewLeftDown = INVALID;
		indexSkewRightUp = INVALID;
		indexSkewRightDown = F1;
	}

	private void initC2() {
		indexLeft = INVALID;
		indexRight = E2;
		indexUp = C3;
		indexDown = INVALID;
		indexSkewLeftUp = INVALID;
		indexSkewLeftDown = B1;
		indexSkewRightUp = INVALID;
		indexSkewRightDown = INVALID;
	}

	private void initF1() {
		indexLeft = D1;
		indexRight = INVALID;
		indexUp = F3;
		indexDown = INVALID;
		indexSkewLeftUp = E2;
		indexSkewLeftDown = INVALID;
		indexSkewRightUp = INVALID;
		indexSkewRightDown = INVALID;
	}

	private void initD1() {
		indexLeft = B1;
		indexRight = F1;
		indexUp = INVALID;
		indexDown = D0;
		indexSkewLeftUp = INVALID;
		indexSkewLeftDown = INVALID;
		indexSkewRightUp = INVALID;
		indexSkewRightDown = INVALID;
	}

	private void initB1() {
		indexLeft = INVALID;
		indexRight = D1;
		indexUp = INVALID;
		indexDown = INVALID;
		indexSkewLeftUp = INVALID;
		indexSkewLeftDown = A0;
		indexSkewRightUp = C2;
		indexSkewRightDown = INVALID;
	}

	private void initG0() {
		indexLeft = D0;
		indexRight = INVALID;
		indexUp = G3;
		indexDown = INVALID;
		indexSkewLeftUp = F1;
		indexSkewLeftDown = INVALID;
		indexSkewRightUp = INVALID;
		indexSkewRightDown = INVALID;
	}

	private void initD0() {
		indexLeft = A0;
		indexRight = G0;
		indexUp = D1;
		indexDown = INVALID;
		indexSkewLeftUp = INVALID;
		indexSkewLeftDown = INVALID;
		indexSkewRightUp = INVALID;
		indexSkewRightDown = INVALID;
	}

	private void initA0() {
		indexLeft = INVALID;
		indexRight = D0;
		indexUp = A3;
		indexDown = INVALID;
		indexSkewLeftUp = INVALID;
		indexSkewLeftDown = INVALID;
		indexSkewRightUp = B1;
		indexSkewRightDown = INVALID;
	}

	public char getLetter() {
		return letter;
	}
	
	public void setLetter(char ch) {
		letter = ch;
	}
	
	public List<MorrisIntersection> getNeighbor() {
		List<MorrisIntersection> ret = new ArrayList<MorrisIntersection>();
		if (hasLeft()) {
			ret.add(left());
		}
		if (hasRight()) {
			ret.add(right());
		}
		if (hasUp()) {
			ret.add(up());
		}
		if (hasDown()) {
			ret.add(down());
		}
		if (this.hasSkewLeftUp()) {
			ret.add(skewLeftUp());
		}
		if (this.hasSkewLeftDown()) {
			ret.add(skewLeftDown());
		}
		if (this.hasSkewRightUp()) {
			ret.add(skewRightUp());
		}
		if (this.hasSkewRightDown()) {
			ret.add(skewRightDown());
		}
		return ret;
	}

	private boolean hasDown() {
		return isValidIndex(indexDown);
	}

	private boolean hasUp() {
		return isValidIndex(indexUp);
	}

	private boolean hasRight() {
		return isValidIndex(indexRight);
	}

	private boolean hasLeft() {
		return isValidIndex(indexLeft);
	}
	
	private boolean hasSkewLeftDown() {
		return isValidIndex(indexSkewLeftDown);
	}

	private boolean hasSkewLeftUp() {
		return isValidIndex(indexSkewLeftUp);
	}

	private boolean hasSkewRightUp() {
		return isValidIndex(indexSkewRightUp);
	}

	private boolean hasSkewRightDown() {
		return isValidIndex(indexSkewRightDown);
	}

	private boolean isValidIndex(int index) {
		return (index < INVALID ? true : false);
	}
	
	public MorrisIntersection left() {
		return getNeighborFromParentBD(indexLeft);
	}
	
	public MorrisIntersection right() {
		return getNeighborFromParentBD(indexRight);
	}
	
	public MorrisIntersection up() {
		return getNeighborFromParentBD(indexUp);
	}

	public MorrisIntersection down() {
		return getNeighborFromParentBD(indexDown);
	}
	
	public MorrisIntersection skewRightDown() {
		return getNeighborFromParentBD(this.indexSkewRightDown);
	}

	public MorrisIntersection skewRightUp() {
		return getNeighborFromParentBD(this.indexSkewRightUp);
	}

	public MorrisIntersection skewLeftDown() {
		return getNeighborFromParentBD(this.indexSkewLeftDown);
	}

	public MorrisIntersection skewLeftUp() {
		return getNeighborFromParentBD(this.indexSkewLeftUp);
	}
	
	private MorrisIntersection getNeighborFromParentBD(int indexOnBD) {
		MorrisIntersection ret = null;
		if ((indexOnBD < MorrisBoard.TOTALPOS) && (parentBD != null)) {
			ret = parentBD.getIntersection(indexOnBD);
		}
		return ret;
	}
	
	public boolean isWhite() {
		return isLetter('W');
	}
	
	public boolean isBlack() {
		return isLetter('B');
	}
	
	public boolean isEmpty() {
		return isLetter('x');
	}

	private boolean isLetter(char c) {
		return (letter == c ? true : false);
	}

	public boolean isCloseMill() {
		boolean ret = false;
		
		List<MorrisLineAdjacent> possibleMills = new ArrayList<MorrisLineAdjacent>();
		possibleMills.addAll(getRowLineAdjacent());
		possibleMills.addAll(getColumnLineAdjacent());
		possibleMills.addAll(getSkewUpLineAdjacent());
		possibleMills.addAll(getSkewDownLineAdjacent());
		if (possibleMills.size() > 0) {
			ret = isLinePiecesSameWithMe(possibleMills);
		}
		return ret;
	}

	private List<MorrisLineAdjacent> getSkewDownLineAdjacent() {
		List<MorrisLineAdjacent> lines = new ArrayList<MorrisLineAdjacent>();
		boolean bslu = this.hasSkewLeftUp();
		boolean bsrd = this.hasSkewRightDown();
		MorrisLineAdjacent oneLine = null;
		if (bslu && bsrd) {	//this is in the middle
			oneLine = new MorrisLineAdjacent(this, this.skewLeftUp(), this.skewRightDown());
			lines.add(oneLine);
		}
		else {
			if (bslu) {	//this is in the most skew right down
				MorrisIntersection slu = this.skewLeftUp();
				if (slu.hasSkewRightUp()) {
					oneLine = new MorrisLineAdjacent(this, slu, slu.skewLeftUp());
					lines.add(oneLine);
				}
			}
			if (bsrd) {	//this is in the most skew left up
				MorrisIntersection srd = this.skewRightDown();
				if (srd.hasSkewRightDown()) {
					oneLine = new MorrisLineAdjacent(this, srd, srd.skewRightDown());
					lines.add(oneLine);
				}
			}
		}
		return lines;
	}

	private List<MorrisLineAdjacent> getSkewUpLineAdjacent() {
		List<MorrisLineAdjacent> lines = new ArrayList<MorrisLineAdjacent>();
		boolean bsru = this.hasSkewRightUp();
		boolean bsld = this.hasSkewLeftDown();
		MorrisLineAdjacent oneLine = null;
		if (bsru && bsld) {	//this is in the middle
			oneLine = new MorrisLineAdjacent(this, this.skewRightUp(), this.down());
			lines.add(oneLine);
		}
		else {
			if (bsru) {	//this is in the most skew left down
				MorrisIntersection sru = this.skewRightUp();
				if (sru.hasSkewRightUp()) {
					oneLine = new MorrisLineAdjacent(this, sru, sru.skewRightUp());
					lines.add(oneLine);
				}
			}
			if (bsld) {	//this is in the most skew right up
				MorrisIntersection sld = this.skewLeftDown();
				if (sld.hasSkewRightDown()) {
					oneLine = new MorrisLineAdjacent(this, sld, sld.skewLeftDown());
					lines.add(oneLine);
				}
			}
		}
		return lines;
	}

	private List<MorrisLineAdjacent> getColumnLineAdjacent() {
		List<MorrisLineAdjacent> lines = new ArrayList<MorrisLineAdjacent>();
		boolean bu = this.hasUp();
		boolean bd = this.hasDown();
		MorrisLineAdjacent oneLine = null;
		if (bu && bd) {	//this is in the middle
			oneLine = new MorrisLineAdjacent(this, this.up(), this.down());
			lines.add(oneLine);
		}
		else {
			if (bu) {	//this is in the most down
				MorrisIntersection u = this.up();
				if (u.hasUp()) {
					oneLine = new MorrisLineAdjacent(this, u, u.up());
					lines.add(oneLine);
				}
			}
			if (bd) {	//this is in the most up
				MorrisIntersection d = this.down();
				if (d.hasDown()) {
					oneLine = new MorrisLineAdjacent(this, d, d.down());
					lines.add(oneLine);
				}
			}
		}
		return lines;
	}

	private List<MorrisLineAdjacent> getRowLineAdjacent() {
		List<MorrisLineAdjacent> lines = new ArrayList<MorrisLineAdjacent>();
		boolean bl = this.hasLeft();
		boolean br = this.hasRight();
		MorrisLineAdjacent oneLine = null;
		if (bl && br) {	//this is in the middle
			oneLine = new MorrisLineAdjacent(this, this.left(), this.right());
			lines.add(oneLine);
		}
		else {
			if (bl) {	//this is in the most right
				MorrisIntersection l = this.left();
				if (l.hasLeft()) {
					oneLine = new MorrisLineAdjacent(this, l, l.left());
					lines.add(oneLine);
				}
			}
			if (br) {	//this is in the most left
				MorrisIntersection r = this.right();
				if (r.hasRight()) {
					oneLine = new MorrisLineAdjacent(this, r, r.right());
					lines.add(oneLine);
				}
			}
		}
		return lines;
	}

	private boolean isLinePiecesSameWithMe(List<MorrisLineAdjacent> lines) {
		boolean ret = false;
		for (Iterator<MorrisLineAdjacent> iter = lines.iterator(); iter.hasNext(); ) {
			MorrisLineAdjacent adjLine = iter.next();
			if (adjLine.isMill()) {
				ret = true;
				break;
			}
		}
		return ret;
	}

}
