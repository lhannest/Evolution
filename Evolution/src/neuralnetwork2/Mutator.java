package neuralnetwork2;

import neuralnetwork2.genes.Arc;
import neuralnetwork2.genes.Node;
import genome.Inov;
import helpers.Random;

public class Mutator {
	public static void mutate(NeuralNetwork neuralNetwork) {
		jiggleWeight(neuralNetwork);
		
		if (Random.randomBoolean(0.2)) {
			addNode(neuralNetwork);
		}
	}
	
	public static void jiggleWeight(NeuralNetwork neuralNetwork) {
		Arc arc = Random.grab(neuralNetwork.arcList);
		arc.jiggleWeight(0.1);
	}
	
	public static void addNode(NeuralNetwork neuralNetwork) {
		Node node = new Node(Inov.makeNext());
		Node parent = Random.grab(neuralNetwork.nodeList);
		Node child = Random.grab(neuralNetwork.nodeList);
		
		Arc arc1 = new Arc(Inov.makeNext(), parent, node, Random.randomDouble(-1, 1));
		Arc arc2 = new Arc(Inov.makeNext(), node, child, Random.randomDouble(-1, 1));
		
		neuralNetwork.nodeList.add(node);
		neuralNetwork.arcList.add(arc1);
		neuralNetwork.arcList.add(arc2);
	}
}
