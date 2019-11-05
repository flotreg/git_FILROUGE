/**
 * 
 */
package org.pneditor.petrinet.adapters.treguib;

import org.pneditor.petrinet.AbstractTransition;
import org.pneditor.petrinet.models.treguib.petriNetwork.Transition;

/**
 * 
 * @author f18guibo
 *
 */
public class TransitionAdapter extends AbstractTransition {
	
	protected Transition ourTransition;

	public TransitionAdapter(String label) {
		super(label);
		ourTransition = new Transition();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
