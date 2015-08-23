package simulation;

import helpers.Random;
import neuralnetwork2.FeedForwardNeuralNetwork;
import neuralnetwork2.Mutator;
import neuralnetwork2.genes.NeuralNetwork;

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
	
	public Agent(int sensorCount, int actuatorCount) {
		neuralNetwork = new FeedForwardNeuralNetwork(sensorCount, 4, actuatorCount);
		SENSOR_COUNT = sensorCount;
		ACTUATOR_COUNT = actuatorCount;
	}
	
	public Agent mate(Agent other) {
		Agent child = new Agent(SENSOR_COUNT, ACTUATOR_COUNT);
		
		switch (Random.randomInteger(0, 5)) {
		case 1:
			//child.neuralNetwork = this.neuralNetwork.cross(other.neuralNetwork);
			break;
			
		case 2:
			//child.neuralNetwork = this.neuralNetwork.merge(other.neuralNetwork);
			break;
			
		case 3:
			child.neuralNetwork = this.neuralNetwork.copy();
			break;
			
		case 4:
			child.neuralNetwork = other.neuralNetwork.copy();
			break;
			
		}
		
		child.neuralNetwork.mutateParamiters();		
		child.neuralNetwork.mutateTopology();
		
		return child;
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