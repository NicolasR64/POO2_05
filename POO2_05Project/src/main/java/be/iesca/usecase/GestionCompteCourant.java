package be.iesca.usecase;

import be.iesca.domaine.Bundle;

public interface GestionCompteCourant {
	void modifierCompteCourant(Bundle bundle);
	void lister(Bundle bundle);
}
