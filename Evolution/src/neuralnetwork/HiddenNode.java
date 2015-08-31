package neuralnetwork;


public class HiddenNode extends Node {
	public HiddenNode() {
		super();
	}
	
	protected HiddenNode(HiddenNode node) {
		super(node);
	}

	@Override
	protected Node callCopyConstructor() {
		return new HiddenNode(this);
	}
}
