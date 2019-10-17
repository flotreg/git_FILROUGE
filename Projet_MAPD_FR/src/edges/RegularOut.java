/**
 * 
 */
package edges;

import petriNetwork.Out;
import petriNetwork.Place;
import petriNetwork.Transition;

/**
 * @author Fratoi
 *
 */
public class RegularOut extends Out {

	/*
	 * CONSTRUCTORS
	 */
	/**
	 * Constructor without parameter.
	 * Uses super().
	 */
	public RegularOut() {
		super();
	}

	/**
	 * Constructor with weight parameter.
	 * Uses super().
	 * @param weight
	 */
	public RegularOut(int weight) {
		super(weight);
	}

	/**
	 * Constructor with all parameters.
	 * Uses super(). 
	 * @param weight
	 * @param p
	 * @param t
	 */
	public RegularOut(int weight, Place p, Transition t) {
		super(weight, p, t);
	}
	
	/*
	 * REDEFINED METHODS
	 */
	/**
	 * This method fills the ending place with the number of tokens
	 * that matches the weight of the edge. 
	 */
	@Override
	public void step() {
		getMyPlace().setTokens(getMyPlace().getTokens()+this.getWeight());
	}
	
	/**
	 * toString() redefined : adds the subtype
	 */
	@Override
	public String toString() {
		return super.toString() + "\n      Subtype : Regular Out";
	}

	/*
	 * TESTING -> tests for RegularOut are in the Edge class.
	 */

}
