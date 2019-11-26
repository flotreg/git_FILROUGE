/**
 * 
 */
package org.pneditor.petrinet.adapters.treguib;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.models.treguib.edges.EdgeTypes;
import org.pneditor.petrinet.models.treguib.edges.EmptierIn;
import org.pneditor.petrinet.models.treguib.edges.RegularIn;
import org.pneditor.petrinet.models.treguib.edges.RegularOut;
import org.pneditor.petrinet.models.treguib.edges.ZeroIn;
import org.pneditor.petrinet.models.treguib.petriNetwork.Edge;
import org.pneditor.petrinet.models.treguib.petriNetwork.In;
import org.pneditor.petrinet.models.treguib.petriNetwork.Out;

/**
 * Adapter for our Edge. 
 * Place and Transition attributes needed to get sce/dest
 * @author f18guibo
 *
 */
public class ArcAdapter extends AbstractArc{
	/*
	 * ATTRIBUTES
	 */
	protected Edge ourArc;
	protected EdgeTypes ourType;
	protected PlaceAdapter itsPlaceAdapter;
	protected TransitionAdapter itsTransitionAdapter;
	
	/*
	 * CONSTRUCTORS
	 */
	/**
	 * Default constructor. 
	 * Not used.
	 */
	public ArcAdapter() {
		super();
	}
	
	/**
	 * Constructor to have the type In or Out
	 * and subtypes for In
	 */
	public ArcAdapter(EdgeTypes e) {
		ourType = e;
		switch(e) {
		case RegularIn:
			this.ourArc = new RegularIn();
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
	
	/*
	 * OVERRIDED METHODS
	 */

	/**
	 * getSource of the Arc
	 * If type is In -> source is a place
	 * If type is Out -> source is a transition
	 * @return place or transition
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
	 * getDestination of the Arc
	 * If IN -> destination = transition
	 * If OUT -> destination = place
	 * @return place or transition
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
	 * Checks if the arc is Emptier In
	 * @return boolean
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
	 * @return boolean
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
	 * @return boolean
	 */
	@Override
	public boolean isInhibitory() {
		if (ourType == EdgeTypes.ZeroIn) {
			return true;
		}
		return false;
	}

	/**
	 * Multiplicity in PNE = Weight in our code.
	 * Uses getWeight from our code. 
	 * @return arc weight
	 */
	@Override
	public int getMultiplicity() throws ResetArcMultiplicityException {
		return this.ourArc.getWeight();
	}
	
	/**
	 * Multiplicity in PNE = Weight in our code.
	 * Uses setWeight from our code. 
	 * @param the desired weight for the arc
	 */
	@Override
	public void setMultiplicity(int multiplicity) throws ResetArcMultiplicityException {
		this.ourArc.setWeight(multiplicity);
		
	}

}
