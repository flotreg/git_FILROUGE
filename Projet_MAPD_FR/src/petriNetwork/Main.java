package petriNetwork;

import java.util.ArrayList;
import java.util.HashMap;

import edges.EdgeTypes;

public class Main {
	
	public static void main(String[]args) {
		
		// Instanciation of the petri network (singleton pattern)
		PetriNetwork pn = PetriNetwork.getInstance();
		System.out.println(pn.toString());
		
		PetriNetwork pn1 = PetriNetwork.getInstance();
		System.out.println(pn1);
		
		System.out.println(PetriNetwork.getInstance());
		System.out.println(PetriNetwork.getInstance());
		
		//TEST 1 CONSTRUCTORS
		System.out.println("\nTEST CONSTRUCTOR1");
		System.out.println("\nTEST CONSTRUCTOR2");
		HashMap hp = new HashMap<Integer, Place>();
		HashMap ht = new HashMap<Integer, Transition>();
		HashMap he = new HashMap<Integer, Edge>();
		PetriNetwork pn2 = PetriNetwork.getInstance(hp, ht, he);
		System.out.println(pn2);

		// TEST 2 BUILDERS
		System.out.println("\nTEST PLACE BUILDER");
		Place pl1 = pn2.buildPlace(8);
		Place pl2 = pn2.buildPlace(1);
		System.out.println("Mes Places : " + pn2.myPlaces);
		System.out.println("\nTEST TRANSITION BUILDER");
		Transition t = pn2.buildTransition();
		System.out.println("Mes Transitions : " + pn2.myTransitions);
		System.out.println("\nTEST EDGE BUILDER");
		Edge e1 = pn2.buildEdge(EdgeTypes.RegularIn, pl1, t, 2);
		Edge e2 = pn2.buildEdge(EdgeTypes.RegularOut, pl2, t, 5);
		System.out.println("Mes Edges : " + pn2.myEdges);

		// TEST STEP
		System.out.println("\nTEST STEP");
		System.out.println(pn2.myPlaces);
		System.out.println("------FIRE------");
		pn2.step(t);
		System.out.println("my place :" + pn2.myPlaces);
		System.out.println("\nTEST STEP UNTIL END");
		pn2.stepUntilEnd();
		System.out.println(pn2.myPlaces);


	}
}
