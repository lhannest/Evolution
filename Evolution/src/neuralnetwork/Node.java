package neuralnetwork;

import helpers.Random;

import java.util.ArrayList;
import java.util.List;



public abstract class Node extends Component {
	private final double DEFAULT_VALUE = 1;
	
	private final List<Arc> arcList = new ArrayList<Arc>();
	private final double biasValue;
	
	public Node() {
		super();
		this.biasValue = Random.randomDouble(-DEFAULT_VALUE, DEFAULT_VALUE);
	}
	
	protected Node(Node other) {
		super(other);
		this.biasValue = other.biasValue;
	}
	
	public final Node copy() {		
		Node copy = this.overriddenCopyMethod();
		
		if (copy.getClass() != this.getClass()) {
			throw new RuntimeException(copy.getClass() + " Must override overrideCopyMethod().");
		} else {
			return copy;
		}
		
	}
	
	protected abstract Node overriddenCopyMethod();
	
	/**
	 * This is only intended to be called by Arc's constructor
	 * @param arc
	 * @return
	 */
	protected boolean addArc(Arc arc) {
		return this.arcList.add(arc);
	}
}
