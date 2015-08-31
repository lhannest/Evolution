package simulation;

import neuralnetwork.Mutator;
import neuralnetwork.NeuralNetwork;
import neuralnetwork.Reproducer;
import helpers.Random;


public class Agent implements Comparable<Agent> {
	public NeuralNetwork neuralNetwork;
	private double fitness = 0;
	private final int SENSOR_COUNT;
	private final int ACTUATOR_COUNT;
	
	public double getFitness() {
		return this.fitness;
	}
	
	public Agent(NeuralNetwork neuralNetwork) {
		this.neuralNetwork = neuralNetwork;
		this.SENSOR_COUNT = neuralNetwork.inputSize();
		this.ACTUATOR_COUNT = neuralNetwork.outputSize();
	}
	
	public Agent mate(Agent other) {
		NeuralNetwork neuralNetwork = null;
		
		switch (Random.randomInteger(0, 4)) {
		case 0:
			neuralNetwork = Reproducer.cross(this.neuralNetwork, other.neuralNetwork);
			break;
			
		case 1:
			neuralNetwork = Reproducer.merge(this.neuralNetwork, other.neuralNetwork);
			break;
			
		case 2:
			neuralNetwork = Reproducer.copy(this.neuralNetwork);
			break;
			
		case 3:
			neuralNetwork = Reproducer.copy(other.neuralNetwork);
			break;
		}
		
		if (Random.randomBoolean(0.3)) {
			Mutator mutator = new Mutator();
			mutator.jiggleWeight(neuralNetwork);
		}
		
		return new Agent(neuralNetwork);
	}
	
	public void process() {
		neuralNetwork.process();
	}
	
	public void resetFitness() {
		this.fitness = 0;
	}
	
	public void increaseFitness(double amount) {
		this.fitness += amount;
	}
	
	public void decreaseFitness(double amount) {
		this.fitness -= amount;
	}
	
	public void setSensor(int i, double value) {
		neuralNetwork.setInput(i, value);
	}
	
	public double getActuator(int i) {
		return neuralNetwork.getOutput(i);
	}
	
	@Override
	public int compareTo(Agent other) {
		return (int) Math.signum(other.fitness - this.fitness);
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + "[fitness=" + fitness + "]";
	}
}