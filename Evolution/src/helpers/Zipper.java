package helpers;

import genome.Gene;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Zipper<E extends Comparable<? super E>> implements Iterable<Pair<E>> {	
	private List<Pair<E>> pairs = new ArrayList<Pair<E>>();
	
	public Zipper(List<E> a, List<E> b) {
		Collections.sort(a);
		Collections.sort(b);
		
		for (E e1: a) {
			pairs.add(new Pair<E>(e1, null));
		}
		
		int k = 0;
		for (E e2: b) {
			Pair<E> pair = pairs.get(k);
			
			int cmp = e2.compareTo(pair.X);
			
			if (cmp == 0) {
				pairs.set(k, new Pair<E>(pair.X, e2));
			} else if (cmp < 0) {
				pairs.add(k, new Pair<E>(null, e2));
			}
			
			k++;
		}
	}

	@Override
	public Iterator<Pair<E>> iterator() {
		return pairs.iterator();
	}
}
