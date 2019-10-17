package petriNetwork;

import java.util.ArrayList;

public class Main {
	
	public static void main(String[]args) {
		
		//TEST : Out.fill() method (not over)
		//TEST 2 : creation of transition with constructor2
		System.out.println("\nTEST2 : constuctor 2");
		Transition trans2 = new Transition(new ArrayList<Place>(),new ArrayList<In>(), new ArrayList<Out>());
		System.out.println(trans2);

	}
}
