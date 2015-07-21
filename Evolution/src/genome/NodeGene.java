package genome;

class NodeGene extends Gene {
	private final NodeType type;
	
	public NodeGene(Inov inov, NodeType type) {
		super(inov);
		this.type = type;
	}
	
	public NodeType getType() {
		return this.type;
	}
}