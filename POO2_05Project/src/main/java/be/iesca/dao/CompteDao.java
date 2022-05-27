package be.iesca.dao;

import be.iesca.domaine.CompteCourant;

public interface CompteDao extends Dao {
	
	boolean modifierCompte(CompteCourant compte);
	boolean modifierCompteVirement(CompteCourant compte);
	CompteCourant getCompte(int id);
	CompteCourant getCompteByNumero(String numero);
}
