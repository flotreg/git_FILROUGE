/**
 * 
 */
package petriNetwork;

/**
 * Inherits abstract class Edge
 * @author Bonjour
 *
 */
public class Out extends Edge {
	
	/**
	 * Constructor without parameters referring to Edge constructor
	 */
	public Out() {
		super();
	}
	
	/**
	 * Constructor with parameters referring to Edge constructor
	 * @param weight
	 */
	public Out(int weight) {
		super(weight);
	}
	
	public String toString() {
		return "I'm an edge Out and my weight is : "+this.getWeight();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// TEST : constructor without parameters
		Edge eOut1 = new Out();
		System.out.println(eOut1.toString());
		
		// TEST : constructor with parameters
		Edge eOut2 = new Out(7);
		System.out.println(eOut2.toString());

	}

}
