package neuralnetwork3;

import helpers.Random;

import java.util.ArrayList;
import java.util.List;


class Node extends Component {
	private final double DEFAULT_VALUE = 1;
	
	private final List<Arc> arcList = new ArrayList<Arc>();
	private final double biasValue;
	
	public Node() {
		this.biasValue = Random.randomDouble(-DEFAULT_VALUE, DEFAULT_VALUE);
	}
	
	protected Node(Node other, Inov newInov) {
		super(newInov);
		this.biasValue = other.biasValue;
	}
	
	protected Node(Node other) {
		super(other);
		this.biasValue = other.biasValue;
	}
	
	protected Node copyWithNewInov(Inov inov) {
		return new Node(this, inov);
	}
	
	/**
	 * node.copy().equals(node) is true, whereas node.copy() == node is false.
	 * @return a copy of this node
	 */
	public Node copy() {
		return new Node(this);
	}
	
	/**
	 * This is only intended to be called by Arc's constructor
	 * @param arc
	 * @return
	 */
	protected boolean addArc(Arc arc) {
		return this.arcList.add(arc);
	}
}
