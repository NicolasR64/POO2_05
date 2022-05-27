package be.iesca.controleurs;

import java.net.URL;
import java.util.ResourceBundle;

import be.iesca.domaine.Bundle;
import be.iesca.domaine.CompteCourant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControleurVirement implements Initializable {

	private static ControleurVirement singleton;
	private Bundle bundle;
	
	public static ControleurVirement getInstance() {
		return singleton;
	}
	
	@FXML
	private javafx.scene.control.Button boutonRetour;
	
	@FXML
	private TextField labelNumero;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void trtBoutonRetour(ActionEvent event) {
		Stage stage = (Stage) boutonRetour.getScene().getWindow();
	    stage.close();
	}
	
	public void garnirBundle(Bundle bundle) {
		this.bundle = bundle;
		CompteCourant compte = (CompteCourant) this.bundle.get(Bundle.COMPTECOURANT);
		this.afficherCompte(compte);
	}
	
	public void afficherCompte(CompteCourant compte) {
		labelNumero.setText(compte.getNumero());
	}

}
