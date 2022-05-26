package be.iesca.dao;

import be.iesca.domaine.CompteCourant;

public interface CompteDao extends Dao {
	
	boolean modifierCompte(CompteCourant compte);
	CompteCourant getCompte(int numero);
}
