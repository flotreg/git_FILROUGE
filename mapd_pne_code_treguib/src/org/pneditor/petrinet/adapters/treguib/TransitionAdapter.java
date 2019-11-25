/**
 * 
 */
package org.pneditor.petrinet.adapters.treguib;

import org.pneditor.petrinet.AbstractTransition;
import org.pneditor.petrinet.models.treguib.petriNetwork.Transition;

/**
 * Transition adapter. 
 * Has one of our transition in attribute to make the connection with our code.
 * Does not do a lot compared to our code. 
 * @author f18guibo
 *
 */
public class TransitionAdapter extends AbstractTransition {
	
	/*
	 * ATTRIBUTES
	 */
	protected Transition ourTransition;

	/*
	 * CONSTRUCTOR
	 */
	public TransitionAdapter(String label) {
		super(label);
		ourTransition = new Transition();
	}

}
