package helpers;

import helpers.Equal;
import junit.framework.TestCase;

public class EqualTest extends TestCase {
	public void testIsSameClass() {
		Object x = new Object();
		Object y = new Object();
		
		assertTrue(Equal.isSameClass(x, y));
		
		Integer i = new Integer(3);
		Boolean b = new Boolean(false);
		
		assertFalse(Equal.isSameClass(i, b));		
	}
}
