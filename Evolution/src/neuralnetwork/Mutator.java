package neuralnetwork;

import helpers.Random;

public class Mutator {
	public void addNode(NeuralNetwork neuralNetwork, Node node) {
		
	}
	
	public void splitArc(NeuralNetwork neuralNetwork, Node node) {
		
	}
	
	public void removeNode(NeuralNetwork neuralNetwork) {
		
	}
	
	public void addArc(NeuralNetwork neuralNetwork) {
		
	}
	
	public void removeArc(NeuralNetwork neuralNetwork) {
		
	}
	
	public void jiggleParameters(NeuralNetwork neuralNetwork) {
		if (Random.randomBoolean(0.5)) {
			Arc arc = Random.grab(neuralNetwork.arcList);
			arc.jiggleWeight();
		} else {
			Node node = Random.grab(neuralNetwork.nodeList);
			node.jiggleBiasValue();
		}
	}
	
	public void resetWeight(NeuralNetwork neuralNetwork) {
		
	}
}
