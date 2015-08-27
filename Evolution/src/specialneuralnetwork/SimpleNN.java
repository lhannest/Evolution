package specialneuralnetwork;

import neuralnetwork.HiddenNode;
import neuralnetwork.InputNode;
import neuralnetwork.NeuralNetwork;
import neuralnetwork.OutputNode;
import neuralnetwork.Topology;

public class SimpleNN extends NeuralNetwork {
	public SimpleNN() {
		super(makeTopology());
	}
	
	public static Topology makeTopology() {
		Topology top = new Topology();
		InputNode input = top.addInput(new InputNode());
		HiddenNode hidden = top.addHidden(new HiddenNode());
		OutputNode output = top.addOutput(new OutputNode());
		top.addArc(input, hidden);
		top.addArc(hidden, output);
		
		return top;
	}
}
