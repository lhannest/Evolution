package simulation;

import helpers.Random;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import neuralnetwork2.genes.Arc;
import neuralnetwork2.genes.HiddenNode;
import neuralnetwork2.genes.InputNode;
import neuralnetwork2.genes.NeuralNetwork;
import neuralnetwork2.genes.OutputNode;
import neuralnetwork2.genes.Topology;

public class SimulationXOR {
	public static void main(String[] args) {
		List<Agent> agents = new ArrayList<Agent>();
		
		for (int i = 0; i < 10; i++) {
			agents.add(new Agent(new NeuralNetwork(makeTopology())));
		}
		
		while(agents.get(0).getFitness() < 1) {
			for (Agent a: agents) {
				double x = Random.randomDouble(0, 1.5);
				
				a.setSensor(0, x);
				a.setSensor(1, x + 0.1);
				
				a.process();
				
				double result = Math.abs((x + 0.2) - a.getActuator(0));
				
				a.decreaseFitness(result);
			}
			
			Collections.sort(agents);
			
			for (int i = agents.size() / 2; i > 0; i--) {
				Agent mother = Random.grab(agents);
				Agent father = Random.grab(agents);
			}
		}
		
	}
	
	public static Topology makeTopology() {
		Topology t = new Topology();
		
		InputNode a = new InputNode();
		InputNode b = new InputNode();
		HiddenNode hidden = new HiddenNode();
		OutputNode output = new OutputNode();
		
		t.addInputNode(a);
		t.addInputNode(b);
		t.addHiddenNode(hidden);
		t.addOutputNode(output);
		
		t.addArc(new Arc(a, hidden));
		t.addArc(new Arc(b, hidden));
		t.addArc(new Arc(hidden, output));
		
		
		return t;
	}
	
	public static int XOR(double a, double b) {
		return XOR(fix(a), fix(b)) ? 1 : 0;
	}
	
	public static boolean XOR(boolean a, boolean b) {
		return (a || b) && !(a && b);
	}
	
	public static boolean fix(double x) {
		return x >= 0.5 ? true : false;
	}
}
