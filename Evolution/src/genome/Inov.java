package genome;

class Inov implements Comparable<Inov> {
	private static int NEXT_INOVATION_NUMBER = 0;
	private final int inovationNumber;
	
	private Inov(int inovationNumber) {
		this.inovationNumber = inovationNumber;
	}
	
	public static Inov makeZero() {
		return new Inov(0);
	}
	
	public static Inov makeNext() {
		NEXT_INOVATION_NUMBER++;
		return new Inov(NEXT_INOVATION_NUMBER);
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "[inovationNumber=" + this.inovationNumber + "]";
	}

	@Override
	public int compareTo(Inov other) {
		return this.inovationNumber - other.inovationNumber;
	}
}
