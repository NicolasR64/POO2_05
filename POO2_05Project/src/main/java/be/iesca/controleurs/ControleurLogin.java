package be.iesca.controleurs;

import java.net.URL;
import java.util.ResourceBundle;

import be.iesca.domaine.Bundle;
import be.iesca.domaine.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControleurLogin implements Initializable {

	@FXML
	private TextField tfEmail;
	@FXML
	private TextField tfPassword;
	
	@FXML
	private Button btConnecter;

	private GestionnaireUseCases gestionnaire = GestionnaireUseCases.getInstance();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tfEmail.setText("nicolas@gmail.com");
		tfPassword.setText("1234");
	}

	public void trtBoutonConnecter() {
		User user = new User();
		user.setEmail(tfEmail.getText());
		user.setPassword(tfPassword.getText());
		Bundle bundle = new Bundle();
		bundle.put(Bundle.USER, user);
		this.gestionnaire.connecterUser( bundle );
		if((boolean)bundle.get(Bundle.OPERATION_REUSSIE)) {
			ControleurPrincipal.getInstance().connexion(bundle);
			
			tfEmail.getScene().getWindow().hide();
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Identification");
			alert.setHeaderText(null);
			alert.setContentText((String)bundle.get(Bundle.MESSAGE));
			alert.showAndWait();
		}
	}

}
