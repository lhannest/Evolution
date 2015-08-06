package neuralnetwork2.genes;

import genome.Inov;
import helpers.Equal;

class Gene {
	private final Inov inov;
	
	public Gene(Inov inov) {
		this.inov = inov;
	}
	
	Gene(Gene gene) {
		this.inov = gene.inov;
	}
	
	Gene copy() {
		return new Gene(this.inov);
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
}
