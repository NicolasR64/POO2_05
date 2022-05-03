package be.iesca.usecase;

import be.iesca.domaine.Bundle;

public interface GestionBieres {
	void ajouterBiere(Bundle bundle);
	void rechercherBiere(Bundle bundle);
	void lister(Bundle bundle);
	void supprimerBiere(Bundle bundle);
	void modifierBiere(Bundle bundle);
}
