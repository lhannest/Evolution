package neuralnetwork3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NeuralNetwork {
	Signature signature;
	NodeList nodeList = new NodeList();
	List<Arc> arcList = new ArrayList<Arc>();
	
	protected NeuralNetwork() { };
	
	public NeuralNetwork(ComponentSet set) {
		signature = new Signature(set.inputSize(), set.hiddenSize(), set.outputSize(), set.arcSize());
		
		int count = 0;
		
		for (Node node: set.hiddenIterator()) {
			count = addDefaultNode(node, count);
		}
		
		for (Node node: set.outputIterator()) {
			count = addDefaultNode(node, count);
		}
		
		for (Node node: set.inputIterator()) {
			count = addDefaultNode(node, count);
		}
		
		for (Arc arc: set.arcIterator()) {
			count = addDefaultArc(arc, count);
		}
		
		Collections.sort(nodeList);
		Collections.sort(arcList);
	}
	
	protected boolean compatible(NeuralNetwork other) {
		return this.signature.equals(other.signature);
	}
	
	private int addDefaultNode(Node node, int count) {
		Node copy = node.copyWithNewInov(Inov.getNegative(count++));
		this.nodeList.add(copy);
		
		return count;
	}
	
	private int addDefaultArc(Arc arc, int count) {
		Arc copy = arc.copyWithNewInov(arc.getParent(), arc.getChild(),
				Inov.getNegative(count++));
		this.arcList.add(copy);
		
		return count;
	}
}
