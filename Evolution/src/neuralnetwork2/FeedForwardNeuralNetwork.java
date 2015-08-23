package neuralnetwork2;

import genome.Inov;

import java.util.ArrayList;
import java.util.List;

import neuralnetwork2.genes.Arc;
import neuralnetwork2.genes.BiasNode;
import neuralnetwork2.genes.HiddenNode;
import neuralnetwork2.genes.InputNode;
import neuralnetwork2.genes.NeuralNetwork;
import neuralnetwork2.genes.Node;
import neuralnetwork2.genes.OutputNode;

public class FeedForwardNeuralNetwork extends NeuralNetwork {

	public FeedForwardNeuralNetwork(int inputCount, int hiddenCount, int outputCount) {
		super();
		
		List<Node> inputs = new ArrayList<Node>();
		List<Node> outputs = new ArrayList<Node>();
		List<Node> hidden = new ArrayList<Node>();

		for (int i = 0; i < inputCount; i++) {
			InputNode node = new InputNode();
			this.addInputNode(node);
			inputs.add(node);
		}
		
		for (int i = 0; i < outputCount; i++) {
			OutputNode node = new OutputNode();
			this.addOutputNode(node);
			outputs.add(node);
		}
		
		for (int i = 0; i < hiddenCount; i++) {
			HiddenNode node = new HiddenNode(Inov.makeZero());
			this.addHiddenNode(node);
			hidden.add(node);
		}		
		
		addArcsBetweenNodes(inputs, hidden);
		addArcsBetweenNodes(hidden, outputs);
	}
	
	private void addArcsBetweenNodes(List<Node> parents, List<Node> children) {
		for (Node parent: parents) {
			for (Node child: children) {
				Arc arc = new Arc(Inov.makeZero(), parent, child, Math.random());
				this.addArc(arc);
			}
		}
	}
}
