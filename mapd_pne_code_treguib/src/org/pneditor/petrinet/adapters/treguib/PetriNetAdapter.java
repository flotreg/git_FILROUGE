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
			aa = new ArcAdapter(EdgeTypes.RegularIn);
			aa.ourArc = this.ourPetri.buildEdge(ta.ourTransition, pa.ourPlace, EdgeTypes.RegularIn, 1);
			// add placeAdapter and transitionAdapter to our ArcAdapter
			aa.itsPlaceAdapter = pa;
			aa.itsTransitionAdapter = ta;
			// add place and transition to our arc
			aa.ourArc.setMyPlace(pa.ourPlace);
			aa.ourArc.setMyTransition(ta.ourTransition);
			// OUT
		} else if (source instanceof AbstractTransition) {
			PlaceAdapter pa = (PlaceAdapter) destination;
			TransitionAdapter ta = (TransitionAdapter) source;
			aa = new ArcAdapter(EdgeTypes.RegularOut);
			aa.ourArc = this.ourPetri.buildEdge(ta.ourTransition, pa.ourPlace, EdgeTypes.RegularOut, 1);
			// add placeAdapter and transitionAdapter to our ArcAdapter
			aa.itsPlaceAdapter = pa;
			aa.itsTransitionAdapter = ta;
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
		ArcAdapter aa = null;
		PlaceAdapter pa = (PlaceAdapter) place;
		TransitionAdapter ta = (TransitionAdapter) transition;
		aa = new ArcAdapter(EdgeTypes.ZeroIn);
		aa.ourArc = this.ourPetri.buildEdge(ta.ourTransition, pa.ourPlace, EdgeTypes.ZeroIn, 1);
		// add placeAdapter and transitionAdapter to our ArcAdapter
		aa.itsPlaceAdapter = pa;
		aa.itsTransitionAdapter = ta;
		// add place and transition to our arc
		aa.ourArc.setMyPlace(pa.ourPlace);
		aa.ourArc.setMyTransition(ta.ourTransition);
		return aa;
	}

	/**
	 * this returns an ArcAdapter with type Emptier In
	 * 
	 * @return Arc Adapter with a type EmptierIn
	 * @Override
	 */
	public AbstractArc addResetArc(AbstractPlace place, AbstractTransition transition)
			throws UnimplementedCaseException {
		ArcAdapter aa = null;
		PlaceAdapter pa = (PlaceAdapter) place;
		TransitionAdapter ta = (TransitionAdapter) transition;
		aa = new ArcAdapter(EdgeTypes.EmptierIn);
		aa.ourArc = this.ourPetri.buildEdge(ta.ourTransition, pa.ourPlace, EdgeTypes.EmptierIn, 1);
		// add placeAdapter and transitionAdapter to our ArcAdapter
		aa.itsPlaceAdapter = pa;
		aa.itsTransitionAdapter = ta;
		// add place and transition to our arc
		aa.ourArc.setMyPlace(pa.ourPlace);
		aa.ourArc.setMyTransition(ta.ourTransition);
		return aa;
	}

	/**
	 * This removes a place from the Place List in our petrinetwork
	 * The removal in PNE code is done automatically
	 * 
	 * @param place
	 */
	@Override
	public void removePlace(AbstractPlace place) {
		int placeId = ((PlaceAdapter) place).ourPlace.getIdentifier();
		System.out.println("DEBUG : Place id : " + placeId);
		ourPetri.deletePlace(placeId);
	}

	@Override
	public void removeTransition(AbstractTransition transition) {
		int transId = ((TransitionAdapter)transition).ourTransition.getIdentifier();
		System.out.println("DEBUG : Transition id : " + transId);
		ourPetri.deleteTransition(transId);

	}

	@Override
	public void removeArc(AbstractArc arc) {
		int arcId = ((ArcAdapter)arc).ourArc.getIdentifier();
		System.out.println("DEBUG : Arc id : " + arcId);
		ourPetri.deleteTransition(arcId);

	}

	@Override
	public boolean isEnabled(AbstractTransition transition) throws ResetArcMultiplicityException {
		return ((TransitionAdapter) transition).ourTransition.isFirable();

	}

	@Override
	public void fire(AbstractTransition transition) throws ResetArcMultiplicityException {
		TransitionAdapter transAdapter = (TransitionAdapter) transition;
		transAdapter.ourTransition.fire();
	}

	public static void main(String[] args) {

		// TEST 0 : build a PetrinetAdapter
		System.out.println("\nTEST 0 : build a PetriNetAdapter");
		PetriNetAdapter pna = new PetriNetAdapter();
		System.out.println(pna);

		// TEST 1 : build a place
		System.out.println("\nTEST 1 : build a place");
		AbstractPlace absPlace1 = pna.addAbstractPlace();
		System.out.println(absPlace1);
		System.out.println("Liste des places du PNE : " + pna.getPlaces());
		System.out.println("Liste des places de notre code : " + pna.ourPetri.getMyPlaces());

		// TEST 1bis : build a second place
		System.out.println("\nTEST 1bis : build a second place");
		AbstractPlace absPlace2 = pna.addAbstractPlace();
		System.out.println(absPlace2);
		System.out.println("Liste des places du PNE : " + pna.getPlaces());
		System.out.println("Liste des places de notre code : " + pna.ourPetri.getMyPlaces());

		// TEST 2 : build transition
		System.out.println("\nTEST 2 : build transition");
		AbstractTransition absTrans1 = pna.addAbstractTransition();
		System.out.println(absTrans1);
		System.out.println("Liste des transitions du PNE : " + pna.getTransitions());
		System.out.println("Liste des transitions de notre code : " + pna.ourPetri.getMyTransitions());

		// TEST 3 : build an IN arc :
		System.out.println("\nTEST 3 : build an IN arc");
		AbstractArc absArcIn1 = null;
		try {
			absArcIn1 = pna.addRegArc(absPlace1, absTrans1);
		} catch (UnimplementedCaseException e) {
			e.printStackTrace();
		}
		System.out.println(absArcIn1);

		System.out.println("(code PNE) Quels sont les arcs co à la transition1 ? : " + pna.getConnectedArcs(absTrans1));
		System.out.println("Liste des arcs de notre code : " + pna.ourPetri.getMyEdges());

		// TEST 3bis : build an OUT arc :
		System.out.println("\nTEST 3bis : build an OUT arc");
		AbstractArc absArcOut1 = null;
		try {
			absArcOut1 = pna.addRegArc(absTrans1, absPlace2);
		} catch (UnimplementedCaseException e) {
			e.printStackTrace();
		}
		System.out.println(absArcOut1);

		System.out.println("(code PNE) Quels sont les arcs co à la transition1 ? : " + pna.getConnectedArcs(absTrans1));
		System.out.println("Liste des arcs de notre code : " + pna.ourPetri.getMyEdges());

		// TEST 4 : set and get multiplicity for Arc IN
		System.out.println("\nTEST 4 : set and get multiplicity for Arc IN :");
		// set
		try {
			absArcIn1.setMultiplicity(13);
		} catch (ResetArcMultiplicityException e) {
			e.printStackTrace();
		}
		// get
		try {
			System.out.println("Multiplicité : " + absArcIn1.getMultiplicity());
		} catch (ResetArcMultiplicityException e) {
			e.printStackTrace();
		}

		// TEST 4bis : set and get multiplicity for Arc OUT
		System.out.println("\nTEST 4bis : set and get multiplicity for Arc OUT");
			// set
		try {
			absArcOut1.setMultiplicity(7);
		} catch (ResetArcMultiplicityException e) {
			e.printStackTrace();
		}
			// get
		try {
			System.out.println("Multiplicité : " + absArcOut1.getMultiplicity());
		} catch (ResetArcMultiplicityException e) {
			e.printStackTrace();
		}

		// TEST 5 : set and get tokens for Place1
		System.out.println("\nTEST 5 : set tokens for Place1");
			// set
		absPlace1.setTokens(18);
			// get
		System.out.println("Nombre de jetons : " + absPlace1.getTokens());

		// TEST 5bis : set and get tokens for Place2
		System.out.println("\nTEST 5bis : set and get tokens for Place2");
			// set
		absPlace2.setTokens(58);
			// get
		System.out.println("Nombre de jetons : " + absPlace2.getTokens());

		// TEST 6 : Build an arc EmptierIn/resetArc
		System.out.println("\n TEST 6 : Build an arc EmptierIn");
		AbstractTransition absTrans2 = pna.addAbstractTransition();
		// create new place and add 3 tokens
		AbstractPlace absPlace3 = pna.addAbstractPlace();
		absPlace3.addToken();
		absPlace3.addToken();
		absPlace3.addToken();
		// create arc
		AbstractArc absArcEmptierIn = null;
		try {
			absArcEmptierIn = pna.addResArc(absPlace3, absTrans2);
		} catch (UnimplementedCaseException e3) {
			e3.printStackTrace();
		}
		System.out.println(absArcEmptierIn);
		System.out.println("(code PNE) Quels sont les arcs co à la transition2 ? : " + pna.getConnectedArcs(absTrans2));
		System.out.println("Liste des arcs de notre code : " + pna.ourPetri.getMyEdges());


		// TEST 7 Build an arc ZeroIn/InibitoryArc
		System.out.println("\n TEST 7 : Build an arc ZeroIn");
			// create place and add no token
		AbstractPlace absPlace4 = pna.addAbstractPlace();
			// create arc
		AbstractArc absArcZeroIn = null;
		try {
			absArcZeroIn = pna.addInhibArc(absPlace4, absTrans2);
		} catch (UnimplementedCaseException e) {
			e.printStackTrace();
		}
		System.out.println(absArcZeroIn);
		System.out.println("(code PNE) Quels sont les arcs co à la transition2 ? : " + pna.getConnectedArcs(absTrans2));
		System.out.println("Liste des arcs de notre code : " + pna.ourPetri.getMyEdges());

		// TEST 8 : fire() absTrans1 :
		System.out.println("\nTEST 8 : fire() absTrans1");
		try {
			System.out.println("Multiplicity RegularIn : Transition1 -> Place 2 : " + absArcOut1.getMultiplicity());
			System.out.println("Multiplicity RegularOut : Place1 -> Transition1 : " + absArcIn1.getMultiplicity());
			System.out.println("BEFORE : Nombre de jetons Place1 : " + absPlace1.getTokens());
			System.out.println("BEFORE : Nombre de jetons Place2 : " + absPlace2.getTokens());
			System.out.println("Is the transition activable ? " + pna.isEnabled(absTrans1));
			pna.fire(absTrans1);
			System.out.println("AFTER : Nombre de jetons Place1 : " + absPlace1.getTokens());
			System.out.println("AFTER : Nombre de jetons Place2 : " + absPlace2.getTokens());
		} catch (ResetArcMultiplicityException e2) {
			e2.printStackTrace();
		}

		// TEST 8bis : try to fire() absTran1 again :
		System.out.println("\nTEST 8bis : try to fire() absTran1 again :");
		try {
			System.out.println("Multiplicity RegularIn : Transition1 -> Place 2 : " + absArcOut1.getMultiplicity());
			System.out.println("Multiplicity RegularOut : Place1 -> Transition1 : " + absArcIn1.getMultiplicity());
			System.out.println("BEFORE : Nombre de jetons Place1 : " + absPlace1.getTokens());
			System.out.println("BEFORE : Nombre de jetons Place2 : " + absPlace2.getTokens());
			System.out.println("Is the transition activable ? " + pna.isEnabled(absTrans1));
			pna.fire(absTrans1);
			System.out.println("AFTER : Nombre de jetons Place1 : " + absPlace1.getTokens());
			System.out.println("AFTER : Nombre de jetons Place2 : " + absPlace2.getTokens());
		} catch (ResetArcMultiplicityException e2) {
			e2.printStackTrace();
		}

		// TEST 9 : fire() absTrans2 + addArcAgain()
		System.out.println("\nTEST 9 : fire() absTrans2");
			// create a place to receive tokens, set to 10 tokens
		AbstractPlace absPlace5 = pna.addAbstractPlace();
		absPlace5.setTokens(10);
			// create an OUT arc to connect to absPlace5
		AbstractArc absArcOut2 = pna.addArcAgain(absArcOut1, absTrans2, absPlace5);
			// check it has right multiplicity (same as absArcOut1)
		try {
			System.out.println("Multiplicity RegularOut (duplicated) : Trans2 -> Place5 : " + absArcOut2.getMultiplicity());
			System.out.println("BEFORE : Nombre de jetons Place3 (co to Emptier/ResetArc) : " + absPlace3.getTokens());
			System.out.println("BEFORE : Nombre de jetons Place4 (co to Zero/InhibArc) : " + absPlace4.getTokens());
			System.out.println("BEFORE : Nombre de jetons Place5 (Receiver) : " + absPlace5.getTokens());
			System.out.println("Is the transition activable ? " + pna.isEnabled(absTrans2));
			pna.fire(absTrans2);
			System.out.println("AFTER : Nombre de jetons Place3 (co to Emptier/ResetArc) : " + absPlace3.getTokens());
			System.out.println("AFTER : Nombre de jetons Place4 (co to Zero/InhibArc) : " + absPlace4.getTokens());
			System.out.println("AFTER : Nombre de jetons Place5 (Receiver) : " + absPlace5.getTokens());
		} catch (ResetArcMultiplicityException e1) {
			e1.printStackTrace();
		}
		
		// TEST 9bis : set some token on Place before inhib
		System.out.println("\nTEST 9bis : set some token on the Place before inhib");
		absPlace4.addToken();
		try {
			System.out.println("Multiplicity RegularOut (duplicated) : Trans2 -> Place5 : " + absArcOut2.getMultiplicity());
			System.out.println("BEFORE : Nombre de jetons Place3 (co to Emptier/ResetArc) : " + absPlace3.getTokens());
			System.out.println("BEFORE : Nombre de jetons Place4 (co to Zero/InhibArc) : " + absPlace4.getTokens());
			System.out.println("BEFORE : Nombre de jetons Place5 (Receiver) : " + absPlace5.getTokens());
			System.out.println("Is the transition activable ? " + pna.isEnabled(absTrans2));
			pna.fire(absTrans2);
			System.out.println("AFTER : Nombre de jetons Place3 (co to Emptier/ResetArc) : " + absPlace3.getTokens());
			System.out.println("AFTER : Nombre de jetons Place4 (co to Zero/InhibArc) : " + absPlace4.getTokens());
			System.out.println("AFTER : Nombre de jetons Place5 (Receiver) : " + absPlace5.getTokens());
		} catch (ResetArcMultiplicityException e1) {
			e1.printStackTrace();
		}
		// TEST 10 : remove arcs
		System.out.println("\nTEST 10 : remove arcs");
		System.out.println("BEFORE : Arc list in our code : " + pna.ourPetri.getMyEdges());
		System.out.println("BEFORE : Arc list of INS connected to transition in our code : " + ((TransitionAdapter)absTrans1).ourTransition.getMyIns());
		System.out.println("BEFORE : Arc list of OUTS connected to transition in our code : " + ((TransitionAdapter)absTrans1).ourTransition.getMyOuts());
		System.out.println("BEFORE : Arc list in PNE code : " + pna.getConnectedArcs(absTrans1));
		pna.removeAbstractArc(absArcIn1);
		System.out.println("AFTER : Arc list in our code : " + pna.ourPetri.getMyEdges());
		System.out.println("AFTER : Arc list of INS connected to transition in our code : " + ((TransitionAdapter)absTrans1).ourTransition.getMyIns());
		System.out.println("AFTER : Arc list of OUTS connected to transition in our code : " + ((TransitionAdapter)absTrans1).ourTransition.getMyOuts());
		System.out.println("AFTER : Arc list in PNE code : " + pna.getConnectedArcs(absTrans1));
		
		// TEST 11 : remove places
		System.out.println("\nTEST 11 : remove places");
		System.out.println("BEFORE : Place list in our code : " + pna.ourPetri.getMyPlaces());
		System.out.println("BEFORE : Place list in PNE code : " + pna.getPlaces());
		pna.removeAbstractPlace(absPlace1);
		System.out.println("AFTER : Place list in our code : " + pna.ourPetri.getMyPlaces());
		System.out.println("AFTER : Place list in PNE code : " + pna.getPlaces());
		
		// TEST 12 : remove transitions
		System.out.println("\nTEST 12 : remove transitions");
		System.out.println("BEFORE : Transition list in our code : " + pna.ourPetri.getMyTransitions());
		System.out.println("BEFORE : Transition list in PNE code : " + pna.getTransitions());
		pna.removeAbstractTransition(absTrans1);
		System.out.println("AFTER : Transition list in our code : " + pna.ourPetri.getMyTransitions());
		System.out.println("AFTER : Transition list in PNE code : " + pna.getTransitions());
		



		// SET TRANSITION TBD
//		for (Integer key : pna.ourPetri.getMyPlaces().keySet()) {
//			if (key == 1)
//				pna.ourPetri.getMyPlaces().get(key).setTokens(9);
//		}
//
//		for (AbstractPlace ap : pna.getPlaces()) {
//			ap.setTokens(9);
//			System.out.println(ap.getTokens());
//		}

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
