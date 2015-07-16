package helpers;

public final class Equal {
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
