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

import be.iesca.domaine.Bundle;
import be.iesca.usecase.GestionOperation;
import be.iesca.domaine.CompteCourant;
import be.iesca.usecase.GestionCompteCourant;
import be.iesca.usecaseimpl.GestionCompteCourantImpl;

@TestMethodOrder(OrderAnnotation.class)
public class Test_Integration {
	

	private static List<CompteCourant> comptes;
	private static GestionCompteCourant gestionComptes;

	private static Bundle bundle;

	@BeforeAll
	// sera exécuté une fois avant toutes les méthodes
	static void initialisation() throws Exception {
		gestionComptes = new GestionCompteCourantImpl();
		bundle = new Bundle();
		comptes = new ArrayList<CompteCourant>(6);
		// ajout des bières (liste déjà triée)
		comptes.add(new CompteCourant("BE123456789", 1000.50, 1000.00));
		comptes.add(new CompteCourant("BE987654321", 1000.50, 1000.00));
		comptes.add(new CompteCourant("BE258147369", 1000.50, 1000.00));
		comptes.add(new CompteCourant("BE123987456", 1000.50, 1000.00));
		comptes.add(new CompteCourant("BE951357826", 1000.50, 1000.00));
		comptes.add(new CompteCourant("BE123654785", 1000.50, 1000.00));
	}

}