package be.iesca.usecaseimpl;

import java.util.List;

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
}
