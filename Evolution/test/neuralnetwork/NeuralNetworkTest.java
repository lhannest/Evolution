package neuralnetwork;

import neuralnetwork.ComponentSet;
import neuralnetwork.HiddenNode;
import neuralnetwork.Inov;
import neuralnetwork.InputNode;
import neuralnetwork.NeuralNetwork;
import neuralnetwork.Node;
import neuralnetwork.OutputNode;
import junit.framework.TestCase;

public class NeuralNetworkTest extends TestCase {
	
	public void testConstructor() {
		NeuralNetwork nn = new NeuralNetwork(build());
		
		assertSame(nn.nodeList.size(), 4);
		assertSame(nn.arcList.size(), 3);
		
		for (Node node: nn.nodeList) {
			Node n = new InputNode();
			assertTrue(node.equals(n.copyWithNewInov(Inov.getNegative(3))));
			//Testing just the first node
			break;
		}
	}
	
	public void testCompatible() {
		NeuralNetwork a = new NeuralNetwork(build());
		NeuralNetwork b = new NeuralNetwork(build());
		
		ComponentSet builder = build();
		builder.addArc(new Node(), new Node());
		
		NeuralNetwork c = new NeuralNetwork(builder);
		
		assertTrue(a.compatible(b));
		assertTrue(b.compatible(a));
		assertFalse(a.compatible(c));
		assertFalse(c.compatible(a));
	}
	
	public static ComponentSet build() {
		ComponentSet builder = new ComponentSet();
		
		Node in1 = builder.addInput(new InputNode());
		Node in2 = builder.addInput(new InputNode());
		Node a = builder.addHidden(new HiddenNode());
		Node out = builder.addOutput(new OutputNode());
		
		builder.addArc(in1, a);
		builder.addArc(in2, a);
		builder.addArc(a, out);
		
		return builder;
	}
}
