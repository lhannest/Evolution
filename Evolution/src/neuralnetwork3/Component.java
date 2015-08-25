package neuralnetwork3;

import helpers.Equal;

abstract class Component implements Comparable<Component> {
	private final Inov inov;
	
	protected Component() {
		inov = null;
	}
	
	protected Component(Inov inov) {
		this.inov = inov;
	}
	
	protected Component(Component other) {
		this.inov = other.inov;
	}
	
	boolean isInovNull() {
		return this.inov == null;
	}
	
	@Override
	public boolean equals(Object other) {
		if (!Equal.isSameClass(this, other)) {
			return false;
		} else {
			Component otherComp = (Component) other;
			return this.inov.equals(otherComp.inov);
		}
	}
	
	@Override
	public int compareTo(Component other) {
		return this.inov.compareTo(other.inov);
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "[inov=" + inov + "]";
	}
}