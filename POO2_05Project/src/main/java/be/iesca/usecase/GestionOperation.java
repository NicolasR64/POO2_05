package be.iesca.usecase;

import be.iesca.domaine.Bundle;

public interface GestionOperation {
	void lister(Bundle bundle);
	void ajouterOperation(Bundle bundle);
}
