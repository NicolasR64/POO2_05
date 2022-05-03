package be.iesca.controleurs;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import be.iesca.domaine.Biere;
import be.iesca.domaine.Bundle;
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
	private TableView<Biere> tableView;
	@FXML
	private TableColumn<Biere, String> colNom, colType, colCouleur, colBrasserie;

	private GestionnaireUseCases gestionnaire = GestionnaireUseCases.getInstance();
	private ObservableList<Biere> tvObservableList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Bundle bundle = new Bundle();
		gestionnaire.lister(bundle);
		@SuppressWarnings("unchecked")
		List<Biere> listeBieres = (List<Biere>) bundle.get(Bundle.LISTE);
		tvObservableList.addAll(listeBieres);
		colNom.setCellValueFactory(new PropertyValueFactory<Biere, String>("nom"));
		colType.setCellValueFactory(new PropertyValueFactory<Biere, String>("type"));
		colCouleur.setCellValueFactory(new PropertyValueFactory<Biere, String>("couleur"));
		colBrasserie.setCellValueFactory(new PropertyValueFactory<Biere, String>("brasserie"));
		this.tableView.setItems(tvObservableList);
	}

	public void selection(MouseEvent mouseEvent) {
		if (mouseEvent.getClickCount() == 2) {
			int index = tableView.getSelectionModel().getSelectedIndex();
			if (index<0 || index >=tvObservableList.size()) return;
			Biere biere = tvObservableList.get(index);
			ControleurPrincipal.getInstance().afficherBiere(biere);
			tableView.getScene().getWindow().hide();
		}
	}
}
