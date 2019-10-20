/**
 * 
 */
package petriNetwork;

import java.util.ArrayList;
import java.util.Map;
import edges.*;

/**
 * @author Bonjour
 * This main class manages all the network
 */
public class PetriNetwork {
	//ATTRIBUTES
	private Map<Integer,Place > myPlaces;
	private Map<Integer,Transition> myTransitions;
	private Map<Integer,Edge> myEdges;
	
	//CONSTRUCTORS
	public PetriNetwork() {
		this.myPlaces = null;
		this.myTransitions = null;
		this.myEdges = null;
	}
	
	public PetriNetwork(Map<Integer,Place> np,Map<Integer,Transition> nt,Map<Integer,Edge>ne) {
		this.myPlaces = np;
		this.myTransitions = nt;
		this.myEdges = ne;
	}
	
	//OWN FUNCTIONS
	public Place buildPlace(int tokens) {
		Place place = new Place(tokens);
		myPlaces.put(place.getIdentifier(),place);
		return place;
	}
	
	public Transition buildTransition() {
		Transition transition = new Transition();
		myTransitions.put(transition.getIdentifier(),transition);
		return transition;
	}
	
	public Edge buildEdge(EdgeTypes e,Place p,Transition t,int weight) {
		switch(e) {
		case RegularIn :
			RegularIn regularIn = new RegularIn(weight,p,t);
			myEdges.put(regularIn.getIdentifier(),regularIn);
			return regularIn;
		case RegularOut :
			RegularOut regularOut = new RegularOut(weight,p,t);
			myEdges.put(regularOut.getIdentifier(),regularOut);
			return regularOut;
		case ZeroIn :
			ZeroIn zeroIn = new ZeroIn(weight,p,t);
			myEdges.put(zeroIn.getIdentifier(),zeroIn);
			return zeroIn;
		case EmptierIn :
			EmptierIn emptierIn = new EmptierIn(weight,p,t);
			myEdges.put(emptierIn.getIdentifier(),emptierIn);
			return emptierIn;
		default :
			RegularIn regular = new RegularIn();
			return regular;
		}
	}
		
	public void deletePlace(int identifier) {
		myPlaces.remove(identifier);
	}
	
	public void deleteTransition(int identifier) {
		myTransitions.remove(identifier);
	}
	
	public void deleteEdge(int identifier) {
		myEdges.remove(identifier);
	}
	
	public void step() {
		
	}
	
	public void stepUntilEnd() {
		
	}
	
	
	
	
	//MAIN
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//TEST : addition of an edge
		Transition trans = new Transition();
		System.out.println(trans.isFirable());

		Place place1 = new Place();
		System.out.println(place1.toString());
		
		
		try {
			trans.addEdge(place1, , 274);
		}catch (Exception e){
			e.printStackTrace();
		}
		
		

	}

}
