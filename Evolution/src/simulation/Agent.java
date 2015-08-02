package simulation;

import neuralnetwork2.NeuralNetwork;

abstract public class Agent implements Comparable<Agent> {
	private NeuralNetwork neuralNetwork;
	
	public Agent(int sensorCount, int actuatorCount) {
		neuralNetwork = new NeuralNetwork(sensorCount, 1, actuatorCount);
	}
	
	public abstract double fitnessFunction();
	
	@Override
	public int compareTo(Agent other) {
		return (int) Math.signum(this.fitnessFunction() - other.fitnessFunction());
	}
}