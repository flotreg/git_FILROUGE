package interfaces;

import exceptions.AddEdgeException;
import edges.EdgeTypes;

/**
 * This interface makes it possible to add edges. 
 * @author Bonjour
 * @version 1
 * @since 17/10/2019
 *
 */
public interface Edgeable {
	
	/**
	 * This method adds an edge between the caller (this) and a destination (dest)
	 * @param dest : the destination of the new edge
	 * @param inOut (boolean) : type of the edge, In if true, Out if false 
	 * @param weight : the weight of the edge
	 * @throws AddEdgeException
	 */
	public void addEdge(Edgeable dest, EdgeTypes e, int weight) throws AddEdgeException;
	
	/**
	 * This method removes an edge based on its identifier
	 * @param identifier
	 */
	public void deleteEdge(int identifier);


}
