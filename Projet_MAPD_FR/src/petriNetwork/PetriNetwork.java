/**
 * 
 */
package petriNetwork;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import edges.*;
import exceptions.AddEdgeException;
import interfaces.Edgeable;

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
	
	public void buildEdge(Transition transition, Edgeable dest, EdgeTypes e, int weight) {
		int key = transition.getIdentifier();
		try {
		myEdges.put(myTransitions.get(key).addEdge(dest, e, weight).getIdentifier(), myTransitions.get(key).addEdge(dest, e, weight));
		} catch(AddEdgeException exception) {
			exception.printStackTrace();
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
		// remove the edges linked to this place 
		for (Map.Entry<Integer, Edge> e : myEdges.entrySet()) {
			if (e.getValue().getMyPlace().getIdentifier() == identifier) {
				deleteEdge(e.getValue().getIdentifier());
			}
		}
		
		// removes the place from the place list in transitions
		for (Map.Entry<Integer, Transition> t : myTransitions.entrySet()) {
			if (t.getValue().getMyPlaces().containsKey(identifier)) {
				t.getValue().getMyPlaces().remove(identifier);
			}
		}
		// remove the place from the list of the petri net.
		myPlaces.remove(identifier);
	}

	public void deleteTransition(int identifier) {
		// delete the edges linked to this transition
		for (Map.Entry<Integer, Edge> e : myEdges.entrySet()) {
			if (e.getValue().getMyTransition().getIdentifier() == identifier) {
				deleteEdge(e.getValue().getIdentifier());
			}
		}
		// remove from the list of the petrinet
		myTransitions.remove(identifier);		
	}

	public void deleteEdge(int identifier) {
		myEdges.remove(identifier);
	}

	public void step(Transition t) {
			t.fire();
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
		System.out.println("\nTEST CONSTRUCTOR1");
		PetriNetwork p1 = new PetriNetwork();
		System.out.println(p1);
		System.out.println("\nTEST CONSTRUCTOR2");
		HashMap hp = new HashMap<Integer,Place>();
		HashMap ht = new HashMap<Integer,Transition>();
		HashMap he = new HashMap<Integer,Edge>();
		PetriNetwork p2 = new PetriNetwork(hp,ht,he);
		System.out.println(p2);
		
		//TEST 2 BUILDERS
		System.out.println("\nTEST PLACE BUILDER");
		Place pl1 = p1.buildPlace(8);
		Place pl2 = p1.buildPlace(1);
		System.out.println("Mes Places : "+p1.myPlaces);
		System.out.println("\nTEST TRANSITION BUILDER");
		Transition t = p1.buildTransition();
		System.out.println("Mes Transitions : "+p1.myTransitions);
		System.out.println("\nTEST EDGE BUILDER");
		Edge e1 = p1.buildEdge(EdgeTypes.RegularIn, pl1, t, 2);
		Edge e2 = p1.buildEdge(EdgeTypes.RegularOut, pl2, t, 5);
		System.out.println("Mes Edges : "+p1.myEdges);
		
		//TEST STEP
		System.out.println("\nTEST STEP");
		System.out.println(p1.myPlaces);
		System.out.println("------FIRE------");
		p1.step(t);
		System.out.println("my place :"+p1.myPlaces);
		System.out.println("\nTEST STEP UNTIL END");
		p1.stepUntilEnd();
		System.out.println(p1.myPlaces);
		
	}

}
