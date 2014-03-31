import static org.junit.Assert.*;

import org.junit.Test;


public class testAlgorithm {


	/*
	 *  for test algorithm, build a tree with MorrisNodeData, leafs' value is 8,7,2,9,1,6,2,4,1
	 *  the expected evaluation result is 4
	 */
	private Tree<MorrisNodeData> buildTestTree() {
		Tree<MorrisNodeData> tree = new Tree<MorrisNodeData> (new MorrisNodeData());
		//add first level children, 1
		Node<MorrisNodeData> root = tree.getRoot();
		root.addChild(new Node<MorrisNodeData>(new MorrisNodeData()));
		Node<MorrisNodeData> c1 = root.getFirstChild();
		
		//add second level children, 3
		c1.addChild(new Node<MorrisNodeData> (new MorrisNodeData()));
		c1.addChild(new Node<MorrisNodeData> (new MorrisNodeData()));
		c1.addChild(new Node<MorrisNodeData> (new MorrisNodeData()));
		
		//add third level children, 3 * 3
		int[][] leafData = {
				{8,7,2},
				{9,1,6},
				{2,4,1}
		};
		Node<MorrisNodeData> c2 = c1.getFirstChild();
		int i = 0;
		while (c2 != null) {
			MorrisNodeData lData = new MorrisNodeData();
			lData.setEstim(new IntegerEstimation(leafData[i][0]));
			c2.addChild(new Node<MorrisNodeData> (lData));
			lData = new MorrisNodeData();
			lData.setEstim(new IntegerEstimation(leafData[i][1]));
			c2.addChild(new Node<MorrisNodeData> (lData));
			lData = new MorrisNodeData();
			lData.setEstim(new IntegerEstimation(leafData[i][2]));
			c2.addChild(new Node<MorrisNodeData> (lData));
			c2 = c2.next();
			i++;
		}
		return tree;
	}
	
	@Test
	public void testEvaluationMiniMax() {
		Tree<MorrisNodeData> tree = buildTestTree();
		Node<MorrisNodeData> root = tree.getRoot();
		//before evaluate, clear the count, then get the new count of evaluated nodes.
		EvaluateAlgorithmMiniMax.clearEvaluateCnt();
		EvaluateAlgorithmMiniMax.applyAlgorithm(root);
		assertTrue(root.getData().getEstim().getValue() == 4);
		assertTrue(EvaluateAlgorithmMiniMax.getEvaluateCnt() == 9);
	}

	@Test
	public void testEvaluationAlphaBeta() {
		Tree<MorrisNodeData> tree = buildTestTree();
		Node<MorrisNodeData> root = tree.getRoot();
		//before evaluate, clear the count, then get the new count of evaluated nodes.
		EvaluateAlgorithmAlphaBeta.clearEvaluateCnt();
		EvaluateAlgorithmAlphaBeta.applyAlgorithm(root);
		assertTrue(root.getData().getEstim().getValue() == 4);
		assertTrue(EvaluateAlgorithmAlphaBeta.getEvaluateCnt() == 7);
	}
}
