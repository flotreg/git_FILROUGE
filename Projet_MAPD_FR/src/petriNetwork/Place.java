/**
 * 
 */
package petriNetwork;

import exceptions.AddEdgeException;
import interfaces.AddEdge;
import java.util.ArrayList;

/**
 * Place holds tokens
 * @author Bonjour
 *
 */
public class Place implements AddEdge{
	
	private int tokens;
	private ArrayList<In> myIns;
	private ArrayList<Out> myOuts;
	
	/**
	 * Constructor without parameters
	 */
	public Place() {
		this.tokens = 0;
		this.myIns = null;
		this.myOuts = null;
	}
	
	public Place(int tokens, ArrayList<In> myIns, ArrayList<Out> myOuts) {
		this.tokens = tokens;
		this.myIns = myIns;
		this.myOuts = myOuts;
	}
	
	/**
	 * Redefinition of the toString() method
	 */
	public String toString() {
		return "This place is " + super.toString() +
				"\n It contains " + this.tokens + " tokens." +
				"\n It is related to the following edge in : " + this.myIns +
				"\n It is related to the follwing edge out : " + this.myOuts;
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
	 * @Override
	 */
	public void addEdge(AddEdge dest, boolean inOut, int weight) throws AddEdgeException {
		if(!(dest instanceof Transition)) {
			throw new AddEdgeException();
		}else{
			if(inOut) {
				this.myIns.add(new In(weight));
			}else{
				this.myOuts.add(new Out(weight));
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
		Place place2 = new Place(9, new ArrayList<In>(), new ArrayList<Out>());
		place2.myIns.add(new In(3));
		place2.myIns.add(new In(5));
		place2.myOuts.add(new Out(4));
		place2.myOuts.add(new Out(6));
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
