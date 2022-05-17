package be.iesca.usecaseimpl;

import be.iesca.dao.CompteDao;
import be.iesca.daoimpl.DaoFactory;
import be.iesca.domaine.Bundle;
import be.iesca.domaine.CompteCourant;
import be.iesca.usecase.GestionCompteCourant;

public class GestionCompteCourantImpl implements GestionCompteCourant {


	private CompteDao compteDao;
	
	public GestionCompteCourantImpl() {
		this.compteDao = (CompteDao) DaoFactory.getInstance().getDaoImpl(CompteDao.class);
	}

	public void getCompte(Bundle bundle, String numero) {
		boolean CompteOk = true;
		String message = "";
		CompteCourant compteCourant = null;
		
		//TO DO, trouvé le nom du compte
		compteCourant = this.compteDao.getCompte(numero);
		if (compteDao==null) {
			CompteOk = false;
		}
		bundle.put(Bundle.OPERATION_REUSSIE, CompteOk);
		bundle.put(Bundle.MESSAGE, message);
		bundle.put(Bundle.LISTE, compteDao);
	}

	@Override
	public void modifierCompteCourant(Bundle bundle) {
		boolean modificationReussie = false;
		String message = "";
		CompteCourant compte= (CompteCourant) bundle.get(Bundle.COMPTECOURANT);
		
		if(compte.getNumero() == null || compte.getNumero().isEmpty()) {
			message = "Impossible d'effectuer une modification, le numero est manquant";
		}else if(compte.getIsCloture() == null || compte.getIsCloture().isEmpty()) {
			message = "Impossible d'effectuer une modification, la valeur de cloture est manquante";
		}else{
			CompteCourant compteDB = this.compteDao.getCompte(compte.getNumero());
			if(compteDB == null) {
				message = "La modification n'a pas pu etre realise, le compte n'existe pas";
			}else {
				modificationReussie = this.compteDao.modifierCompte(compte);
				if(modificationReussie) {
					message = "Modification du compte reussie";
				}else {
					message = "La modification n'a pas pu etre faite";
				}
			}
		}
		bundle.put(Bundle.OPERATION_REUSSIE, modificationReussie);
		bundle.put(Bundle.MESSAGE, message);
		
	}
}
