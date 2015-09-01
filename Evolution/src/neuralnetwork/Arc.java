package neuralnetwork;

import helpers.Random;

public class Arc extends Component {
	public static class ArcFactory {
		public static Arc makeDefault(Node parent, Node child) {
			return null;
		}
	}
	
	private final double DEFAULT_VALUE = 1;
	private final double JIGGLE_AMOUNT = 0.1;
	
	private double weight;
	private Node parent;
	private Node child;
	
	public double getWeight() {
		return this.weight;
	}
	
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
	
	public void jiggleWeight() {
		this.weight += Random.randomDouble(-JIGGLE_AMOUNT, JIGGLE_AMOUNT);
	}
	
	public Arc copy(Node parent, Node child) {
		return new Arc(this, parent, child);
	}
	
	private void callAddArc() {
		this.parent.addArc(this);
		this.child.addArc(this);
	}
}
