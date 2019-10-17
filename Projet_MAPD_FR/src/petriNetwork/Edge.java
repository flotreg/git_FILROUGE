package petriNetwork;

import java.util.ArrayList;

/**
 * This abstract class represents the edge It shares global edge attributes It
 * forces global edge behaviors
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
	 * 
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
	 * 
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
	 * 
	 * @return weight of the edge
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * setter of weight
	 * 
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

	/*
	 * OWN METHODS
	 */

	/*
	 * OVERRIDE METHODS
	 */
	/**
	 * Redefinition of toString : Gives the identifier, the weight, the place and
	 * the transition
	 */
	@Override
	public String toString() {
		return "Edge n°" + identifier + "\n      Weight = " + weight + "\n      Place = " + myPlace
				+ "\n      Transition = " + myTransition;
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
		Edge eIn1 = new In();
		System.out.println(eIn1);
		Edge eOut1 = new Out();
		System.out.println(eOut1);

		// TEST 2 : constructor with weight param + toString
		System.out.println("\n // TEST 2 : constructor with weight param + toString :");
		Edge eIn2 = new In(18);
		System.out.println(eIn2);
		Edge eOut2 = new Out(5);
		System.out.println(eOut2);

		// TEST 3 : constructor with all params + toString
		System.out.println("\n // TEST 3 : constructor with all params + toString : ");
		Edge eIn3 = new In(72, new Place(), new Transition());
		System.out.println(eIn3);
		Edge eOut3 = new Out(5, new Place(), new Transition());
		System.out.println(eOut3);

		// TEST 4 : activable and empty() for In : NO
		System.out.println("\n // TEST 4 : activable and empty() for In : NO :");
		eIn3.setMyPlace(new Place(48, new ArrayList<In>(), new ArrayList<Out>()));
		System.out.println("nombre de jetons de la place : " + eIn3.getMyPlace().getTokens());
		System.out.println("poids de la transition : " + eIn3.getWeight());
		System.out.println("est activable ? " + ((In) eIn3).activable());
		((In) eIn3).empty();
		System.out.println("résultat du empty/step : "  + eIn3.getMyPlace().getTokens());

		// TEST 5 : activable and empty() for In : YES
		System.out.println("\n // TEST 5 : activable and empty() for In : YES :");
		eIn3.setMyPlace(new Place(198, new ArrayList<In>(), new ArrayList<Out>()));
		System.out.println("nombre de jetons de la place : " + eIn3.getMyPlace().getTokens());
		System.out.println("poids de la transition : " + eIn3.getWeight());
		System.out.println("est activable ? " + ((In) eIn3).activable());
		System.out.println(((In) eIn3).activable());
		((In) eIn3).empty();
		System.out.println("résultat du empty/step : " + eIn3.getMyPlace().getTokens());
		
		// TEST 6 : fill() for Out : 
		System.out.println("\n // TEST 6 : fill() for Out : ");
		eOut3.setMyPlace(new Place(12, new ArrayList<In>(), new ArrayList<Out>()));
		System.out.println("nombre de jetons de la place : " + eOut3.getMyPlace().getTokens());
		System.out.println("poids de la transition : " + eOut3.getWeight());
		((Out) eOut3).fill();
		System.out.println("résultat du fill/step : " + eOut3.getMyPlace().getTokens());

	}

}
