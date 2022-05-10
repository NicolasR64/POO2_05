package be.iesca.controleurs;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import be.iesca.domaine.Bundle;
import be.iesca.domaine.CompteCourant;
import be.iesca.domaine.Operation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ControleurListeOperation implements Initializable {

	@FXML
	private TableView<Operation> tableView;
	@FXML
	private TableColumn<Operation, String> colMontant, colNumero, colCompteEnBanque;

	private GestionnaireUseCases gestionnaire = GestionnaireUseCases.getInstance();
	private ObservableList<Operation> tvObservableList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Bundle bundle = new Bundle();
		gestionnaire.lister(bundle);
		@SuppressWarnings("unchecked")
		List<Operation> listeOperation = (List<Operation>) bundle.get(Bundle.LISTE);
		tvObservableList.addAll(listeOperation);
		colNumero.setCellValueFactory(new PropertyValueFactory<Operation, String>("numero"));
		colMontant.setCellValueFactory(new PropertyValueFactory<Operation, String>("solde"));
		colCompteEnBanque.setCellValueFactory(new PropertyValueFactory<Operation, String>("Bénéficaire"));
		this.tableView.setItems(tvObservableList);
	}

	public void selection(MouseEvent mouseEvent) {
		if (mouseEvent.getClickCount() == 2) {
			int index = tableView.getSelectionModel().getSelectedIndex();
			if (index<0 || index >= tvObservableList.size()) return;
			Operation operation = tvObservableList.get(index);
			//TO DO
			//ControleurPrincipal.getInstance().afficherOperation(operation);
			tableView.getScene().getWindow().hide();
		}
	}
}
