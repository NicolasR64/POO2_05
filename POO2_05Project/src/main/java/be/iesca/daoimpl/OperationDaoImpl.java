package be.iesca.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import be.iesca.dao.OperationDao;
import be.iesca.domaine.Operation;

public class OperationDaoImpl implements OperationDao{
	//To do
	private static final String AJOUT = "INSERT INTO operations (compte, montant, solde) VALUES (?,?,?)";
	private static final String LISTER = "SELECT * FROM operations o ORDER BY o.compte";

	// obligatoire pour pouvoir construire une instance avec newInstance()
	public OperationDaoImpl() {
	}

	private void cloturer(ResultSet rs, PreparedStatement ps, Connection con) {
		try {
			if (rs != null)
				rs.close();
		} catch (Exception ex) {
		}
		try {
			if (ps != null)
				ps.close();
		} catch (Exception ex) {
		}
		try {
			if (con != null)
				con.close();
		} catch (Exception ex) {
		}
	}

	@Override
	public boolean ajouterOperation(Operation operation) {
		boolean ajoutReussi = false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DaoFactory.getInstance().getConnexion();
			ps = con.prepareStatement(AJOUT);
			ps.setInt(1, operation.getAutreCompte());
			ps.setDouble(2, operation.getMontant());
			ps.setDouble(3, operation.getSolde());
			int resultat = ps.executeUpdate();
			if (resultat == 1) {
				ajoutReussi = true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			cloturer(null, ps, con);
		}
		return ajoutReussi;
	}
	
	@Override
	public List<Operation> listerOperation() {
		List<Operation> liste = new ArrayList<Operation>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DaoFactory.getInstance().getConnexion();
			ps = con.prepareStatement(LISTER);
			rs = ps.executeQuery();
			while (rs.next()) {
				Operation operation = new Operation( rs.getInt(1), rs.getDouble(2), rs.getDouble(3), rs.getInt(4) );
				liste.add(operation);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			cloturer(rs, ps, con);
		}
		return liste;

	}

}
