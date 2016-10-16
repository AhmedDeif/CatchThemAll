package Abstractions;

public abstract class Problem {

	/*
	 * The Problem class is a five tuple. A specific problem extends the problem class
	 * adjusting the state space with the initial state.
	 */
	
	Operation[] operations;
	State[] stateSpace;
	
	public Operation[] getOperations() {
		return operations;
	}
	public void setOperations(Operation[] operations) {
		this.operations = operations;
	}
	public State[] getStateSpace() {
		return stateSpace;
	}
	public void setStateSpace(State[] stateSpace) {
		this.stateSpace = stateSpace;
	}
	public State getInitState() {
		return initState;
	}
	public void setInitState(State initState) {
		this.initState = initState;
	}


	State initState;
	
	abstract boolean GoalTest(Node node);
	abstract int PathCost();
	abstract State TransitionModel(State state, Operation operation);
	abstract Node ChildNode(Node parent, Operation action);
	abstract Node[] expandNode(Node node);
	
	
	/*
	 * function CHILD-NODE(problem,parent,action) returns a node return a node with
			STATE = problem.RESULT(parent.STATE,action),
			PARENT = parent, ACTION = action,
			PATH-COST = parent.PATH-COST + problem.STEP-COST(parent.STATE,action)
	 */
	
	public Problem() {
		
	}
	
	
	
		
}
