package neuralnetwork;

import genome.Genome;
import junit.framework.TestCase;

public class NeuralNetworkTest extends TestCase {
	public void testCreate() {
		Genome genome = new Genome(3, 2, 3);
		NeuralNetwork nn = new NeuralNetwork(genome);
	}
}
