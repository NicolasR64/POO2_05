package be.iesca.usecase;

import be.iesca.domaine.Bundle;

public interface GestionCompteCourant {
	void rechercherCompteCourant(Bundle bundle);
	void lister(Bundle bundle);
}
