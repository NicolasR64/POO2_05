package be.iesca.dao;


import be.iesca.domaine.User;

public interface UserDao extends Dao {
	User getUser(String email, String password);
}
