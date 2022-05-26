package be.iesca.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import be.iesca.dao.UserDao;
import be.iesca.domaine.User;

public class UserDaoImpl implements UserDao {
	private static final String GET = "SELECT * FROM users WHERE email=? and password = crypt(?, password)";

	// obligatoire pour pouvoir construire une instance avec newInstance() 
	public UserDaoImpl() {
	}

	@Override
	public User getUser(String email, String password) {
		User user = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DaoFactory.getInstance().getConnexion();
			ps = con.prepareStatement(GET);
			ps.setString(1, email.trim());
			ps.setString(2, password.trim());
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setNom(rs.getString("nom"));
				user.setEmail(rs.getString("email"));
				user.setIdCompte(rs.getInt("compte"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			cloturer(rs, ps, con);
		}
		return user;
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
}
