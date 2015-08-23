package neuralnetwork2.genes;

import genome.Inov;

public class InputNode extends Node {

	public InputNode() {
		super(Inov.makeZero());
	}
	
	/**
	 * Copy constructor, node.equals(new InputNode(node)) evaluates to true.
	 * @param node
	 */
	protected InputNode(InputNode node) {
		super(node);
	}
	
	@Override
	public InputNode copy() {
		return new InputNode(this);
	}
	
	@Override
	public void setVisited(boolean value) {
		// Intentionally empty
	}
	
	@Override
	public boolean isVisited() {
		return true;
	}
}
