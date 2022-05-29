package be.iesca.usecaseimpl;

import java.util.List;

import be.iesca.dao.OperationDao;
import be.iesca.daoimpl.DaoFactory;
import be.iesca.domaine.Bundle;
import be.iesca.domaine.Operation;
import be.iesca.usecase.GestionOperation;

public class GestionOperationImpl implements GestionOperation {

	private OperationDao operationDao;

	public GestionOperationImpl() {
		this.operationDao = (OperationDao) DaoFactory.getInstance().getDaoImpl(OperationDao.class);
	}

	@Override
	public void lister(Bundle bundle) {
		boolean listeOk = true;
		String message = "";
		List<Operation> listeOperation= null;
		listeOperation = this.operationDao.listerOperation();
		if (listeOperation==null) {
			listeOk = false;
		} else if (listeOperation.isEmpty())
			message = "Liste vide";
		else if (listeOperation.size() == 1)
			message = "Il y a 1 operation";
		else
			message = "Il y a " + listeOperation.size() + " opération";
		bundle.put(Bundle.OPERATION_REUSSIE, listeOk);
		bundle.put(Bundle.MESSAGE, message);
		bundle.put(Bundle.LISTE, listeOperation);
	}
}
