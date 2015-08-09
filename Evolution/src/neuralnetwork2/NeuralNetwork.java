package neuralnetwork2;

import genome.Inov;
import genome.Mutator;
import helpers.Mapping;
import helpers.Random;
import helpers.Zipper;
import helpers.Pair;

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
		NeuralNetwork childNet = new NeuralNetwork(INPUT_COUNT, OUTPUT_COUNT);
		Zipper<Node> zipper = new Zipper<Node>(this.nodeList, other.nodeList);
		
		for (Pair<Node> pair: zipper) {
			Node x = pair.X;
			Node y = pair.Y;
		}
		
		return childNet;
	}
	
	public NeuralNetwork merge2(NeuralNetwork other) {
		NeuralNetwork childNeuralNetwork = new NeuralNetwork(INPUT_COUNT, OUTPUT_COUNT);
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
		NeuralNetwork childNeuralNet = new NeuralNetwork(INPUT_COUNT, OUTPUT_COUNT);
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

	private List<Node> getInputs() {
		return this.nodeList.subList(0, INPUT_COUNT);
	}
	
	private List<Node> getOutputs() {
		return this.nodeList.subList(INPUT_COUNT, INPUT_COUNT+OUTPUT_COUNT);
	}
}