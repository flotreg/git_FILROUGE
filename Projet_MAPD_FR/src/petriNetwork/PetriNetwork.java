/**
 * 
 */
package petriNetwork;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import edges.*;

/**
 * @author Bonjour This main class manages all the network
 */
public final class PetriNetwork {
	// ATTRIBUTES
	private Map<Integer, Place> myPlaces;
	private Map<Integer, Transition> myTransitions;
	private Map<Integer, Edge> myEdges;
	private static PetriNetwork uniqueInstance;

	// CONSTRUCTORS
	private PetriNetwork() {
		this.myPlaces = new HashMap<Integer,Place>();
		this.myTransitions = new HashMap<Integer,Transition>();
		this.myEdges = new HashMap<Integer,Edge>();
	}

	private PetriNetwork(Map<Integer, Place> np, Map<Integer, Transition> nt, Map<Integer, Edge> ne) {
		this.myPlaces = np;
		this.myTransitions = nt;
		this.myEdges = ne;
	}

	// GETTERS OF THE SINGLETON
	/**
	 * getter of the singleton : uses the constructor without parameters Needs to
	 * build the all petri network objects afterwards.
	 * 
	 * @return the unique Instance of the PetriNetwork without anything
	 */
	public static PetriNetwork getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new PetriNetwork();
		}
		return uniqueInstance;
	}

	/**
	 * getter of the singleton : uses the constructor with parameters Some petri
	 * network objects can be created with this.
	 * 
	 * @param np
	 * @param nt
	 * @param ne
	 * @return the unique Instance of the PetriNetwork with places, edge and
	 *         transitions
	 */
	public static PetriNetwork getInstance(Map<Integer, Place> np, Map<Integer, Transition> nt, Map<Integer, Edge> ne) {
		if (uniqueInstance == null) {
			uniqueInstance = new PetriNetwork(np, nt, ne);
		}
		return uniqueInstance;
	}

	// OWN FUNCTIONS
	public Place buildPlace(int tokens) {
		Place place = new Place(tokens);
		myPlaces.put(place.getIdentifier(), place);
		return place;
	}

	public Transition buildTransition() {
		Transition transition = new Transition();
		myTransitions.put(transition.getIdentifier(), transition);
		return transition;
	}

	public Edge buildEdge(EdgeTypes e, Place p, Transition t, int weight) {
		switch (e) {
		case RegularIn:
			RegularIn regularIn = new RegularIn(weight, p, t);
			myEdges.put(regularIn.getIdentifier(), regularIn);
			return regularIn;
		case RegularOut:
			RegularOut regularOut = new RegularOut(weight, p, t);
			myEdges.put(regularOut.getIdentifier(), regularOut);
			return regularOut;
		case ZeroIn:
			ZeroIn zeroIn = new ZeroIn(weight, p, t);
			myEdges.put(zeroIn.getIdentifier(), zeroIn);
			return zeroIn;
		case EmptierIn:
			EmptierIn emptierIn = new EmptierIn(weight, p, t);
			myEdges.put(emptierIn.getIdentifier(), emptierIn);
			return emptierIn;
		default:
			RegularIn regular = new RegularIn();
			return regular;
		}
	}

	/**
	 * Deletes the place from :
	 * 		the list of the petrinet
	 * 		the list of the transition
	 * Also deletes the edge linked to the place. 
	 * @param identifier of the place to delete
	 */
	public void deletePlace(int identifier) {
		// remove the place from the list of the petri net.
		myPlaces.remove(identifier);

		// removes the place from the list of the transitions.
		// iterate over the transitions of the petri network
		for (Map.Entry<Integer, Transition> t : myTransitions.entrySet()) {
			// check if the transition has the place
			if (t.getValue().getMyPlaces().containsKey(identifier)) {
				// removes the place
				t.getValue().getMyPlaces().remove(identifier);
			}
		}
		
		// removes the edges linked to this place
		for (Map.Entry<Integer, Edge> e : myEdges.entrySet()) {
			if (e.getValue().getMyPlace().getIdentifier() == identifier) {
				deleteEdge(e.getValue().getIdentifier());
			}
		}
		
//		// removes the place from the edge.
//		for (Map.Entry<Integer, Edge> e : myEdges.entrySet()) {
//			if (e.getValue().getMyPlace().getIdentifier() == identifier) {
//				e.getValue().setMyPlace(null);
//			}
//		}
	}

	public void deleteTransition(int identifier) {
		// remove from the list of the petrinet
		myTransitions.remove(identifier);
		
		// delete the edges linked to this transition
		for (Map.Entry<Integer, Edge> e : myEdges.entrySet()) {
			if (e.getValue().getMyTransition().getIdentifier() == identifier) {
				deleteEdge(e.getValue().getIdentifier());
			}
		}
		
		// removes from the edge
		
	}

	public void deleteEdge(int identifier) {
		myEdges.remove(identifier);
	}

	public void step() {
		for(Map.Entry<Integer,Transition> transition : myTransitions.entrySet()) {
			transition.getValue().fire();
		}
	}

	public void stepUntilEnd() {
		for(Map.Entry<Integer,Transition> transition : myTransitions.entrySet()) {
			while(transition.getValue().isFirable()) {
				transition.getValue().fire();
			}
		}
	}

	// MAIN
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//TEST 1 CONSTRUCTORS
		System.out.println("\nCONSTRUCTOR1");
		PetriNetwork p1 = new PetriNetwork();
		System.out.println(p1);
		System.out.println("\nCONSTRUCTOR2");
		HashMap hp = new HashMap<Integer,Place>();
		HashMap ht = new HashMap<Integer,Transition>();
		HashMap he = new HashMap<Integer,Edge>();
		PetriNetwork p2 = new PetriNetwork(hp,ht,he);
		System.out.println(p2);
		//TEST 2 BUILDERS
		System.out.println("\nPLACE BUILDER");
		Place pl1 = p1.buildPlace(8);
		Place pl2 = p1.buildPlace(1);
		System.out.println("Mes Places : "+p1.myPlaces);
		System.out.println("\nTRANSITION BUILDER");
		Transition t = p1.buildTransition();
		System.out.println("Mes Transitions : "+p1.myTransitions);
		System.out.println("\nEDGE BUILDER");
		Edge e1 = p1.buildEdge(EdgeTypes.RegularIn, pl1, t, 2);
		Edge e2 = p1.buildEdge(EdgeTypes.RegularOut, pl2, t, 5);
		System.out.println(""he);
		//TEST STEP
		p1.step();
		p1.stepUntilEnd();
		
		
	}

}
