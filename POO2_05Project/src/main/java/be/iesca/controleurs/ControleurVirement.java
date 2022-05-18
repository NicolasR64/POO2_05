package be.iesca.controleurs;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class ControleurVirement implements Initializable {

	@FXML
	private javafx.scene.control.Button boutonRetour;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void trtBoutonRetour(ActionEvent event) {
		Stage stage = (Stage) boutonRetour.getScene().getWindow();
	    stage.close();
	}

}
