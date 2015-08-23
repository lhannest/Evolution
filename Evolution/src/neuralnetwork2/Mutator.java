package neuralnetwork2;

import neuralnetwork2.genes.Arc;
import neuralnetwork2.genes.HiddenNode;
import neuralnetwork2.genes.NeuralNetwork;
import neuralnetwork2.genes.Node;
import genome.Inov;
import helpers.Random;

public class Mutator {
	public static void mutateTopology(NeuralNetwork neuralNetwork) {
		if (Random.randomBoolean(0.1)) {
			HiddenNode node = new HiddenNode(Inov.makeNext());
		}
	}
	
	public static void mutateParamiters(NeuralNetwork neuralNetwork) {
		for (Node node: neuralNetwork.nodeIterator()) {
			for (Arc arc: node.arcIterator()) {
				if (Random.randomBoolean(0.4)) {
					arc.jiggleWeight(0.01);
				}
			}
			
			if (Random.randomBoolean(0.4)) {
				node.jiggleBiasValue(0.01);
			}
		}
	}
	
	
	
//	public static void mutate(NeuralNetwork neuralNetwork) {
//		jiggleWeight(neuralNetwork);
//		
//		if (Random.randomBoolean(0.02)) {
//			addNode(neuralNetwork);
//		}
//		
//		if (Random.randomBoolean(0.02)) {
//			removeNode(neuralNetwork);
//		}
//	}
//	
//	public static void jiggleWeight(NeuralNetwork neuralNetwork) {
//		Arc arc = Random.grab(neuralNetwork.arcList);
//		arc.jiggleWeight(0.1);
//	}
//	
//	public static void addNode(NeuralNetwork neuralNetwork) {
//		Node node = new Node(Inov.makeNext());
//		Node parent = Random.grab(neuralNetwork.nodeList);
//		Node child = Random.grab(neuralNetwork.nodeList);
//		
//		Arc arc1 = new Arc(Inov.makeNext(), parent, node, Random.randomDouble(-1, 1));
//		Arc arc2 = new Arc(Inov.makeNext(), node, child, Random.randomDouble(-1, 1));
//		
//		neuralNetwork.nodeList.add(node);
//		neuralNetwork.arcList.add(arc1);
//		neuralNetwork.arcList.add(arc2);
//	}
//	
//	public static void removeNode(NeuralNetwork neuralNetwork) {
//		if (neuralNetwork.getHidden().size() > 0) {
//			Node node = Random.grab(neuralNetwork.getHidden());
//			
//			if (!node.isInov(0)) {
//			
//				neuralNetwork.nodeList.remove(node);
//				
//				boolean active = true;
//				while (active) {
//					active = false;
//					for (Arc arc: neuralNetwork.arcList) {
//						if (arc.getParent() == node || arc.getChild() == node) {
//							neuralNetwork.arcList.remove(arc);
//							active = true;
//							break;
//						}
//					}
//				}
//			}
//		}
//	}
}
