package neuralnetwork2.genes;

import genome.Inov;
import helpers.Equal;

class Gene {
	private final Inov inov;
	
	public Gene(Inov inov) {
		this.inov = inov;
	}
	
	/**
	 * Copy constructor, gene.equals(new Gene(gene)) evaluates to true.
	 * @param node
	 */
	public Gene(Gene gene) {
		this.inov = gene.inov;
	}
	
	@Override
	public boolean equals(Object other) {
		boolean valid = (this instanceof Gene) && (other instanceof Gene);
		
		if (other == null) {
			return false;
		} else if (!valid) {
			return false;
		} else {
			Gene thisGene = (Gene) this;
			Gene otherGene = (Gene) other;
			
			return this.inov.equals(otherGene.inov);
		}
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + "[inov=" + inov + "]";
	}
}
