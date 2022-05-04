package be.iesca.application;

public class Main  {

	// Cette classe ne peut pas hériter de javafx.application.Application
	// Si c'est le cas le java launcher va planter (bug en jdk 11)
	
	public static void main(String[] args) {
		ApplicationCompteBancaire.lancer(args);
	}
	
}
