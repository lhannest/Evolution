package neuralnetwork2.genes;

import genome.Inov;

public class InputNode extends Node {

	public InputNode(Inov inov) {
		super(inov);
	}
	
	@Override
	public void setVisited(boolean value) {
		throw new InvalidNodeOperationException("Cannot make this node unvisited");
	}
	
	@Override
	public boolean isVisited() {
		return true;
	}
}
