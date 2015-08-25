package neuralnetwork3;

import helpers.Equal;

class Signature {
	private final int INPUT_COUNT;
	private final int HIDDEN_COUNT;
	private final int OUTPUT_COUNT;
	private final int ARC_COUNT;
	
	public Signature(int inputCount, int hiddenCount, int outputCount, int arcCount) {
		INPUT_COUNT = inputCount;
		HIDDEN_COUNT = hiddenCount;
		OUTPUT_COUNT = outputCount;
		ARC_COUNT = arcCount;
	}
	
	@Override
	public boolean equals(Object other) {
		if (!Equal.isSameClass(this, other)) {
			return false;
		} else {
			Signature sig = (Signature) other;
			
			boolean equal =
					this.INPUT_COUNT == sig.INPUT_COUNT &&
					this.HIDDEN_COUNT == sig.HIDDEN_COUNT &&
					this.OUTPUT_COUNT == sig.OUTPUT_COUNT &&
					this.ARC_COUNT == sig.ARC_COUNT;
			
			return equal;
		}
	}
}
