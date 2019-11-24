package org.pneditor.petrinet;

import java.util.HashSet;
import java.util.Set;

import logger.PNEditorLogger;

public abstract class PetriNetInterface {

	/*
	 * Stocking the elements of the PetriNet, used for the Boundedness algorithm.
	 * The id is used for saving petriNets
	 */
	private final Set<AbstractPlace> places = new HashSet<>();
	private final Set<AbstractTransition> transitions = new HashSet<>();
	private final Set<AbstractArc> arcs = new HashSet<>();
	private int nextId = 1;

	/*
	 * Interface that adapters must implement (+ the element specific methods)
	 */

	public abstract AbstractPlace addPlace();

	public abstract AbstractTransition addTransition();

	public abstract AbstractArc addRegularArc(AbstractNode source, AbstractNode destination)
			throws UnimplementedCaseException;

	public abstract AbstractArc addInhibitoryArc(AbstractPlace place, AbstractTransition transition)
			throws UnimplementedCaseException;

	public abstract AbstractArc addResetArc(AbstractPlace place, AbstractTransition transition)
			throws UnimplementedCaseException;

	public abstract void removePlace(AbstractPlace place);

	public abstract void removeTransition(AbstractTransition transition);

	public abstract void removeArc(AbstractArc arc);

	public abstract boolean isEnabled(AbstractTransition transition) throws ResetArcMultiplicityException;

	public abstract void fire(AbstractTransition transition) throws ResetArcMultiplicityException;

	/*
	 * Methods used by the software, not defined by the models. It mostly concerns
	 * stocking the elements, and adding the ID
	 */
	// DONE
	public final AbstractPlace addAbstractPlace() { // template pattern
		final AbstractPlace place = addPlace();
		this.places.add(place);
		place.setId(this.nextId++);
		return place;
	}
	
	// NOT DONE : is it useful to test ?
	public final AbstractPlace addAbstractPlace(final int id) { // template pattern
		final AbstractPlace place = addPlace();
		this.places.add(place);
		place.setId(id);
		if (id >= this.nextId) {
			this.nextId = id + 1;
		}
		return place;
	}

	// DONE
	public final AbstractTransition addAbstractTransition() { // template pattern
		final AbstractTransition transition = addTransition();
		this.transitions.add(transition);
		transition.setId(this.nextId++);
		return transition;
	}

	// NOT DONE : is it useful to test ?
	public final AbstractTransition addAbstractTransition(final int id) { // template pattern
		final AbstractTransition transition = addTransition();
		this.transitions.add(transition);
		transition.setId(id);
		if (id >= this.nextId) {
			this.nextId = id + 1;
		}
		return transition;
	}

	// DONE 
	public final AbstractArc addArcAgain(final AbstractArc arc, final AbstractNode source, final AbstractNode destination) {
		AbstractArc newArc = null;
		try {
			if (arc.isReset()) {
				newArc = addResArc((AbstractPlace) source, (AbstractTransition) destination);
			} else {
				if (arc.isInhibitory()) {
					newArc = addInhibArc((AbstractPlace) source, (AbstractTransition) destination);
				} else {
					newArc = addRegArc(source, destination);
				}

				newArc.setMultiplicity(arc.getMultiplicity());
			}
		} catch (ResetArcMultiplicityException e) {
			// should not happen
			PNEditorLogger.severeLogs(e.getMessage());
		} catch (UnimplementedCaseException e) {
			// should not happen since we're manipulating new objects, except if a behavior
			// is not implemented
			PNEditorLogger.warningLogs(e.getMessage());
		}
		return newArc;
	}

	// DONE
	public final AbstractArc addRegArc(final AbstractNode source, final AbstractNode destination)
			throws UnimplementedCaseException {
		final AbstractArc arc = addRegularArc(source, destination);
		this.arcs.add(arc);
		return arc;
	}

	// DONE
	public final AbstractArc addInhibArc(final AbstractPlace place, final AbstractTransition transition)
			throws UnimplementedCaseException {
		final AbstractArc arc = addInhibitoryArc(place, transition);
		this.arcs.add(arc);
		return arc;
	}

	// DONE
	public final AbstractArc addResArc(final AbstractPlace place, final AbstractTransition transition)
			throws UnimplementedCaseException {
		final AbstractArc arc = addResetArc(place, transition);
		this.arcs.add(arc);
		return arc;
	}

	// DONE
	public final void removeAbstractPlace(final AbstractPlace place) {
		removePlace(place);
		this.places.remove(place);
	}

	public final void removeAbstractTransition(final AbstractTransition transition) {
		removeTransition(transition);
		this.transitions.remove(transition);
	}

	// DONE
	public final void removeAbstractArc(final AbstractArc arc) {
		removeArc(arc);
		this.arcs.remove(arc);
	}

	public final boolean isBounded() throws ResetArcMultiplicityException {
		return new Boundedness(this).isBounded();
	}

	// TODO
	public final AbstractPlace clonePlace(final AbstractPlace place) {
		final AbstractPlace placeClone = addAbstractPlace();
		placeClone.setLabel(place.getLabel());
		placeClone.setTokens(place.getTokens());
		return placeClone;
	}

	// TODO
	public final AbstractTransition cloneTransition(final AbstractTransition transition) {
		final AbstractTransition transitionClone = addAbstractTransition();
		transitionClone.setLabel(transition.getLabel());
		return transitionClone;
	}

	// TODO
	public final AbstractArc cloneArc(final AbstractArc arc, final AbstractNode source, final AbstractNode destination) {
		AbstractArc newArc = null;
		try {
			if (arc.isReset()) {
				newArc = addResArc((AbstractPlace) source, (AbstractTransition) destination);
			} else {
				if (arc.isInhibitory()) {
					newArc = addInhibArc((AbstractPlace) source, (AbstractTransition) destination);
				} else {
					newArc = addRegArc(source, destination);
				}
					newArc.setMultiplicity(arc.getMultiplicity());
			}
		} catch (UnimplementedCaseException | ResetArcMultiplicityException e) {
			// should never happen because source and destination are new objects
			// and ResetArcMultiplicity sould not happen as well
			PNEditorLogger.severeLogs(e.getMessage());
		}
		return newArc;
	}

	/*
	 * Methods for the Boundedness algorithm
	 * 
	 */

	// DONE
	public Set<AbstractPlace> getPlaces() {
		return this.places;
	}

	// DONE
	public Set<AbstractTransition> getTransitions() {
		return this.transitions;
	}

	// DONE
	public Set<AbstractArc> getConnectedArcs(final AbstractTransition transition) {
		final Set<AbstractArc> connectedArcs = new HashSet<>();
		for (final AbstractArc arc : this.arcs) {
			if (arc.getSource().equals(transition) || arc.getDestination().equals(transition)) {
				connectedArcs.add(arc);
			}
		}
		return connectedArcs;
	}

}
