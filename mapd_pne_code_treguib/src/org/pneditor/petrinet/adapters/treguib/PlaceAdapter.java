/**
 * 
 */
package org.pneditor.petrinet.adapters.treguib;

import org.pneditor.petrinet.AbstractPlace;
import org.pneditor.petrinet.models.treguib.petriNetwork.Place;

/**
 * @author f18guibo
 *
 */
public class PlaceAdapter extends AbstractPlace {

	private Place ourPlace = null; 

	
	public PlaceAdapter(String label) {
		super(label);
		this.ourPlace = new Place();
	}
	/**
	 * Add one token
	 */
	@Override
	public void addToken() {
		int myTokens = this.ourPlace.getTokens();
		this.ourPlace.setTokens(myTokens + 1);
	}

	/**
	 * Remove one token
	 */
	@Override
	public void removeToken() {
		int myTokens = this.ourPlace.getTokens();
		// check that there are tokens
		if(myTokens > 0) this.ourPlace.setTokens(myTokens - 1);
	}

	/**
	 * returns the number of tokens using our place
	 * @return tokens
	 */
	@Override
	public int getTokens() {
		return ourPlace.getTokens();
	}

	/**
	 * set the number of tokens using our place
	 * @param tokens
	 */
	@Override
	public void setTokens(int tokens) {
		this.ourPlace.setTokens(tokens);
	}

	
}
