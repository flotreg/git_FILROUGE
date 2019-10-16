/**
 * 
 */
package interfaces;

import exceptions.AddEdgeException;

/**
 * This interface permit to add edges
<<<<<<< HEAD
 * @author Bonjour
=======
 * @author Bonjour & François & Florian
>>>>>>> branch 'master' of https://github.com/flotreg/fil_rouge.git
 *
 */
public interface AddEdge {
	/**
	 * This method add an edges between a this and a destination
	 * @param dest
	 * @param inOut
	 * @param weight
	 */
	public void addEdge(AddEdge dest, boolean inOut, int weight) throws AddEdgeException;


}
