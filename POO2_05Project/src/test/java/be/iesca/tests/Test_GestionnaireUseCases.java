package be.iesca.tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import be.iesca.controleurs.GestionnaireUseCases;
import be.iesca.domaine.Biere;
import be.iesca.domaine.Bundle;
import be.iesca.domaine.User;
import be.iesca.usecase.GestionCompteCourant;
import be.iesca.usecaseimpl.GestionCompteCourantImpl;

@TestMethodOrder(OrderAnnotation.class)
public class Test_GestionnaireUseCases {
	private static final String EMAIL_TOTO = "toto@gmail.com";
	private static final String NOM_TOTO = "Toto";
	private static final String PASSWORD_TOTO = "1234";

	private static GestionnaireUseCases gestionnaire;
	private static Bundle bundle;
	private User user;

	@BeforeAll
	static void initialiser() {
		bundle = new Bundle();
		viderLaTable();
		gestionnaire = GestionnaireUseCases.getInstance();
	}

	@AfterAll
	static void terminer() {
		viderLaTable();
	}

	@SuppressWarnings("unchecked")
	private static void viderLaTable() {
		GestionCompteCourant gestionBieres = new GestionCompteCourantImpl();
		gestionBieres.lister(bundle);
		List<Biere> bieresObtenues = (List<Biere>) bundle.get(Bundle.LISTE);
		for (Biere b : bieresObtenues) {
			bundle.put(Bundle.NOM, b.getNom());
			gestionBieres.supprimerBiere(bundle);
		}
	}

	@Test
	@Order(1)
	public void testDeconnexion() {
		gestionnaire.deconnecterUser(bundle);
		assertFalse((Boolean) bundle.get(Bundle.OPERATION_REUSSIE));
	}

	@Test
	@Order(2)
	public void testAjouterBiere() {
		gestionnaire.ajouterBiere(bundle);
		assertFalse((Boolean) bundle.get(Bundle.OPERATION_REUSSIE));
	}

	@Test
	@Order(3)
	public void testModifierBiere() {
		gestionnaire.modifierBiere(bundle);
		assertFalse((Boolean) bundle.get(Bundle.OPERATION_REUSSIE));
	}

	@Test
	@Order(4)
	public void testSupprimerBiere() {
		gestionnaire.supprimerBiere(bundle);
		assertFalse((Boolean) bundle.get(Bundle.OPERATION_REUSSIE));
	}

	@Test
	@Order(5)
	public void testListerBieres() {
		gestionnaire.lister(bundle);
		assertFalse((Boolean) bundle.get(Bundle.OPERATION_REUSSIE));
	}

	@Test
	@Order(6)
	public void testConnexion() {
		this.user = new User();
		this.user.setEmail(EMAIL_TOTO);
		this.user.setPassword(PASSWORD_TOTO);
		bundle.put(Bundle.USER, this.user);
		gestionnaire.connecterUser(bundle);
		assertTrue((Boolean) bundle.get(Bundle.OPERATION_REUSSIE));
		User userConnecte = (User) bundle.get(Bundle.USER);
		assertNotNull(userConnecte);
		assertEquals(NOM_TOTO, userConnecte.getNom());
		assertEquals(EMAIL_TOTO, userConnecte.getEmail());
	}

	@Test
	@Order(7)
	public void testReconnexion() {
		gestionnaire.connecterUser(bundle);
		assertFalse((Boolean) bundle.get(Bundle.OPERATION_REUSSIE));
	}

	@Test
	@Order(8)
	public void testAjouterBiereUserConnecte() {
		Biere biere = new Biere("Blanche De Bruxelles", "Blanche", "blanche", "Brasserie Lefèvre");
		bundle.put(Bundle.BIERE, biere);
		gestionnaire.ajouterBiere(bundle);
		assertTrue((Boolean) bundle.get(Bundle.OPERATION_REUSSIE));
	}

	@Test
	@Order(9)
	public void testModifierBiereUserConnecte() {
		Biere biere = new Biere("Blanche De Bruxelles", "Blanche!", "blanche", "Brasserie Lefèvre");
		bundle.put(Bundle.BIERE, biere);
		gestionnaire.modifierBiere(bundle);
		assertTrue((Boolean) bundle.get(Bundle.OPERATION_REUSSIE));
	}

	@Test
	@Order(10)
	public void testListeBieresUserConnecte() {
		gestionnaire.lister(bundle);
		assertTrue((Boolean) bundle.get(Bundle.OPERATION_REUSSIE));
	}

	@Test
	@Order(11)
	public void testSupprimerBiereUserConnecte() {
		String nom = "Blanche De Bruxelles";
		bundle.put(Bundle.NOM, nom);
		gestionnaire.supprimerBiere(bundle);
		assertTrue((Boolean) bundle.get(Bundle.OPERATION_REUSSIE));
	}

}
