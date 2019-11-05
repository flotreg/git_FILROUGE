/**
 * 
 */
package org.pneditor.petrinet.models.treguib.edges;

import org.pneditor.petrinet.models.treguib.petriNetwork.*;

/**
 * This type of Edge is activated only if there is no token in the starting
 * place.
 * 
 * @author Fratoi
 * @version 1
 * @since 17/10/2019
 */
public class ZeroIn extends In {

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
	 * REDEFINED METHODS
	 */
	/**
	 * This method ain't do nothing for ZeroIn
	 */
	@Override
	public void step() {
	}

	/**
	 * This method check if the transition can be activated or not.
	 * For this edge, it is activated only if the number of tokens = 0.
	 * @return
	 */
	@Override
	public boolean activable() {
		// intermediate values for clarity
		int tokens = this.getMyPlace().getTokens();
		return (tokens == 0) ? true : false;
	}
	
	/**
	 * toString() redefined : add subtype
	 * @return String
	 */
	@Override
	public String toString() {
		return super.toString() +  "\n      Subtype : Zero In\n";
	}

	
	
	/*
	 * TESTING -> tests for ZeroIn in the Edge class
	 */

}
