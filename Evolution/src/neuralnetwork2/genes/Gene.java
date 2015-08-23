package neuralnetwork2.genes;

import genome.Inov;
import helpers.Equal;

class Gene implements Comparable<Gene> {
	private Inov inov;
	
	protected void setInov(Inov inov) {
		this.inov = inov;
	}
	
	public Gene() {
		
	}
	
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
	
	public boolean hasInov(int inovationNumber) {
		return this.inov.isInov(inovationNumber);
	}
	
	@Override
	public String toString() {
		return this.hashCode() + getClass().getSimpleName() + "[inov=" + inov + "]";
	}

	@Override
	public int compareTo(Gene other) {
		return this.inov.compareTo(other.inov);
	}
}
