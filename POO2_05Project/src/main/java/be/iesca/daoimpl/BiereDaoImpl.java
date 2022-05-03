package be.iesca.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import be.iesca.dao.BiereDao;
import be.iesca.domaine.Biere;

public class BiereDaoImpl implements BiereDao {
	private static final String GET = "SELECT * FROM Bieres b WHERE b.nom = ?";
	private static final String AJOUT = "INSERT INTO Bieres (nom, type, couleur, brasserie) VALUES (?,?,?,?)";
	private static final String MAJ = "UPDATE Bieres SET type= ?, couleur= ?, brasserie= ? where nom= ?";
	private static final String LISTER = "SELECT * FROM Bieres b ORDER BY b.nom";
	private static final String SUPPRIMER = "DELETE FROM Bieres b WHERE b.nom = ?";

	// obligatoire pour pouvoir construire une instance avec newInstance()
	public BiereDaoImpl() {
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
	public boolean ajouterBiere(Biere biere) {
		boolean ajoutReussi = false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DaoFactory.getInstance().getConnexion();
			ps = con.prepareStatement(AJOUT);
			ps.setString(1, biere.getNom().trim());
			ps.setString(2, biere.getType().trim());
			ps.setString(3, biere.getCouleur().trim());
			ps.setString(4, biere.getBrasserie().trim());
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
	public Biere getBiere(String nom) {
		Biere biere = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DaoFactory.getInstance().getConnexion();
			ps = con.prepareStatement(GET);
			ps.setString(1, nom.trim());
			rs = ps.executeQuery();
			if (rs.next()) {
				biere = new Biere(nom, rs.getString(2), rs.getString(3),
						rs.getString(4));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			cloturer(rs, ps, con);
		}
		return biere;
	}

	@Override
	public List<Biere> listerBieres() {
		List<Biere> liste = new ArrayList<Biere>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DaoFactory.getInstance().getConnexion();
			ps = con.prepareStatement(LISTER);
			rs = ps.executeQuery();
			String nom = "";
			while (rs.next()) {
				nom = rs.getString("nom");
				Biere biere = new Biere(nom, rs.getString(2), rs.getString(3),
						rs.getString(4));
				liste.add(biere);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			cloturer(rs, ps, con);
		}
		return liste;

	}

	@Override
	public boolean supprimerBiere(String nom) {
		boolean suppressionReussie = false;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DaoFactory.getInstance().getConnexion();
			ps = con.prepareStatement(SUPPRIMER);
			ps.setString(1, nom.trim());
			int nb = ps.executeUpdate();
			if (nb == 1)
				suppressionReussie = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			cloturer(rs, ps, con);
		}
		return suppressionReussie;
	}

	@Override
	public boolean modifierBiere(Biere biere) {
		boolean modificationReussie = false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DaoFactory.getInstance().getConnexion();
			ps = con.prepareStatement(MAJ);
			String nom = biere.getNom().trim();
			ps.setString(4, nom); // <------------------- 4 ème paramètre !
			ps.setString(1, biere.getType().trim());
			ps.setString(2, biere.getCouleur().trim());
			ps.setString(3, biere.getBrasserie().trim());
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
