package neuralnetwork2.genes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import neuralnetwork2.Mutable;

import genome.Inov;
import helpers.Random;

public class Node extends Gene implements Mutable {
	private List<Arc> arcList = new ArrayList<Arc>();
	private double value = 0;
	private boolean visited = false;
	
	private double biasValue;
	
	public void makeUnvisited() {
		this.visited = false;
	}
	
	public double visit() {
		if (isVisited()) {
			return getOutputValue();
		} else {
			setVisited(true);
			double sum = biasValue;
			
			for (Arc arc: arcList) {
				if (arc.getChild() == this) {
					sum += arc.getParent().visit() * arc.getWeight();
				}
			}
			double sigmoid = approxSigmoid(sum);
			setOutputValue(sigmoid);
			return getOutputValue();
		}
	}
	
	private double approxSigmoid(double x) {
		return x / (1 + Math.abs(x));
	}
	
	protected Node(Inov inov) {
		super(inov);
		biasValue = Random.randomDouble(-5, 5);
	}
	
	/**
	 * Copy constructor, node.equals(new Node(node)) evaluates to true.
	 * @param node
	 */
	protected Node(Node node) {
		super(node);
		biasValue = node.biasValue;
	}
	
	public double getBiasValue() {
		return this.biasValue;
	}
	
	public Node copy() {
		return new Node(this);
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
		return this.arcList.add(arc);
	}
	
	public void jiggleBiasValue(double amount) {
		this.biasValue += Random.randomDouble(-amount, amount);
	}
	
	@SuppressWarnings("serial")
	public class InvalidNodeOperationException extends RuntimeException {
		public InvalidNodeOperationException(String message) {
			super(message);
		}
	}

	@Override
	public void mutate() {
		for (Arc arc: this.arcList) {
			if (Random.randomBoolean(0.1)) {
				arc.mutate();
			}
		}
	}
}
