package be.iesca.usecase;

import be.iesca.domaine.Bundle;

public interface GestionCompteCourant {
	void getCompte(Bundle bundle);
	void modifierCompteCourant(Bundle bundle);
	void getCompteByNumero(Bundle bundle, String numero);
}