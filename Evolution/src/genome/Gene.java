package genome;

import helpers.Equal;

public class Gene implements Comparable<Gene> {
	private final Inov inov;
	
	public Gene(Inov inov) {
		this.inov = inov;
	}
	
	protected Inov getInov() {
		return this.inov;
	}

	@Override
	public int compareTo(Gene other) {
		return this.inov.compareTo(other.inov);
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
