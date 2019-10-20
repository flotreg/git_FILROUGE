This application makes it possible to build and fire Petri Network. 

Use the Main.java to run the application. 

BUILDING THE PETRI NETWORK

In order to build a Petri Network, use the static method getInstance() of the PetriNetwork class to get the singleton.
Then, use this concrete object to build places, transitions and edges.
Each place, transition and edge have an identifier (number) when they are created.
You can remove an object based on its identifier. 
	

	BUILDING PLACES : buildPlace(tokens)
	In order to build places, please use the buildPlace(tokens) with the number of tokens as the parameter.  
		
	BUILDING TRANSITIONS : buildTransition()
	In order to build transitions, please use the buildTransition().
	
	BUILDING EDGES : buildEdges(EdgeType, Place, Transition, weight)
	In order to build edges, you must have at least one place and one transition as they are required parameters. 
	As for the edge types, there are 4 possibilities : 
	- RegularIn : takes some tokens from the starting place based on the weight of the edge. Starting place must have more tokens than the weight to be activated.
	- RegularOut : put some tokens in the ending place based on the weight of the edge.
	- ZeroIn : same as Regular In, but it is activated only if there are no token in the starting place.
	- EmptierIn : same as Regular In, but it takes out all the tokens from the starting place. 
	
	DELETE PLACE : deletePlace(identifier)
	This will remove the Place from the Petri Network. Also, all the edges linked to this place will be deleted as well. 
	
	DELETE TRANSITION : deleteTransition(identifier)
	This will remove the Transition from the Petri Network. Also, all the edges linked to this place will be deleted as well. 
	
	DELETE EDGE : deleteEdge(identifier)
	This will remove the Edge from the Petri Network. 
	
	

FIRING THE PETRI NETWORK

There are two ways to launch the petri network. Either you fire one transition, either you fire all the transitions. 

	FIRING ONE TRANSITION : step(transition)
	This will fire the transition, if it is fireable. If so, all the edges make their token operations with their linking places. 
	
	FIRING ALL TRANSITIONS : stepUntilStop()
	This will fire all the transitions recursively. The program will stop once there are no more transitions fireable. 