package petriNetwork;

import exceptions.AddEdgeException;
import java.util.ArrayList;
import interfaces.AddEdge;

/** 
 * Here the class Transition. This class fires the transitions.
 */
public class Transition implements AddEdge{
	
	private boolean isFirable;
	private Place myPlaces;
	private ArrayList<In> myIns;
	private ArrayList<Out> myOuts;

	/** 
	 * Constructors, settors and gettors
	 */
	public Transition() {
		this.isFirable = true;
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
	public Transition(boolean nIsFirable, Place nMyPlaces,ArrayList<In> nMyIns,ArrayList<Out> nMyOuts) {
		this.isFirable = nIsFirable;
		this.myPlaces = nMyPlaces;
		this.myIns = nMyIns;
		this.myOuts = nMyOuts; 
	}
	/**
	 * Redefinition of the toString method
	 */
	public String toString() {
		return "isFirable : "+isFirable+
				"\n, myplaces : "
				+myPlaces+"\n, myIns : "
				+myIns+"\n, myOuts : "
				+myOuts
				+"\n ancienne méthode toString : " + super.toString();
	}
	public boolean isFirable() {
		return isFirable;
	}
	public void setFirable(boolean isFirable) {
		this.isFirable = isFirable;
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
				this.myPlaces = (Place)dest;
				//check the type of the edge
				if(inOut) {
					this.myIns = new In(weight);
				}else {
					this.myOuts = new Out(weight);
				}
			}
		}
	
	
	public static void main(String [] args) {
		//Test creation of transition
		Transition trans = new Transition();
		System.out.println(trans.isFirable);
		
		//Test creation of transition with constructor2
		Transition trans2 = new Transition(true,new Place(), new In(),new Out());
		
		//Test toString()
		System.out.println(trans2);
		

	}
	
}
