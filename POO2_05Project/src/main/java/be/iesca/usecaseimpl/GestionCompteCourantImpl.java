package be.iesca.usecaseimpl;

import java.util.List;

import be.iesca.dao.BiereDao;
import be.iesca.daoimpl.DaoFactory;
import be.iesca.domaine.Bundle;
import be.iesca.domaine.CompteCourant;
import be.iesca.usecase.GestionCompteCourant;

//TO DO
public class GestionCompteCourantImpl implements GestionCompteCourant {
	/*private ComtpeCourantDAO compteCourantDao;

	public GestionCompteCourantImpl() {
		this.ComtpeCourantDAO = (compteCourantDao) DaoFactory.getInstance().getDaoImpl(CompteCourantDao.class);
	}

	@Override
	public void lister(Bundle bundle) {
		boolean listeOk = true;
		String message = "";
		List<CompteCourant> listeCompteCourant = null;
		listeCompteCourant = this.ComtpeCourantDAO.listerCompteCourant();
		if (ComtpeCourantDAO==null) {
			listeOk = false;
		} else if (ComtpeCourantDAO.isEmpty())
			message = "Liste vide";
		else if (ComtpeCourantDAO.size() == 1)
			message = "Il y a 1 bière";
		else
			message = "Il y a " + ComtpeCourantDAO.size() + " bières";
		bundle.put(Bundle.OPERATION_REUSSIE, listeOk);
		bundle.put(Bundle.MESSAGE, message);
		bundle.put(Bundle.LISTE, ComtpeCourantDAO);
	}

	@Override
	public void modifierComtpeCourantDAO(Bundle bundle) {
		boolean modificationReussie = false;
		String message = "";
		ComtpeCourant comtpeCourant = (ComtpeCourant) bundle.get(Bundle.COMPTECOURANT);
		if (comtpeCourant.getNom() == null || comtpeCourant.getNom().isEmpty()) {
			message = "La modification n'a pas pu être réalisée. Il manque le nom du compte";
		} else if (comtpeCourant.getBrasserie() == null
				|| comtpeCourant.getBrasserie().isEmpty()) {
			message = "La modification n'a pas pu être réalisée. Il manque le nom de la brasserie";
		} else if (comtpeCourant.getCouleur() == null || comtpeCourant.getCouleur().isEmpty()) {
			message = "La modification n'a pas pu être réalisée. Il manque la couleur";
		} else if (comtpeCourant.getType() == null || comtpeCourant.getType().isEmpty()) {
			message = "La modification n'a pas pu être réalisée. Il manque le type";

		} else {
			Biere biereDB = this.ComtpeCourantDAO.getBiere(comtpeCourant.getNom());
			if (biereDB == null) {
				message = "La modification n'a pas pu être réalisée. Cette bière n'existe pas";
			} else {
				modificationReussie = this.ComtpeCourantDAO.modifierBiere(comtpeCourant);
				if (modificationReussie) {
					message = "Modification réalisée avec succès";
				} else {
					message = "La modification n'a pas pu être réalisée";
				}
			}
		}
		bundle.put(Bundle.OPERATION_REUSSIE, modificationReussie);
		bundle.put(Bundle.MESSAGE, message);
	}*/
}
