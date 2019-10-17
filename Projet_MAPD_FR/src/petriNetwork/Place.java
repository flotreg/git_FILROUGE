/**
 * 
 */
package petriNetwork;

import exceptions.AddEdgeException;
import interfaces.AddEdge;

/**
 * Place holds tokens
 * @author Bonjour
 *
 */
public class Place implements AddEdge{
	
	/*
	 * ATTRIBUTES
	 */
	/**
	 * number of tokens
	 */
	private int tokens;

	/**
	 * static counter to build the identifier
	 */
	private static int counter = 0;

	/**
	 * identifier of the object
	 */
	private int identifier;
	
	/*
	 * CONSTRUCTORS
	 */
	/**
	 * Constructor without parameters
	 */
	public Place() {
		counter += 1;
		this.identifier = counter;
		this.tokens = 0;
	}
	
	public Place(int tokens) {
		counter += 1;
		this.identifier = counter;
		this.tokens = tokens;
	}
	
	/**
	 * Redefinition of the toString() method
	 */
	public String toString() {
		return "This place is " + super.toString() +
				"\n It contains " + this.tokens + " tokens.";
	}
	
	/**
	 * @return the tokens
	 */
	public int getTokens() {
		return tokens;
	}

	/**
	 * @param tokens the tokens to set
	 */
	public void setTokens(int tokens) {
		this.tokens = tokens;
	}
	
	/**
	 * addEdge redefinition
	 * 
	 */
	@Override
	public void addEdge(AddEdge dest, boolean inOut, int weight) throws AddEdgeException {
		if(!(dest instanceof Transition)) {
			throw new AddEdgeException();
		}else{
			if(inOut) {
				new In(weight, this, (Transition)dest);
			} else {
				new Out(weight, this, (Transition)dest);
			}
		}
		
	}
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// TEST 1: constructor without parameters + toString
		System.out.println("\n TEST 1: constructor without parameters + toString");
		Place place1 = new Place();
		System.out.println(place1.toString());
		
		// TEST 2: constructor with parameters + toString
		System.out.println("\n TEST 2: constructor with parameters + toString");
		Place place2 = new Place(9);
		System.out.println(place2.toString());
		
		// TEST 3 : AddEdge()
		System.out.println("\n TEST 3 : AddEdge()");
		try {
			place2.addEdge(new Transition(), true, 2);
		} catch (AddEdgeException e) {
			e.printStackTrace();
		} finally {
			System.out.println(place2.toString());
		}

	}





}
