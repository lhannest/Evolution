package genome;

import java.util.ArrayList;
import java.util.List;

public class Genome {
	List<NodeGene> nodeGenes = new ArrayList<NodeGene>();
	List<ArcGene> arcGenes = new ArrayList<ArcGene>();
	
	Genome() { }
	
	public Genome(int inputCount, int hiddenCount, int outputCount) {
		if (inputCount <= 0 || hiddenCount <= 0 || outputCount <= 0) {
			throw new InvalidNodeCountException("There must be at least one node in each layer.");
		}
		
		addNodeGenes(inputCount, NodeType.INPUT);
		addNodeGenes(outputCount, NodeType.OUTPUT);
		addNodeGenes(1, NodeType.BIAS);
		addNodeGenes(hiddenCount, NodeType.HIDDEN);
		
		List<NodeGene> inputs = nodeGenes.subList(0, inputCount);
		List<NodeGene> outputs = nodeGenes.subList(inputCount, inputCount+outputCount);
		List<NodeGene> bias = nodeGenes.subList(inputCount+outputCount, inputCount+outputCount+1);
		List<NodeGene> hidden = nodeGenes.subList(inputCount+outputCount+1, nodeGenes.size());
		
		addArcGenes(inputs, hidden);
		addArcGenes(hidden, outputs);
		addArcGenes(bias, hidden);
		addArcGenes(bias, outputs);
		
	}
	
	private void addArcGenes(List<NodeGene> parents, List<NodeGene> children) {
		for (NodeGene parent: parents) {
			for (NodeGene child: children) {
				this.arcGenes.add(new ArcGene(Inov.makeZero(), parent, child));
			}
		}
	}

	private void addNodeGenes(int count, NodeType type) {
		for (int i = 0; i < count; i++) {
			nodeGenes.add(new NodeGene(Inov.makeZero(), type));
		}
	}
	
	public class InvalidNodeCountException extends RuntimeException {
		public InvalidNodeCountException(String message) {
			super(message);
		}
	}
}