This code makes it possible to use the petrinet graphic interface but with the code we made during the first exercice.

All the tests are available in the class PetriNetAdapter of the package org.pneditor.petrinet.adapters.treguib

This code is composed of :

- the following package which contains the code we made during the first part of the exercice 
	org.pneditor.petrinet.models.treguib.interfaces
	org.pneditor.petrinet.models.treguib.petriNetwork
	org.pneditor.petrinet.models.treguib.exceptions
	org.pneditor.petrinet.models.treguib.exceptions
	org.pneditor.petrinet.models.treguib.edges
	
- the package org.pneditor.petrinet.adapters.treguib which contains all the work we made during this second part of the exercice. It's composed of the following classes :
	ArcAdapter.java 
	PetriNetAdapter.java
	PlaceAdapter.java
	TransitionAdapter.java

	We made all the work together. It was made between the 5th and the 26th of november .
		
-the file grilleAutoEval which contains our auto evaluation.
	Comments :
		We did not write comments for mainteners.
		With the use of github, some tests have bizarre characters. We thinkg it's the encodage of git because of the accents.
		We did not put capital letters for the constants but it was to difficult to change them at the end of the project. 
		Apart from that we think we tick all the good practices.
	
	

BILAN DE L'EXERCICE 

A améliorer
->Il serait intéressant de donner au début de cet exercice des exemples d'utilisations concrets du petrinetwork pour susciter l'intérêt des étudiants
->Il aurait été bien de nous faire appliquer les méthodes d'idl(par exemple faire junit, pmd) pendant le projet. Nous avons par exemple bien executé pmd après avoir eu le 
tp dessus mais il arrive bien trop tard et il détecte des anomalies qui deviennent très dures à modifier en fin de projet. C'est plutot un outil qui s'utilise au fur et à mesure.
->Nous avons utilisé github car nous nous sommes autoformé seuls mais ce n'est pas le cas de tous les groupes. Obliger les étudiants à l'utiliser serait idéal.
->Pas de retour sur notre première version du code, ce qui ne nous permet pas d'évoluer et de corriger nos premières erreurs.
->L'utilisation de stan aurait du etre utilisé sur notre code avant de l'utiliser sur le code du petrinetwork complet car logiciel complexe et on ne s'en ai pas servi finalement. 
->Il faudrait revoir l'enchainement des cours de MAPD et d'IDL. Que les cours aient lieu sur la même période pour pouvoir utiliser ce qui a été appris en IDL dans le projet, et ce dès le début.
C'est le point essentiel de ce retour.


Positif
->L'implémentation d'un objet comme le petrinetwork qui parait extremement simple d'emblée nous fait bien comprendre toute la complexité du développement logiciel.
->Cet exercice permet de bien prendre en main eclipse avec notamment le debugger qui nous a beaucoup servi.
->L'exercice permet de bien comprendre les objets, les types (apparents/réels)
->Les adapters nous ont bien fait comprendre les types de retour et de bien faire la différence entre héritage et composition.
->Les adapters nous ont bien permis de voir comment rentrer sur un projet déjà commencé
->Très intéressant et utile de faire des tests pour bien comprendre le fonctionnement de ses fonction.
->Travail en petite équipe sur un projet. A 2 c'est très bien, à plus ça aurait été compliqué.

Dans l'ensemble c'etait un exercice très intéressant qui nous a permis de bien comprendre comment fonctionne le développement logiciel.
Nous pensons que c'est un très bon exercice pour débuter. 
Nous pensons qu'un exercice tel que le développement d'un jeu pourrait être aussi très intéressant et susciter un grand intérêt chez les étudiants.



ANNEXE : FIRST PART OF THE EXERCICE README

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