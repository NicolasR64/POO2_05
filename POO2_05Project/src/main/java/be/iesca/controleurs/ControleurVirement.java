package be.iesca.controleurs;

import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import be.iesca.domaine.Bundle;
import be.iesca.domaine.CompteCourant;
import be.iesca.domaine.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControleurVirement implements Initializable {

	private GestionnaireUseCases gestionnaire = GestionnaireUseCases.getInstance();
	private Bundle bundle = new Bundle();
	private CompteCourant compte;
	
	@FXML
	private Button boutonRetour, boutonConfirmer;
	
	@FXML
	private TextField labelNumero, labelMontant;
	
	@FXML
	private Label labelMessage;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		labelMessage.setText("");
		gestionnaire.getCompte(bundle);
		compte = (CompteCourant) bundle.get(Bundle.COMPTECOURANT);
	}
	
	public void trtBoutonRetour(ActionEvent event) {
		Stage stage = (Stage) boutonRetour.getScene().getWindow();
	    stage.close();
	}
	
	public void trtBoutonConfirmer() throws Exception {
		CompteCourant compteVirement = new CompteCourant();
		String montantString = labelMontant.getText();
		Double montant = getMontant(montantString);
		compteVirement.setNumero(labelNumero.getText());
		bundle.put(Bundle.COMPTEVIREMENT, compteVirement);
		this.gestionnaire.getCompteByNumero(bundle);
		
		if((boolean)bundle.get(Bundle.OPERATION_REUSSIE)){
			User user = (User) bundle.get(Bundle.USER);
			user.setCompteCourant(compte);
			try {
				compteVirement = (CompteCourant) bundle.get(Bundle.COMPTEVIREMENT);
				compte.effectuerVirement(user, compteVirement, montant);
				bundle.put(Bundle.COMPTEVIREMENT, compteVirement);
				bundle.put(Bundle.COMPTECOURANT, compte);
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.gestionnaire.modifierCompteCourant(bundle);
			this.gestionnaire.modifierCompteVirement(bundle);
			
			labelMessage.setText((String) bundle.get(Bundle.MESSAGE));	
		}else {
			labelMessage.setText((String) bundle.get(Bundle.MESSAGE));
			ControleurPrincipal.getInstance().afficherCompteCourant(compteVirement);
		}
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
