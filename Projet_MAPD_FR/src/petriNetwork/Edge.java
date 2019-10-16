/**
 * 
 */
package petriNetwork;

/**
 * This class create a weight
 * @author Bonjour
 *
 */
public abstract class Edge {
	
	private int weight;
	

	/*
	 * Constructors of Edge
	 */
	public Edge() {
		this.weight = 0;
	}
	
	public Edge(int w) {
		this.weight = w;
	}
	/**
	 * Redefinition of toString
	 */
	public String toString() {
		return "I'm a edge and my weight is "+weight;
	}
	
	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Instanciation of one edge
		
		

	}

}
