/**
 * 
 */
package petriNetwork;

/**
 * @author Bonjour
 * This main class manages all the network
 */
public class PetriNetwork {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//TEST : addition of an edge
		Transition trans = new Transition();
		System.out.println(trans.isFirable());

		Place place1 = new Place();
		System.out.println(place1.toString());
		
		
		try {
			trans.addEdge(place1, true, 274);
		}catch (Exception e){
			e.printStackTrace();
		}
		
		

	}

}
