/**
 * 
 */
package petriNetwork;

import exceptions.AddEdgeException;
import interfaces.Edgeable;
import edges.*;

/**
 * Place holds tokens
 * 
 * @author Bonjour
 *
 */
public class Place implements Edgeable {

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

	/*
	 * GETTERS AND SETTERS
	 */
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
	
	public int getIdentifier() {
		return this.identifier;
	}

	/*
	 * REDEFINED METHODS
	 */
	/**
	 * Redefinition of the toString() method
	 */
	@Override
	public String toString() {
		return "Place n° " + identifier + ". Tokens : " +tokens;
	}
	
	/**
	 * addEdge redefinition : not used right now (the Petri uses only the addEdge() from the Transition)
	 * But it might be useful in the future. 
	 * @param dest
	 * @param e
	 */
	@Override
	public Edge addEdge(Edgeable dest, EdgeTypes e, int weight) throws AddEdgeException {
		return null;
	}
	
	/**
	 * Redefinition of deleteEdge : not used right now (the Place does not know its edges)
	 * But might be useful in the future. 
	 */
	@Override
	public void deleteEdge(int identifier) {
	}

	
	
	/*
	 * MAIN FOR TESTING
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// TEST 1: constructor without parameters + toString
		System.out.println("\n TEST 1: constructor without parameters + toString");
		Place place1 = new Place();
		System.out.println(place1.toString());

		// TEST 2: constructor with weight parameter + toString
		System.out.println("\n TEST 2: constructor with parameters + toString");
		Place place2 = new Place(9);
		System.out.println(place2.toString());

		// TEST 3 : AddEdge() : IN
		System.out.println("\n TEST 3 : AddEdge() : IN");
		try {
			place2.addEdge(new Transition(), EdgeTypes.RegularIn, 2);
		} catch (AddEdgeException e) {
			e.printStackTrace();
		} finally {
			System.out.println(place2.toString());
		}
		
		// TEST 4 : AddEdge() : OUT
		System.out.println("\n TEST 4 : AddEdge() : OUT");
		try {
			place2.addEdge(new Transition(), EdgeTypes.RegularOut, 6);
		} catch (AddEdgeException e) {
			e.printStackTrace();
		} finally {
			System.out.println(place2.toString());
		}
		

	}



}
