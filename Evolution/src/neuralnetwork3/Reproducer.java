package neuralnetwork3;

public class Reproducer {
	public NeuralNetwork copy(NeuralNetwork neuralNetwork) {
		NeuralNetwork child = new NeuralNetwork();
		
		for (Arc arc: neuralNetwork.arcList) {
			copyOverArc(neuralNetwork, arc);
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
