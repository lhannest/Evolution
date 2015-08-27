package neuralnetwork;

import junit.framework.TestCase;

public class ReproducerTest extends TestCase {
	public void testCopy() {
		NeuralNetwork nn = networkFactory();
		NeuralNetwork b = Reproducer.copy(nn);
		
		System.out.println(nn.nodeList.size());
		System.out.println(b.nodeList.size());
		
		assertEquals(nn.nodeList.size(), b.nodeList.size());
		assertEquals(nn.arcList.size(), b.arcList.size());
		
		for (int i = 0; i < nn.nodeList.size(); i++) {
			nn.nodeList.get(i).equals(b.nodeList.get(i));
		}
	}
	
	
	private NeuralNetwork networkFactory() {
		Topology set = new Topology();
		
		Node input = set.addInput(new InputNode());
		Node hidden = set.addHidden(new HiddenNode());
		Node output = set.addOutput(new OutputNode());
		
		set.addArc(input, hidden);
		set.addArc(hidden, output);
		
		return new NeuralNetwork(set);
	}
}
