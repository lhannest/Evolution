package helpers;

import java.util.ArrayList;
import java.util.List;

public class Mapping<E,T> {
	public static void main(String[] args) {
		Mapping<Integer, String> mapping = new Mapping<Integer, String>();
		mapping.add(new Integer(2), "hello");
		String hi = (String) mapping.getOther(new Integer(2));
	}
	
	private List<Pair<E,T>> pairs = new ArrayList<Pair<E,T>>();
	
	private class Pair<E,T> {
		public E e;
		public T t;
		public Pair(E e, T t) {
			this.e = e;
			this.t = t;
		}
	}
	
	public void add(E e, T t) {
		pairs.add(new Pair<E, T>(e, t));
	}
	
	/**
	 * 
	 * @param object
	 * @return the first object that has been paired with <i>object</i>.
	 */
	public Object getOther(Object object) {
		for (Pair<E, T> pair: this.pairs) {
			if (pair.e == object) {
				return pair.t;
			} else if (pair.t == object) {
				return pair.e;
			}
		}
		
		return null;
	}
}
