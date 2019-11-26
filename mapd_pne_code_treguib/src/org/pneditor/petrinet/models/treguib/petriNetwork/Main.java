package org.pneditor.petrinet.models.treguib.petriNetwork;


import org.pneditor.petrinet.models.treguib.edges.EdgeTypes;

public class Main {

	public static void main(String[] args) {

		// Instanciation of the petri network (singleton pattern)
		PetriNetwork pn = PetriNetwork.getInstance();
		System.out.println(pn.toString());

		// Build some elements for the PetriNetwork
		// places
		Place place1 = pn.buildPlace(10);
		Place place2 = pn.buildPlace(5);
		Place place3 = pn.buildPlace(8);
		Place place4 = pn.buildPlace(11);
		Place place5 = pn.buildPlace(0);
		System.out.println(pn.getMyPlaces());

		// transitions
		Transition trans1 = pn.buildTransition();
		Transition trans2 = pn.buildTransition();
		System.out.println(pn.getMyTransitions());

		// edges
		Edge e1 = pn.buildEdge(trans1, place1, EdgeTypes.RegularIn, 3);
		Edge e2 = pn.buildEdge(trans1, place2, EdgeTypes.RegularIn, 2);
		Edge e3 = pn.buildEdge(trans1, place3, EdgeTypes.RegularOut, 5);
		Edge e4 = pn.buildEdge(trans1, place5, EdgeTypes.RegularOut, 9);
		Edge e5 = pn.buildEdge(trans2, place4, EdgeTypes.EmptierIn, 0);
		Edge e6 = pn.buildEdge(trans2, place5, EdgeTypes.RegularOut, 7);
		System.out.println(pn.getMyEdges());

		// fire a transition
		System.out.println("Is transition 1 fireable ? " + trans1.isFirable());
		trans1.fire();

		// display the network after the fire()
		System.out.println("My place : "+pn.getMyPlaces());

		// fire another transition
		System.out.println("Is transition 2 fireable ? " + trans2.isFirable());
		trans2.fire();

		// display the network after the fire()
		System.out.println("My place : "+pn.getMyPlaces());
		
		// removes some edge
		pn.deleteEdge(4);
		System.out.println("My edges : "+pn.getMyEdges());
		
		//remove some transition
		pn.deleteTransition(2);
		System.out.println("My transitions"+pn.getMyTransitions());

		//remove some place 
		pn.deletePlace(4);
		System.out.println("My places : "+pn.getMyPlaces());
		
		//Fire all
		System.out.println("My places : "+pn.getMyPlaces());
		pn.stepUntilEnd();
		System.out.println("My places : "+pn.getMyPlaces());
		

	}
}
