/**
 * 
 */
package petriNetwork;

import java.util.ArrayList;

/**
 * @author Bonjour In extends Edge. It represents edges that start from place
 *         and end to transition.
 */
public class In extends Edge {

	/*
	 * ATTRIBUTES
	 */
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
	 * 
	 * Constructor of In that call the constructor of Edge
	 */
	public In() {
		super();
	}

	/**
	 * Constructor of In with parameters that call constructor with parameters of
	 * Edge
	 * 
	 * @param weight
	 */
	public In(int weight) {
		super(weight);
	}

	/**
	 * Constructor of In with parameters of Place and Transition
	 */
	public In(int weight, Place p, Transition t) {
		super(weight);
		this.myPlace = p;
		this.myTransition = t;
	}

	/*
	 * GETTERS AND SETTERS
	 */
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

	/*
	 * OWN METHODS
	 */
	/**
	 * Redefinition of the toString method
	 */
	public String toString() {
		return "In :" + super.toString()+ "\nMon poids : " + this.getWeight() + "\nMa place : " + this.myPlace + "\nMa transition : "
				+ this.myTransition + "\n";
	}

	/**
	 * Take the tokens from the start place
	 */
	public void empty() {
		this.myPlace.setTokens(this.getWeight());
	}
	
	/**
	 * Check that there is enough tokens in the start place
	 * This is used by the Edge
	 */
	public boolean activable() {
		return (this.getWeight() >= this.myPlace.getTokens()) ? true : false;
	}

	/*
	 * MAIN FOR TESTING
	 */

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TEST 1 : instanciations of In
		System.out.println("\n TEST 1 : instanciations of In :");
		In in1 = new In();
		System.out.println(in1.toString());
		In in2 = new In(15);
		System.out.println(in2.toString());
		In in3 = new In(17, new Place(), new Transition());
		System.out.println(in3.toString());

		// TEST 2 : empty() places
		System.out.println("\n TEST 2 : empty() places : ");
		Place p1 = new Place(3, new ArrayList<In>(), new ArrayList<Out>()) ;
		Transition t1 = new Transition();
		In in4 = new In(2, p1, t1);
		System.out.println(in4.toString());
		

	}

}
