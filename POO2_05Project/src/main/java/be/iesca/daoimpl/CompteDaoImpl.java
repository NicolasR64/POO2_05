package be.iesca.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import be.iesca.dao.CompteDao;
import be.iesca.domaine.CompteCourant;

public class CompteDaoImpl implements CompteDao {
	private static final String GET = "SELECT * FROM comptes c WHERE c.numero = ?";

	// obligatoire pour pouvoir construire une instance avec newInstance()
	public CompteDaoImpl() {
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
	public CompteCourant getCompte(String numero) {
		CompteCourant compte = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DaoFactory.getInstance().getConnexion();
			ps = con.prepareStatement(GET);
			ps.setString(1, numero.trim());
			rs = ps.executeQuery();
			if (rs.next()) {
				compte = new CompteCourant(numero, rs.getDouble(2), rs.getDouble(3));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			cloturer(rs, ps, con);
		}
		return compte;
	}

}
