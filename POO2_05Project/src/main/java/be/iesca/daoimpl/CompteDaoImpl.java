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
	private static final String MAJ = "UPDATE comptes SET solde= ?, decouvert= ?, isCloture= ? where numero= ?";
	private static final String LISTER = "SELECT * FROM comptes c ORDER BY c.numero";

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
	public boolean modifierCompte(CompteCourant compte) {
		boolean modificationReussie = false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DaoFactory.getInstance().getConnexion();
			ps = con.prepareStatement(MAJ);
			String numero = compte.getNumero().trim();
			ps.setString(4, numero); // <------------------- 4 ème paramètre !
			ps.setDouble(1, compte.getSolde());
			ps.setDouble(2, compte.getDecouvertMax());
			ps.setBoolean(3, compte.isCloture());
			int resultat = ps.executeUpdate();
			if (resultat == 1) {
				modificationReussie = true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			cloturer(null, ps, con);
		}
		return modificationReussie;
	}

	@Override
	public List<CompteCourant> listerCompte() {
		List<CompteCourant> liste = new ArrayList<CompteCourant>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DaoFactory.getInstance().getConnexion();
			ps = con.prepareStatement(LISTER);
			rs = ps.executeQuery();
			String numero = "";
			while (rs.next()) {
				numero = rs.getString("numero");
				CompteCourant compte = new CompteCourant(numero, rs.getDouble(2), rs.getDouble(3));
				liste.add(compte);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			cloturer(rs, ps, con);
		}
		return liste;
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
