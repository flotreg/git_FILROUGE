package petriNetwork;

import exceptions.AddEdgeException;
import java.util.ArrayList;
import interfaces.AddEdge;

/** 
 * Here the class Transition. This class fires the transitions.
 */
public class Transition implements AddEdge{
	
	/*
	 * ATTRIBUTES
	 */
	private static int counter = 0;
	private int identifier;
	private ArrayList<Place> myPlaces;
	private ArrayList<In> myIns;
	private ArrayList<Out> myOuts;

	/** 
	 * CONSTRUCTORS
	 */
	public Transition() {
		counter += 1;
		this.identifier = counter;
		this.myPlaces = null;
		this.myIns = null;
		this.myOuts = null;
	}
	/**
	 * Constructor with every parameters
	 * @param nIsFirable
	 * @param nMyPlaces
	 * @param nMyIns
	 * @param nMyOuts
	 */
	public Transition(ArrayList<Place> nMyPlaces,ArrayList<In> nMyIns,ArrayList<Out> nMyOuts) {
		counter += 1;
		this.identifier = counter;
		this.myPlaces = nMyPlaces;
		this.myIns = nMyIns;
		this.myOuts = nMyOuts; 
	}
	/*
	 * GETTERS AND SETTERS
	 */
	public ArrayList<Place> getMyPlaces() {
		return myPlaces;
	}
	public ArrayList<In> getMyIns() {
		return myIns;
	}
	public ArrayList<Out> getMyOuts() {
		return myOuts;
	}
	/*
	 * REDEFINITION
	 */
	/**
	 * toString redefined : identifier of the transition
	 */
	@Override
	public String toString() {
		return "Transition n° : " + this.identifier;
	}
	
	
	public boolean isFirable(boolean activable) {
		boolean b = true;
		for (In in : myIns) {
			if (in.activable() == true && b == true) {
				b = true;
			} else {
				b = false;
			}
		}
		return b;
	}

	/**
	 * Add edges
	 * depends on the edge type, the weight
	 * @Override
	 * 
	 */
	public void addEdge(AddEdge dest, boolean inOut, int weight) throws AddEdgeException {
		if(!(dest instanceof Place)) {
			throw new AddEdgeException();
		}else{
			if(inOut) {
				myIns.add(new In(weight,(Place)dest,this));
			} else {
				myOuts.add(new Out(weight, (Place)dest,this));
			}
		}
		
	}
	
	/*
	 * OWN METHODS
	 */
	public void displayEdges() {
		System.out.println("In: ");
		for (In in : myIns) {
			System.out.println(in.toString());
		}
		System.out.println("Out: ");
		for (Out out : myOuts) {
			System.out.println(out.toString());
		}
		
	}
	public void fire() {
		
	}
	
	/*
	 * MAIN FOR TESTING
	 */
	
	public static void main(String [] args) {
		//TEST 1 : creation of transition with constructor1
		System.out.println("\nTEST 1 : constructor 1");
		Transition trans = new Transition();
		System.out.println(trans);
		
		//TEST 2 : creation of transition with constructor2
		System.out.println("\nTEST2 : constuctor 2");
		Transition trans2 = new Transition(new ArrayList<Place>(),new ArrayList<In>(), new ArrayList<Out>());
		System.out.println(trans2);
		System.out.println(trans2.myIns);
		
		//TEST 4 : AddEdge()
		System.out.println("\nTEST3 : addEdge()");
		try {
			trans2.addEdge(new Place(3), true, 3);
		} catch(AddEdgeException e) {
			e.printStackTrace();
		}
		
		for(In i : trans2.getMyIns()) {
			System.out.println(i);
		}
		
		

	}
	
}
