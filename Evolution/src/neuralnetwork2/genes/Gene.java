package neuralnetwork2.genes;

import genome.Inov;

abstract class Gene {
	private final Inov inov;
	
	public Gene(Inov inov) {
		this.inov = inov;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		} else if (other.getClass() != this.getClass()) {
			return false;
		} else {
			Gene otherGene = (Gene) other;
			return this.inov.equals(otherGene.inov);
		}
	}

}
