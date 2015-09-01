package neuralnetwork.basenetwork;

import java.util.ArrayList;
import java.util.List;

import neuralnetwork.Arc;
import neuralnetwork.Node;
import neuralnetwork.NodeList;

public class BaseNetwork {
	private NodeList nodeList = new NodeList();
	private List<Arc> arcList = new ArrayList<Arc>();
	
	private int defaultCounter = 0;
	
	protected void addDefaultNode(Node node) {
		
	}
}
