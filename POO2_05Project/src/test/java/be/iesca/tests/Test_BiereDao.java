package be.iesca.tests;
/*
 *  Tests unitaires de BiereDao
 */

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

import be.iesca.dao.BiereDao;
import be.iesca.daoimpl.DaoFactory;
import be.iesca.domaine.Biere;

@TestMethodOrder(OrderAnnotation.class)
public class Test_BiereDao {
	private static List<Biere> bieres;
	private static BiereDao biereDao = (BiereDao) DaoFactory.getInstance().getDaoImpl(BiereDao.class);

	@BeforeAll
	static void initialiserListeBieres() {
		viderLaTable();
		bieres = new ArrayList<Biere>(6);
		bieres.add(new Biere("Blanche De Bruxelles", "Blanche", "blanche", "Brasserie Lefèbvre"));
		bieres.add(new Biere("Blanche de Hoegaarden", "Blanche", "blanche", "Brasserie De Kluis"));
		bieres.add(new Biere("Chimay Bleue", "Trappiste", "brune", "Abbaye de Scourmont"));
		bieres.add(new Biere("Chimay Rouge", "Trappiste", "brune", "Abbaye de Scourmont"));
		bieres.add(new Biere("Floreffe Blonde", "Abbaye", "blonde", "Brasserie Lefèbvre"));
		bieres.add(new Biere("Floreffe Triple", "Abbaye", "blonde", "Brasserie Lefèbvre"));
	}

	@AfterAll
	static void viderLaTable() {
		List<Biere> bieresObtenues = biereDao.listerBieres();
		for (Biere b : bieresObtenues) {
			assertTrue(biereDao.supprimerBiere(b.getNom()));
		}
	}

	@Test
	@Order(1)
	public void testAjouter() {
		for (Biere b : bieres) {
			assertTrue(biereDao.ajouterBiere(b));
		}
	}

	@Test
	@Order(2)
	public void testLister() {
		List<Biere> bieresObtenues = biereDao.listerBieres();
		for (int i = 0; i < bieresObtenues.size(); i++) {
			assertEquals(bieresObtenues.get(i), bieres.get(i));
		}
	}

	@Test
	@Order(3)
	public void testRechercher() {
		for (Biere b : bieres) {
			Biere biereObtenue = biereDao.getBiere(b.getNom());
			assertEquals(biereObtenue, b);
		}
	}

	@Test
	@Order(4)
	public void testModifier() {
		Biere biere = biereDao.getBiere(bieres.get(0).getNom());
		biere.setBrasserie(biere.getBrasserie() + "!");
		biere.setType(biere.getType() + "!");
		biere.setCouleur(biere.getCouleur() + "!");
		assertTrue(biereDao.modifierBiere(biere));
		Biere biereObtenue = biereDao.getBiere(biere.getNom());
		assertEquals(biereObtenue, biere);
	}

	@Test
	@Order(5)
	public void testSupprimer() {
		for (Biere b : bieres) {
			assertTrue(biereDao.supprimerBiere(b.getNom()));
		}
		List<Biere> bieresObtenues = biereDao.listerBieres();
		assertNotNull(bieresObtenues);
		assertEquals(0, bieresObtenues.size());
	}

	@Test
	@Order(6)
	public void testListeTriee() {
		Biere biereU = new Biere("u", "u", "u", "u");
		Biere biereZ = new Biere("z", "z", "z", "z");
		Biere biereA = new Biere("a", "a", "a", "a");
		biereDao.ajouterBiere(biereZ);
		biereDao.ajouterBiere(biereA);
		biereDao.ajouterBiere(biereU);
		List<Biere> bieresObtenues = biereDao.listerBieres();
		assertEquals(bieresObtenues.get(0), biereA);
		assertEquals(bieresObtenues.get(1), biereU);
		assertEquals(bieresObtenues.get(2), biereZ);
		assertTrue(biereDao.supprimerBiere("u"));
		assertTrue(biereDao.supprimerBiere("z"));
		assertTrue(biereDao.supprimerBiere("a"));

	}

}
