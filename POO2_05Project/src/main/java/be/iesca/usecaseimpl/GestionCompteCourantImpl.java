package be.iesca.usecaseimpl;

import java.util.List;

import be.iesca.daoimpl.DaoFactory;
import be.iesca.domaine.Bundle;
import be.iesca.usecase.GestionCompteCourant;

public class GestionCompteCourantImpl implements GestionCompteCourant {


	private compteDAO CompteDao;

	private ComtpeCourantDAO compteCourantDao;
	
	public GestionCompteCourantImpl() {
		this.compteDAO = (CompteDao) DaoFactory.getInstance().getDaoImpl(CompteDao.class);
	}

	public void getCompte(Bundle bundle) {
		boolean CompteOk = true;
		String message = "";
		CompteCourant compteCourant = null;
		compteCourant = this.CompteDAO.getCompte();
		if (CompteDAO==null) {
			listeOk = false;
		} else if (CompteDAO.isEmpty())
			message = "Aucun compte";
		else if (CompteDao.size() == 1)
			message = "Compte trouvé";
		bundle.put(Bundle.OPERATION_REUSSIE, CompteOk);
		bundle.put(Bundle.MESSAGE, message);
		bundle.put(Bundle.LISTE, CompteDao);
	}
}
