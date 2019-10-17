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

	public void step() {
		if (this.activable()) {
			// intermediate variables for lisibility
			int tokens = this.getMyPlace().getTokens();
			int weight = this.getWeight();
			this.getMyPlace().setTokens(tokens - weight);
		}
	}

	public boolean activable() {
		// intermediate variables for lisibility
		int tokens = this.getMyPlace().getTokens();
		int weight = this.getWeight();
		return (weight <= tokens) ? true : false;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
