package neuralnetwork;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class NodeList extends ArrayList<Node> {
	
	/**
	 * @param other
	 * @return the first member <i>x</i> where other.equals(x). If there is no
	 * such member, then returns <i>null</i>
	 */
	public Node getMember(Node other) {
		for (Node node: this) {
			if (other.equals(node)) {
				return node;
			}
		}
		
		return null;
	}
}
