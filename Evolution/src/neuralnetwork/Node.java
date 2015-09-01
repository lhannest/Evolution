package neuralnetwork;

import helpers.Random;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public abstract class Node extends Component {
	private final double DEFAULT_VALUE = 1;
	private final double JIGGLE_AMOUNT = 0.1;
	
	private final List<Arc> arcList = new ArrayList<Arc>();
	private double biasValue;
	private boolean visited = false;
	private double value = 0;
	
	public Node() {
		super();
		this.biasValue = Random.randomDouble(-DEFAULT_VALUE, DEFAULT_VALUE);
	}
	
	protected Node(Node other) {
		super(other);
		this.biasValue = other.biasValue;
	}
	
	public final Node copy() {		
		Node copy = this.callCopyConstructor();
		
		if (copy.getClass() != this.getClass()) {
			throw new RuntimeException(copy.getClass() + " must override callCopyConstructor().");
		} else {
			return copy;
		}
		
	}
	
	public final Iterable<Arc> iterateOverArcs() {
		return new Iterable<Arc>() {
			@Override
			public Iterator<Arc> iterator() {
				return Collections.unmodifiableCollection(arcList).iterator();
			}
		};
	}
	
	/**
	 * This method is called NeuralNetwork's process method.
	 * @return
	 */
	public double visit() {
		if (isVisited()) {
			return getValue();
		} else {
			double sum = -getBiasValue();
			setVisited(true);
			for (Arc arc: this.iterateOverArcs()) {
				if (arc.getChild() == this) {
					double weight = arc.getWeight();
					Node parent = arc.getParent();
					
					sum += weight * parent.visit();
				}
			}
			setValue(activationFunction(sum));
			return getValue();
		}
	}
	
	public double activationFunction(double x) {
		return x / (1+ Math.abs(x));
	}
	
	public double getBiasValue() {
		return this.biasValue;
	}
	
	public double getValue() {
		return this.value;
	}
	
	public void setValue(double value) {
		this.value = value;
	}
	
	public boolean isVisited() {
		return this.visited;
	}
	
	public void setVisited(boolean isVisited) {
		this.visited = isVisited;
	}
	
	public void jiggleBiasValue() {
		this.biasValue += Random.randomDouble(-JIGGLE_AMOUNT, JIGGLE_AMOUNT);
	}
	
	/**
	 * Every class that is derived from Node should override this method.
	 * @return
	 * While its functionality depends on derived class, it should return
	 * an instance of the derived class, produced by its copy constructor.
	 */
	protected abstract Node callCopyConstructor();
	
	/**
	 * This is only intended to be called by Arc's constructor
	 * @param arc
	 * @return
	 */
	protected boolean addArc(Arc arc) {
		return this.arcList.add(arc);
	}
}
