package interfaces;

import exceptions.AddEdgeException;

/**
 * This interface makes it possible to add edges. 
 * @author Bonjour
 * @version 1
 * @since 17/10/2019
 *
 */
public interface AddEdge {
	
	/**
	 * This method adds an edge between the caller (this) and a destination (dest)
	 * @param dest : the destination of the new edge
	 * @param inOut (boolean) : type of the edge, In if true, Out if false 
	 * @param weight : the weight of the edge
	 * @throws AddEdgeException
	 */
	public void addEdge(AddEdge dest, boolean inOut, int weight) throws AddEdgeException;


}
