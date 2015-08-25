package neuralnetwork3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NeuralNetwork {
	Signature signature;
	NodeList nodeList = new NodeList();
	List<Arc> arcList = new ArrayList<Arc>();
	
	protected NeuralNetwork(NetworkBuilder builder) {
		signature = new Signature(builder.inputSize(), builder.hiddenSize(), builder.outputSize(), builder.arcSize());
		
		int count = 0;
		
		for (Node node: builder.hiddenIterator()) {
			count = addDefaultNode(node, count);
		}
		
		for (Node node: builder.outputIterator()) {
			count = addDefaultNode(node, count);
		}
		
		for (Node node: builder.inputIterator()) {
			count = addDefaultNode(node, count);
		}
		
		for (Arc arc: builder.arcIterator()) {
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
	
	protected Node takeExtraNode(Node node) {
		Inov next = Inov.getNext();
		Node copy = node.copyWithNewInov(next);
		nodeList.add(copy);
		return copy;
	}
	
	protected boolean takeExtraArc(Arc arc) {
		Node parent = nodeList.getMember(arc.getParent());
		Node child = nodeList.getMember(arc.getChild());
		
		if (parent != null && child != null) {
			arcList.add(arc.copy(parent, child));
			return false;
		} else {
			if (parent == null) {
				parent = takeExtraNode(arc.getParent());
			}
			
			if (child == null) {
				child = takeExtraNode(arc.getChild());
			}
			
			Inov next = Inov.getNext();
			arcList.add(arc.copyWithNewInov(parent, child, next));
			return true;
		}
	}
}
