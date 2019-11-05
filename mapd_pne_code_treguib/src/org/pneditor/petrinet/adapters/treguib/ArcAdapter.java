/**
 * 
 */
package org.pneditor.petrinet.adapters.treguib;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.models.treguib.edges.*;
import org.pneditor.petrinet.models.treguib.petriNetwork.*;

/**
 * @author f18guibo
 *
 */
public class ArcAdapter extends AbstractArc{
	/*
	 * ATTRIBUTES
	 * -> for now the EdgeType attribute is not used
	 * BUT may be easier to use than ourArc for some cases
	 */
	protected Edge ourArc;
	protected EdgeTypes ourType;
	
	/*
	 * CONSTRUCTORS
	 */
	/**
	 * NOT OVERRIDE : WILL NOT BE USED IN PNE CODE!!!!!
	 * Default constructor. 
	 * Not used right now
	 */
	public ArcAdapter() {
		ourType = EdgeTypes.EmptierIn;
	}
	
	/**
	 * NOT OVERRIDE : WILL NOT BE USED IN PNE CODE!!!!!
	 * Constructor to have the type In or Out
	 * If not Regular, then it goes to default
	 */
	public ArcAdapter(EdgeTypes e) {
		ourType = e;
		switch(e) {
		case RegularIn:
			this.ourArc = new RegularIn();
		case RegularOut:
			this.ourArc = new RegularOut();
		default:
			break;
		}
	}

	/**
	 * If type is In -> source is a place
	 * If type is Out -> source is a transition
	 */
	@Override
	public AbstractNode getSource() {
		if(ourArc instanceof In) {
			return new PlaceAdapter("");
		} else if (ourArc instanceof Out) {
			return new TransitionAdapter("");
		}
		return null;
	}

	@Override
	public AbstractNode getDestination() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isReset() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRegular() {
		if(ourArc instanceof RegularIn || ourArc instanceof RegularOut) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isInhibitory() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Multiplicity in PNE = Weight in our code.
	 * Uses getWeight from our code. 
	 */
	@Override
	public int getMultiplicity() throws ResetArcMultiplicityException {
		return this.ourArc.getWeight();
	}
	
	/**
	 * Multiplicity in PNE = Weight in our code.
	 * Uses setWeight from our code. 
	 */
	@Override
	public void setMultiplicity(int multiplicity) throws ResetArcMultiplicityException {
		this.ourArc.setWeight(multiplicity);
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
