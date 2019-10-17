/**
 * 
 */
package petriNetwork;

import java.util.ArrayList;
import edges.*;

/**
 * @author Bonjour
 * This main class manages all the network
 */
public class PetriNetwork {
	//ATTRIBUTES
	private int index;
	private ArrayList<Place> myPlaces;
	private ArrayList<Transition> myTransitions;
	private ArrayList<Edge> myEdges;
	
	//CONSTRUCTORS
	public PetriNetwork() {
		this.index = 0;
		this.myPlaces = null;
		this.myTransitions = null;
		this.myEdges = null;
	}
	
	public PetriNetwork(int index,ArrayList<Place> np,ArrayList<Transition> nt,ArrayList<Edge>ne) {
		this.index = index;
		this.myPlaces = np;
		this.myTransitions = nt;
		this.myEdges = ne;
	}
	
	//OWN FUNCTIONS
	public void buildPlace() {
		myPlaces.add(new Place());
	}
	
	public void buildTransition() {
		myTransitions.add(new Transition());
	}
	
	public void buildEdge(EdgeTypes e,Place p,Transition t,int weight) {
		switch(e) {
		case RegularIn :
			myEdges.add(new RegularIn(weight,p,t));
			break;
		case RegularOut :
			myEdges.add(new RegularOut(weight,p,t));
			break;
		case ZeroIn :
			myEdges.add(new ZeroIn(weight,p,t));
			break;
		case EmptierIn :
			myEdges.add(new EmptierIn(weight,p,t));
			break;
		}
	}
		
	public void deletePlace(int placeIndex) {
		
	}
	
	public void deleteTransition(int transitionIndex) {
	}
	
	public void deleteEdge(Place place,Transition transition) {
		
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
