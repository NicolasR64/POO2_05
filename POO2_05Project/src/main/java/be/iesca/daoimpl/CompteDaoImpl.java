package be.iesca.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import be.iesca.dao.CompteDao;
import be.iesca.domaine.CompteCourant;

public class CompteDaoImpl implements CompteDao {
	private static final String GET = "SELECT * FROM comptes WHERE id = ?";
	private static final String MAJ = "UPDATE comptes SET solde= ?, \"isCloture\"= ?,  decouvert= ?, numero = ?  WHERE id= ?";
	private static final String MAJVIRM = "UPDATE comptes SET solde= ?, \"isCloture\"= ?,  decouvert= ? WHERE numero= ?";
	private static final String GETBYNUMERO = "SELECT * FROM comptes WHERE numero = ?";

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
			int id = compte.getId();
			ps.setInt(5, id);
			ps.setDouble(1, compte.getSolde());
			ps.setBoolean(2, compte.isCloture());
			ps.setDouble(3, compte.getDecouvertMax());
			ps.setString(4, compte.getNumero());
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
	public CompteCourant getCompte(int id) {
		CompteCourant compte = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DaoFactory.getInstance().getConnexion();
			ps = con.prepareStatement(GET);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				compte = new CompteCourant(id, rs.getDouble("solde"), rs.getBoolean("isCloture"), rs.getDouble("decouvert"), rs.getString("numero"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			cloturer(rs, ps, con);
		}
		return compte;
	}

	@Override
	public CompteCourant getCompteByNumero(String numero) {
		CompteCourant compte = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DaoFactory.getInstance().getConnexion();
			ps = con.prepareStatement(GETBYNUMERO);
			ps.setString(1, numero.trim());
			rs = ps.executeQuery();
			if (rs.next()) {
				compte = new CompteCourant(rs.getInt("id"), rs.getDouble("solde"), rs.getBoolean("isCloture"), rs.getDouble("decouvert"),numero);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			cloturer(rs, ps, con);
		}
		return compte;
	}

	@Override
	public boolean modifierCompteVirement(CompteCourant compte) {
		boolean modificationReussie = false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DaoFactory.getInstance().getConnexion();
			ps = con.prepareStatement(MAJVIRM);
			String numero = compte.getNumero();
			ps.setString(4, numero.trim());
			 // <------------------- 4 ème paramètre !
			ps.setDouble(1, compte.getSolde());
			ps.setBoolean(2, compte.isCloture());
			ps.setDouble(3, compte.getDecouvertMax());
			
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
}
