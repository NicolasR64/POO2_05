package be.iesca.dao;

import java.util.List;

import be.iesca.domaine.CompteCourant;

public interface CompteDao extends Dao {
	
	boolean modifierCompte(Compte compte);
	List<Compte> listerCompte();
	Compte getCompte(String numero);
}
