package neuralnetwork3;

import junit.framework.TestCase;

public class InovTest extends TestCase {
	public void testEquals() {
		Inov inov = Inov.getNegative(2);
		Inov inov2 = Inov.getNegative(-2);
		
		assertEquals(inov, inov2);
	}
}
