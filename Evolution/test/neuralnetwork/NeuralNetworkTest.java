package neuralnetwork;

import neuralnetwork.Topology;
import neuralnetwork.HiddenNode;
import neuralnetwork.InputNode;
import neuralnetwork.NeuralNetwork;
import neuralnetwork.Node;
import neuralnetwork.OutputNode;
import junit.framework.TestCase;

public class NeuralNetworkTest extends TestCase {
	
	public void testCompatible() {
		NeuralNetwork a = new NeuralNetwork(build());
		NeuralNetwork b = new NeuralNetwork(build());
		
		Topology builder = build();
		
		NeuralNetwork c = new NeuralNetwork(builder);
		
		assertTrue(a.compatible(b));
		assertTrue(b.compatible(a));
		//assertFalse(a.compatible(c));
		//assertFalse(c.compatible(a));
	}
	
	public static Topology build() {
		Topology builder = new Topology();
		
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
