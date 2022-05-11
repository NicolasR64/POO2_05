package be.iesca.dao;

import java.util.List;

import be.iesca.domaine.CompteCourant;

public interface CompteDao extends Dao {
	
	boolean modifierCompte(CompteCourant compte);
	CompteCourant getCompte(String numero);
}
