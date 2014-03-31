import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public abstract class MorrisBase implements IMorrisEvaluateAlgorithm{
	public MorrisTree mTree;

	public static boolean checkArgs(String args[]) {
		boolean argsValid = false;
		
		if (args.length == 3) {
			String inFN = args[0];
			String depthStr = args[2];
			int depth = Integer.parseInt(depthStr);
			//check validation of args
			if ((UIController.isFileExist(inFN) 
					&& (depth < 100))) {
				argsValid = true;
			}
		}
		return argsValid;
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

	public void expandTree(Node<MorrisNodeData> crtNode, int depth) {
		int expectedDep = depth;
		if (crtNode.getDepth() < depth) {
			MorrisBoard crtBd = crtNode.getData().getBoard();
			List<MorrisBoard> subBd = generateAction(crtBd);
			//for test
			System.out.print(crtNode.getDepth());
			System.out.print("-");
			System.out.print(subBd.size());
			System.out.print(",");
			
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
	
	public void showPlayResult() {
		String line1 = "Board Position: " + getResultStr();
		System.out.println(line1);
		System.out.print("Positions evaluated by static estimation: ");
		System.out.println(getEvaluatedCnt());
		System.out.print("MINIMAX estimate: ");
		System.out.println(getEstimation());
	}
	

	public String getResultStr() {
		String rstStr = new String();
		
		Node<MorrisNodeData> root = mTree.getRoot();
		Node<MorrisNodeData> targetLeaf = search(root);
		if (targetLeaf != null) {
			MorrisBoard bd = targetLeaf.getData().getBoard();
			rstStr = bd.convertPosI2C();
		}
		return rstStr;
	}

	//the search target is a leaf node whose estimation == crtNode.estimation
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
	
	public abstract Estimation calculateEstimation(MorrisBoard board);
	public abstract List<MorrisBoard> generateAction(MorrisBoard board);
	//public abstract void play(int depth);
	public abstract int getEvaluatedCnt();
}
