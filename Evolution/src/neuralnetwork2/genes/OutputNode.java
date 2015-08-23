package neuralnetwork2.genes;

import genome.Inov;

public class OutputNode extends Node {

	public OutputNode() {
		super(Inov.makeZero());
	}
	
	protected OutputNode(OutputNode node) {
		super(node);
	}
	
	@Override
	public OutputNode copy() {
		return new OutputNode(this);
	}
}
