package neuralnetwork2;

import neuralnetwork2.genes.Arc;
import neuralnetwork2.genes.Node;
import genome.Inov;
import helpers.Random;

public class Mutator {
	public static void mutate(NeuralNetwork neuralNetwork) {
		jiggleWeight(neuralNetwork);
	}
	
	public static void jiggleWeight(NeuralNetwork neuralNetwork) {
		Arc arc = Random.grab(neuralNetwork.arcList);
		arc.jiggleWeight(0.1);
	}
	
	public static void addNode(NeuralNetwork neuralNetwork) {
		Node node = new Node(Inov.makeNext());
		
		neuralNetwork.nodeList.add(node);
	}
}
