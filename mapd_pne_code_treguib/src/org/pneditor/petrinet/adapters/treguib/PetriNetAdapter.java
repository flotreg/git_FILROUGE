/**
 * 
 */
package org.pneditor.petrinet.adapters.treguib;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.AbstractPlace;
import org.pneditor.petrinet.AbstractTransition;
import org.pneditor.petrinet.PetriNetInterface;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.UnimplementedCaseException;
import org.pneditor.petrinet.models.treguib.petriNetwork.PetriNetwork;

/**
 * @author f18guibo
 *
 */
public class PetriNetAdapter extends PetriNetInterface {
	
	PlaceAdapter pa = null;

	@Override
	public AbstractPlace addPlace() {
		this.pa = new PlaceAdapter("");
		return pa;
	}

	@Override
	public AbstractTransition addTransition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractArc addRegularArc(AbstractNode source, AbstractNode destination) throws UnimplementedCaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractArc addInhibitoryArc(AbstractPlace place, AbstractTransition transition)
			throws UnimplementedCaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractArc addResetArc(AbstractPlace place, AbstractTransition transition)
			throws UnimplementedCaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removePlace(AbstractPlace place) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeTransition(AbstractTransition transition) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeArc(AbstractArc arc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEnabled(AbstractTransition transition) throws ResetArcMultiplicityException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void fire(AbstractTransition transition) throws ResetArcMultiplicityException {
		// TODO Auto-generated method stub
		
	}
	
	
	public static void main(String[] args) {
		PetriNetAdapter p = new PetriNetAdapter();
		p.addPlace();
		System.out.println(p);
		p.pa.setTokens(5);
		System.out.println(p.pa.getTokens());
		
	}

}
