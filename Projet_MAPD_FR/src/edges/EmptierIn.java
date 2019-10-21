/**
 * 
 */
package edges;

import petriNetwork.In;
import petriNetwork.Place;
import petriNetwork.Transition;

/**
 * This type of edge empty all the tokens from the starting place.
 * 
 * @author Fratoi
 * @version 1
 * @since 17/10/2019
 */
public class EmptierIn extends In {

	/*
	 * ATTRIBUTES -> inherited from Edge.
	 */

	/*
	 * CONSTRUCTORS
	 */
	/**
	 * Constructor without parameter. Uses super().
	 */
	public EmptierIn() {
		super();
	}

	/**
	 * Constructor with weight parameter. Uses super().
	 * 
	 * @param w
	 */
	public EmptierIn(int w) {
		super(w);
	}

	/**
	 * Constructor with all parameters. Uses super().
	 * 
	 * @param w
	 * @param p
	 * @param t
	 */
	public EmptierIn(int w, Place p, Transition t) {
		super(w, p, t);
	}

	/*
	 * OWN METHODS
	 */
	/**
	 * This method removes all the token from the 
	 * starting place. 
	 */
	@Override
	public void step() {
		if(this.activable()) {
			this.getMyPlace().setTokens(0);
		}
	}
	
	/**
	 * This method check if the transition can be activated or not. 
	 * @return boolean : is the edge activable or not ?
	 */
	@Override
	public boolean activable() {
		// intermediate variables for lisibility
		int tokens = this.getMyPlace().getTokens();
		int weight = this.getWeight();
		return (weight <= tokens) ? true : false;
	}
	
	/**
	 * toString() redefined : add subtype
	 * @return String
	 */
	@Override
	public String toString() {
		return super.toString() +  "\n      Subtype : Emptier In\n";
	}

	/*
	 * TESTING -> tests for EmptierIn in the Edge class
	 */

}
