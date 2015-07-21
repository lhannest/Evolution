package genome;

import junit.framework.TestCase;

public class GenomeTest extends TestCase {
	public void testConstructor() {
		final int I = 919;
		final int H = 99;
		final int O = 929;
		
		Genome genome = new Genome(I, H, O);
		
		assertEquals(genome.arcGenes.size(), I*H + H*O + H + O);
		assertEquals(genome.nodeGenes.size(), I + H + O + 1);
	}
}
