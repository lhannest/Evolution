package simulation;

import helpers.Random;

public class Simulation {
	public static void main(String[] args) {
		Population population = new Population();
		
		for (int i = 0; i < 10; i++) {
			population.add(new Agent(1, 1));
		}
		
		while (true) {
			
			for (Agent agent: population) {
				double error = 0;
				
				for (int i = 0; i < 10; i++) {
					double x = i/10f;// + Random.randomDouble(-0.1, 0.1);
					agent.setSensor(0, x);
					agent.process();
					double answer = agent.getActuator(0);
					
					error += Math.abs(Math.sin(x) - answer);
				}
				
				agent.decreaseFitness(error);
			}
			
			population.sort();
			population.kill(0.5);
			population.breed(0.5);
			
			for (Agent agent: population) {
				agent.resetFitness();
			}
		}
		
	}
}
