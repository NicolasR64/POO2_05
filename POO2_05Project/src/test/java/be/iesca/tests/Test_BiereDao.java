package be.iesca.tests;
/*
 *  Tests unitaires de BiereDao
 */

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;

import be.iesca.dao.CompteDao;
import be.iesca.daoimpl.DaoFactory;
import be.iesca.domaine.CompteCourant;

@TestMethodOrder(OrderAnnotation.class)
public class Test_BiereDao {
	private static List<CompteCourant> comptes;
	private static CompteDao compteDao = (CompteDao) DaoFactory.getInstance().getDaoImpl(CompteDao.class);

	@BeforeAll
	static void initialiserListeBieres() throws Exception {
		comptes = new ArrayList<CompteCourant>(6);
		comptes.add(new CompteCourant("BE123456789", 1000.50, 1000.00));
		comptes.add(new CompteCourant("BE123456789", 1000.50, 1000.00));
		comptes.add(new CompteCourant("BE123456789", 1000.50, 1000.00));
		comptes.add(new CompteCourant("BE123456789", 1000.50, 1000.00));
		comptes.add(new CompteCourant("BE123456789", 1000.50, 1000.00));
		comptes.add(new CompteCourant("BE123456789", 1000.50, 1000.00));
	}
}
