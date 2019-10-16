/**
 * 
 */
package petriNetwork;
import java.util.ArrayList;

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
	 * Constructor without parameters referring to Edge constructor
	 */
	public Out() {
		super();
	}
	
	/**
	 * Constructor with parameters referring to Edge constructor
	 * @param weight
	 */
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
	 * Redefinition of toString Method
	 * @see petriNetwork.Edge#toString()
	 */

	public String toString() {
		return "In :\n"
	+"Mon poids : "+this.getWeight()
	+"\nMa place : "+this.myTransition
	+"\nMa transition :"+this.myPlace;
	}
	
	public void fill() {
		myPlace.setTokens(myPlace.getTokens()+this.getWeight());
	}

	/**
	 * @param argsa
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// TEST1 : constructor without parameters
		Edge eOut1 = new Out();
		System.out.println(eOut1.toString());
		
		// TEST2 : constructor with parameters
		Edge eOut2 = new Out(7);
		System.out.println(eOut2.toString());
		
		//TEST3 : fill() method
		Out eOut3 = new Out(3);
		ArrayList<Out> myOut = new ArrayList<Out>();
		myOut.add(eOut3);
		Place p = new Place(3,new ArrayList<In>(),myOut);
		System.out.println(p);
		eOut3.fill();
		System.out.println(p);
		

	}

}
