package genome;

import helpers.Random;

class ArcGene extends Gene {
	private final static double DEFAULT_WEIGHT_MIN = 0;
	private final static double DEFAULT_WEIGHT_MAX = 1;
	
	private final NodeGene parent;
	private final NodeGene child;
	private final double weight;
	private final boolean enabled;
	
	public ArcGene(Inov inov, NodeGene parent, NodeGene child, double weight, boolean enabled) {
		super(inov);
		this.parent = parent;
		this.child = child;
		this.weight = weight;
		this.enabled = enabled;
	}
	
	/**
	 * The constructed ArcGene is enabled, and it's weight is randomized between
	 * DEFAULT_WEIGHT_MIN and DEFAULT_WEIGHT_MAX.
	 * @param inov
	 * @param parent
	 * @param child
	 */
	public ArcGene(Inov inov, NodeGene parent, NodeGene child) {
		super(inov);
		this.parent = parent;
		this.child = child;
		this.weight = Random.randomDouble(DEFAULT_WEIGHT_MIN, DEFAULT_WEIGHT_MAX);
		this.enabled = true;
	}
	
	public boolean isEnabled() {
		return this.enabled;
	}
	
	public double getWeight() {
		return this.weight;
	}
	
	public NodeGene getParent() {
		return this.parent;
	}
	
	public NodeGene getChild() {
		return this.child;
	}
}
