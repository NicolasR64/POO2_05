package be.iesca.dao;


import java.util.List;

import be.iesca.domaine.Biere;

public interface BiereDao extends Dao {
	boolean ajouterBiere(Biere Biere);
	Biere getBiere(String nom);
	List<Biere> listerBieres();
	boolean supprimerBiere(String nom);
	boolean modifierBiere(Biere Biere);
}
