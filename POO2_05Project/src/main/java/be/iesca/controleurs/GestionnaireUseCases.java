package be.iesca.controleurs;

import be.iesca.domaine.Bundle;
import be.iesca.domaine.User;
import be.iesca.usecase.GestionCompteCourant;
import be.iesca.usecase.GestionUsers;
import be.iesca.usecaseimpl.GestionCompteCourantImpl;
import be.iesca.usecaseimpl.GestionUsersImpl;
/**
 * Contrôleur de l'application (couche logique)
 * C'est un singleton.
 * @author Olivier Legrand
 *
 */
public class GestionnaireUseCases implements GestionUsers, GestionCompteCourant {

	private static final GestionnaireUseCases INSTANCE = new GestionnaireUseCases();

	private User user; // null si pas identifié par le système
	private GestionUsers gestionUsers;
	private GestionCompteCourant gestionCompteCourant;

	public static GestionnaireUseCases getInstance() {
		return INSTANCE;
	}

	private GestionnaireUseCases() {
		this.gestionUsers = new GestionUsersImpl();
		this.gestionCompteCourant = new GestionCompteCourantImpl();
		this.user = null; // pas de user connecté
	}

	@Override
	public void connecterUser(Bundle bundle) {
		if (user == null) {
			this.gestionUsers.connecterUser(bundle);
			if ((boolean) bundle.get(Bundle.OPERATION_REUSSIE)) {
				this.user = (User) bundle.get(Bundle.USER);
				bundle.put(Bundle.MESSAGE, "Bienvenue " + user.getNom());
			}
		} else { // un utilisateur est déjà connecté
			bundle.put(Bundle.MESSAGE,
					"Opération impossible. Un utilisateur est déjà connecté.");
			bundle.put(Bundle.OPERATION_REUSSIE, false);
		}
	}

	public void deconnecterUser(Bundle bundle) {
		if (user == null) { // pas de user identifié
			bundle.put(Bundle.MESSAGE,
					"Opération impossible. Pas d'utilisateur connecté.");
			bundle.put(Bundle.OPERATION_REUSSIE, false);
		} else {
			this.user = null; // utilisateur déconnecté
			bundle.put(Bundle.MESSAGE, "Vous avez été déconnecté.");
			bundle.put(Bundle.OPERATION_REUSSIE, true);
		}
	}

	@Override
	public void lister(Bundle bundle) {
		if (user == null) { // pas de user identifié
			bundle.put(Bundle.MESSAGE,
					"Opération impossible. Pas d'utilisateur connecté.");
			bundle.put(Bundle.OPERATION_REUSSIE, false);
		} else {
			this.gestionCompteCourant.lister(bundle);
		}
	}
	
	@Override
	public void modifierCompteCourant(Bundle bundle) {
		if (user == null) { // pas de user identifié
			bundle.put(Bundle.MESSAGE,
					"Opération impossible. Pas d'utilisateur connecté.");
			bundle.put(Bundle.OPERATION_REUSSIE, false);
		} else {
			this.gestionCompteCourant.modifierCompteCourant(bundle);
		}
	}

}
