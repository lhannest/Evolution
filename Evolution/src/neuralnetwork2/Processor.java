package neuralnetwork2;

import java.util.List;

import neuralnetwork2.genes.Arc;
import neuralnetwork2.genes.Node;

class Processor {
	public static double visit(Node node) {
		if (node.isVisited()) {
			return node.getOutputValue();
		} else {
			node.setVisited(true);
			double sum = node.getBiasValue();
			
			for (Arc arc: node.arcIterator()) {
				if (node.equals(arc.getChild())) {
					sum += visit(arc.getParent()) * arc.getWeight();
				}
			}
			
			node.setOutputValue(activationFunction(sum));
			return node.getOutputValue();
		}
	}
	
	public static void reset(Node node) {
		node.setVisited(false);
	}
	
	private static double activationFunction(double x) {
		return x / (1 + Math.abs(x));
	}
}
