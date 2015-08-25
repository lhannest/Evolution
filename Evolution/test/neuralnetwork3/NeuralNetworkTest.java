package neuralnetwork3;

import junit.framework.TestCase;

public class NeuralNetworkTest extends TestCase {
	public void testTakeComponent() {
		NeuralNetwork nn = new NeuralNetwork(build());
		Node node = new Node();
		
		nn.takeExtraNode(node);
		
		Node lastNode = null;
		
		for (Node n: nn.nodeList) {
			lastNode = n;
		}
		
		assertNotSame(node, lastNode);
		assertTrue(node.isInovNull());
		assertFalse(lastNode.isInovNull());
	}
	
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
		
		NetworkBuilder builder = build();
		builder.addArc(new Node(), new Node());
		
		NeuralNetwork c = new NeuralNetwork(builder);
		
		assertTrue(a.compatible(b));
		assertTrue(b.compatible(a));
		assertFalse(a.compatible(c));
		assertFalse(c.compatible(a));
	}
	
	public static NetworkBuilder build() {
		NetworkBuilder builder = new NetworkBuilder();
		
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
