package neuralnetwork2.genes;

import genome.Inov;
import genome.Mutator;
import helpers.Mapping;
import helpers.Random;
import helpers.Zipper;
import helpers.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class NeuralNetwork {	
	protected List<Node> nodeList = new ArrayList<Node>();
	protected List<Arc> arcList = new ArrayList<Arc>();
	
	private int inputCount = 0;
	private int outputCount = 0;
	
	public NeuralNetwork(Topology topology) {
		this.nodeList.addAll(topology.inputNodes);
		this.nodeList.addAll(topology.outputNodes);
		this.nodeList.addAll(topology.hiddenNodes);
		this.arcList.addAll(topology.arcs);
		
		this.inputCount = topology.inputNodes.size();
		this.outputCount = topology.outputNodes.size();
	}
	
	public int inputSize() {
		return this.inputCount;
	}
	
	public int outputSize() {
		return this.outputCount;
	}
	
	public void mutateTopology() {
		// Add a new node
		if (Random.randomBoolean(0.01)) {
			HiddenNode node = new HiddenNode(Inov.makeNext());
			Node parent = Random.grab(nodeList);
			Node child = Random.grab(nodeList);
			
			Arc arc1 = new Arc(Inov.makeNext(), parent, node);
			Arc arc2 = new Arc(Inov.makeNext(), node, child);
			
			addHiddenNode(node);
			addArc(arc1);
			addArc(arc2);
		}
		// Split an arc
		if (Random.randomBoolean(0.01)) {
			Arc arc = Random.grab(arcList);
			Node parent = arc.getParent();
			Node child = arc.getChild();
			double weight = arc.getWeight();
			
			if (removeArc(arc)) {
				HiddenNode node = new HiddenNode(Inov.makeNext());
				Arc arc1 = new Arc(Inov.makeNext(), parent, node, 1);
				Arc arc2 = new Arc(Inov.makeNext(), node, child, weight);
				
				addHiddenNode(node);
				addArc(arc1);
				addArc(arc2);
			}
		}
		
		if (Random.randomBoolean(0.01)) {
			Node parent = Random.grab(nodeList);
			Node child = Random.grab(nodeList);
			Arc arc = new Arc(Inov.makeNext(), parent, child);
			addArc(arc);
		}
		
		// Remove node
		if (Random.randomBoolean(0.01)) {
			Node node = Random.grab(nodeList);
			removeNode(node);
		}
		
		if (Random.randomBoolean(0.01)) {
			Arc arc = Random.grab(arcList);
			removeArc(arc);
		}
	}
	
	public void mutateParamiters() {
		for (Node node: nodeList) {
			if (Random.randomBoolean(0.1)) {
				node.jiggleBiasValue(0.01);
			}
		}
		
		for (Arc arc: arcList) {
			if (Random.randomBoolean(0.1)) {
				arc.jiggleWeight(0.01);
			}
		}
	}
	
	private NeuralNetwork(int inputCount, int outputCount) {
		this.inputCount = inputCount;
		this.outputCount = outputCount;
	}
	
	protected NeuralNetwork() {	}
	
	public void process() {
		for (Node node: this.nodeList) {
			node.makeUnvisited();
		}
		
		for (Node node: this.nodeList) {
			node.visit();
		}
	}
	
	public void setInput(int i, double value) {
		List<Node> inputs = nodeList.subList(0, inputCount);
		inputs.get(i).setOutputValue(value);
	}
	
	public double getOutput(int i) {
		List<Node> outputs = nodeList.subList(inputCount, inputCount+outputCount);
		return outputs.get(i).getOutputValue();
	}
	
	public NeuralNetwork copy() {
		NeuralNetwork clone = new NeuralNetwork(inputCount, outputCount);
		Mapping<Node, Node> mapping = new Mapping<Node, Node>();
		
		for (Node node: nodeList) {
			Node copy = node.copy();
			clone.nodeList.add(copy);
			mapping.add(copy, node);
		}
		
		for (Arc arc: arcList) {
			Node parent = (Node) mapping.getOther(arc.getParent());
			Node child = (Node) mapping.getOther(arc.getChild());
			
			clone.arcList.add(new Arc(arc, parent, child));
		}
		
		return clone;
	}
	
	public NeuralNetwork merge(NeuralNetwork other) {
		NeuralNetwork childNet = new NeuralNetwork(inputCount, outputCount);
		Zipper<Node> zipper = new Zipper<Node>(this.nodeList, other.nodeList);
		
		for (Pair<Node> pair: zipper) {
			childNet.nodeList.add(pair.getNotNull());
		}
		
		Zipper<Arc> zipperArc = new Zipper<Arc>(this.arcList, other.arcList);
		
		for (Pair<Arc> pair: zipperArc) {
			Arc arc;
			
			if (pair.X != null && pair.Y != null) {
				arc = Random.grab(pair.X, pair.Y);
			} else {
				arc = pair.getNotNull();
			}
			
			childNet.takeArc(zipper, arc);
		}
		
		return childNet;
	}

	private void takeArc(Zipper<Node> zipper, Arc arc) {
		int p = zipper.indexOf(arc.getParent());
		
		if (p == -1) {
			zipper.indexOf(arc.getParent());
		}
		int c = zipper.indexOf(arc.getChild());
		
		Node parent=null;
		Node child=null;
		try {
			parent = nodeList.get(p);
			child = nodeList.get(c);
		} catch (ArrayIndexOutOfBoundsException e) {
			int youre_a_bitch = 3;
			int jk = youre_a_bitch + 2;
		}
		
		arcList.add(new Arc(arc, parent, child));
	}
	
	public NeuralNetwork merge2(NeuralNetwork other) {
		NeuralNetwork childNeuralNetwork = new NeuralNetwork(inputCount, outputCount);
		Mapping<Node, Node> mapping = new Mapping<Node, Node>();
		
		int min = Math.min(this.nodeList.size(), other.nodeList.size());
		for (int i = 0; i < min; i++) {
			Node thisNode = this.nodeList.get(i);
			Node otherNode = other.nodeList.get(i);
			
			if (thisNode.equals(otherNode)) {
				Node copy = thisNode.copy();
				childNeuralNetwork.nodeList.add(copy);
				mapping.add(copy, thisNode);
				mapping.add(copy, otherNode);
			}
		}
		
		int max = Math.max(this.nodeList.size(), other.nodeList.size());
		for (int i = min; i < max; i++) {
			if (i < this.nodeList.size()) {
				Node copy = this.nodeList.get(i).copy();
				childNeuralNetwork.nodeList.add(copy);
				mapping.add(copy, this.nodeList.get(i));
			} else {
				Node copy = other.nodeList.get(i).copy();
				childNeuralNetwork.nodeList.add(copy);
				mapping.add(copy, other.nodeList.get(i));
			}
		}
		
		for (Arc arc: this.arcList) {
			Node parent = (Node) mapping.getOther(arc.getParent());
			Node child = (Node) mapping.getOther(arc.getChild());
			
			Arc copy = new Arc(arc, parent, child);
			childNeuralNetwork.arcList.add(copy);
		}
		
		for (Arc arc: other.arcList) {
			if (!childNeuralNetwork.arcList.contains(arc)) {
				Node parent = (Node) mapping.getOther(arc.getParent());
				Node child = (Node) mapping.getOther(arc.getChild());
				
				Arc copy = new Arc(arc, parent, child);
				childNeuralNetwork.arcList.add(copy);
			}
		}
		
		return childNeuralNetwork;
	}
	
	public NeuralNetwork cross(NeuralNetwork other) {
		NeuralNetwork childNeuralNet = new NeuralNetwork(inputCount, outputCount);
		Mapping<Node, Node> mapping = new Mapping<Node, Node>();
		
		for (Node node: this.nodeList) {
			Node copy = node.copy();
			childNeuralNet.nodeList.add(copy);
			mapping.add(copy, node);
		}
		
		int d = Mutator.pointOfDivergence(this.arcList, other.arcList);
		
		for (int i = 0; i < d; i ++) {
			Arc arc = this.arcList.get(i);
			
			Node parent = (Node) mapping.getOther(arc.getParent());
			Node child = (Node) mapping.getOther(arc.getChild());
			double weight = Random.grab(arc.getWeight(), other.arcList.get(i).getWeight());
			
			childNeuralNet.arcList.add(new Arc(arc, parent, child, weight));
		}
		
		for (int i = d; i < this.arcList.size(); i++) {
			Arc arc = this.arcList.get(i);
			
			Node parent = (Node) mapping.getOther(arc.getParent());
			Node child = (Node) mapping.getOther(arc.getChild());
			
			childNeuralNet.arcList.add(new Arc(arc, parent, child));
		}
		
		return childNeuralNet;
	}
	
	private boolean addNode(Node node) {
		return this.nodeList.add(node);
	}
	
	protected boolean addInputNode(InputNode node) {
		inputCount++;
		return addNode(node);
	}
	
	protected boolean addHiddenNode(HiddenNode node) {
		return addNode(node);
	}
	
	protected boolean addOutputNode(OutputNode node) {
		outputCount++;
		return addNode(node);
	}
	
	protected boolean removeNode(Node node) {
		if (node.hasInov(0)) {
			return false;
		}
		
		boolean success = this.nodeList.remove(node);
		
		List<Arc> toBeRemoved = new ArrayList<Arc>();
		
		if (success) {
			for (Arc arc: this.arcList) {
				if (arc.getParent() == node || arc.getChild() == node) {
					toBeRemoved.add(arc);
				}
			}
		}
		
		for (Arc arc: toBeRemoved) {
			removeArc(arc);
		}
		
		return success;
	}
	
	protected boolean addArc(Arc arc) {
		return this.arcList.add(arc);
	}
	
	protected boolean removeArc(Arc arc) {
		if (arc.hasInov(0)) {
			return false;
		} else {
			return this.arcList.remove(arc);
		}
	}
	
	protected Iterable<Node> nodeIterator() {
		return new Iterable<Node>() {
			@Override
			public Iterator<Node> iterator() {
				return Collections.unmodifiableCollection(nodeList).iterator();
			}
		};
	}
}