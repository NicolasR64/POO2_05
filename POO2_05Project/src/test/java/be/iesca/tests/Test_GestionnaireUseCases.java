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
import be.iesca.usecase.GestionOperation;
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
		GestionOperation gestionBieres = new GestionCompteCourantImpl();
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
	public void testListerBieres() {
		gestionnaire.lister(bundle);
		assertFalse((Boolean) bundle.get(Bundle.OPERATION_REUSSIE));
	}

	@Test
	@Order(3)
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
	@Order(4)
	public void testReconnexion() {
		gestionnaire.connecterUser(bundle);
		assertFalse((Boolean) bundle.get(Bundle.OPERATION_REUSSIE));
	}
	
	@Test
	@Order(5)
	public void testListerOperationsUserConnecte() {
		gestionnaire.lister(bundle);
		assertTrue((Boolean) bundle.get(Bundle.OPERATION_REUSSIE));
	}
}
