package be.iesca.dao;

import java.util.List;

import be.iesca.domaine.Operation;

public interface OperationDao extends Dao {
	
	boolean ajouterOperation(Operation operation);
	List<Operation> listerOperation();

}
