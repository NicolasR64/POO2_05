package be.iesca.domaine;

public interface CompteEnBanque {

	/* des methodes qui posent des questions */
	double getSolde(); 
	double getDecouvertMax();
	String getNumero();
	boolean isCloture();
	boolean equals(Object autre);
	
	/* des methodes qui agissent sur l'objet cible */
	void cloturer() throws Exception;
	void deposer(double montant) throws Exception;
	void retirer(double montant) throws Exception;
	void effectuerVirement(User emetteur, CompteEnBanque cpteDestination, double montant) throws Exception;	

}
