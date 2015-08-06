package neuralnetwork2.genes;

import genome.Inov;
import helpers.Random;

public class Arc extends Gene {
	public final Node PARENT;
	public final Node CHILD;
	public final double WEIGHT;
	
	public Arc(Inov inov, Node parent, Node child, double weight) {
		super(inov);
		this.PARENT = parent;
		this.CHILD = child;
		this.WEIGHT = weight;
		
		this.PARENT.addArc(this);
		this.CHILD.addArc(this);
	}
	
	private Arc(Gene gene, Node parent, Node child, double weight) {
		super(gene);
		this.PARENT = parent;
		this.CHILD = child;
		this.WEIGHT = weight;
		
		this.PARENT.addArc(this);
		this.CHILD.addArc(this);
	}
	
	/**
	 * This method functions as a cloning method.
	 * <p>
	 * arc.equals(arc.copy(,)) evaluates true,<br>
	 * arc == arc.copy(,) evaluates false,<br>
	 * arc.PARENT.equals(arc.copy(,).PARENT) may be either true or false.
	 * <p>
	 * This method is intended to be used only when cloning one set of arcs from a parent neural network
	 * to a child neural network.
	 * @param parent the parent (outgoing) node of the arc
	 * @param child the child (incoming) node of the arc
	 * @return a copy of the arc, but with the specified parent and child.
	 */
	public Arc copy(Node parent, Node child) {
		return new Arc(super.copy(), parent, child, this.WEIGHT);
	}
	
	public Arc copyWithJiggledWeight(double amount) {
		double mutatedWeight = WEIGHT + Random.randomDouble(-amount, amount);
		
		return new Arc(copy(), PARENT, CHILD, mutatedWeight);
	}
}
