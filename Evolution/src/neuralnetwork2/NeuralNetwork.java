package neuralnetwork2;

import genome.Inov;
import genome.Mutator;
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
		for (int i = 0; i < inputCount; i++) {
			nodeList.add(new InputNode(Inov.makeZero()));
		}
		
		for (int i = 0; i < outputCount; i++) {
			nodeList.add(new Node(Inov.makeZero()));
		}
		
		nodeList.add(new BiasNode(Inov.makeZero()));
		
		for (int i = 0; i < hiddenCount; i++) {
			nodeList.add(new Node(Inov.makeZero()));
		}
		
		INPUT_COUNT = inputCount;
		OUTPUT_COUNT = outputCount;
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
	
	public void getOutput(int i) {
		getOutputs().get(i).getOutputValue();
	}
	
	public NeuralNetwork copy() {
		NeuralNetwork clone = new NeuralNetwork(INPUT_COUNT, OUTPUT_COUNT);
		
		for (Node node: nodeList) {
			clone.takeCopyOfNode(node);
		}
		
		for (Arc arc: arcList) {
			clone.takeCopyOfArc(arc);
		}
		
		return clone;
	}
	
	
	public NeuralNetwork merge(NeuralNetwork other) {
		NeuralNetwork childNeuralNetwork = new NeuralNetwork(INPUT_COUNT, OUTPUT_COUNT);
		
		for (Node node: this.nodeList) {
			childNeuralNetwork.takeCopyOfNode(node);
		}
		
		for (Node node: other.nodeList) {
			childNeuralNetwork.takeCopyOfNode(node);
		}
		
		for (Arc arc: this.arcList) {
			childNeuralNetwork.takeCopyOfArc(arc);
		}
		
		for (Arc arc: other.arcList) {
			childNeuralNetwork.takeCopyOfArc(arc);
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
		
		return childNeuralNet;
	}

	private boolean takeCopyOfNode(Node node) {
		if (this.nodeList.contains(node)) {
			return false;
		} else {			
			nodeList.add(node.copy());
			return true;
		}
	}

	private boolean takeCopyOfArc(Arc arc) {
		if (this.arcList.contains(arc)) {
			return false;
		} else {
			Node parent = getCorrespondingNode(arc.PARENT);
			Node child = getCorrespondingNode(arc.CHILD);
			
			arcList.add(arc.copy(parent, child));
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
		return this.nodeList.subList(INPUT_COUNT, INPUT_COUNT+OUTPUT_COUNT);
	}
}