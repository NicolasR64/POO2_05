package be.iesca.usecaseimpl;

import java.util.List;

import be.iesca.daoimpl.DaoFactory;
import be.iesca.domaine.Bundle;
import be.iesca.domaine.CompteCourant;
import be.iesca.usecase.GestionOperation;

public class GestionOperationImpl implements GestionOperation {

	private operationDAO OperaionDao;

	public GestionOperationImpl() {
		this.operationDAO = (OperaionDao) DaoFactory.getInstance().getDaoImpl(OperaionDao.class);
	}

	@Override
	public void lister(Bundle bundle) {
		boolean listeOk = true;
		String message = "";
		List<Operation> listeOperation= null;
		listeOperation = this.operationDAO.listerOperation();
		if (operationDAO==null) {
			listeOk = false;
		} else if (operationDAO.isEmpty())
			message = "Liste vide";
		else if (operationDAO.size() == 1)
			message = "Il y a 1 operation";
		else
			message = "Il y a " + operationDAO.size() + " opération";
		bundle.put(Bundle.OPERATION_REUSSIE, listeOk);
		bundle.put(Bundle.MESSAGE, message);
		bundle.put(Bundle.LISTE, operationDAO);
	}
}
