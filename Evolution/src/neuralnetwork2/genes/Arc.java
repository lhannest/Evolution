package neuralnetwork2.genes;

import genome.Inov;
import helpers.Random;

public class Arc extends Gene {
	private final Node parent;
	private final Node child;
	private double weight;
	
	public Arc(Inov inov, Node parent, Node child, double weight) {
		super(inov);
		this.parent = parent;
		this.child = child;
		this.weight = weight;
		
		this.parent.addArc(this);
		this.child.addArc(this);
	}
	
	/**
	 * Copy constructor, arc.equals(new Arc(arc, parent, child)) evaluates to true.
	 * @param arc
	 * @param parent
	 * @param child
	 */
	public Arc(Arc arc, Node parent, Node child) {
		super(arc);
		this.parent = parent;
		this.child = child;
		this.weight = arc.weight;
		
		this.parent.addArc(this);
		this.child.addArc(this);
	}
	
	/**
	 * Copy constructor, arc.equals(new Arc(arc, parent, child, 0)) evaluates to true.
	 * @param arc
	 * @param parent
	 * @param child
	 * @param weight
	 */
	public Arc(Arc arc, Node parent, Node child, double weight) {
		this(arc, parent, child);
		this.weight = weight;
	}
	
	public Node getParent() {
		return parent;
	}
	
	public Node getChild() {
		return child;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public void jiggleWeight(double amount) {
		this.weight += Random.randomDouble(-amount, amount);
	}
	
	public void randomizeWeight() {
		this.weight = Random.randomDouble(-1, 1);
	}
	
	@Override
	public String toString() {
		return super.toString() + "[parent = " + parent + ", child=" + child + "]";
	}
}
