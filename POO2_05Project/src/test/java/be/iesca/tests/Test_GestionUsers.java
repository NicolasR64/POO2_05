package be.iesca.tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import be.iesca.domaine.Bundle;
import be.iesca.domaine.User;
import be.iesca.usecase.GestionUsers;
import be.iesca.usecaseimpl.GestionUsersImpl;

/*
 * Tests d'intégration du usecase GestionUsers
 * Ces tests supposent que la db contient 3 users (cf. script d'ajout)
 */
public class Test_GestionUsers {

	private static final String EMAIL_TOTO = "toto@gmail.com";
	private static final String NOM_TOTO = "Toto";
	private static final String PASSWORD_TOTO = "1234";
	private static final String AUTRE_PASSWORD = "****";
	private static final String EMAIL_TITI = "titi@gmail.com";
	private static final String EMAIL_TATA = "tata@gmail.com";
	private static final String NOM_TATA = "Tata";
	private static final String PASSWORD_TATA = "1234";

	private static GestionUsers gestionUsers;
	private static Bundle bundle;

	@BeforeAll
	static void initialisation() {
		bundle = new Bundle();
		gestionUsers = new GestionUsersImpl();
	}

	@Test
	public void testConnecter1() {
		User user = new User();
		user.setEmail(EMAIL_TOTO);
		user.setPassword(PASSWORD_TOTO);
		bundle.put(Bundle.USER, user);
		gestionUsers.connecterUser(bundle);
		assertTrue((Boolean) bundle.get(Bundle.OPERATION_REUSSIE));
		user = (User) bundle.get(Bundle.USER);
		assertNotNull(user);
		assertEquals(NOM_TOTO, user.getNom());
		assertEquals(EMAIL_TOTO, user.getEmail());
	}

	@Test
	public void testConnecter2() {
		User user = new User();
		user.setEmail(EMAIL_TOTO);
		user.setPassword(AUTRE_PASSWORD);
		bundle.put(Bundle.USER, user);
		gestionUsers.connecterUser(bundle);
		assertFalse((Boolean) bundle.get(Bundle.OPERATION_REUSSIE));
	}

	@Test
	public void testConnecter3() {
		User user = new User();
		user.setEmail(EMAIL_TATA);
		user.setPassword(PASSWORD_TATA);
		bundle.put(Bundle.USER, user);
		gestionUsers.connecterUser(bundle);
		assertTrue((Boolean) bundle.get(Bundle.OPERATION_REUSSIE));
		user = (User) bundle.get(Bundle.USER);
		assertNotNull(user);
		assertEquals(NOM_TATA, user.getNom());
		assertEquals(EMAIL_TATA, user.getEmail());
	}

	@Test
	public void testConnecter4() {
		User user = new User();
		user.setEmail(EMAIL_TITI);
		user.setPassword(PASSWORD_TOTO);
		bundle.put(Bundle.USER, user);
		gestionUsers.connecterUser(bundle);
		assertFalse((Boolean) bundle.get(Bundle.OPERATION_REUSSIE));
	}

}