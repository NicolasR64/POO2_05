package be.iesca.controleurs;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import be.iesca.domaine.Bundle;
import be.iesca.domaine.CompteCourant;
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
	private TextField tfCloture, tfSolde, tfNumero, tfDecouvertMax, tfMessage;
	@FXML
	private Button cbVirement, cbLister, cbConnecter;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tfMessage.setText("Veuillez vous connecter");
		if (singleton == null)
			singleton = this; // on mémorise afin d'y accéder par la suite
	}

	public void trtBoutonConnecter() {
		if(this.cbConnecter.getText().equals("Connecter")) {
				Parent root;
			try {
				tfMessage.setText("");
				root = FXMLLoader.load(getClass().getResource("/be/iesca/vues/VueLogin.fxml"));
				Stage stage = new Stage();
				stage.setTitle("Se connecter");
				//TO DO
				//changer image
				stage.getIcons().add(new Image("/be/iesca/application/banque.jpg"));
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setScene(new Scene(root));
				stage.setResizable(false);
				stage.showAndWait();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			this.gestionnaire.deconnecterUser(bundle);
			this.cbConnecter.setText("Connecter");
			tfNumero.setText("");
			tfSolde.setText("");
			tfDecouvertMax.setText("");
			tfCloture.setText("");
			this.cbLister.setDisable(true);
			this.cbVirement.setDisable(true);
			this.majMessage();
		}
	}

	private void majMessage() {
		String message = (String) bundle.get(Bundle.MESSAGE);
		this.tfMessage.setText(message);
	}

	public void lister() {
		Parent root;
		try {
			tfMessage.setText("");
			root = FXMLLoader.load(getClass().getResource("/be/iesca/vues/VueListeOperation.fxml"));
			Stage stage = new Stage();
			stage.setTitle("Liste des op�ration");
			stage.getIcons().add(new Image("/be/iesca/application/banque.jpg"));
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(new Scene(root));
			stage.setResizable(false);
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void trtBoutonVirement() {
		Parent root;
		try {
			
			tfMessage.setText("");
			root = FXMLLoader.load(getClass().getResource("/be/iesca/vues/VueVirement.fxml"));
			Stage stage = new Stage();
			stage.setTitle("Page de virements");
			stage.getIcons().add(new Image("/be/iesca/application/banque.jpg"));
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(new Scene(root));
			stage.setResizable(false);
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void afficherCompteCourant(CompteCourant compteCourant) {
		if (compteCourant==null) return;
		tfNumero.setText(compteCourant.getNumero());
		tfSolde.setText(String.format ("%.9f", compteCourant.getSolde()));
		tfDecouvertMax.setText(String.format ("%.9f", compteCourant.getDecouvertMax()));
		tfCloture.setText(compteCourant.getIsCloture());
	}
	
	public void connexion(Bundle bundle) {
		this.bundle.put(Bundle.MESSAGE, bundle.get(Bundle.MESSAGE));
		this.bundle.put(Bundle.USER, bundle.get(Bundle.USER));
		this.bundle.put(Bundle.COMPTECOURANT, bundle.get(Bundle.COMPTECOURANT));
		CompteCourant compte = (CompteCourant) this.bundle.get(Bundle.COMPTECOURANT);
		afficherCompteCourant(compte);
		this.cbConnecter.setText("Deconnecter");
		this.cbVirement.setDisable(false);
		this.cbLister.setDisable(false);
		this.cbVirement.setDisable(false);
		this.majMessage();
	}

	public String formaterNombre(double montant) {
		   DecimalFormat formatter = new DecimalFormat("# ###.##");
		   String sMontant = formatter.format(montant);
		   return sMontant;
	}
	
	public double getMontant(String sMontant) throws Exception {
		   double montant;
		   if(sMontant.contains(",")) {
			   NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
			   Number number = format.parse(sMontant);
			   montant = number.doubleValue();
		   }else {
			   montant = Double.parseDouble(sMontant);
		   }
		   return montant;
	}
}
