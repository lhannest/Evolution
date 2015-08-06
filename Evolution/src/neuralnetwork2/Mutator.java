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
		int index = Random.randomInteger(0, neuralNetwork.arcList.size());
		
		Arc arc = neuralNetwork.arcList.get(index);

		neuralNetwork.arcList.remove(index);
		neuralNetwork.arcList.add(arc.copyWithJiggledWeight(0.1));
	}
	
	public static void addNode(NeuralNetwork neuralNetwork) {
		Node node = new Node(Inov.makeNext());
		
		neuralNetwork.nodeList.add(node);
	}
}
