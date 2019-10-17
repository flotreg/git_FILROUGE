/**
 * 
 */
package petriNetwork;

/**
 * In extends Edge. It represents edges that start from place and end to
 * transition.
 * 
 * @author Bonjour
 * @version 1
 * @since 17/10/2019
 */
public class In extends Edge {

	/*
	 * ATTRIBUTES -> every attributes are inherited from Edge.
	 */

	/*
	 * CONSTRUCTORS
	 */
	/**
	 * Constructor of In without parameter that calls the constructor of Edge
	 */
	public In() {
		super();
	}

	/**
	 * Constructor of In with weight parameter that calls constructor with
	 * parameters of Edge
	 * 
	 * @param weight
	 */
	public In(int weight) {
		super(weight);
	}

	/**
	 * Constructor of In with parameters of weight, Place and Transition that calls
	 * constructor of Edge with same parameters
	 */
	public In(int weight, Place p, Transition t) {
		super(weight, p, t);
	}

	/*
	 * GETTERS AND SETTERS
	 */

	/*
	 * OWN METHODS
	 */

	/**
	 * Take the tokens from the start place
	 */
	public void empty() {
		if(this.activable()) {
			// intermediate variables for lisibility
			int tokens = this.getMyPlace().getTokens();
			int weight = this.getWeight();
			this.getMyPlace().setTokens(tokens - weight);
		}
	}

	/**
	 * Check that there is enough tokens in the start place This is used by the
	 * transition to fire the petri network
	 */
	public boolean activable() {
		// intermediate variables for lisibility
		int tokens = this.getMyPlace().getTokens();
		int weight = this.getWeight();
		return (weight <= tokens) ? true : false;
	}

	/*
	 * REDEFINITION
	 */
	/**
	 * toString() redefined adds the type of the Edge.
	 */
	public String toString() {
		return super.toString() + "\n     Type : IN";
	}

	/*
	 * MAIN FOR TESTING
	 * -> tests in the Edge class
	 */

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}

}
