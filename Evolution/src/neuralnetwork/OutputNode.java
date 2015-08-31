package neuralnetwork;


final public class OutputNode extends Node {
	public OutputNode() {
		super();
	}
	
	protected OutputNode(OutputNode node) {
		super(node);
	}

	@Override
	protected Node callCopyConstructor() {
		return new OutputNode(this);
	}
}
