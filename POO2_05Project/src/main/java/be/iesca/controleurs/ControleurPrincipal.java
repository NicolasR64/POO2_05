package be.iesca.controleurs;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import be.iesca.domaine.Biere;
import be.iesca.domaine.Bundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControleurPrincipal implements Initializable {

	private static ControleurPrincipal singleton;
	private GestionnaireUseCases gestionnaire = GestionnaireUseCases.getInstance();
	private Bundle bundle = new Bundle();

	public static ControleurPrincipal getInstance() {
		return singleton;
	}

	@FXML
	private TextField tfNom, tfType, tfBrasserie, tfCouleur, tfMessage;
	@FXML
	private Button btAjouter, btSupprimer, btModifier, btLister, btRechercher, btConnecter;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tfMessage.setText("Veuillez vous connecter");
		if (singleton == null)
			singleton = this; // on mémorise afin d'y accéder par la suite
	}


	public void trtBoutonConnecter() {
		if(this.btConnecter.getText().equals("Connecter")) {
				Parent root;
			try {
				tfMessage.setText("");
				root = FXMLLoader.load(getClass().getResource("/be/iesca/vues/VueLogin.fxml"));
				Stage stage = new Stage();
				stage.setTitle("Connection");
				stage.getIcons().add(new Image("/be/iesca/application/biere.jpg"));
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setScene(new Scene(root));
				stage.setResizable(false);
				stage.showAndWait();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			this.gestionnaire.deconnecterUser(bundle);
			this.btConnecter.setText("Connecter");
			tfType.setText("");
			tfCouleur.setText("");
			tfBrasserie.setText("");
			tfNom.setText("");
			this.btAjouter.setDisable(true);
			this.btLister.setDisable(true);
			this.btModifier.setDisable(true);
			this.btRechercher.setDisable(true);
			this.btSupprimer.setDisable(true);
			this.majMessage();
		}
		
	}
	
	public void ajouter() {
		garnirBundle();
		this.gestionnaire.ajouterBiere(bundle);
		majMessage();
	}

	private void garnirBundle() {
		String nom = tfNom.getText().trim();
		String type = tfType.getText().trim();
		String couleur = tfCouleur.getText().trim();
		String brasserie = tfBrasserie.getText().trim();
		Biere biere = new Biere(nom, type, couleur, brasserie);
		bundle.put(Bundle.BIERE, biere);
	}

	private void majMessage() {
		String message = (String) bundle.get(Bundle.MESSAGE);
		this.tfMessage.setText(message);
	}

	public void modifier() {
		garnirBundle();
		this.gestionnaire.modifierBiere(bundle);
		majMessage();
	}

	public void supprimer() {
		String nom = tfNom.getText().trim();
		this.bundle.put(Bundle.NOM, nom);
		this.gestionnaire.supprimerBiere(bundle);
		majAffichageBiere();
		majMessage();

	}

	public void rechercher() {
		String nom = tfNom.getText().trim();
		this.bundle.put(Bundle.NOM, nom);
		this.gestionnaire.rechercherBiere(bundle);
		majAffichageBiere();
		majMessage();
	}

	private void majAffichageBiere() {
		boolean reussite = (boolean) bundle.get(Bundle.OPERATION_REUSSIE);
		if (reussite) {
			Biere biere = (Biere) bundle.get(Bundle.BIERE);
			afficherBiere(biere);
		} else {
			tfType.setText("");
			tfCouleur.setText("");
			tfBrasserie.setText("");
		}
	}

	public void lister() {
		Parent root;
		try {
			tfMessage.setText("");
			root = FXMLLoader.load(getClass().getResource("/be/iesca/vues/VueListe.fxml"));
			Stage stage = new Stage();
			stage.setTitle("Liste des bières");
			stage.getIcons().add(new Image("/be/iesca/application/biere.jpg"));
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(new Scene(root));
			stage.setResizable(false);
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void afficherBiere(Biere biere) {
		if (biere==null) return;
		tfNom.setText(biere.getNom());
		tfType.setText(biere.getType());
		tfCouleur.setText(biere.getCouleur());
		tfBrasserie.setText(biere.getBrasserie());
	}
	
	public void connexion(Bundle bundle) {
		this.bundle.put(Bundle.MESSAGE, bundle.get(Bundle.MESSAGE));
		this.bundle.put(Bundle.USER, bundle.get(Bundle.USER));
		this.btConnecter.setText("Déconnecter");
		this.btAjouter.setDisable(false);
		this.btLister.setDisable(false);
		this.btModifier.setDisable(false);
		this.btRechercher.setDisable(false);
		this.btSupprimer.setDisable(false);
		this.majMessage();
	}

}
