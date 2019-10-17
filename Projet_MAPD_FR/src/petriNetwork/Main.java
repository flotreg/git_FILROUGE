package petriNetwork;

import java.util.ArrayList;

public class Main {
	
	public static void main(String[]args) {
		
		//TEST : Out.fill() method (not over)
		Transition t = new Transition();
		Out tOut = new Out(3);
		ArrayList<Out> myOut = new ArrayList<Out>();
		myOut.add(tOut);
		Place p = new Place(3,new ArrayList<In>(),myOut);
		System.out.println(p);
		tOut.fill();
		System.out.println(p);
	}
}
