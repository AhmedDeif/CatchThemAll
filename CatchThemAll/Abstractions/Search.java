package Abstractions;

import java.util.LinkedList;
import java.util.Queue;

abstract class Search {

	public static Node GeneralSearch(Problem problem, QingFunction QingFunc) {
		Queue<Node> nodes = new LinkedList<Node>();		
		// The root node has not parent and is at depth 0.
		Node root = new Node(problem.getInitState(), null, 0, 0, true);
		nodes.add(root);
		boolean stop = false;
		while(!stop) {
			if(nodes.isEmpty()) {
				//	There is no solution.
				return null;
			} else {
				Node currentNode = nodes.poll();		
				//	Check goal test
				if(problem.GoalTest(currentNode)) {
					//	we found a solution.
					return currentNode;
				}
				nodes = QingFunc.updateQueue(nodes, problem.expandNode(currentNode));
			}
		}
		return null;
	}
}
