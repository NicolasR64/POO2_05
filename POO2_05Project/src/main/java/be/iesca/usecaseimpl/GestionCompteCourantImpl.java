package be.iesca.usecaseimpl;

<<<<<<< HEAD
import java.util.List;

import be.iesca.daoimpl.DaoFactory;
=======
>>>>>>> d76a856a3da1bc6bc2564c36635fbfb34318a964
import be.iesca.domaine.Bundle;
import be.iesca.usecase.GestionCompteCourant;

public class GestionCompteCourantImpl implements GestionCompteCourant {

<<<<<<< HEAD
	private compteDAO CompteDao;
=======
	@Override
	public void modifierCompteCourant(Bundle bundle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void lister(Bundle bundle) {
		// TODO Auto-generated method stub
		
	}
	/*private ComtpeCourantDAO compteCourantDao;
>>>>>>> d76a856a3da1bc6bc2564c36635fbfb34318a964

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
