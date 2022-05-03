package be.iesca.tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import be.iesca.daoimpl.ParserConfig;
import be.iesca.daoimpl.Persistance;
import be.iesca.dao.*;

/*
 * Tests unitaires de ParserConfig
 */
public class Test_Parser {
	private Persistance persistance;

	@Test
	public void testMock1() {
		try {
			persistance = ParserConfig
					.lireConfiguration("src/test/resources/configMock1.xml");
			assertEquals("MOCK", persistance.getType());
			String toStringDao = persistance.getDaoImpl(BiereDao.class)
					.toString();
			assertTrue(toStringDao
					.contains("be.iesca.daoimpl.BiereDaoMockImpl"));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testMock2() {
		try {
			persistance = ParserConfig
					.lireConfiguration("src/test/resources/configMock2.xml");
			fail("Il aurait du générer une exception car il manque la balise <dao>");
		} catch (Exception e) {
			// ok car il devait générer une exception
		}
	}

	@Test
	public void testMock3() {
		try {
			persistance = ParserConfig
					.lireConfiguration("src/test/resources/configMock3.xml");
			fail("Il aurait du générer une exception car il manque la balise <persistance>");
		} catch (Exception e) {
			// ok car il devait générer une exception
		}
	}

	@Test
	public void testMock4() {
		try {
			persistance = ParserConfig
					.lireConfiguration("src/test/resources/configMock4.xml");
			fail("Il aurait du générer une exception car il manque l'attribut type");
		} catch (Exception e) {
			// ok car il devait générer une exception
		}
	}

	@Test
	public void testMock5() {
		try {
			persistance = ParserConfig
					.lireConfiguration("src/test/resources/configMock5.xml");
			fail("Il aurait du générer une exception car le type de persistance est incorrect");
		} catch (Exception e) {
			// ok car il devait générer une exception
		}
	}

	@Test
	public void testPostgres1() {
		try {
			persistance = ParserConfig
					.lireConfiguration("src/test/resources/configPostgres1.xml");
			assertEquals("DB", persistance.getType());
			String toStringDao = persistance.getDaoImpl(BiereDao.class)
					.toString();
			assertTrue(toStringDao.contains("be.iesca.daoimpl.BiereDaoImpl"));
			assertEquals("jdbc:postgresql://localhost:5432/postgres",
					persistance.getUrl());
			assertEquals("postgres", persistance.getUser());
			assertEquals("1234", persistance.getPassword());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testPostgres2() { // doit fonctionner avec les 2 daos
		try {
			persistance = ParserConfig
					.lireConfiguration("src/test/resources/configPostgres2.xml");
			assertEquals("DB", persistance.getType());
			String toStringDao = persistance.getDaoImpl(UserDao.class)
					.toString();
			assertTrue(toStringDao.contains("be.iesca.daoimpl.UserDaoImpl"));
			toStringDao = persistance.getDaoImpl(BiereDao.class).toString();
			assertTrue(toStringDao.contains("be.iesca.daoimpl.BiereDaoImpl"));
			assertEquals("jdbc:postgresql://localhost:5432/postgres",
					persistance.getUrl());
			assertEquals("postgres", persistance.getUser());
			assertEquals("1234", persistance.getPassword());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testPostgres3() {
		try {
			persistance = ParserConfig
					.lireConfiguration("src/test/resources/configPostgres3.xml");
			fail("Il aurait du générer une exception car il manque la balise <url>");
		} catch (Exception e) {
			// ok car il devait générer une exception
		}
	}

	@Test
	public void testPostgres4() {
		try {
			persistance = ParserConfig
					.lireConfiguration("src/test/resources/configPostgres4.xml");
			fail("Il aurait du générer une exception car il manque la balise <user>");
		} catch (Exception e) {
			// ok car il devait générer une exception
		}
	}

	@Test
	public void testPostgres5() {
		try {
			persistance = ParserConfig
					.lireConfiguration("src/test/resources/configPostgres5.xml");
			fail("Il aurait du générer une exception car il manque la balise <password>");
		} catch (Exception e) {
			// ok car il devait générer une exception
		}
	}

	@Test
	public void testMockPostgres1() {
		try {
			persistance = ParserConfig
					.lireConfiguration("src/test/resources/configMockPostgres1.xml");
			assertEquals("DB", persistance.getType());
			String toStringDao = persistance.getDaoImpl(UserDao.class)
					.toString();
			assertTrue(toStringDao.contains("be.iesca.daoimpl.UserDaoImpl"));
			toStringDao = persistance.getDaoImpl(BiereDao.class)
					.toString();
			assertTrue(toStringDao.contains("be.iesca.daoimpl.BiereDaoMockImpl"));
			assertEquals("jdbc:postgresql://localhost:5432/postgres",
					persistance.getUrl());
			assertEquals("postgres", persistance.getUser());
			assertEquals("1234", persistance.getPassword());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}