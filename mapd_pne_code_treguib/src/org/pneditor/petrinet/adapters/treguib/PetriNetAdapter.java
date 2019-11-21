/**
 * 
 */
package org.pneditor.petrinet.adapters.treguib;

import java.util.Map;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.AbstractPlace;
import org.pneditor.petrinet.AbstractTransition;
import org.pneditor.petrinet.PetriNetInterface;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.UnimplementedCaseException;
import org.pneditor.petrinet.models.treguib.petriNetwork.PetriNetwork;
import org.pneditor.petrinet.models.treguib.petriNetwork.Place;
import org.pneditor.petrinet.models.treguib.edges.*;

/**
 * Main adapter. Interacts with PetriNetInterface in PNE Mainly linked to
 * PetriNetwork in our code
 * 
 * @author f18guibo
 *
 */
public class PetriNetAdapter extends PetriNetInterface {

	/*
	 * ATTRIBUTES
	 */
	PetriNetwork ourPetri = PetriNetwork.getInstance();

	/*
	 * OVERRIDED METHODS
	 */
	/**
	 * Build new PlaceAdapter. The latter will create a Place in our code. String
	 * label not used for now
	 */
	@Override
	public AbstractPlace addPlace() {
		PlaceAdapter pa = new PlaceAdapter("");
		pa.ourPlace = this.ourPetri.buildPlace(0);
		return pa;
	}

	/**
	 * Build new TransitionAdapter. The latter will create a Transition in our code.
	 * String label not used for now
	 */
	@Override
	public AbstractTransition addTransition() {
		TransitionAdapter ta = new TransitionAdapter("");
		ta.ourTransition = this.ourPetri.buildTransition();
		return ta;
	}

	/**
	 * Have to convert to the In/Out logic If the source is a Place, then it is a
	 * RegularIn If the source is a Transition, then it is a RegularOut TO DO :
	 * check the weight parameter
	 */
	@Override
	public AbstractArc addRegularArc(AbstractNode source, AbstractNode destination) throws UnimplementedCaseException {
		ArcAdapter aa = null;
		// IN
		if (source instanceof AbstractPlace) {
			PlaceAdapter pa = (PlaceAdapter) source;
			TransitionAdapter ta = (TransitionAdapter) destination;
			this.ourPetri.buildEdge(ta.ourTransition, pa.ourPlace, EdgeTypes.RegularIn, 1);
			aa = new ArcAdapter(EdgeTypes.RegularIn);
			// add place and transition to our arc
			aa.ourArc.setMyPlace(pa.ourPlace);
			aa.ourArc.setMyTransition(ta.ourTransition);
			// OUT
		} else if (source instanceof AbstractTransition) {
			PlaceAdapter pa = (PlaceAdapter) destination;
			TransitionAdapter ta = (TransitionAdapter) source;
			this.ourPetri.buildEdge(ta.ourTransition, pa.ourPlace, EdgeTypes.RegularOut, 1);
			aa = new ArcAdapter(EdgeTypes.RegularOut);
			// add place and transition to our arc
			aa.ourArc.setMyPlace(pa.ourPlace);
			aa.ourArc.setMyTransition(ta.ourTransition);
		}
		return aa;
	}

	/**
	 * This returns a ArcAdapter with a type ZeroIn.
	 * 
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
		this.aa = new ArcAdapter(EdgeTypes.EmptierIn);
		return aa;
	}

	/**
	 * This removes a place from the Place List in our petrinetwork
	 * 
	 * @param place
	 */
	@Override
	public void removePlace(AbstractPlace place) {
		int myPlaceId = this.pa.ourPlace.getIdentifier();
		this.pn.deleteEdge(myPlaceId);

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

		return false;
	}

	@Override
	public void fire(AbstractTransition transition) throws ResetArcMultiplicityException {
		TransitionAdapter transAdapter = (TransitionAdapter) transition;
		transAdapter.ourTransition.fire();
	}

