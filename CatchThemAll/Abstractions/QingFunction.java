package Abstractions;

import java.util.Queue;

public abstract class QingFunction {

	// The quing function will place nodes in a regular queue, in the order that they should be extracted in.
	// for example, if we have a lIFO system. Then the queuing function will arrange the queue so that the 
	// new node will go to the front of the queue. Therefore dequeue produces a stack.
	
	/**
	 * This method receives the frontier queue which contains nodes that will be expanded and
	 * the new nodes created by applying all the possible operations to the current expanded node.
	 * These new nodes will be added to the frontier. The frontier is reordered depending on the 
	 * search strategy used to solve the problem.
	 *
	 * @param  nodes  queue of nodes to be expanded
	 * @param  expandedNodes the nodes that will be added to nodes queue
	 * @return      A queue of nodes ordered according to the queuing function
	 * @see         Node
	 */
	public abstract Queue<Node> updateQueue(Queue<Node> nodes, Node[] expandedNodes);
	
}
