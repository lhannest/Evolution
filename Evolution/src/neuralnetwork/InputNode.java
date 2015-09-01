package neuralnetwork;


final public class InputNode extends Node {
	public InputNode() {
		super();
	}
	
	protected InputNode(InputNode node) {
		super(node);
	}

	@Override
	protected Node callCopyConstructor() {
		return new InputNode(this);
	}
	
	@Override
	public boolean isVisited() {
		return true;
	}
	
	@Override
	public void setVisited(boolean value) { };
}
