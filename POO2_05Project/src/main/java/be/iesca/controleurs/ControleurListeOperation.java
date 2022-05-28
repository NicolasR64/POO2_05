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
	private TableColumn<Operation, String> colSolde, colNumero, colBeneficiaire;

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
		colSolde.setCellValueFactory(new PropertyValueFactory<Operation, String>("solde"));
		colBeneficiaire.setCellValueFactory(new PropertyValueFactory<Operation, String>("beneficiare"));
		this.tableView.setItems(tvObservableList);
	}
}
