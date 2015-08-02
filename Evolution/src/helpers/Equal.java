package helpers;

public final class Equal {
	
	/**
	 * isSameClass(null,null) evaluates to false.
	 * @param a
	 * @param b
	 * @return true if a and b are the same class, false otherwise.
	 */
	public static <E, T> boolean isSameClass(E a, T b) {
		if (a == null || b == null) {
			return false;
		} else if (a.getClass() != b.getClass()) {
			return false;
		} else {
			return true;
		}
	}
}
