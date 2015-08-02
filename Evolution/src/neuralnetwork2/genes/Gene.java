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
		if (!Equal.isSameClass(this, other)) {
			return false;
		} else {
			Gene otherGene = (Gene) other;
			return this.inov.equals(otherGene.inov);
		}
	}
}
