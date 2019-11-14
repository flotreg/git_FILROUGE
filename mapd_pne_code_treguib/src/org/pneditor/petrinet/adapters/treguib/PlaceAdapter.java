package org.pneditor.petrinet.adapters.treguib;

import org.pneditor.petrinet.AbstractPlace;
import org.pneditor.petrinet.models.treguib.petriNetwork.Place;

/**
 * Adapter for the Place object.
 * Interacts with AbstractPlace in PNE code
 * Mainly linked to Place in our code
 * @author f18guibo
 *
 */
public class PlaceAdapter extends AbstractPlace {

	/*
	 * ATTRIBUTES
	 */
	protected Place ourPlace; 

	/*
	 * CONSTRUCTOR
	 */
	/**
	 * Constructor with Label.
	 * ID is already defined in PNE code. 
	 * @param label
	 */
	public PlaceAdapter(String label) {
		super(label);
		this.ourPlace = new Place();
	}
	/*
	 * OVERRIDED METHODS
	 */
	/**
	 * Add one token to the Place
	 */
	@Override
	public void addToken() {
		int myTokens = this.ourPlace.getTokens();
		this.ourPlace.setTokens(myTokens + 1);
	}

	/**
	 * Remove one token from the Place
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
	 * sets the number of tokens using our place
	 * @param tokens
	 */
	@Override
	public void setTokens(int tokens) {
		this.ourPlace.setTokens(tokens);
	}

	
}
