package neuralnetwork3;

import helpers.Random;

class Arc extends Component {
	private final double DEFAULT_VALUE = 1;
	
	private double weight;
	private Node parent;
	private Node child;
	
	public Node getParent() {
		return this.parent;
	}
	
	public Node getChild() {
		return this.child;
	}
	
	public Arc(Node parent, Node child) {
		this.parent = parent;
		this.child = child;
		this.weight = Random.randomDouble(-DEFAULT_VALUE, DEFAULT_VALUE);
		
		callAddArc();
	}
	
	protected Arc(Arc other, Node parent, Node child) {
		super(other);
		this.parent = parent;
		this.child = child;
		this.weight = other.weight;
		
		callAddArc();
	}
	
	protected Arc(Arc other, Node parent, Node child, Inov inov) {
		super(inov);
		this.parent = parent;
		this.child = child;
		this.weight = other.weight;
		
		callAddArc();
	}
	
	public Arc copy(Node parent, Node child) {
		return new Arc(this, parent, child);
	}
	
	protected Arc copyWithNewInov(Node parent, Node child, Inov inov) {
		return new Arc(this, parent, child, inov);
	}
	
	private void callAddArc() {
		this.parent.addArc(this);
		this.child.addArc(this);
	}
}
