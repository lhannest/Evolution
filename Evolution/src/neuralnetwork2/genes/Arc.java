package neuralnetwork2.genes;

import genome.Inov;

class Arc extends Gene {
	public final Node PARENT;
	public final Node CHILD;
	public final double WEIGHT;
	
	public Arc(Inov inov, Node parent, Node child, double weight) {
		super(inov);
		this.PARENT = parent;
		this.CHILD = child;
		this.WEIGHT = weight;
	}
	
}
