package helpers;

import java.util.ArrayList;
import java.util.List;

public class Mapping<E,T> {
	
	private List<Pair<E,T>> pairs = new ArrayList<Pair<E,T>>();
	
	public class Pair<E,T> {
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
	 * For the first pair {a, b} within this mapping, getOther(a) returns b. <br>
	 * getOther(c) where a == c will return b, but getOther(c) where a.equals(c) might not return b.
	 * @param object
	 * @return the first object that has been paired with <i>object</i>. If none found, returns <i>null</i>.
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
