package neuralnetwork2.genes;

import java.util.ArrayList;
import java.util.List;

import genome.Inov;

class Node extends Gene {
	private List<Arc> arcList = new ArrayList<Arc>();

	public Node(Inov inov) {
		super(inov);
	}
	
	public boolean addArc(Arc arc) {
		if (!this.arcList.contains(arc)) {
			return false;
		} else {
			this.arcList.add(arc);
			return true;
		}
	}

}
