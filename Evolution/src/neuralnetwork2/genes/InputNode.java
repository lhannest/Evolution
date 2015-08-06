package neuralnetwork2.genes;

import genome.Inov;

public class InputNode extends Node {

	public InputNode(Inov inov) {
		super(inov);
	}
	
	/**
	 * Copy constructor, node.equals(new InputNode(node)) evaluates to true.
	 * @param node
	 */
	public InputNode(InputNode node) {
		super(node);
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
