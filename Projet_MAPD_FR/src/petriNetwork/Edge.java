package petriNetwork;

/**
 * This abstract class represents the edge
 * It shares global edge attributes
 * It forces global edge behaviors
 * @author Bonjour
 * @version 1
 * @since 17/10/2019
 *
 */
public abstract class Edge {
	
	/*
	 * ATTRIBUTES
	 */
	/**
	 * weight of the edge
	 */
	private int weight;
	
	/**
	 * static counter to build the identifier
	 */
	private static int counter = 0;
	
	/**
	 * identifier of the object
	 */
	private int identifier;
	

	/*
	 * CONSTRUCTORS
	 */
	/**
	 * constructor without parameters
	 */
	public Edge() {
		counter += 1;
		this.identifier = counter;
		this.weight = 0;
		
	}
	
	/**
	 * constructor with weight parameter
	 */
	public Edge(int w) {
		counter +=1;
		this.identifier = counter;
		this.weight = w;
	}
	

	/*
	 * GETTERS AND SETTERS
	 */
	/**
	 * getter of weight
	 * @return weight of the edge
	 */
	public int getWeight() {
		return weight;
	}
	
	/**
	 * setter of weight
	 * @param weight
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	/*
	 * OWN METHODS
	 */
	
	
	
	/*
	 * OVERRIDE METHODS
	 */
	/**
	 * Redefinition of toString : gives the identifier and the weight.
	 */
	@Override
	public String toString() {
		return "Edge n°" + identifier + ". Weight = " + weight;
	}
	

	/*
	 * MAIN FOR TESTING
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TEST 1 : instanciation + toString
		Edge e1 = new In(3);
		System.out.println(e1);
		
		Edge e2 = new In(18);
		System.out.println(e2);
		
		Edge e3 = new In();
		System.out.println(e3);
		
		

	}

}
