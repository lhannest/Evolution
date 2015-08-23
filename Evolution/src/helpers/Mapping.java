package helpers;

import java.util.ArrayList;
import java.util.List;

public class Mapping<E> {
	
	private List<Pair> pairs = new ArrayList<Pair>();
	
	public class Pair {
		public E obj1;
		public E obj2;
		public Pair(E obj1, E obj2) {
			this.obj1 = obj1;
			this.obj2 = obj2;
		}
	}
	
	public void add(E obj1, E obj2) {
		pairs.add(new Pair(obj1, obj2));
	}
	
	/**
	 * For the first pair {a, b} within this mapping, getOther(a) returns b. <br>
	 * getOther(c) where a == c will return b, but getOther(c) where a.equals(c) might not return b.
	 * @param object
	 * @return the first object that has been paired with <i>object</i>. If none found, returns <i>null</i>.
	 */
	public E getOther(Object object) {
		for (Pair pair: this.pairs) {
			if (pair.obj1 == object) {
				return pair.obj2;
			} else if (pair.obj2 == object) {
				return pair.obj1;
			}
		}
		
		return null;
	}
}
