/**
 * 
 */
package petriNetwork;

/**
 * Inherits abstract class Edge
 * @author Bonjour
 *
 */
public class Out extends Edge {
	
	/*
	 * ATTRIBUTES
	 */
	private Transition myTransition;
	private Place myPlace;
	/**
	 * CONSTRUCTORS
	 */
	public Out() {
		super();
	}
	
	public Out(int weight) {
		super(weight);
	}
	/*
	 * GETTORS AND SETTORS
	 */
	
	public Transition getMyTransition() {
		return myTransition;
	}

	public void setMyTransition(Transition myTransition) {
		this.myTransition = myTransition;
	}

	public Place getMyPlace() {
		return myPlace;
	}

	public void setMyPlace(Place myPlace) {
		this.myPlace = myPlace;
	}
	
	/*
	 * OWN METHODS
	 */
	public void fill() {
		myPlace.setTokens(myPlace.getTokens()+this.getWeight());
	}
	/*
	 * REDEFINITION
	 * @see petriNetwork.Edge#toString()
	 */

	public String toString() {
		return "In :\n"
	+"Mon poids : "+this.getWeight()
	+"\nMa place : "+this.myTransition
	+"\nMa transition :"+this.myPlace;
	}
	

	/**
	 * MAIN
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// TEST1 : constructor without parameters
		Edge eOut1 = new Out();
		System.out.println(eOut1.toString());
		
		// TEST2 : constructor with parameters
		Edge eOut2 = new Out(7);
		System.out.println(eOut2.toString());
	}

}
