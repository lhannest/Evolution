package neuralnetwork;

import helpers.Equal;

public final class Inov implements Comparable<Inov> {
	private static int NEXT_NUMBER = 0;	
	private final int number;
	
	private Inov(int number) {
		this.number = number;
	}
	
	/**
	 * Intended to be used only when creating the novel components of a NeuralNetwork
	 */
	public static Inov getNext() {
		NEXT_NUMBER++;
		return new Inov(NEXT_NUMBER);
	}
	
	/**
	 * Intended to be used only when creating the default components of a NeuralNetwork.
	 */
	public static Inov getNegative(int number) {
		return new Inov(- Math.abs(number));
	}
	
	public int getAge() {
		return NEXT_NUMBER - this.number;
	}

	@Override
	public int compareTo(Inov other) {
		return this.number - other.number;
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "[number=" + this.number + "]";
	}
	
	@Override
	public boolean equals(Object other) {
		if (!Equal.isSameClass(this, other)) {
			return false;
		} else {
			Inov otherInov = (Inov) other;
			return this.number == otherInov.number;
		}
	}
	
	public boolean isOverZero() {
		return this.number > 0;
	}
}
