package petriNetwork;

import edges.*;

/**
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
		return "Edge n°" + getIdentifier() + "\n      Weight = " + getWeight();
	}

	/*
	 * MAIN FOR TESTING
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TEST 1 : constructor without param + toString
		System.out.println("\n TEST 1 : constructor without param + toString :");
		Edge eIn1 = new RegularIn();
		System.out.println(eIn1);
		Edge eOut1 = new RegularOut();
		System.out.println(eOut1);

		// TEST 2 : constructor with weight param + toString
		System.out.println("\n // TEST 2 : constructor with weight param + toString :");
		Edge eIn2 = new RegularIn(18);
		System.out.println(eIn2);
		Edge eOut2 = new RegularOut(5);
		System.out.println(eOut2);

		// TEST 3 : constructor with all params + toString
		System.out.println("\n // TEST 3 : constructor with all params + toString : ");
		Edge eIn3 = new RegularIn(72, new Place(), new Transition());
		System.out.println(eIn3);
		Edge eOut3 = new RegularOut(5, new Place(), new Transition());
		System.out.println(eOut3);

		// TEST 4 : activable and empty() for In : NO
		System.out.println("\n // TEST 4 : activable and empty() for In : NO :");
		eIn3.setMyPlace(new Place(48));
		System.out.println("nombre de jetons de la place : " + eIn3.getMyPlace().getTokens());
		System.out.println("poids de la transition : " + eIn3.getWeight());
		System.out.println("est activable ? " + ((In) eIn3).activable());
		((In) eIn3).step();
		System.out.println("résultat du empty/step : "  + eIn3.getMyPlace().getTokens());

		// TEST 5 : activable and empty() for In : YES
		System.out.println("\n // TEST 5 : activable and empty() for In : YES :");
		eIn3.setMyPlace(new Place(198));
		System.out.println("nombre de jetons de la place : " + eIn3.getMyPlace().getTokens());
		System.out.println("poids de la transition : " + eIn3.getWeight());
		System.out.println("est activable ? " + ((In) eIn3).activable());
		System.out.println(((In) eIn3).activable());
		((In) eIn3).step();
		System.out.println("résultat du empty/step : " + eIn3.getMyPlace().getTokens());
		
		// TEST 6 : fill() for Out : 
		System.out.println("\n // TEST 6 : fill() for Out : ");
		eOut3.setMyPlace(new Place(12));
		System.out.println("nombre de jetons de la place : " + eOut3.getMyPlace().getTokens());
		System.out.println("poids de la transition : " + eOut3.getWeight());
		((Out) eOut3).step();
		System.out.println("résultat du fill/step : " + eOut3.getMyPlace().getTokens());
		
		// TEST 7 : empty() for EmptierIn :
		System.out.println("\n // TEST 7 : empty() for EmptierIn :");
		Edge eEmptierIn1 = new EmptierIn(34, new Place(72), new Transition());
		System.out.println("nombre de jetons de la place : " + eEmptierIn1.getMyPlace().getTokens());
		System.out.println("poids de la transition : " + eEmptierIn1.getWeight());
		((EmptierIn) eEmptierIn1).step();
		System.out.println("résultat du fill/step : " + eEmptierIn1.getMyPlace().getTokens());
		
		
		// TEST 8 : zero() for ZeroIn : 
		System.out.println("\n // TEST 8 : zero() for ZeroIn : ");
		Edge eZeroIn1 = new ZeroIn(22, new Place(178), new Transition());
		System.out.println("nombre de jetons de la place : " + eZeroIn1.getMyPlace().getTokens());
		System.out.println("poids de la transition : " + eZeroIn1.getWeight());
		System.out.println("La transition est-elle activable ? " + ((ZeroIn) eZeroIn1).activable());
		((ZeroIn) eZeroIn1).step();
		System.out.println("résultat du fill/step : " + eEmptierIn1.getMyPlace().getTokens());
		

	}

}
