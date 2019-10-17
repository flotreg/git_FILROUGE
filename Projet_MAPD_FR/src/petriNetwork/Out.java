package petriNetwork;

/**
 * Inherits abstract class Edge
 * @author Bonjour
 *
 */
public class Out extends Edge {
	
	/*
	 * ATTRIBUTES
	 * -> every attributes inherited from Edge.
	 */ 
	
	/*
	 * CONSTRUCTORS
	 */
	/**
	 * Constructor of Out without parameter
	 *  that calls the constructor of Edge
	 */
	public Out() {
		super();
	}

	/**
	 * Constructor of Out with weight parameter
	 * that calls constructor with parameters of Edge
	 * 
	 * @param weight
	 */
	public Out(int weight) {
		super(weight);
	}

	/**
	 * Constructor of Out with parameters of weight, Place and Transition
	 * that calls constructor of Edge with same parameters
	 */
	public Out(int weight, Place p, Transition t) {
		super(weight, p, t);
	}
	
	/*
	 * GETTERS AND SETTERS
	 */
	
	
	/*
	 * OWN METHODS
	 */
	public void fill() {
		getMyPlace().setTokens(getMyPlace().getTokens()+this.getWeight());
	}
	
	/*
	 * REDEFINITION
	 */
	/**
	 * toString() redefined
	 * adds the type of the Edge.
	 */
	public String toString() {
		return super.toString() + "\n     Type : " + this.getClass().getName();
	}
	

	/*
	 * MAIN FOR TESTING
	 * -> tests in the Edge class
	 */
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
	}

}
