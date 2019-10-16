/**
 * 
 */
package petriNetwork;

/**
 * @author Bonjour
 *
 */
public class In extends Edge{
	/*
	 * constructor of In that call the constructor of Edge
	 */
	public In() {
		super();
	}
	/**
	 * Constructor of In with parameters that call constuctor with parameters of Edge
	 * @param weight
	 */
	public In(int weight) {
		super(weight);
	}
	/**
	 * Redefinition of the toString method
	 */
	public String toString() {
		return "I'm an edge In and my weight is : "+this.getWeight();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Test instanciation of in
		In in = new In(1);
		System.out.println(in);
		

	}

}
