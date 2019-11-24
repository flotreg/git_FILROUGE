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
	protected PlaceAdapter itsPlaceAdapter;
	protected TransitionAdapter itsTransitionAdapter;
	
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
			this.ourType = EdgeTypes.RegularIn;
			break;
		case RegularOut:
			this.ourArc = new RegularOut();
			break;
		case ZeroIn:
			this.ourArc = new ZeroIn();
			break;
		case EmptierIn:
			this.ourArc = new EmptierIn();
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
		if(this.ourArc instanceof In) {
			itsPlaceAdapter.ourPlace = this.ourArc.getMyPlace();
			return itsPlaceAdapter;
			
		} else if (this.ourArc instanceof Out) {
			itsTransitionAdapter.ourTransition = this.ourArc.getMyTransition();
			return itsTransitionAdapter;
		}
		return null;
	}

	/**
	 * If IN -> destination = transition
	 * If OUT -> destination = place
	 */
	@Override
	public AbstractNode getDestination() {
		if(this.ourArc instanceof In) {
			itsTransitionAdapter.ourTransition = this.ourArc.getMyTransition();
			return itsTransitionAdapter;
			
		}else if(this.ourArc instanceof Out) {
			itsPlaceAdapter.ourPlace = this.ourArc.getMyPlace();
			return itsPlaceAdapter;
		}
		return null;
	}

	/**
	 * Checks if the arc is emptier in
	 * TO DO : change instanceof with EdgeTypes
	 */
	@Override
	public boolean isReset() {
		if (ourArc instanceof EmptierIn) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if the arc is regular
	 * TO DO : change instanceof with EdgeTypes
	 */
	@Override
	public boolean isRegular() {
		if(ourArc instanceof RegularIn || ourArc instanceof RegularOut) {
			return true;
		}
		return false;
	}
	/**
	 * Checks if the arc is zero in
	 * TO DO : change instanceof with EdgeTypes
	 */
	@Override
	public boolean isInhibitory() {
		if (ourArc instanceof ZeroIn) {
			return true;
		}
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
