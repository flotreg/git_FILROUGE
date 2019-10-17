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
	private ArrayList<Place> myPlaces;
	private ArrayList<In> myIns;
	private ArrayList<Out> myOuts;

	/** 
	 * CONSTRUCTORS
	 */
	public Transition() {
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
		this.myPlaces = nMyPlaces;
		this.myIns = nMyIns;
		this.myOuts = nMyOuts; 
	}
	/**
	 * REDEFINITION
	 */
	public String toString() {
		return "\n, myplaces : "
				+myPlaces+"\n, myIns : "
				+myIns+"\n, myOuts : "
				+myOuts
				+"\n ancienne méthode toString : " + super.toString();
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
	public void addEdge(AddEdge dest, boolean inOut, int weight) throws AddEdgeException{
			if(!(dest instanceof Place)) {
				throw new AddEdgeException();
			}else{
				this.myPlaces.add((Place)dest);
				//check the type of the edge
				if(inOut) {
					this.myIns.add(new In(weight));
				}else {
					this.myOuts.add(new Out(weight));
				}
			}
		}
	
	/*
	 * OWN METHODS
	 */
	
	public void fire() {
		
	}
	
	/*
	 * MAIN FOR TESTING
	 */
	
	public static void main(String [] args) {
		//Test creation of transition with constructor1
		Transition trans = new Transition();
		System.out.println(trans);
		
		//Test creation of transition with constructor2
		Transition trans2 = new Transition(new ArrayList<Place>(),new ArrayList<In>(), new ArrayList<Out>());
		
		//Test toString()
		System.out.println(trans2);
		

	}
	
}
