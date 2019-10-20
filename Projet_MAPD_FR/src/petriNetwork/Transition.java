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

	/*
	 * CONSTRUCTORS
	 */
	/**
	 * Constructor without parameter,
	 * Empty hashmaps are created.
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
	
	
	/**
	 * Add edges
	 * depends on the edge type, the weight
	 * 
	 */
	@Override
	public void addEdge(Edgeable dest, EdgeTypes e, int weight) throws AddEdgeException {
		if(!(dest instanceof Place)) {
			throw new AddEdgeException();
		} else {
				switch (e) {
				case RegularIn:
					RegularIn regularIn = new RegularIn(weight,(Place)dest,this);
					myIns.put(regularIn.getIdentifier(),regularIn);
					myPlaces.put(((Place)dest).getIdentifier(), ((Place)dest));
					break;
				case RegularOut:
					RegularOut regularOut = new RegularOut(weight,(Place)dest,this);
					myOuts.put(regularOut.getIdentifier(),regularOut);
					myPlaces.put(((Place)dest).getIdentifier(), ((Place)dest));
					break;
				case ZeroIn:
					ZeroIn zeroIn = new ZeroIn(weight,(Place)dest,this);
					myIns.put(zeroIn.getIdentifier(),zeroIn);
					myPlaces.put(((Place)dest).getIdentifier(), ((Place)dest));
					break;
				case EmptierIn:
					EmptierIn emptierIn = new EmptierIn(weight,(Place)dest,this);
					myIns.put(emptierIn.getIdentifier(),emptierIn);
					myPlaces.put(((Place)dest).getIdentifier(), ((Place)dest));
					break;
				}
		}
		
	}
	
	/**
	 * Removes edge
	 */
	@Override
	public void deleteEdge(int identifier) {
		this.myIns.remove(identifier);
	}
	

	/*
	 * OWN METHODS
	 */
	/**
	 * Checks if the transition is firable, it uses the activable() method from edge
	 * to know if every linked edge is activable
	 * @return boolean : is fireable or not
	 */
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
	 * Display the edges linked to the transition. 
	 */
	public void displayEdges() {
		System.out.println("In: ");
		for(Map.Entry<Integer,In> in : myIns.entrySet()) {
			System.out.println(in.getValue().toString());
		}
		System.out.println("Out: ");
		for(Map.Entry<Integer,Out> out : myOuts.entrySet()) {
			System.out.println(out.getValue().toString());
		}
	}
	
	/**
	 * Fire the transition, if it is fireable
	 */
	public void fire() {
		if (isFirable() == true) {
			// step the Ins
			for(Map.Entry<Integer, In> ins : myIns.entrySet()) {
				ins.getValue().step();
			}
			// step the Outs
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
		Transition trans1 = new Transition();
		System.out.println(trans1);
		
		// TEST 2 : add some edges
		System.out.println("\nTEST2 : add some edges");
		Place place1 = new Place(4);
		Place place2 = new Place (10);
		Place place3 = new Place(0);
		Place place4 = new Place(1);
		try {
			trans1.addEdge(place1, EdgeTypes.RegularIn, 3);
		} catch(AddEdgeException e) {
			e.printStackTrace();
		}
		try {
			trans1.addEdge(place2, EdgeTypes.EmptierIn, 0);
		} catch (AddEdgeException e) {
			e.printStackTrace();
		}
		try {
			trans1.addEdge(place3, EdgeTypes.ZeroIn, 0);
		} catch (AddEdgeException e) {
			e.printStackTrace();
		}
		try {
			trans1.addEdge(place4, EdgeTypes.RegularOut, 200);
		} catch (AddEdgeException e) {
			e.printStackTrace();
		}
		System.out.println("The edges : ");
		trans1.displayEdges();
		
		System.out.println("The places : ");
		for(Map.Entry<Integer, Place> p : trans1.myPlaces.entrySet()) {
			System.out.println(p.getValue());
		}
		
		// TEST 3 : firable
		System.out.println("\n // TEST 3 : firable");
		System.out.println("Is the transition fireable ?" + trans1.isFirable());
		
		
		// TEST 4 : fire the transition
		System.out.println("\n // TEST 4 : fire the transition");
		trans1.fire();
		System.out.println("The edges : ");
		trans1.displayEdges();
		
		System.out.println("The places : ");
		for(Map.Entry<Integer, Place> p : trans1.myPlaces.entrySet()) {
			System.out.println(p.getValue());
		}


	
		

	}

	
}
