package genome;

import junit.framework.TestCase;

public class MutatorTest extends TestCase {
	public void testCopy() {
		Mutator mutator = new Mutator();
		Genome genome = new Genome(2, 3, 4);
		Genome copy = mutator.copy(genome);
		
		for (int i = 0; i < genome.arcGenes.size(); i++) {
			assertEquals(genome.arcGenes.get(i), copy.arcGenes.get(i));
		}
		
		for (int i = 0; i < genome.nodeGenes.size(); i++) {
			assertEquals(genome.nodeGenes.get(i), copy.nodeGenes.get(i));
		}
	}
}
