/**
 * 
 */
package petriNetwork;

import java.util.ArrayList;
import java.util.Map;
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
		this.myPlaces = null;
		this.myTransitions = null;
		this.myEdges = null;
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

	public void deletePlace(int identifier) {
		// remove the place from the list of the petri net.
		myPlaces.remove(identifier);

		// removes the place from the edge.
		for (Map.Entry<Integer, Edge> e : myEdges.entrySet()) {
			if (e.getValue().getMyPlace().getIdentifier() == identifier) {
				e.getValue().setMyPlace(null);
			}
		}

		// removes the place from the list of the transitions.
		// iterate over the transitions of the petri network
		for (Map.Entry<Integer, Transition> t : myTransitions.entrySet()) {
			// check if the transition has the place
			if (t.getValue().getMyPlaces().containsKey(identifier)) {
				// removes the place
				t.getValue().getMyPlaces().remove(identifier);
			}
		}
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

	// MAIN
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}

}
