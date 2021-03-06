import java.util.Iterator;
import java.util.List;


public class MorrisBoard {
	private MorrisIntersection[] position;

	public static final int TOTALPOS = 23;

	public static final int ROWC = 2;
	public static final int COLUMN3 = 3;

	//empty board, every position is empty
	MorrisBoard() {
		position = new MorrisIntersection[TOTALPOS]; 
		for (int i = 0; i < position.length; i++) {
			position[i] = new MorrisIntersection(i, this);
		}
	}
	
	//board with given position string
	public MorrisBoard(String posStr) {
		position = new MorrisIntersection[TOTALPOS]; 
		if (posStr.length() == position.length) {
			for (int i = 0; i < position.length; i++) {
				byte[] chs = posStr.getBytes();
				position[i] = new MorrisIntersection(i, (char) chs[i], this);
			}
		}
	}

	//copy from given board
	public MorrisBoard(MorrisBoard crtBoard) {
		String posStr = crtBoard.convertPosI2C();
		position = new MorrisIntersection[TOTALPOS]; 
		if (posStr.length() == position.length) {
			for (int i = 0; i < position.length; i++) {
				byte[] chs = posStr.getBytes();
				position[i] = new MorrisIntersection(i, (char) chs[i], this);
			}
		}
	}

	public String convertPosI2C() {
		String posStr = new String();
		for (int i = 0; i < TOTALPOS; i++) {
			posStr += position[i].getLetter();
		}
		return posStr;
	}

	public int countPosition() {
		return position.length;
	}

	public int countWhite() {
		return countLetter('W');
	}

	public int countBlack() {
		return countLetter('B');
	}

	public int countEmpty() {
		return countLetter('x');
	}
	
	private int countLetter(char letter) {
		int cnt = 0;
		for (int i = 0; i < position.length; i++) {
			if (position[i] != null) {
				if (position[i].getLetter() == letter) {
					cnt++;
				}				
			}
		}
		return cnt;
	}

	public MorrisIntersection getIntersection(int index) {
		MorrisIntersection ret = null;
		if (index < position.length ) {
			ret = position[index];
		}
		return ret;
	}

	public void addWhite(int index) {
		setPiece(index, 'W');
	}
	
	public void setEmpty(int index) {
		setPiece(index, 'x');
	}
	
	private void setPiece(int index, char letter4Piece) {
		if (index < TOTALPOS) {
			position[index].setLetter(letter4Piece);
		}
	}

	public boolean closeMill(int index) {
		boolean ret = false;
		MorrisIntersection mi = getIntersection(index);
		if (mi != null) {
			if (mi.isCloseMill()) {
				ret = true;				
			}
		}
		return ret;
	}

	public MorrisBoard swap() {
		String crtPosStr = convertPosI2C();	
		String swapStr = new String();
		for (int i = 0; i < TOTALPOS; i++) {
			char ch = crtPosStr.charAt(i);
			switch (ch) {
			case 'x':
				swapStr += ch;
				break;
			case 'W':
				swapStr += 'B';
				break;
			case 'B':
				swapStr += 'W';
				break;
			default:
				break;
			}
		}
		MorrisBoard newBoard = new MorrisBoard(swapStr);
		
		return newBoard;
	}

	public int countEmptyNeighbor4White() {
		return countEmptyNeighbor('W');
	}

	public int countEmptyNeighbor4Black() {
		return countEmptyNeighbor('B');
	}
	
	private int countEmptyNeighbor(char colorPiece) {
		int cnt = 0;
		int[] nIndex = new int[TOTALPOS];
		for (int t = 0; t < TOTALPOS; t++) {
			nIndex[t] = TOTALPOS;
		}
		for (int i = 0; i < position.length; i++) {
			MorrisIntersection pos = position[i];
			if (pos.getLetter() == colorPiece) {
				List<MorrisIntersection> neighbors = pos.getNeighbor();
				for (Iterator<MorrisIntersection> iter = neighbors.iterator(); iter.hasNext(); ) {
					MorrisIntersection ngh = iter.next();
					
					if (ngh.isEmpty()) {
						int emptyIndex = ngh.getIndex();
						boolean added = false;
						for (int j = 0; j < TOTALPOS; j++) {
							if (emptyIndex == nIndex[j]) {
								//this empty is added
								added = true;
								break;
							}
						}
						if (!added) {
							//add this empty to the array, and cnt + 1
							nIndex[cnt] = emptyIndex;
							cnt++;
						}
					}
				}
			}
		}
		return cnt;
	}

	

}
