package org.pneditor.petrinet.models.treguib.petriNetwork;

import org.pneditor.petrinet.models.treguib.edges.EmptierIn;
import org.pneditor.petrinet.models.treguib.edges.RegularIn;
import org.pneditor.petrinet.models.treguib.edges.RegularOut;
import org.pneditor.petrinet.models.treguib.edges.ZeroIn;

/**
 * 
 * This abstract class represents the edge.
 * It shares global edge attributes.
 * It forces global edge behaviors.
 * 
 * @author Bonjour
 * @version 1
 * @since 17/10/2019
 *
 */
public abstract class Edge {

	/*
	 * ATTRIBUTES
	 */
	/**
	 * weight of the edge
	 */
	private int weight;

	/**
	 * static counter to build the identifier
	 */
	private static int counter = 0;

	/**
	 * identifier of the object
	 */
	private int identifier;

	/**
	 * The place linked to the edge
	 */
	private Place myPlace;

	/**
	 * The transition linked to the edge
	 */
	private Transition myTransition;

	/*
	 * CONSTRUCTORS
	 */
	/**
	 * constructor without parameters
	 */
	public Edge() {
		counter += 1;
		this.identifier = counter;
		this.weight = 0;
		this.setMyPlace(null);
		this.setMyTransition(null);

	}

	/**
	 * constructor with weight parameter
	 * @param weight
	 */
	public Edge(int w) {
		counter += 1;
		this.identifier = counter;
		this.weight = w;
		this.setMyPlace(null);
		this.setMyTransition(null);
	}

	/**
	 * constructor with weight, place and transition
	 * @param weight, place, transition
	 */
	public Edge(int w, Place p, Transition t) {
		counter += 1;
		this.identifier = counter;
		this.weight = w;
		this.setMyPlace(p);
		this.setMyTransition(t);
	}

	/*
	 * GETTERS AND SETTERS
	 */
	/**
	 * getter of weight
	 * @return weight of the edge
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * setter of weight
	 * @param weight
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	/**
	 * @return the myPlace
	 */
	public Place getMyPlace() {
		return myPlace;
	}

	/**
	 * @param myPlace the myPlace to set
	 */
	public void setMyPlace(Place myPlace) {
		this.myPlace = myPlace;
	}

	/**
	 * @return the myTransition
	 */
	public Transition getMyTransition() {
		return myTransition;
	}

	/**
	 * @param myTransition the myTransition to set
	 */
	public void setMyTransition(Transition myTransition) {
		this.myTransition = myTransition;
	}
	/**
	 * getter of the edge identifier
	 * @return identifier
	 */
	public int getIdentifier() {
		return identifier;
	}

	/*
	 * ABSTRACT METHODS
	 */
	/**
	 * Abstract to force children.
	 * Either fills or remove tokens, depending if it's In or Out. 
	 */
	public abstract void step();

	/*
	 * OVERRIDE METHODS
	 */
	/**
	 * Redefinition of toString : 
	 * Gives the identifier, the weight, the place and the transition
	 */
	@Override
	public String toString() {
		return "Edge n�" + getIdentifier() + "\n      Weight = " + getWeight() ;
	}

