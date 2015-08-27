package neuralnetwork;

import helpers.Zipper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NeuralNetwork {
	Signature signature;
	NodeList nodeList = new NodeList();
	List<Arc> arcList = new ArrayList<Arc>();
	
	private NeuralNetwork() { };
	
	protected static NeuralNetwork callEmptyConstructor() {
		return new NeuralNetwork();
	}
	
	public NeuralNetwork(Topology top) {
		if (!isValid(top)) {
			throw new RuntimeException("ComponentSet is not valid.");
		}
		
		signature = new Signature(top.inputSize(), top.hiddenSize(), top.outputSize(), top.arcSize());
		
		int count = 0;
		
		for (Node node: top.hiddenIterator()) {
			count = addDefaultNode(node, count);
		}
		
		for (Node node: top.outputIterator()) {
			count = addDefaultNode(node, count);
		}
		
		for (Node node: top.inputIterator()) {
			count = addDefaultNode(node, count);
		}
		
		for (Arc arc: top.arcIterator()) {
			count = addDefaultArc(arc, count);
		}
		
		Collections.sort(nodeList);
		Collections.sort(arcList);
	}
	
	private int addDefaultArc(Arc arc, int count) {		
		Node parent = nodeList.getMember(arc.getParent());
		Node child = nodeList.getMember(arc.getChild());
		
		arc.setInov(Inov.getNegative(count++));		
		this.arcList.add(arc.copy(parent, child));
		
		return count;
	}

	private boolean isValid(Topology set) {
		List<Node> nodes = new ArrayList<Node>();
		
		for (Node n: set.inputIterator()) nodes.add(n);
		for (Node n: set.hiddenIterator()) nodes.add(n);
		for (Node n: set.outputIterator()) nodes.add(n);
		
		boolean flag = false;
		for (Node n: nodes) {
			flag = false;
			for (Arc arc: set.arcIterator()) {
				if (n == arc.getParent() || n == arc.getChild()) {
					flag = true;
				}
			}
			if (flag == false) break;
		}
		
		return flag;
	}
	
	protected boolean compatible(NeuralNetwork other) {
		return this.signature.equals(other.signature);
	}
	
	private int addDefaultNode(Node node, int count) {
		node.setInov(Inov.getNegative(count++));
		this.nodeList.add(node.copy());
		
		return count;
	}
	
	public int inputSize() {
		return signature.INPUT_COUNT;
	}
	
	public int outputSize() {
		return signature.OUTPUT_COUNT;
	}

	public void process() {
		// TODO Auto-generated method stub
	}

	public double getOutput(int i) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setInput(int i, double value) {
		// TODO Auto-generated method stub
		
	}
}
