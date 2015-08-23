package neuralnetwork2;

import genome.Inov;

import java.util.ArrayList;
import java.util.List;

import neuralnetwork2.genes.Arc;
import neuralnetwork2.genes.HiddenNode;
import neuralnetwork2.genes.InputNode;
import neuralnetwork2.genes.Node;
import neuralnetwork2.genes.OutputNode;

public class NetBuilder {
	List<InputNode> inputs = new ArrayList<InputNode>();
	List<HiddenNode> hidden = new ArrayList<HiddenNode>();
	List<OutputNode> outputs = new ArrayList<OutputNode>();
	List<Arc> arcs = new ArrayList<Arc>();
	
	public InputNode addInputNode() {
		InputNode node = new InputNode();
		this.inputs.add(node);
		return node;
	}
	
	public HiddenNode addHiddenNode() {
		HiddenNode node = new HiddenNode(Inov.makeZero());
		this.hidden.add(node);
		return node;
	}
	
	public OutputNode addOutputNode() {
		OutputNode node = new OutputNode();
		this.outputs.add(node);
		return node;
	}
	
	public void addArc(Node parent, Node child) {
		this.arcs.add(new Arc(Inov.makeZero(), parent, child));
	}
}
