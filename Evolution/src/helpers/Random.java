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
	 * Randomly returns an integer between <i>min</i> inclusive and <i>max</> exclusive.
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
	 * Randomly returns either <i>obj1</i> or <i>obj2</i>.
	 * @param obj1
	 * @param obj2
	 * @return Random.<i>randomBoolean</i>(0.5) ? obj1 : obj2
	 */
	public static <E> E grab(E obj1, E obj2) {
		if (randomBoolean(0.5)) {
			return obj1;
		} else {
			return obj2;
		}
	}
	
	/**
	 * Randomly returns one of the members of <i>list</i>.
	 * @param list
	 * @return
	 */
	public static <E> E grab(List<E> list) {
		int index = Random.randomInteger(0, list.size());
		return list.get(index);
	}
	
	/**
	 * Randomly returns one of the members of <i>array</i>.
	 * @param array
	 * @return
	 */
	public static <E> E grab(E[] array) {
		int index = Random.randomInteger(0, array.length);
		return array[index];
	}
}
