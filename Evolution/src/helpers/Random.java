package helpers;

import java.util.List;

public class Random {
	/**
	 * Randomly returns a double from the range [min, max).
	 * @param min
	 * @param max
	 * @return
	 */
	public static double randomDouble(double min, double max) {
		return (Math.random() * (max-min)) + min;
	}
	
	/**
	 * Randomly returns an integer from the range [min, max).
	 * @param min
	 * @param max
	 * @return
	 */
	public static int randomInteger(int min, int max) {
		return (int) randomDouble(min, max);
	}
	
	public static boolean randomBoolean(double probability) {
		if (Math.random() <= probability) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Randomly returns either obj1 or obj2.
	 * @param obj1
	 * @param obj2
	 * @return
	 */
	public static <E> E grab(E obj1, E obj2) {
		if (randomBoolean(0.5)) {
			return obj1;
		} else {
			return obj2;
		}
	}
	
	/**
	 * Randomly returns one of the members of list.
	 * @param list
	 * @return
	 */
	public static <E> E grab(List<E> list) {
		int index = Random.randomInteger(0, list.size());
		return list.get(index);
	}
	
	public static <E> E grab(E[] array) {
		int index = Random.randomInteger(0, array.length);
		return array[index];
	}
}
