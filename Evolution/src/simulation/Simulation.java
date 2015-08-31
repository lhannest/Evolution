package simulation;

import neuralnetwork.HiddenNode;
import neuralnetwork.InputNode;
import neuralnetwork.NeuralNetwork;
import neuralnetwork.Node;
import neuralnetwork.OutputNode;
import neuralnetwork.Topology;

public class Simulation {
	public static double function(double x) {
		return Math.sin(x);
	}
	
	public static void main(String[] args) {
		Population population = new Population();
		
		int RANGE = 1;
		
		for (int i = 0; i < 100; i++) {
			Topology top = new Topology();
			Node input = top.addInput(new InputNode());
			Node hidden = top.addHidden(new HiddenNode());
			Node output = top.addOutput(new OutputNode());
			top.addArc(input, hidden);
			top.addArc(hidden, output);
			
			NeuralNetwork neuralNetwork = new NeuralNetwork(top);
			
			population.add(new Agent(neuralNetwork));
		}
		
		while (true) {
			
			for (Agent agent: population) {
				
				double error = 0;
				
				for (int i = 0; i < RANGE; i++) {
					double x = i/10f;// + Random.randomDouble(-0.001, 0.001);
					agent.setSensor(0, x);
					agent.process();
					double answer = agent.getActuator(0);
					
					error += Math.abs(function(x) - answer);
					System.out.println(((float)x)+","+((float)answer));
				}
				
				System.out.println();
				System.out.println();
				System.out.println();
				
				if (error <= 0.1) {
					RANGE ++;
					break;
				}
				
				agent.decreaseFitness(error);
			}
			
			population.sort();
			int i = population.kill(0.5);
			population.breed(i, 0.5);
			
			for (Agent agent: population) {
				agent.resetFitness();
			}
		}
		
	}
}
