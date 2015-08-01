package neuralnetwork;

public class Arc {
	private final Node parent;
	private final Node child;
	private final double weight;
	
	public Arc(Node parent, Node child, double weight) {
		this.parent = parent;
		this.child = child;
		this.weight = weight;
	}
}
