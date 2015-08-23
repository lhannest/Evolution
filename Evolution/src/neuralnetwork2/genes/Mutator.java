package neuralnetwork2.genes;

import genome.Inov;
import helpers.Mapping;
import helpers.Pair;
import helpers.Random;
import helpers.Zipper;

public class Mutator {
	public static void main(String[] args) {
		Topology top = new Topology();
		InputNode input = new InputNode();
		HiddenNode a = new HiddenNode();
		HiddenNode b = new HiddenNode();
		HiddenNode c = new HiddenNode();
		OutputNode out = new OutputNode();
		
		top.addInputNode(input);
		top.addHiddenNode(a);
		top.addHiddenNode(b);
		top.addHiddenNode(c);
		top.addOutputNode(out);
		
		top.addArc(new Arc(input, a));
		top.addArc(new Arc(input, b));
		top.addArc(new Arc(input, c));
		
		top.addArc(new Arc(a, out));
		top.addArc(new Arc(c, a));
		top.addArc(new Arc(b, a));
		top.addArc(new Arc(b, c));
		top.addArc(new Arc(c, b));
		top.addArc(new Arc(a, c));
		top.addArc(new Arc(a, b));
		
		NeuralNetwork hello = new NeuralNetwork(top);
		
		for (int i = 0; i < 10; i++) {
			hello.setInput(0, (i/10.01));
			hello.process();
			double d = hello.getOutput(0);
			System.out.println(d);
		}
		
	}
	
	
	public static void addNode(Node node, NeuralNetwork neuralNetwork) {
		node.setInov(Inov.makeNext());
		neuralNetwork.nodeList.add(node);
	}
	
	public static boolean removeNode(Node node, NeuralNetwork neuralNetwork) {
		if (node.hasInov(0)) {
			return false;
		} else {
			return neuralNetwork.nodeList.remove(node);
		}
	}
	
	public static void addArc(NeuralNetwork neuralNetwork) {
		//TODO: later.
	}
	
	public static boolean removeArc(Arc arc, NeuralNetwork neuralNetwork) {
		if (arc.hasInov(0)) {
			return false;
		} else {
			return neuralNetwork.arcList.remove(arc);
		}
	}
	
	public static NeuralNetwork copy(NeuralNetwork neuralNetwork) {
		NeuralNetwork childNetwork = new NeuralNetwork();
		Mapping<Node> mapping = new Mapping<Node>();
		
		for (Node node: neuralNetwork.nodeList) {
			Node copy = node.copy();
			childNetwork.nodeList.add(copy);
			mapping.add(node, copy);
		}
		
		for (Arc arc: neuralNetwork.arcList) {
			Node parent = mapping.getOther(arc.getParent());
			Node child = mapping.getOther(arc.getChild());
			childNetwork.arcList.add(arc.copy(parent, child));
		}
		
		return childNetwork;
	}
	
	public static NeuralNetwork cross(NeuralNetwork a, NeuralNetwork b) {
		NeuralNetwork childNetwork = new NeuralNetwork();
		Zipper<Node> zipper = new Zipper<Node>(a.nodeList, b.nodeList);
		
		for (Pair<Node> p: zipper) {
			if (p.X != null) {
				childNetwork.nodeList.add(p.X.copy());
			}
		}
		
		for (Pair<Arc> p: new Zipper<Arc>(a.arcList, b.arcList)) {
			Arc arc;
			if (p.X != null && p.Y != null) {
				arc = Random.grab(p.X, p.Y);
			} else if (p.X != null) {
				arc = p.X;
			} else {
				break;
			}
			
			copyOverArc(childNetwork, arc, zipper);
		}
		
		
		return childNetwork;
	}
	
	private static void copyOverArc(NeuralNetwork childNetwork, Arc arc, Zipper<Node> zipper) {
		int indexP = zipper.indexOf(arc.getParent());
		int indexC = zipper.indexOf(arc.getChild());
		
		Node parentNode = childNetwork.nodeList.get(indexP);
		Node childNode = childNetwork.nodeList.get(indexC);
		
		childNetwork.arcList.add(arc.copy(parentNode, childNode));
	}
}
