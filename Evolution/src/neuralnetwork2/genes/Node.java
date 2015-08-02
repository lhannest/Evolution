package neuralnetwork2.genes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import genome.Inov;

public class Node extends Gene {
	private List<Arc> arcList = new ArrayList<Arc>();
	private double value;
	private boolean visited;

	public Node(Inov inov) {
		super(inov);
	}
	
	public Node copy() {
		return (Node) super.copy();
	}
	
	public Iterable<Arc> arcIterator() {
		return new Iterable<Arc>() {
			@Override
			public Iterator<Arc> iterator() {
				return Collections.unmodifiableCollection(arcList).iterator();
			}
		};
	}
	
	public boolean isVisited() {
		return this.visited;
	}
	
	public void setVisited(boolean value) {
		this.visited = value;
	}
	
	public double getOutputValue() {
		return value;
	}
	
	public void setOutputValue(double value) {
		this.value = value;
	}
	
	boolean addArc(Arc arc) {
		if (!this.arcList.contains(arc)) {
			return false;
		} else {
			this.arcList.add(arc);
			return true;
		}
	}
	
	@SuppressWarnings("serial")
	public class InvalidNodeOperationException extends RuntimeException {
		public InvalidNodeOperationException(String message) {
			super(message);
		}
	}

}
