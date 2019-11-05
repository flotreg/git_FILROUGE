/**
 * 
 */
package org.pneditor.petrinet.adapters.treguib;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.AbstractPlace;
import org.pneditor.petrinet.AbstractTransition;
import org.pneditor.petrinet.PetriNetInterface;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.UnimplementedCaseException;
import org.pneditor.petrinet.models.treguib.petriNetwork.PetriNetwork;
import org.pneditor.petrinet.models.treguib.edges.*;

/**
 * Main adapter.
 * Interacts with PetriNetInterface in PNE
 * Mainly linked to PetriNetwork in our code
 * @author f18guibo
 *
 */
public class PetriNetAdapter extends PetriNetInterface {
	
	/*
	 * ATTRIBUTES
	 */
	PetriNetwork pn = PetriNetwork.getInstance();
	PlaceAdapter pa = null;
	TransitionAdapter ta;
	ArcAdapter aa = null;

	/*
	 * OVERRIDED METHODS
	 */
	/**
	 * Build new PlaceAdapter.
	 * The latter will create a Place in our code. 
	 * String label not used for now
	 */
	@Override
	public AbstractPlace addPlace() {
		this.pa = new PlaceAdapter("");
		return pa;
	}
	
	/**
	 * Build new TransitionAdapter.
	 * The latter will create a Transition in our code. 
	 * String label not used for now
	 */
	@Override
	public AbstractTransition addTransition() {
		this.ta = new TransitionAdapter("");
		return ta;
	}

	/**
	 * Have to convert to the In/Out logic
	 * If the source is a Place, then it is a RegularIn
	 * If the source is a Transition, then it is a RegularOut
	 */
	@Override
	public AbstractArc addRegularArc(AbstractNode source, AbstractNode destination) throws UnimplementedCaseException {
		if(source instanceof AbstractPlace) {
			this.aa = new ArcAdapter(EdgeTypes.RegularIn);
		}else if(source instanceof AbstractTransition) {
			this.aa = new ArcAdapter(EdgeTypes.RegularOut);
		}
		return aa;
	}

	/**
	 * This returns a ArcAdapter with a type ZeroIn. 
	 * @return ArcAdapter with a type ZeroIn
	 */
	@Override
	public AbstractArc addInhibitoryArc(AbstractPlace place, AbstractTransition transition)
			throws UnimplementedCaseException {
		this.aa = new ArcAdapter(EdgeTypes.ZeroIn);
		return aa;
	}

	@Override
	public AbstractArc addResetArc(AbstractPlace place, AbstractTransition transition)
			throws UnimplementedCaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removePlace(AbstractPlace place) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeTransition(AbstractTransition transition) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeArc(AbstractArc arc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEnabled(AbstractTransition transition) throws ResetArcMultiplicityException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void fire(AbstractTransition transition) throws ResetArcMultiplicityException {
		// TODO Auto-generated method stub
		
	}
	
	
	public static void main(String[] args) {
		// TEST 1 : build a place
		System.out.println("\nTEST 1 : build a place");
		PetriNetAdapter p = new PetriNetAdapter();
		p.addPlace();
		System.out.println(p);
		p.pa.setTokens(5);
		System.out.println(p.pa.getTokens());
		System.out.println(p.pa.ourPlace.toString());
		
		// TEST 2 : build a transition
		System.out.println("\n TEST 2 : build a transition");
		p.addTransition();
		System.out.println(p.ta.getClass());
		System.out.println(p.ta.ourTransition.toString());
		
		// DEBUG
		System.out.println("DEBUG");
		System.out.println((p.pa instanceof AbstractPlace));
		System.out.println((p.ta instanceof AbstractTransition));
		
		// TEST 3 : build an IN edge between the two
		System.out.println("\n TEST 3 : build an edge between the two");
		try {
			p.addRegularArc(p.pa, p.ta );
			System.out.println(p.aa.getSource());
		} catch (UnimplementedCaseException e) {
			e.printStackTrace();
		}
		try {
			p.aa.setMultiplicity(18);
		} catch (ResetArcMultiplicityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(p.aa.ourArc.toString());
		
		
	}

}