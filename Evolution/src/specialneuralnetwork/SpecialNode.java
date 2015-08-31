package specialneuralnetwork;

import neuralnetwork.HiddenNode;
import neuralnetwork.Node;

public class SpecialNode extends HiddenNode {
	protected SpecialNode(SpecialNode node) {
		super(node);
	}
	
	@Override
	protected Node callCopyConstructor() {
		return null;
	}

}