	/*
	 * MAIN FOR TESTING
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TEST 1 : constructor without param + toString
		System.out.println("\n // TEST 1 : constructor without param + toString :");
		Edge eIn1 = new RegularIn();
		System.out.println(eIn1);
		Edge eOut1 = new RegularOut();
		System.out.println(eOut1);
		Edge eZIn1 = new ZeroIn();
		System.out.println(eZIn1);
		Edge eEIn1 = new EmptierIn();
		System.out.println(eEIn1);

		// TEST 2 : constructor with weight param + toString
		System.out.println("\n // TEST 2 : constructor with weight param + toString :");
		Edge eIn2 = new RegularIn(18);
		System.out.println(eIn2);
		Edge eOut2 = new RegularOut(5);
		System.out.println(eOut2);
		Edge eZIn2 = new ZeroIn(875);
		System.out.println(eZIn2);
		Edge eEIn2 = new EmptierIn(349);
		System.out.println(eEIn2);

		// TEST 3 : constructor with all params + toString
		System.out.println("\n // TEST 3 : constructor with all params + toString : ");
		Edge eIn3 = new RegularIn(72, new Place(), new Transition());
		System.out.println(eIn3);
		System.out.println("	Place associated to this edge : " + eIn3.getMyPlace());
		System.out.println("	Transition associated to this edge : " + eIn3.getMyTransition());
		Edge eOut3 = new RegularOut(5, new Place(), new Transition());
		System.out.println(eOut3);
		System.out.println("	Place associated to this edge : " + eOut3.getMyPlace());
		System.out.println("	Transition associated to this edge : " + eOut3.getMyTransition());
		Edge eZIn3 = new ZeroIn(12, new Place(), new Transition());
		System.out.println(eZIn3);
		System.out.println("	Place associated to this edge : " + eZIn3.getMyPlace());
		System.out.println("	Transition associated to this edge : " + eZIn3.getMyTransition());
		Edge eEIn3 = new EmptierIn(38, new Place(), new Transition());
		System.out.println(eEIn3);
		System.out.println("	Place associated to this edge : " + eEIn3.getMyPlace());
		System.out.println("	Transition associated to this edge : " + eEIn3.getMyTransition());


		// TEST 4 : activable and step() for Regular In : NO
		System.out.println("\n // TEST 4 : activable and step() for Regular In : NO :");
		eIn3.setMyPlace(new Place(48));
		System.out.println("nombre de jetons de la place : " + eIn3.getMyPlace().getTokens());
		System.out.println("poids de la transition : " + eIn3.getWeight());
		System.out.println("est activable ? " + ((In) eIn3).activable());
		((In) eIn3).step();
		System.out.println("r�sultat du empty/step : "  + eIn3.getMyPlace().getTokens());

		// TEST 4bis : activable and step() for Regular In : YES
		System.out.println("\n // TEST 4bis : activable and step() for Regular In : YES :");
		eIn3.setMyPlace(new Place(198));
		System.out.println("nombre de jetons de la place : " + eIn3.getMyPlace().getTokens());
		System.out.println("poids de la transition : " + eIn3.getWeight());
		System.out.println("est activable ? " + ((In) eIn3).activable());
		((In) eIn3).step();
		System.out.println("r�sultat du empty/step : " + eIn3.getMyPlace().getTokens());
		
		// TEST 5 : step() for regular Out : 
		System.out.println("\n // TEST 5 : step() for regular Out :  ");
		eOut3.setMyPlace(new Place(12));
		System.out.println("nombre de jetons de la place : " + eOut3.getMyPlace().getTokens());
		System.out.println("poids de la transition : " + eOut3.getWeight());
		((Out) eOut3).step();
		System.out.println("r�sultat du fill/step : " + eOut3.getMyPlace().getTokens());
		
		// TEST 6 : activable and step() for EmptierIn : NO
		System.out.println("\n // TEST 6 : activable and step() for EmptierIn : NO");
		eEIn3.setMyPlace(new Place(7));
		System.out.println("nombre de jetons de la place : " + eEIn3.getMyPlace().getTokens());
		System.out.println("poids de la transition : " + eEIn3.getWeight());
		System.out.println("La transition est-elle activable ? " + ((EmptierIn) eEIn3).activable());
		((EmptierIn) eEIn3).step();
		System.out.println("r�sultat du fill/step : " + eEIn3.getMyPlace().getTokens());
		
		// TEST 6bis : activable and step() for EmptierIn : YES
		System.out.println("\n // TEST 6bis : activable and step() for EmptierIn : YES");
		eEIn3.setMyPlace(new Place(72));
		System.out.println("nombre de jetons de la place : " + eEIn3.getMyPlace().getTokens());
		System.out.println("poids de la transition : " + eEIn3.getWeight());
		System.out.println("La transition est-elle activable ? " + ((EmptierIn) eEIn3).activable());
		((EmptierIn) eEIn3).step();
		System.out.println("r�sultat du fill/step : " + eEIn3.getMyPlace().getTokens());
		
		
		// TEST 7 : activable and step() for ZeroIn : NO
		System.out.println("\n // TEST 7 : activable and step() for ZeroIn : NO");
		eZIn3.setMyPlace(new Place(33));
		System.out.println("nombre de jetons de la place : " + eZIn3.getMyPlace().getTokens());
		System.out.println("poids de la transition : " + eZIn3.getWeight());
		System.out.println("La transition est-elle activable ? " + ((ZeroIn) eZIn3).activable());
		((ZeroIn) eZIn3).step();
		System.out.println("r�sultat du fill/step : " + eZIn3.getMyPlace().getTokens());
		
		// TEST 7bis : activable and step() for ZeroIn : YES
		System.out.println("\n // TEST 7bis : activable and step() for ZeroIn : YES ");
		eZIn3.setMyPlace(new Place(0));
		System.out.println("nombre de jetons de la place : " + eZIn3.getMyPlace().getTokens());
		System.out.println("poids de la transition : " + eZIn3.getWeight());
		System.out.println("La transition est-elle activable ? " + ((ZeroIn) eZIn3).activable());
		((ZeroIn) eZIn3).step();
		System.out.println("r�sultat du fill/step : " + eZIn3.getMyPlace().getTokens());
		

	}

}
