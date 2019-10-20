/**
 * 
 */
package petriNetwork;

import java.util.ArrayList;
import edges.*;

/**
 * @author Bonjour This main class manages all the network
 */
public final class PetriNetwork2 {
	// ATTRIBUTES
	private int index;
	private ArrayList<Place> myPlaces;
	private ArrayList<Transition> myTransitions;
	private ArrayList<Edge> myEdges;
	private static PetriNetwork2 instance = null;

	// CONSTRUCTORS
	private PetriNetwork2() {
		this.index = 0;
		this.myPlaces = null;
		this.myTransitions = null;
		this.myEdges = null;
	}

	private PetriNetwork2(int index, ArrayList<Place> np, ArrayList<Transition> nt, ArrayList<Edge> ne) {
		this.index = index;
		this.myPlaces = np;
		this.myTransitions = nt;
		this.myEdges = ne;
	}

	// ACCESSORS OF THE SINGLETON
	public static PetriNetwork2 getInstance() {
		if (instance == null) {
			System.out.println("Création d'un nouveau pétri");
			instance = new PetriNetwork2();
		}
		System.out.println("Pétri déjà créé");
		return instance;
	}

	public static PetriNetwork2 getInstance(int index, ArrayList<Place> np, ArrayList<Transition> nt,
			ArrayList<Edge> ne) {
		if (instance == null) {
			System.out.println("Création d'un nouveau pétri");
			instance = new PetriNetwork2(index, np, nt, ne);
		}
		System.out.println("Pétri déjà créé");
		return instance;
	}

	// OWN FUNCTIONS
	public void buildPlace() {
		myPlaces.add(new Place());
	}

	public void buildTransition() {
		myTransitions.add(new Transition());
	}

	public void buildEdge(EdgeTypes e, Place p, Transition t, int weight) {
		switch (e) {
		case RegularIn:
			myEdges.add(new RegularIn(weight, p, t));
			break;
		case RegularOut:
			myEdges.add(new RegularOut(weight, p, t));
			break;
		case ZeroIn:
			myEdges.add(new ZeroIn(weight, p, t));
			break;
		case EmptierIn:
			myEdges.add(new EmptierIn(weight, p, t));
			break;
		}
	}

	public void deletePlace(int placeIndex) {

	}

	public void deleteTransition(int transitionIndex) {
	}

	public void deleteEdge(Place place, Transition transition) {

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
		// TODO Auto-generated method stub

	}

}
