package neuralnetwork2.genes;

import genome.Inov;

import java.util.ArrayList;
import java.util.List;

public class Topology {
	List<InputNode> inputNodes = new ArrayList<InputNode>();
	List<HiddenNode> hiddenNodes = new ArrayList<HiddenNode>();
	List<OutputNode> outputNodes = new ArrayList<OutputNode>();
	List<Arc> arcs = new ArrayList<Arc>();
	
	public boolean addInputNode(InputNode node) {
		node.setInov(Inov.makeZero());
		return inputNodes.add(node);
	}
	
	public boolean addHiddenNode(HiddenNode node) {
		node.setInov(Inov.makeZero());
		return hiddenNodes.add(node);
	}
	
	public boolean addOutputNode(OutputNode node) {
		node.setInov(Inov.makeZero());
		return outputNodes.add(node);
	}
	
	public boolean addArc(Arc arc) {
		arc.setInov(Inov.makeZero());
		return arcs.add(arc);
	}
}
