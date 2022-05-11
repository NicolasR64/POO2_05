package be.iesca.tests;

/*
 * Tests d'intégration de type JUnit 5
 * Chaque cas d'utilisation est testé individuellement, les uns après les autres
 */


import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

import be.iesca.domaine.Biere;
import be.iesca.domaine.Bundle;
import be.iesca.usecase.GestionOperation;
import be.iesca.usecaseimpl.GestionCompteCourantImpl;

@TestMethodOrder(OrderAnnotation.class)
public class Test_Integration {

	private static List<Biere> bieres;
	private static GestionOperation gestionBieres;
	private static Bundle bundle;

	@BeforeAll
	// sera exécuté une fois avant toutes les méthodes
	static void initialisation() {
		gestionBieres = new GestionCompteCourantImpl();
		bundle = new Bundle();
		bieres = new ArrayList<Biere>(6);
		// ajout des bières (liste déjà triée)
		bieres.add(new Biere("Blanche De Bruxelles", "Blanche", "blanche",
				"Brasserie Lefébvre"));
		bieres.add(new Biere("Blanche de Hoegaarden", "Blanche", "blanche",
				"Brasserie De Kluis"));
		bieres.add(new Biere("Chimay Bleue", "Trappiste", "brune",
				"Abbaye de Scourmont"));
		bieres.add(new Biere("Chimay Rouge", "Trappiste", "brune",
				"Abbaye de Scourmont"));
		bieres.add(new Biere("Floreffe Blonde", "Abbaye", "blonde",
				"Brasserie Lefébvre"));
		bieres.add(new Biere("Floreffe Triple", "Abbaye", "blonde",
				"Brasserie Lefébvre"));
	}

	@Test
	@Order(1)
	public void testAjouter() {
		for (Biere b : bieres) {
			bundle.put(Bundle.BIERE, b);
			gestionBieres.ajouterBiere(bundle);
			assertTrue((boolean) bundle.get(Bundle.OPERATION_REUSSIE));
		}
		// tentative d'ajout d'une biere déjà présente
		bundle.put(Bundle.BIERE, bieres.get(0));
		gestionBieres.ajouterBiere(bundle);
		assertFalse((boolean) bundle.get(Bundle.OPERATION_REUSSIE));

		// tentative d'ajout d'une biere dont il manque des données
		bundle.put(Bundle.BIERE, new Biere(null,null,null,null));
		gestionBieres.ajouterBiere(bundle);
		assertFalse((boolean) bundle.get(Bundle.OPERATION_REUSSIE));

		bundle.put(Bundle.BIERE, new Biere("nom",null,null,null));
		gestionBieres.ajouterBiere(bundle);
		assertFalse((boolean) bundle.get(Bundle.OPERATION_REUSSIE));

		bundle.put(Bundle.BIERE, new Biere("nom","type",null,null));
		gestionBieres.ajouterBiere(bundle);
		assertFalse((boolean) bundle.get(Bundle.OPERATION_REUSSIE));

		bundle.put(Bundle.BIERE, new Biere("nom","type","couleur",null));
		gestionBieres.ajouterBiere(bundle);
		assertFalse((boolean) bundle.get(Bundle.OPERATION_REUSSIE));
	}

	@Test
	@Order(2)
	public void testLister() {
		gestionBieres.lister(bundle);
		@SuppressWarnings("unchecked")
		List<Biere> bieresObtenues = (List<Biere>) bundle.get(Bundle.LISTE);
		assertTrue((boolean) bundle.get(Bundle.OPERATION_REUSSIE));
		for (int i = 0; i < bieres.size(); i++) {
			assertEquals(bieresObtenues.get(i), bieres.get(i));
		}
	}

	@Test
	@Order(3)
	public void testRechercher() {
		for (Biere b : bieres) {
			bundle.put(Bundle.NOM, b.getNom());
			gestionBieres.rechercherBiere(bundle);
			Biere biereObtenue = (Biere) bundle.get(Bundle.BIERE);
			assertTrue((boolean) bundle.get(Bundle.OPERATION_REUSSIE));
			assertEquals(biereObtenue, b);
		}
	}

	@Test
	@Order(4)
	public void testModifier() {
		// modification biere existante
		bundle.put(Bundle.NOM, bieres.get(0).getNom());
		gestionBieres.rechercherBiere(bundle);
		assertTrue((boolean) bundle.get(Bundle.OPERATION_REUSSIE));
		Biere biere = (Biere) bundle.get(Bundle.BIERE);
		biere.setBrasserie(biere.getBrasserie() + "!");
		biere.setType(biere.getType() + "!");
		biere.setCouleur(biere.getCouleur() + "!");
		gestionBieres.modifierBiere(bundle);
		assertTrue((boolean) bundle.get(Bundle.OPERATION_REUSSIE));
		bundle.vider();
		bundle.put(Bundle.NOM, biere.getNom());
		gestionBieres.rechercherBiere(bundle);
		assertTrue((boolean) bundle.get(Bundle.OPERATION_REUSSIE));
		Biere biereObtenue = (Biere) bundle.get(Bundle.BIERE);
		assertEquals(biereObtenue, biere);

		//tentative de modification d'une biere inexistante
		bundle.put(Bundle.BIERE, new Biere("?", "?", "?", "?"));
		gestionBieres.modifierBiere(bundle);
		assertFalse((boolean) bundle.get(Bundle.OPERATION_REUSSIE));
	}

	@Test
	@Order(5)
	public void testSupprimer() {
		for (Biere b : bieres) {
			bundle.put(Bundle.NOM, b.getNom());
			gestionBieres.supprimerBiere(bundle);
			assertTrue((boolean) bundle.get(Bundle.OPERATION_REUSSIE));
		}
		gestionBieres.lister(bundle);
		@SuppressWarnings("unchecked")
		List<Biere> bieresObtenues = (List<Biere>) bundle.get(Bundle.LISTE);
		assertTrue((boolean) bundle.get(Bundle.OPERATION_REUSSIE));
		assertEquals(0, bieresObtenues.size());

		//tentative de suppression d'une biere inexistante
		bundle.put(Bundle.BIERE, new Biere("?", "?", "?", "?"));
		gestionBieres.supprimerBiere(bundle);
		assertFalse((boolean) bundle.get(Bundle.OPERATION_REUSSIE));

	}

	@Test
	@Order(6)
	public void testListeTriee() {
		Biere biereU = new Biere("u", "u", "u", "u");
		Biere biereZ = new Biere("z", "z", "z", "z");
		Biere biereA = new Biere("a", "a", "a", "a");
		bundle.put(Bundle.BIERE, biereU);
		gestionBieres.ajouterBiere(bundle);
		bundle.put(Bundle.BIERE, biereZ);
		gestionBieres.ajouterBiere(bundle);
		bundle.put(Bundle.BIERE, biereA);
		gestionBieres.ajouterBiere(bundle);
		gestionBieres.lister(bundle);
		assertTrue((boolean) bundle.get(Bundle.OPERATION_REUSSIE));
		@SuppressWarnings("unchecked")
		List<Biere> bieresObtenues = (List<Biere>) bundle.get(Bundle.LISTE);
		assertEquals( bieresObtenues.get(0), biereA);
		assertEquals( bieresObtenues.get(1), biereU);
		assertEquals( bieresObtenues.get(2), biereZ);
	}

}