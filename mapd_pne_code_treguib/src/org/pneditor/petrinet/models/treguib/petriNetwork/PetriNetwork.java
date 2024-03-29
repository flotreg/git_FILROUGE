/**
 * 
 */
package org.pneditor.petrinet.models.treguib.petriNetwork;

import java.util.Map;
import java.util.HashMap;
import org.pneditor.petrinet.models.treguib.edges.EdgeTypes;
import org.pneditor.petrinet.models.treguib.exceptions.AddEdgeException;
import java.util.concurrent.ConcurrentHashMap;
import org.pneditor.petrinet.models.treguib.interfaces.Edgeable;

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
		this.myPlaces = new ConcurrentHashMap<Integer,Place>();
		this.myTransitions = new ConcurrentHashMap<Integer,Transition>();
		this.myEdges = new ConcurrentHashMap<Integer,Edge>();
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

	
	/*
	 * GETTERS AND SETTERS
	 */
	public Map<Integer, Place> getMyPlaces() {
		return myPlaces;
	}

	public void setMyPlaces(Map<Integer, Place> myPlaces) {
		this.myPlaces = myPlaces;
	}

	public Map<Integer, Transition> getMyTransitions() {
		return myTransitions;
	}

	public void setMyTransitions(Map<Integer, Transition> myTransitions) {
		this.myTransitions = myTransitions;
	}

	public Map<Integer, Edge> getMyEdges() {
		return myEdges;
	}

	public void setMyEdges(Map<Integer, Edge> myEdges) {
		this.myEdges = myEdges;
	}

	// OWN FUNCTIONS
	/**
	 * Build a place and adds it to the list myPlaces
	 * @param tokens
	 * @return
	 */
	public Place buildPlace(int tokens) {
		Place place = new Place(tokens);
		myPlaces.put(place.getIdentifier(), place);
		return place;
	}

	/**
	 * Build a transition and adds it to the list myTransitions
	 * @return
	 */
	public Transition buildTransition() {
		Transition transition = new Transition();
		myTransitions.put(transition.getIdentifier(), transition);
		return transition;
	}
	
	/**
	 * Builds an edge and add it to the list myEdges
	 * @param transition
	 * @param dest
	 * @param e
	 * @param weight
	 */
	public Edge buildEdge(Transition transition, Edgeable dest, EdgeTypes e, int weight) {
		int key = transition.getIdentifier();
		Edge edge = null;
		try {
			 edge = myTransitions.get(key).addEdge(dest, e, weight);
			 myEdges.put(edge.getIdentifier(), edge);
		} catch(AddEdgeException exception) {
			exception.printStackTrace();
		}		
		return edge;
	}


	/**
	 * Deletes the place :
	 * 	removes the edges linked to the place
	 * 	removes the place from the place list in transition
	 * 	removes the place from the list in petrinetwork
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

	/**
	 * Removes the transition
	 * 	deletes the edges linked to this transition
	 *  removes from the list of the petrinet
	 * @param identifier
	 */
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

	/**
	 * Deletes the edge from the myEdges
	 * deletes the edge from the list in transition
	 * @param identifier
	 */
	public void deleteEdge(int identifier) {
		// removes from the list of the petrinetwork
		myEdges.remove(identifier);
		
		// removes from the list in Transition
		for(Map.Entry<Integer, Transition> t : myTransitions.entrySet()) {
			if(t.getValue().getMyIns().containsKey(identifier)) {
				t.getValue().deleteEdge(identifier);
			}else if(t.getValue().getMyOuts().containsKey(identifier)) {
				t.getValue().deleteEdge(identifier);
			}
		}
	}

	/**
	 * fires only one transition
	 * @param t
	 */
	public void step(Transition t) {
			t.fire();
		}

	/**
	 * check the transition and return true until there are no more activable transitions
	 */
	
	public boolean continueToStep() {
		for (Map.Entry<Integer, Transition> t : myTransitions.entrySet()) {
			if (t.getValue().isFirable() == true) {
				return true;
			}
		}
		return false;
	}
	/*
	 * Fire the transition and return true until there are no more activable transitions
	 */
	public void stepUntilEnd() {
		while(continueToStep()==true) {
			for (Map.Entry<Integer, Transition> t1 : myTransitions.entrySet()) {
				if (t1.getValue().isFirable()) {
					t1.getValue().fire();
				}
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
