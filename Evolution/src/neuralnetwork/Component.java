package neuralnetwork;

import helpers.Equal;

public abstract class Component implements Comparable<Component> {
	private Inov inov;
	
	protected Component() {
		inov = null;
	}
	
	public double getAge() {
		return this.inov.getAge();
	}
	
	protected Component(Component other) {
		this.inov = other.inov;
	}
	
	
	
	boolean isInovNull() {
		return this.inov == null;
	}
	
	protected void setInov(Inov inov) {
		this.inov = inov;
	}
	
	@Override
	public boolean equals(Object other) {
		if (!Equal.isSameClass(this, other)) {
			return false;
		} else {
			Component otherComp = (Component) other;
			if (this.inov == null || otherComp.inov == null) {
				return false;
			} else {
				return this.inov.equals(otherComp.inov);
			}
		}
	}
	
	@Override
	public int compareTo(Component other) {
		if (this.inov == null || other.inov == null) {
			return 0;
		} else {
			return this.inov.compareTo(other.inov);
		}
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "[inov=" + inov + "]" + this.hashCode();
	}
}