/**
 * 
 */
package edges;

import petriNetwork.In;
import petriNetwork.Place;
import petriNetwork.Transition;

/**
 * This is the standard type of Edge In.
 * 
 * @author Fratoi
 * @version 1
 * @since 17/10/2019
 */
public class RegularIn extends In {

	/*
	 * CONSTRUCTORS
	 */
	/**
	 * Constructor without param
	 */
	public RegularIn() {
		super();
	}

	/**
	 * Constructor with weight parameter.
	 * 
	 * @param weight
	 */
	public RegularIn(int weight) {
		super(weight);
	}

	/**
	 * Constructor with all parameters.
	 * 
	 * @param weight
	 * @param p
	 * @param t
	 */
	public RegularIn(int weight, Place p, Transition t) {
		super(weight, p, t);
	}
	
	/*
	 * REDEFINED METHODS
	 */
	/**
	 * This method removes the number of tokens
	 * corresponding to the weight.
	 * Only if the edge is activable.
	 */
	@Override
	public void step() {
		if (this.activable()) {
			// intermediate variables for lisibility
			int tokens = this.getMyPlace().getTokens();
			int weight = this.getWeight();
			this.getMyPlace().setTokens(tokens - weight);
		}
	}

	/**
	 * This method checks that the edge can be activated.
	 * It is activable only if there are more tokens in the place
	 * than the weight of the edge.
	 * @return boolean : activable or not ?
	 */
	@Override
	public boolean activable() {
		// intermediate variables for lisibility
		int tokens = this.getMyPlace().getTokens();
		int weight = this.getWeight();
		return (weight <= tokens) ? true : false;
	}
	
	/**
	 * toString redefined : adds the subtype.
	 */
	@Override
	public String toString() {
		return super.toString() + "\n      Subtype : Regular In\n";
	}

	/*
	 * TESTING -> tests for RegularIn are in the Edge class.
	 */

}
