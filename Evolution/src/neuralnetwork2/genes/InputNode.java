package neuralnetwork2.genes;

import genome.Inov;

public class InputNode extends Node {

	public InputNode(Inov inov) {
		super(inov);
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
