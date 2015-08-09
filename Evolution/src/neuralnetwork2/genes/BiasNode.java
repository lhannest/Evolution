package neuralnetwork2.genes;

import genome.Inov;

public class BiasNode extends InputNode {

	public BiasNode(Inov inov) {
		super(inov);
	}
	
	/**
	 * Copy constructor, node.equals(new BiasNode(node)) evaluates to true.
	 * @param node
	 */
	protected BiasNode(BiasNode node) {
		super(node);
	}
	
	@Override
	public BiasNode copy() {
		return new BiasNode(this);
	}
	
	@Override
	public void setOutputValue(double value) {
		throw new InvalidNodeOperationException("Cannot modify the output value of this node");
	}
	
	@Override
	public double getOutputValue() {
		return -1;
	}
}
