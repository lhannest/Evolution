package helpers;

public class Pair<E> {
	public final E X;
	public final E Y;
	public Pair(E x, E y) {
		this.X = x;
		this.Y = y;
	}
	
	/**
	 * Returns X if it is not null, otherwise returns Y.
	 * @return X != <b>null</b> ? X : (Y != <b>null</b> ? Y : <b>null</b>)
	 */
	public E getNotNull() {
		if (X == null) {
			return Y;
		} else {
			return X;
		}
	}
}