	public static void main(String[] args) {

		// TEST 0 : build a place
		System.out.println("\nTEST 0 : build a place");
		PetriNetAdapter pna = new PetriNetAdapter();
		AbstractPlace absPlace1 = pna.addAbstractPlace();
		System.out.println("Méthode du PNE : " + pna.getPlaces());
		System.out.println("Notre méthode : " + pna.ourPetri.getMyPlaces());
		for (Integer key : pna.ourPetri.getMyPlaces().keySet()) {
			if (key == 1)
				pna.ourPetri.getMyPlaces().get(key).setTokens(9);
		}

		for (AbstractPlace ap : pna.getPlaces()) {
			ap.setTokens(9);
			System.out.println(ap.getTokens());
		}
		System.out.println("Notre méthode : " + pna.ourPetri.getMyPlaces());
		System.out.println("Clefs de la méthode PNE : " + pna.getPlaces());

		// TEST 1 : build transition

		System.out.println("Créer une transition");
		AbstractTransition absTr1 = pna.addAbstractTransition();
		System.out.println("Méthode du PNE : " + pna.getTransitions());
		System.out.println("Notre méthode : " + pna.ourPetri.getMyTransitions());

		// TEST 2 : build arc :
		System.out.println("\nTEST 0 : build an arc");
		try {
			pna.addRegArc(absPlace1, absTr1);
		} catch (UnimplementedCaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Notre méthode : " + pna.ourPetri.getMyEdges());

		System.out.println("Clefs de la méthode PNE : " + pna.getConnectedArcs(absTr1));

//		// TEST 1 : build a place
//		System.out.println("\nTEST 1 : build a place");
//		PetriNetAdapter p = new PetriNetAdapter();
//		p.addPlace();
//		System.out.println(p);
//		p.pa.setTokens(5);
//		System.out.println(p.pa.getTokens());
//		System.out.println(p.pa.ourPlace.toString());
//		
//		// TEST 2 : build a transition
//		System.out.println("\n TEST 2 : build a transition");
//		p.addTransition();
//		System.out.println(p.ta.getClass());
//		System.out.println(p.ta.ourTransition.toString());
//		
//		
//		// TEST 3 : build an IN edge between the two
//		System.out.println("\n TEST 3 : build an IN edge between the two");
//		try {
//			p.addRegularArc(p.pa, p.ta );
//		} catch (UnimplementedCaseException e) {
//			e.printStackTrace();
//		}
//		try {
//			p.aa.setMultiplicity(18);
//		} catch (ResetArcMultiplicityException e) {
//			e.printStackTrace();
//		}
//		System.out.println(p.aa.ourArc.toString());
//		
//		// TEST 4 : build an OUT edge between the two
//		System.out.println("\n TEST 4 : build an OUT edge between the two");
//		try {
//			p.addRegularArc(p.ta, p.pa );
//		} catch (UnimplementedCaseException e) {
//			e.printStackTrace();
//		}
//		try {
//			p.aa.setMultiplicity(9);
//		} catch (ResetArcMultiplicityException e) {
//			e.printStackTrace();
//		}
//		System.out.println(p.aa.ourArc.toString());
//		
//		// TEST 5 : get the source of the Arc
//		System.out.println("\n TEST 5 : get the source of the Arc");
//		System.out.println(p.aa.getSource());
//		
//		// TEST 6 : build a INHIBITORY arc
//		System.out.println("\n TEST 6 : build a INHIBITORY arc");
//		try {
//			p.addInhibitoryArc(p.pa, p.ta);
//		} catch (UnimplementedCaseException e) {
//			e.printStackTrace();
//		}
//		try {
//			p.aa.setMultiplicity(9);
//		} catch (ResetArcMultiplicityException e) {
//			e.printStackTrace();
//		}
//		System.out.println(p.aa.ourArc.toString());
//		
//		//TEST 7 : build an ADDRESETARC
//		System.out.println("\nTEST 7 : build an ADDRESETARC");
//		try {
//			p.addResetArc(p.pa, p.ta);
//		} catch (UnimplementedCaseException e) {
//			e.printStackTrace();
//		}
//		try {
//			p.aa.setMultiplicity(5);
//		} catch (ResetArcMultiplicityException e) {
//			e.printStackTrace();
//		}
//		System.out.println(p.aa.ourArc.toString());
//		
//		// TEST 8 : test fire
	}

}
