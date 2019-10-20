package petriNetwork;

import exceptions.AddEdgeException;

import java.util.HashMap;
import java.util.Map;

import edges.EdgeTypes;
import edges.RegularIn;
import edges.RegularOut;
import edges.ZeroIn;
import edges.EmptierIn;
import interfaces.Edgeable;

/** 
 * Here the class Transition. This class fires the transitions.
 */
public class Transition implements Edgeable{
	
	/*
	 * ATTRIBUTES
	 */
	private static int counter = 0;
	private int identifier;
	private Map<Integer,Place> myPlaces;
	private Map<Integer,Out> myOuts;
	private Map<Integer, In> myIns;

	/** 
	 * CONSTRUCTORS
	 */
	public Transition() {
		counter += 1;
		this.identifier = counter;
		this.myPlaces = new HashMap<Integer, Place>();
		this.myIns = new HashMap<Integer, In>();
		this.myOuts = new HashMap<Integer, Out>();
	}
	/**
	 * Constructor with every parameters
	 * @param nIsFirable
	 * @param nMyPlaces
	 * @param nMyIns
	 * @param nMyOuts
	 */
	public Transition(Map<Integer,Place> nMyPlaces, Map<Integer,In> nMyIns,Map<Integer,Out> nMyOuts) {
		counter += 1;
		this.identifier = counter;
		this.myPlaces = nMyPlaces;
		this.myIns = nMyIns;
		this.myOuts = nMyOuts; 
	}
	/*
	 * GETTERS AND SETTERS
	 */
	public Map<Integer,Place> getMyPlaces() {
		return myPlaces;
	}
	public Map<Integer,In> getMyIns() {
		return myIns;
	}
	public Map<Integer,Out> getMyOuts() {
		return myOuts;
	}
	public int getIdentifier() {
		return this.identifier;
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
	
	
	/*public boolean isFirable(boolean activable) {
		boolean b = true;
		for (In in : myIns) {
			if (in.activable() == true && b == true) {
				b = true;
			} else {
				b = false;
			}
		}
		return b;
	}*/
	
	public boolean isFirable() {
		boolean b = true;
		for(Map.Entry<Integer,In> in : myIns.entrySet()) {
			if (in.getValue().activable() == true && b == true) {
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
	public void addEdge(Edgeable dest, EdgeTypes e, int weight) throws AddEdgeException {
		if(!(dest instanceof Place)) {
			throw new AddEdgeException();
		} else {
				switch (e) {
				case RegularIn:
					RegularIn regularIn = new RegularIn(weight,(Place)dest,this);
					System.out.println("TEST");
					System.out.println(regularIn.getIdentifier());
					myIns.put(regularIn.getIdentifier(),regularIn);
					break;
				case RegularOut:
					RegularOut regularOut = new RegularOut(weight,(Place)dest,this);
					myOuts.put(regularOut.getIdentifier(),regularOut);
					break;
				case ZeroIn:
					ZeroIn zeroIn = new ZeroIn(weight,(Place)dest,this);
					myIns.put(zeroIn.getIdentifier(),zeroIn);
					break;
				case EmptierIn:
					EmptierIn emptierIn = new EmptierIn(weight,(Place)dest,this);
					myIns.put(emptierIn.getIdentifier(),emptierIn);
					break;
				}
		}
		
	}
	
	/*
	 * OWN METHODS
	 */
	public void displayEdges() {
		System.out.println("In: ");
		for(Map.Entry<Integer,In> in : myIns.entrySet()) {
			System.out.println(in.getValue().toString());
		}
		System.out.println("Out: ");
		for(Map.Entry<Integer,In> out : myIns.entrySet()) {
			System.out.println(out.getValue().toString());
		}
		
	}
	public void fire() {
		if (isFirable() == true) {
			for(Map.Entry<Integer,Out> outs : myOuts.entrySet()) {
				outs.getValue().step();
			}
		}
	}
	
	/*
	 * MAIN FOR TESTING
	 */
	
	public static void main(String [] args) {
		//TEST 1 : creation of transition with constructor1
		System.out.println("\nTEST 1 : constructor 1");
		Transition trans = new Transition();
		System.out.println(trans);
		System.out.println(trans.getMyIns());
		
		/*
		//TEST 2 : creation of transition with constructor2
		System.out.println("\nTEST2 : constuctor 2");
		Place place = new Place();
		In in = new RegularIn ();
		Out out = new RegularOut();
		Transition trans2 = new Transition();
		System.out.println(trans2);
		System.out.println(trans2.myIns);
		*/
		
		//TEST 4 : AddEdge()
		System.out.println("\nTEST3 : addEdge()");
		Place place = new Place(3);
		try {
			trans.addEdge(place, EdgeTypes.RegularIn, 3);
		} catch(AddEdgeException e) {
			e.printStackTrace();
		}
		
		

	}
	
}
