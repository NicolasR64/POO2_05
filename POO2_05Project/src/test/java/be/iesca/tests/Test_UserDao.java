package be.iesca.tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import be.iesca.dao.UserDao;
import be.iesca.daoimpl.DaoFactory;
import be.iesca.domaine.User;

/*
 * Tests unitaires de UserDao
 * Ces tests supposent que la db contient 3 users (cf. script d'ajout)
 */
public class Test_UserDao {
	private static final String EMAIL_TOTO = "toto@gmail.com";
	private static final String NOM_TOTO = "Toto";
	private static final String PASSWORD_TOTO = "1234";
	private static final String AUTRE_PASSWORD = "****";
	private static final String EMAIL_TITI = "titi@gmail.com";
	private static final String EMAIL_TATA = "tata@gmail.com";
	private static final String NOM_TATA = "Tata";
	private static final String PASSWORD_TATA = "1234";

	private UserDao userDao = (UserDao) DaoFactory.getInstance().getDaoImpl(
			UserDao.class);

	@Test
	public void test1() {
		User user = this.userDao.getUser(EMAIL_TOTO, PASSWORD_TOTO);
		assertNotNull(user);
		assertEquals(EMAIL_TOTO, user.getEmail());
		assertEquals(NOM_TOTO, user.getNom());
	}

	@Test
	public void test2() {
		User user = this.userDao.getUser(EMAIL_TOTO, AUTRE_PASSWORD);
		assertNull(user);
	}

	@Test
	public void test3() {
		User user = this.userDao.getUser(EMAIL_TATA, PASSWORD_TATA);
		assertNotNull(user);
		assertEquals(EMAIL_TATA, user.getEmail());
		assertEquals(NOM_TATA, user.getNom());
	}

	@Test
	public void test4() {
		User user = this.userDao.getUser(EMAIL_TITI, PASSWORD_TOTO);
		assertNull(user);
	}
}
