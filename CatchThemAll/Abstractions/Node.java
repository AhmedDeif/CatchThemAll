package Abstractions;

public class Node {
	
	State state;
	Node parent;
	Operation operation;
	int depth;
	int pathCost;
	boolean isRoot;

	public Node(State state, Node parent, int depth, int pathCost, boolean isRoot) {
		this.state = state;
		this.parent = parent;
		this.depth = depth;
		this.pathCost = pathCost;
		this.isRoot = isRoot;
	}
	
}
