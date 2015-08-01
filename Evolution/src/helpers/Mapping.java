package helpers;

import java.util.ArrayList;
import java.util.List;

public class Mapping<E,T> {
	public static void main(String[] args) {
		Mapping<Integer, String> mapping = new Mapping<Integer, String>();
		mapping.add(new Integer(2), "hello");
		String hi = (String) mapping.get(new Integer(2));
	}
	
	private class Pair<E,T> {
		public E e;
		public T t;
		public Pair(E e, T t) {
			this.e = e;
			this.t = t;
		}
	}
	
	List<Pair<E,T>> pairs = new ArrayList<Pair<E,T>>();
	
	public void add(E e, T t) {
		pairs.add(new Pair<E, T>(e, t));
	}
	
	public T get(E e) {
		for (Pair pair: this.pairs) {
			if (pair.e.equals(e)) {
				return (T) pair.t;
			}
		}
		return null;
	}
}
