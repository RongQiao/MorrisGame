import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Node<T> {
	private T data;
	private Node<T> parent;
	private Node<T> nextSibling;
	private List<Node<T>> children;
	private int dep;
	
	Node(T data4node) {
		data = data4node;
		parent = null;
		nextSibling = null;
		children = new ArrayList<Node<T>> ();
		dep = 0;
	}
	
	Node() {
		data = null;
		parent = null;
		nextSibling = null;
		children = new ArrayList<Node<T>> ();
		dep = 0;
	}

	public T getData() {
		return data;
	}
	
	public Node<T> getParent() {
		return parent;
	}
	
	public void setParent(Node<T> pr) {
		parent = pr;
	}
	
	public int countChildren() {
		return children.size();
	}

	public boolean hasParent() {
		return (parent == null ? false : true);
	}
	
	public int getDepth() {
		return dep;
	}
	
	public void setDepth(int depth) {
		dep = depth;
	}

	public void addChild(Node<T> node) {
		node.setParent(this);
		node.setDepth(this.getDepth() + 1);
		int cntChildren = children.size();
		if (cntChildren > 0) {
			Node<T> lastChild = children.get(cntChildren - 1);
			lastChild.setNext(node);
		}
		children.add(node);
	}

	public Node<T> getFirstChild() {
		Node<T> ret = null;
		if (countChildren() > 0) {
			ret = children.get(0);
		}
		return ret;
	}

	private List<Node<T>> getChildren() {
		return children;
	}
	
	public Node<T> next() {
		return nextSibling;
		/*Node<T> sibling = null;
		if (hasParent()) {
			boolean gotit = false;
			List<Node<T>> sbls = parent.getChildren();
			for (Iterator<Node<T>> iter=sbls.iterator(); iter.hasNext(); ) {
				Node<T> crtNode = iter.next();
				if (gotit) {
					sibling = crtNode;
				}
				if (crtNode.equals(this)) {
					gotit = true;
				}
			}
		}
		return sibling;*/
	}
	
	public void setNext(Node<T> sibling) {
		nextSibling = sibling;
	}

	public int countSubDepth() {
		int cnt = 0;
		if (countChildren() > 0) {
			//get the maximum subDepth from all children
			int subCnt = 0;
			Node<T> c = this.getFirstChild();
			while (c != null) {
				int t = c.countSubDepth();
				if (t > subCnt) {
					subCnt = t;
				}
				c = c.next();
			}
			cnt = subCnt + 1;
		}
		return cnt;
	}

	public boolean hasChildren() {
		return (this.countChildren() > 0 ? true : false);
	}

	public boolean hasNext() {
		return (this.nextSibling == null ? false : true);
	}

	public void getLeaves(List<Node<T>> leaves) {
		if (hasChildren()) {
			for (Iterator<Node<T>> iter = children.iterator(); iter.hasNext(); ) {
				Node<T> child = iter.next();
				child.getLeaves(leaves);
			}
		}
		else {
			leaves.add(this);
		}
	}
}
