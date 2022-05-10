package be.iesca.dao;

import java.util.List;

import be.iesca.domaine.CompteCourant;

public interface CompteDao extends Dao {
	
	boolean modifierCompte(CompteCourant compte);
	List<CompteCourant> listerCompte();
	CompteCourant getCompte(String numero);
}
