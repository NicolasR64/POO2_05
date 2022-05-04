package be.iesca.usecaseimpl;

import java.util.List;

import be.iesca.dao.BiereDao;
import be.iesca.daoimpl.DaoFactory;
import be.iesca.domaine.Bundle;
import be.iesca.domaine.Biere;
import be.iesca.usecase.GestionCompteCourant;

//TO DO
public class GestionCompteCourantImpl implements GestionCompteCourant {
	private BiereDao biereDao;

	public GestionCompteCourantImpl() {
		this.biereDao = (BiereDao) DaoFactory.getInstance().getDaoImpl(BiereDao.class);
	}

	@Override
	public void rechercherBiere(Bundle bundle) {
		boolean operationReussie = false;
		String nom = (String) bundle.get(Bundle.NOM);
		Biere biere = this.biereDao.getBiere(nom);
		String message = "";
		if (biere == null) {
			message = "Biere inconnue";
		} else {
			operationReussie = true;
		}
		bundle.put(Bundle.NOM, nom);
		bundle.put(Bundle.OPERATION_REUSSIE, operationReussie);
		bundle.put(Bundle.BIERE, biere);
		bundle.put(Bundle.MESSAGE, message);
	}

	@Override
	public void ajouterBiere(Bundle bundle) {
		boolean ajoutReussi = false;
		String message = "";
		Biere biere = (Biere) bundle.get(Bundle.BIERE);
		if (biere.getNom() == null || biere.getNom().isEmpty()) {
			message = "L'ajout n'a pas pu être réalisé. Il manque le nom de la bière";
		} else if (biere.getBrasserie() == null
				|| biere.getBrasserie().isEmpty()) {
			message = "L'ajout n'a pas pu être réalisé. Il manque le nom de la brasserie";
		} else if (biere.getCouleur() == null || biere.getCouleur().isEmpty()) {
			message = "L'ajout n'a pas pu être réalisé. Il manque la couleur";
		} else if (biere.getType() == null || biere.getType().isEmpty()) {
			message = "L'ajout n'a pas pu être réalisé. Il manque le type";
		} else {
			Biere biereDB = this.biereDao.getBiere(biere.getNom());
			if (biereDB != null) {
				message = "Cette biere existe déjà. Impossible de l'ajouter";
			} else {
				ajoutReussi = this.biereDao.ajouterBiere(biere);
				if (ajoutReussi) {
					message = "Ajout réalisé avec succès";
				} else {
					message = "L'ajout n'a pas pu être réalisé";
				}
			}
		}
		bundle.put(Bundle.OPERATION_REUSSIE, ajoutReussi);
		bundle.put(Bundle.BIERE, biere);
		bundle.put(Bundle.MESSAGE, message);
	}

	@Override
	public void lister(Bundle bundle) {
		boolean listeOk = true;
		String message = "";
		List<Biere> listeBieres = null;
		listeBieres = this.biereDao.listerBieres();
		if (listeBieres==null) {
			listeOk = false;
		} else if (listeBieres.isEmpty())
			message = "Liste vide";
		else if (listeBieres.size() == 1)
			message = "Il y a 1 bière";
		else
			message = "Il y a " + listeBieres.size() + " bières";
		bundle.put(Bundle.OPERATION_REUSSIE, listeOk);
		bundle.put(Bundle.MESSAGE, message);
		bundle.put(Bundle.LISTE, listeBieres);
	}

	@Override
	public void supprimerBiere(Bundle bundle) {
		String nom = (String) bundle.get(Bundle.NOM);
		String message = "";
		boolean suppressionReussie = false;
		suppressionReussie = this.biereDao.supprimerBiere(nom);
		if (suppressionReussie)
			message = "Suppresion réalisée avec succès";
		else
			message = "Echec suppression";
		bundle.put(Bundle.OPERATION_REUSSIE, suppressionReussie);
		bundle.put(Bundle.MESSAGE, message);
	}

	@Override
	public void modifierBiere(Bundle bundle) {
		boolean modificationReussie = false;
		String message = "";
		Biere biere = (Biere) bundle.get(Bundle.BIERE);
		if (biere.getNom() == null || biere.getNom().isEmpty()) {
			message = "La modification n'a pas pu être réalisée. Il manque le nom de la bière";
		} else if (biere.getBrasserie() == null
				|| biere.getBrasserie().isEmpty()) {
			message = "La modification n'a pas pu être réalisée. Il manque le nom de la brasserie";
		} else if (biere.getCouleur() == null || biere.getCouleur().isEmpty()) {
			message = "La modification n'a pas pu être réalisée. Il manque la couleur";
		} else if (biere.getType() == null || biere.getType().isEmpty()) {
			message = "La modification n'a pas pu être réalisée. Il manque le type";

		} else {
			Biere biereDB = this.biereDao.getBiere(biere.getNom());
			if (biereDB == null) {
				message = "La modification n'a pas pu être réalisée. Cette bière n'existe pas";
			} else {
				modificationReussie = this.biereDao.modifierBiere(biere);
				if (modificationReussie) {
					message = "Modification réalisée avec succès";
				} else {
					message = "La modification n'a pas pu être réalisée";
				}
			}
		}
		bundle.put(Bundle.OPERATION_REUSSIE, modificationReussie);
		bundle.put(Bundle.MESSAGE, message);
	}
}
