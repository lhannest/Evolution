package neuralnetwork2.genes;

import genome.Inov;

public class HiddenNode extends Node {
	
	public HiddenNode() {
		super(Inov.makeZero());
	}

	public HiddenNode(Inov inov) {
		super(inov);
	}
	
	protected HiddenNode(HiddenNode node) {
		super(node);
	}
	
	@Override
	public HiddenNode copy() {
		return new HiddenNode(this);
	}

}
