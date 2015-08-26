package neuralnetwork3;

import helpers.Pair;
import helpers.Random;
import helpers.Zipper;

public class Reproducer {
	public NeuralNetwork copy(NeuralNetwork neuralNetwork) {
		NeuralNetwork child = new NeuralNetwork();
		
		for (Arc arc: neuralNetwork.arcList) {
			copyOverArc(neuralNetwork, arc);
		}
		
		return child;
	}
	
	public NeuralNetwork cross(NeuralNetwork dominant, NeuralNetwork submissive) {
		NeuralNetwork child = new NeuralNetwork();
		Zipper<Arc> zipper = new Zipper<Arc>(dominant.arcList, submissive.arcList);
		
		for (Pair<Arc> p: zipper) {
			Arc arc;
			if (p.X != null && p.Y != null) {
				arc = Random.grab(p.X, p.Y);
			} else if (p.X != null) {
				arc = p.X;
			} else {
				break;
			}
			
			copyOverArc(child, arc);
		}
		
		return child;
	}
	
	public NeuralNetwork merge(NeuralNetwork nn1, NeuralNetwork nn2) {
		NeuralNetwork child = new NeuralNetwork();
		Zipper<Arc> zipper = new Zipper<Arc>(nn1.arcList, nn2.arcList);
		 
		for (Pair<Arc> p: zipper) {
			Arc arc = null;
			if (p.X != null && p.Y != null) {
				arc = Random.grab(p.X, p.Y);
			} else if (p.X != null) {
				arc = p.X;
			} else if (p.Y != null) {
				arc = p.Y;
			} else {
				assert false;
			}
			
			copyOverArc(child, arc);
		}
		
		return child;
	}
	
	private static void copyOverArc(NeuralNetwork neuralNetwork, Arc arc) {
		Node parent = neuralNetwork.nodeList.getMember(arc.getParent());
		Node child = neuralNetwork.nodeList.getMember(arc.getChild());
		
		if (parent != null && child != null) {
			neuralNetwork.arcList.add(arc.copy(parent, child));
		} else {
			if (parent == null) parent = copyOverNode(neuralNetwork, arc.getParent());
			if (child == null) child = copyOverNode(neuralNetwork, arc.getChild());
			
			neuralNetwork.arcList.add(arc.copy(parent, child));
		}
	}
	
	private static Node copyOverNode(NeuralNetwork neuralNetwork, Node node) {
		Node copy = node.copy();
		neuralNetwork.nodeList.add(copy);
		return node;
	}
}
