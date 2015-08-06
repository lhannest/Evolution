package neuralnetwork2;

import genome.Inov;
import genome.Mutator;
import helpers.Mapping;
import helpers.Random;

import java.util.ArrayList;
import java.util.List;

import neuralnetwork2.genes.BiasNode;
import neuralnetwork2.genes.InputNode;
import neuralnetwork2.genes.Node;
import neuralnetwork2.genes.Arc;

public class NeuralNetwork {	
	List<Node> nodeList = new ArrayList<Node>();
	List<Arc> arcList = new ArrayList<Arc>();
	
	public final int INPUT_COUNT;
	public final int OUTPUT_COUNT;
	
	private NeuralNetwork(int inputCount, int outputCount) {
		INPUT_COUNT = inputCount;
		OUTPUT_COUNT = outputCount;
	}
	
	public NeuralNetwork(int inputCount, int hiddenCount, int outputCount) {		
		INPUT_COUNT = inputCount;
		OUTPUT_COUNT = outputCount;
		
		for (int i = 0; i < inputCount; i++) {
			nodeList.add(new InputNode(Inov.makeZero()));
		}
		
		List<Node> inputs = endSubList(inputCount);
		
		for (int i = 0; i < outputCount; i++) {
			nodeList.add(new Node(Inov.makeZero()));
		}
		
		List<Node> outputs = endSubList(outputCount);
		
		nodeList.add(new BiasNode(Inov.makeZero()));
		
		List<Node> bias = endSubList(1);
		
		for (int i = 0; i < hiddenCount; i++) {
			nodeList.add(new Node(Inov.makeZero()));
		}
		
		List<Node> hidden = endSubList(hiddenCount);
		
		
		fullyConnect(inputs, hidden);
		fullyConnect(hidden, outputs);
		fullyConnect(bias, hidden);
		fullyConnect(bias, outputs);
		
	}
	
	private void fullyConnect(List<Node> parents, List<Node> children) {
		for (Node parent: parents) {
			for (Node child: children) {
				Arc arc = new Arc(Inov.makeZero(), parent, child, Math.random());
				this.arcList.add(arc);
			}
		}
	}

	private List<Node> endSubList(int inputCount) {
		List<Node> subList = new ArrayList<Node>();
		
		for (Node node: nodeList) {
			subList.add(node);
		}
		
		return subList;
	}
	
	public void process() {
		for (Node node: this.nodeList) {
			Processor.visit(node);
		}
		
		for (Node node: this.nodeList) {
			Processor.reset(node);
		}
	}
	
	public void setInput(int i, double value) {
		getInputs().get(i).setOutputValue(value);
	}
	
	public double getOutput(int i) {
		return getOutputs().get(i).getOutputValue();
	}
	
	public NeuralNetwork copy() {
		NeuralNetwork clone = new NeuralNetwork(INPUT_COUNT, OUTPUT_COUNT);
		Mapping<Node, Node> mapping = new Mapping<Node, Node>();
		
		for (Node node: nodeList) {
			Node copy = new Node(node);
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
		NeuralNetwork childNeuralNetwork = new NeuralNetwork(INPUT_COUNT, OUTPUT_COUNT);
		Mapping<Node, Node> mapping = new Mapping<Node, Node>();
		
		for (Node node: this.nodeList) {
			Node copy = new Node(node);
			childNeuralNetwork.nodeList.add(copy);
			mapping.add(copy, node);
		}
		
		for (Node node: other.nodeList) {
			if (!childNeuralNetwork.nodeList.contains(node)) {
				Node copy = new Node(node);
				childNeuralNetwork.nodeList.add(copy);
				mapping.add(copy, node);
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
		NeuralNetwork childNeuralNet = new NeuralNetwork(INPUT_COUNT, OUTPUT_COUNT);
		
		for (Node node: this.nodeList) {
			childNeuralNet.takeCopyOfNode(node);
		}
		
		int d = Mutator.pointOfDivergence(this.arcList, other.arcList);
		
		for (int i = 0; i < d; i ++) {
			Arc grabbed = Random.grab(this.arcList.get(i), other.arcList.get(i));
			childNeuralNet.takeCopyOfArc(grabbed);
		}
		
		for (int i = d; i < this.arcList.size(); i++) {
			Arc arc = this.arcList.get(i);
			childNeuralNet.takeCopyOfArc(arc); 
		}
		
		if (childNeuralNet.nodeList.size() == 1) {
			return null;
		}
		
		return childNeuralNet;
	}

	private boolean takeCopyOfNode(Node node) {
		if (this.nodeList.contains(node)) {
			return false;
		} else {			
			nodeList.add(new Node(node));
			return true;
		}
	}

	private boolean takeCopyOfArc(Arc arc) {
		if (this.arcList.contains(arc)) {
			return false;
		} else {
			Node parent = getCorrespondingNode(arc.getParent());
			Node child = getCorrespondingNode(arc.getParent());
			
			arcList.add(new Arc(arc, parent, child));
			return true;
		}
	}
	
	private Node getCorrespondingNode(Node node) {
		for (Node n: this.nodeList) {
			if (node.equals(n)) {
				return n;
			}
		}
		return null;
	}

	private List<Node> getInputs() {
		return this.nodeList.subList(0, INPUT_COUNT);
	}
	
	private List<Node> getOutputs() {
		System.out.println(INPUT_COUNT + " " + OUTPUT_COUNT + " " + this.nodeList.size());
		return this.nodeList.subList(INPUT_COUNT, INPUT_COUNT+OUTPUT_COUNT);
	}
}