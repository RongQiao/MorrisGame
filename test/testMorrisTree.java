import static org.junit.Assert.*;

import org.junit.Test;


public class testMorrisTree {

	//test tree's basic structure
	@Test
	public void testNodeInit() {
		Node<Integer> node = new Node<Integer>(3);
		assertTrue(node.getData() == 3);
		assertTrue(node.getParent() == null);
		assertTrue(node.countChildren() == 0);
	}
	
	@Test
	public void testTreeInit() {
		Tree<Integer> tree = new Tree<Integer> (3);
		Node<Integer> root = tree.getRoot();
		assertTrue(root.getData() == 3);
		assertTrue(root.getParent() == null);
		assertTrue(root.countChildren() == 0);
		assertTrue(root.getDepth() == 0);
	}
	
	//test tree's operation by building a tree
	@Test
	public void testBuildTree() {
		Tree<Integer> tree = new Tree<Integer> ();
		//add first level children, 1
		Node<Integer> root = tree.getRoot();
		root.addChild(new Node<Integer>());
		Node<Integer> c1 = root.getFirstChild();
		assertTrue(c1.getData() == null);
		//add second level children, 3
		c1.addChild(new Node<Integer> ());
		c1.addChild(new Node<Integer> ());
		c1.addChild(new Node<Integer> ());
		assertTrue(c1.countChildren() == 3);
		//add third level children, 3 * 3
		int[][] leafData = {
				{8,7,2},
				{9,1,6},
				{2,4,1}
		};
		Node<Integer> c2 = c1.getFirstChild();
		int i = 0;
		while (c2 != null) {
			c2.addChild(new Node<Integer> (leafData[i][0]));
			c2.addChild(new Node<Integer> (leafData[i][1]));
			c2.addChild(new Node<Integer> (leafData[i][2]));
			c2 = c2.next();
			i++;
		}
		assertTrue(tree.countDepth() == 3);
		Node<Integer> leaf = root;
		while (leaf.countChildren() > 0) {
			leaf = leaf.getFirstChild();
		}
		assertTrue(leaf.getData() == 8);			
	}
		
	@Test
	public void testMorrisTreeInit() {
		String posStr = "xxxxxBxWWWWWBBBBxxxxxxx";
		MorrisTree mTree = MorrisTree.getInstance(posStr);		
		assertTrue(mTree != null);
		MorrisBoard mBD = mTree.getBoard();
		Estimation est = mTree.getEstimation();
		assertTrue(mBD != null);
		assertTrue(est == null);
	}
	
}
