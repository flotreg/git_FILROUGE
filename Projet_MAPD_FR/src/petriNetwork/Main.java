package petriNetwork;

import java.util.ArrayList;
import java.util.HashMap;

import edges.EdgeTypes;

public class Main {

	public static void main(String[] args) {

		// Instanciation of the petri network (singleton pattern)
		PetriNetwork pn = PetriNetwork.getInstance();
		System.out.println(pn.toString());

		// Build some elements for the PetriNetwork
		// places
		Place place1 = pn.buildPlace(4);
		Place place2 = pn.buildPlace(0);
		Place place3 = pn.buildPlace(10);
		Place place4 = pn.buildPlace(1);
		Place place5 = pn.buildPlace(0);
		System.out.println(pn.getMyPlaces());

		// transitions
		Transition trans1 = pn.buildTransition();
		Transition trans2 = pn.buildTransition();
		System.out.println(pn.getMyTransitions());

		// edges
		Edge e1 = pn.buildEdge(trans1, place1, EdgeTypes.RegularIn, 3);
		Edge e2 = pn.buildEdge(trans1, place2, EdgeTypes.ZeroIn, 0);
		Edge e3 = pn.buildEdge(trans1, place3, EdgeTypes.RegularOut, 22);
		Edge e4 = pn.buildEdge(trans1, place5, EdgeTypes.RegularOut, 9);
		Edge e5 = pn.buildEdge(trans2, place4, EdgeTypes.EmptierIn, 0);
		Edge e6 = pn.buildEdge(trans2, place5, EdgeTypes.RegularOut, 7);
		System.out.println(pn.getMyEdges());

		// fire a transition
		System.out.println("Is transition 1 fireable ? " + trans1.isFirable());
		trans1.fire();

		// display the network after the fire()
		System.out.println(pn.getMyPlaces());

		// fire another transition
		System.out.println("Is transition 2 fireable ?" + trans2.isFirable());
		trans2.fire();

		// display the network after the fire()
		System.out.println(pn.getMyPlaces());
		
		// removes some edge
		
//		
//		
//		// fire a transition
//		
//		
//		//TEST 1 CONSTRUCTORS
//		System.out.println("\nTEST CONSTRUCTOR1");
//		System.out.println("\nTEST CONSTRUCTOR2");
//		HashMap hp = new HashMap<Integer, Place>();
//		HashMap ht = new HashMap<Integer, Transition>();
//		HashMap he = new HashMap<Integer, Edge>();
//		PetriNetwork pn2 = PetriNetwork.getInstance(hp, ht, he);
//		System.out.println(pn2);
//
//		// TEST 2 BUILDERS
//		System.out.println("\nTEST PLACE BUILDER");
//		Place pl1 = pn.buildPlace(8);
//		Place pl2 = pn.buildPlace(1);
//		System.out.println("Mes Places : " + pn.myPlaces);
//		System.out.println("\nTEST TRANSITION BUILDER");
//		Transition t = pn2.buildTransition();
//		System.out.println("Mes Transitions : " + pn2.myTransitions);
//		System.out.println("\nTEST EDGE BUILDER");
//		Edge e1 = pn2.buildEdge(EdgeTypes.RegularIn, pl1, t, 2);
//		Edge e2 = pn2.buildEdge(EdgeTypes.RegularOut, pl2, t, 5);
//		System.out.println("Mes Edges : " + pn2.myEdges);
//
//		// TEST STEP
//		System.out.println("\nTEST STEP");
//		System.out.println(pn2.myPlaces);
//		System.out.println("------FIRE------");
//		pn2.step(t);
//		System.out.println("my place :" + pn2.myPlaces);
//		System.out.println("\nTEST STEP UNTIL END");
//		pn2.stepUntilEnd();
//		System.out.println(pn2.myPlaces);

	}
}
