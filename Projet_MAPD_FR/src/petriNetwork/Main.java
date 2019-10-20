package petriNetwork;

import java.util.ArrayList;

public class Main {
	
	public static void main(String[]args) {
		
		// Instanciation of the petri network (singleton pattern)
		PetriNetwork pn = PetriNetwork.getInstance();
		System.out.println(pn.toString());
		
		PetriNetwork pn2 = PetriNetwork.getInstance();
		System.out.println(pn2);
		
		System.out.println(PetriNetwork.getInstance());
		System.out.println(PetriNetwork.getInstance());


	}
}
