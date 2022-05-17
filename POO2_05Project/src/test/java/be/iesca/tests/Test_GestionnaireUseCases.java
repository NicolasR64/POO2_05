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
		gestionnaire = GestionnaireUseCases.getInstance();
	}

	@Test
	@Order(1)
	public void testDeconnexion() {
		gestionnaire.deconnecterUser(bundle);
		assertFalse((Boolean) bundle.get(Bundle.OPERATION_REUSSIE));
	}

	@Test
	@Order(2)
	public void testModifierCompteCourant() {
		gestionnaire.modifierCompteCourant(bundle);
		assertFalse((Boolean) bundle.get(Bundle.OPERATION_REUSSIE));
	}

	@Test
	@Order(3)
	public void testGetCompteCourant() {
		gestionnaire.getCompteCourant(bundle);
		assertFalse((Boolean) bundle.get(Bundle.OPERATION_REUSSIE));
	}

	@Test
	@Order(4)
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
	@Order(5)
	public void testReconnexion() {
		gestionnaire.connecterUser(bundle);
		assertFalse((Boolean) bundle.get(Bundle.OPERATION_REUSSIE));
	}

	@Test
	@Order(11)
	public void testGetCompteCourantConnecte(String numero) {
		gestionnaire.getCompte(bundle, numero);
		assertTrue((Boolean) bundle.get(Bundle.OPERATION_REUSSIE));
	}

}
