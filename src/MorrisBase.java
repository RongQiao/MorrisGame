import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public abstract class MorrisBase implements IMorrisEvaluateAlgorithm{
	public MorrisTree mTree;

	public static boolean checkArgs(String args[]) {
		boolean argsValid = false;
		
		if (args.length >= 3) {
			String inFN = args[0];
			//check validation of args
			if (UIController.isFileExist(inFN)) {
				argsValid = true;
			}
		}
		return argsValid;
	}
	
	public boolean checkDepth(int depth) {
		MorrisBoard inputBd = mTree.getBoard();
		int depthLimit = getSearchDepthLimit(inputBd);
		if (depth <= depthLimit) {
			if (depthLimit == MorrisPhaseStrategyGame.UNVALIED) {
				System.out.println("Unvalid input position for play game.");
			}
			return true;
		}
		else {
			return false;
		}
	}
	
	public static List<MorrisBoard> generateAdd(MorrisBoard crtBoard) {
		List<MorrisBoard> possibleBoard = new ArrayList<MorrisBoard> ();
		
		for (int indexPos = 0; indexPos < MorrisBoard.TOTALPOS; indexPos++) {
			MorrisIntersection intersection = crtBoard.getIntersection(indexPos);
			if (intersection.isEmpty()) {
				MorrisBoard newBoard = new MorrisBoard(crtBoard);
				newBoard.addWhite(indexPos);
				if (newBoard.closeMill(indexPos)) {
					generateRemove(newBoard, possibleBoard);
				}
				else {
					possibleBoard.add(newBoard);
				}
			}
		}
		
		return possibleBoard;		
	}

	private static void generateRemove(MorrisBoard crtBoard, List<MorrisBoard> possibleBoard) {
		for (int indexPos = 0; indexPos < MorrisBoard.TOTALPOS; indexPos++) {
			MorrisIntersection intersection = crtBoard.getIntersection(indexPos);
			if (intersection.isBlack()) {
				if (!crtBoard.closeMill(indexPos)) {
					MorrisBoard newBoard = new MorrisBoard(crtBoard);
					newBoard.setEmpty(indexPos);			
					possibleBoard.add(newBoard);
				}
			}
		}
	}

	public static List<MorrisBoard> generateMove(MorrisBoard crtBoard) {
		List<MorrisBoard> possibleBoard = new ArrayList<MorrisBoard> ();
		
		for (int indexMoveFrom = 0; indexMoveFrom < MorrisBoard.TOTALPOS; indexMoveFrom++) {
			MorrisIntersection intersection = crtBoard.getIntersection(indexMoveFrom);
			if (intersection.isWhite()) {
				List<MorrisIntersection> neighbors = intersection.getNeighbor();
				for (Iterator<MorrisIntersection> iter = neighbors.iterator(); iter.hasNext(); ) {
					MorrisIntersection nghb = iter.next();
					if (nghb.isEmpty()) {
						MorrisBoard newBoard = new MorrisBoard(crtBoard);
						newBoard.setEmpty(indexMoveFrom);
						int indexMoveTo = nghb.getIndex();
						newBoard.addWhite(indexMoveTo);
						if (newBoard.closeMill(indexMoveTo)) {
							generateRemove(newBoard, possibleBoard);
						}
						else {
							possibleBoard.add(newBoard);
						}							
					}
				}
			}
		}
		
		return possibleBoard;
	}

	public static List<MorrisBoard> generateHopping(MorrisBoard crtBoard) {
		List<MorrisBoard> possibleBoard = new ArrayList<MorrisBoard> ();
		
		for (int indexFrom = 0; indexFrom < MorrisBoard.TOTALPOS; indexFrom++) {
			MorrisIntersection posFrom = crtBoard.getIntersection(indexFrom);
			if (posFrom.isWhite()) {
				for (int indexJumpTo = 0; indexJumpTo < MorrisBoard.TOTALPOS; indexJumpTo++) {
					MorrisIntersection posTo = crtBoard.getIntersection(indexJumpTo);
					if (posTo.isEmpty()) {
						MorrisBoard newBoard = new MorrisBoard(crtBoard);
						newBoard.setEmpty(indexFrom);
						newBoard.addWhite(indexJumpTo);
						if (newBoard.closeMill(indexJumpTo)) {
							generateRemove(newBoard, possibleBoard);
						}
						else {
							possibleBoard.add(newBoard);
						}
					}
				}
			}
		}		
		return possibleBoard;
	}	

	/*
	 * if the low depth has decided WIN or LOSE, shouldn't continue to expand to higher depth, which is useless and exhaust time and space
	 * for example: with input xxxxxBxWWxWxxxBBxxxxxxx, W move to b3 from e2 can be figured out as WIN on depth 1, 
	 * it's unnecessary to continue to next depth
	 * it's better to use breadth-first traversal, 3/31/2014
	 */
	public void expandTreeDF(Node<MorrisNodeData> crtNode, int depth) {
		int expectedDep = depth;
		if (crtNode.getDepth() < expectedDep) {
			MorrisBoard crtBd = crtNode.getData().getBoard();
			List<MorrisBoard> subBd = generateAction(crtBd);
			//for test
			/*
			System.out.print(crtNode.getDepth());
			System.out.print("-");
			System.out.print(subBd.size());
			System.out.print(",");*/
			
			if (subBd.size() > 0) {
				for (Iterator<MorrisBoard> iter = subBd.iterator(); iter.hasNext(); ) {
					MorrisBoard childBd = iter.next();
					MorrisNodeData childData = new MorrisNodeData();
					childData.setBoard(childBd);
					Node<MorrisNodeData> child = new Node<MorrisNodeData> (childData);
					crtNode.addChild(child);
					
					if (child.getDepth() == expectedDep) {
						//leaf node
						Estimation leafEst = calculateEstimation(childBd);
						childData.setEstim(leafEst);
					}
					else {
						expandTree(child, expectedDep);
					}
				}
			}
			else {
				//current node has no possible sub node, so it's leaf node
				Estimation est = calculateEstimation(crtBd);
				crtNode.getData().setEstim(est);
			}
		}
	}
	
	/*
	 * below information shows the optimized rsult by change the expand tree from depth-first to breadth-first
	E:\Java\Project\MorrisGame\bin>java MiniMaxGame white3w3b.txt black3w3b.txt 3
	Board Position: xxxxxxxxWWWxxxBBxxxxxxx
	Positions evaluated by static estimation: 130977
	MINIMAX estimate: 10000
	
	E:\Java\Project\MorrisGame\bin>java MiniMaxGame white3w3b.txt black3w3b.txt 2
	Board Position: xxxxxxxxWWWxxxBBxxxxxxx
	Positions evaluated by static estimation: 2605
	MINIMAX estimate: 10000
	
	E:\Java\Project\MorrisGame\bin>java MiniMaxGame white3w3b.txt black3w3b.txt 1
	Board Position: xxxxxxxxWWWxxxBBxxxxxxx
	Positions evaluated by static estimation: 53
	MINIMAX estimate: 10000
	
	E:\Java\Project\MorrisGame\bin>java MiniMaxGame white3w3b.txt black3w3b.txt 1
	Board Position: xxxxxxxxWWWxxxBBxxxxxxx
	Positions evaluated by static estimation: 53
	MINIMAX estimate: 10000
	
	E:\Java\Project\MorrisGame\bin>java MiniMaxGame white3w3b.txt black3w3b.txt 2
	Board Position: xxxxxxxxWWWxxxBBxxxxxxx
	Positions evaluated by static estimation: 53
	MINIMAX estimate: 10000
	
	E:\Java\Project\MorrisGame\bin>java MiniMaxGame white3w3b.txt black3w3b.txt 3
	Board Position: xxxxxxxxWWWxxxBBxxxxxxx
	Positions evaluated by static estimation: 53
	MINIMAX estimate: 10000
	 */
	public void expandTree(Node<MorrisNodeData> crtNode, int depth) {
		int expectedDep = depth;
		if (crtNode.getDepth() < expectedDep) {
			MorrisBoard crtBd = crtNode.getData().getBoard();
			List<MorrisBoard> subBd = generateAction(crtBd);

			if (subBd.size() == 0) {
				//current node is leaf, set estimation, the expanding is done
				Estimation est = calculateEstimation(crtBd);
				crtNode.getData().setEstim(est);
			}
			else {
				boolean toNextDepth = true;
				for (Iterator<MorrisBoard> iter = subBd.iterator(); iter.hasNext(); ) {
					MorrisBoard childBd = iter.next();
					MorrisNodeData childData = new MorrisNodeData();
					childData.setBoard(childBd);
					Node<MorrisNodeData> child = new Node<MorrisNodeData> (childData);
					crtNode.addChild(child);
					//check if we need expand the current level nodes, if it's set to be false by previous node, then don't need check again
					if (toNextDepth) {
						//1. this level has WIN or LOSE estimation, needn't to next depth					
						Estimation childEst = calculateEstimation(childBd);
						if (childEst.isWin() || childEst.isLose()) {
							toNextDepth = false;
						}
						//2. the depth of this level is the expected depth, don't go to next
						if (child.getDepth() == expectedDep) {
							toNextDepth = false;
						}						
					}

				}
				//added all children to current node, set estimation for children if they are leaves, otherwise continue to expand
				Node<MorrisNodeData> childNode = crtNode.getFirstChild();
				while (childNode != null) {
					if (toNextDepth) {
						expandTree(childNode, expectedDep);
					}
					else {
						Estimation est = calculateEstimation(childNode.getData().getBoard());
						childNode.getData().setEstim(est);
					}
					childNode = childNode.next();
				}
			}
		}
	}
	
	public String[] orgPlayResultResponse() {
		String[] rst = new String[3];
		rst[0] = "Board Position: " + getResultStr();
		rst[1] = "Positions evaluated by static estimation: ";
		rst[1] += getEvaluatedCnt();
		rst[2] = "MINIMAX estimate: ";
		rst[2] += getEstimation();
		return rst;
	}
	
	public void showPlayResult(String[] rstStr) {
		for (int i = 0; i < rstStr.length; i++) {
			System.out.println(rstStr[i]);
		}
	}
	

	public String getResultStr() {
		String rstStr = new String();
		
		Node<MorrisNodeData> root = mTree.getRoot();
		
		Node<MorrisNodeData> nextMoveNode = null;
		Estimation parentEst = root.getData().getEstim();
		Node<MorrisNodeData> child = root.getFirstChild();
		while (child != null) {
			Estimation childEst = child.getData().getEstim();
			if (childEst.isEqual(parentEst)) {
				nextMoveNode = child;
				break;
			}
			child = child.next();
		}
		
		if (nextMoveNode != null) {
			MorrisBoard bd = nextMoveNode.getData().getBoard();
			rstStr = bd.convertPosI2C();
		}
		return rstStr;
	}

	//the search target is a leaf node whose estimation == crtNode.estimation
	@SuppressWarnings("unused")
	private Node<MorrisNodeData> search(Node<MorrisNodeData> crtNode) {
		Node<MorrisNodeData> leaf = null;
		
		if (!crtNode.hasChildren()) {
			leaf = crtNode;
		}
		else {
			Estimation parentEst = crtNode.getData().getEstim();
			Node<MorrisNodeData> child = crtNode.getFirstChild();
			while (child != null) {
				Estimation childEst = child.getData().getEstim();
				if (childEst.isEqual(parentEst)) {
					leaf = search(child);
					if (leaf != null) {
						//got it
						break;
					}
				}
				child = child.next();
			}
		}
		
		return leaf;
	}

	public int getEstimation() {
		Estimation rootEst = mTree.getEstimation();
		return rootEst.getValue();
	}
	
	/*
	 * precondition: the unique MorrisTree is created, which only has one root node with the input position
	 * 1. expand the tree according given depth, during expand process, give leaf estimation
	 * 2. apply algorithm to get the best move
	 * postcondition: 
	 * a. root.estimation is set
	 * b. evaluated positions are counted 
	 */
	public void play(int depth) {
		Node<MorrisNodeData> root = mTree.getRoot();
		expandTree(root, depth);
		applyAlgorithm(root);
	}
	

	public static String swapPosStr(String posStr) {
		String swaped = new String();
		for (int i = 0; i < posStr.length(); i++) {
			char ch = posStr.charAt(i);
			switch(ch) {
			case 'W':
				swaped += 'B';
				break;
			case 'B':
				swaped += 'W';
				break;
			case 'x':
				swaped += ch;
				break;
			default:
				break;
			}
		}
		return swaped;
	}
	
	public abstract Estimation calculateEstimation(MorrisBoard board);
	public abstract List<MorrisBoard> generateAction(MorrisBoard board);
	public abstract int getSearchDepthLimit(MorrisBoard board);
	public abstract int getEvaluatedCnt();
}
