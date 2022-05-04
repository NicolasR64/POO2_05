package be.iesca.controleurs;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import be.iesca.domaine.Bundle;
import be.iesca.domaine.CompteCourant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ControleurListe implements Initializable {

	@FXML
	private TableView<CompteCourant> tableView;
	@FXML
	private TableColumn<CompteCourant, String> colNom, colType, colCouleur, colBrasserie;

	private GestionnaireUseCases gestionnaire = GestionnaireUseCases.getInstance();
	private ObservableList<CompteCourant> tvObservableList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Bundle bundle = new Bundle();
		gestionnaire.lister(bundle);
		@SuppressWarnings("unchecked")
		List<CompteCourant> listeBieres = (List<CompteCourant>) bundle.get(Bundle.LISTE);
		tvObservableList.addAll(listeBieres);
		colNom.setCellValueFactory(new PropertyValueFactory<CompteCourant, String>("numero"));
		colType.setCellValueFactory(new PropertyValueFactory<CompteCourant, String>("solde"));
		colCouleur.setCellValueFactory(new PropertyValueFactory<CompteCourant, String>("decouvert max"));
		colBrasserie.setCellValueFactory(new PropertyValueFactory<CompteCourant, String>("est cloture?"));
		this.tableView.setItems(tvObservableList);
	}

	public void selection(MouseEvent mouseEvent) {
		if (mouseEvent.getClickCount() == 2) {
			int index = tableView.getSelectionModel().getSelectedIndex();
			if (index<0 || index >= tvObservableList.size()) return;
			CompteCourant biere = tvObservableList.get(index);
			ControleurPrincipal.getInstance().afficherCompteCourant(biere);
			tableView.getScene().getWindow().hide();
		}
	}
}
