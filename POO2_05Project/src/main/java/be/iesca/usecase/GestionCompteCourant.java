package be.iesca.usecase;

import be.iesca.domaine.Bundle;

public interface GestionCompteCourant {
	void modifierCompteCourant(Bundle bundle);
	void modifierCompteVirement(Bundle bundle);
	void getCompteByNumero(Bundle bundle);
	void getCompte(Bundle bundle);
}