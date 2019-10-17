/**
 * 
 */
package edges;

import petriNetwork.Edge;
import petriNetwork.Place;
import petriNetwork.Transition;

/**
 * This type of Edge is activated only if there is no token in the starting
 * place.
 * 
 * @author Fratoi
 * @version 1
 * @since 17/10/2019
 */
public class ZeroIn extends Edge {

	/*
	 * ATTRIBUTES -> inherited from Edge
	 */

	/*
	 * CONSTRUCTORS
	 */
	/**
	 * Constructor without parameter Uses super
	 */
	public ZeroIn() {
		super();
	}

	/**
	 * Constructor with weight parameter Uses super
	 * 
	 * @param w
	 */
	public ZeroIn(int w) {
		super(w);
	}

	/**
	 * Constructor with all parameters Uses super
	 * 
	 * @param w
	 * @param p
	 * @param t
	 */
	public ZeroIn(int w, Place p, Transition t) {
		super(w, p, t);
	}

	/*
	 * OWN METHODS
	 */
	/**
	 * This method ain't do nothing for ZeroIn
	 */
	public void step() {
	}

	/**
	 * This method check if the transition can be activated or not.
	 * For this edge, it is activated only if the number of tokens = 0.
	 * @return
	 */
	public boolean activable() {
		// intermediate values for clarity
		int tokens = this.getMyPlace().getTokens();
		return (tokens == 0) ? true : false;
	}

	/*
	 * MAIN FOR TESTING -> tests in the Edge class
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
