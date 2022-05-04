package be.iesca.domaine;
public class CompteCourant implements CompteEnBanque {

	private String numero;
	
	private double solde;
	
	private double decouvertMax;
	
	private boolean cloture;

	private RegistreOperations registreOperations;	

	public CompteCourant(String numero, double decouvertMax) throws Exception {
		this(numero,0,decouvertMax);
	}

	public CompteCourant(String numero, double solde, double decouvertMax) throws Exception {
		if (solde < 0) throw new Exception("Le solde de depart doit etre positif");
		if (decouvertMax < 0) throw new Exception("Limite de decouvert negative");
		this.numero = numero;
		this.solde = solde;
		this.decouvertMax = decouvertMax;
		this.cloture = false;
		this.registreOperations = new RegistreOperations();
	}	
	
	public String getNumero() {
		return numero;
	}

	public double getSolde() {
		return solde;
	}

	public double getDecouvertMax() {
		return decouvertMax;
	}

	public boolean isCloture() {
		return cloture;
	}

	public void setDecouvertMax(double decouvertMax) throws Exception {
		if(isCloture()) throw new Exception("Compte cloture");
		if(decouvertMax<0) throw new Exception("Limite de decouvert negative");
		this.decouvertMax = decouvertMax;
	}	

	public void cloturer() throws Exception {
		if(isCloture()) throw new Exception("Compte cloture");
		if(Math.abs(this.getSolde()) > 0.00001) throw new Exception("Solde non nul");
		// Ce test ne semble pas pleinement satisfaisant car
		// en java, les "double" ne sont pas les nombres les plus precis donc quand on
		// compare il faut faire attention.
		// On aurait pu comparer le solde restant à 0 en utilisant le type Math.BigDecimal
		// pour l'attribut solde.  Les BigDecimal sont des nombres a precision exacte.
		this.cloture = true;

	}

	public void deposer(double montant) throws Exception {
		if(isCloture()) throw new Exception("Compte cloture");
		if(montant<=0) throw new Exception("le montant doit etre strictement positif");
			
		this.solde += montant;
	}

	public void effectuerVirement(Client emetteur, CompteEnBanque destination,
			double montant) throws Exception {
		if(!emetteur.getCompteCourant().equals(this)) throw new Exception("Titulaire errone");
		if(destination.isCloture()) throw new Exception("Compte beneficiaire cloture");
		
		this.retirer(montant);
		destination.deposer(montant);
	}

	public void retirer(double montant) throws Exception {
		if(isCloture()) throw new Exception("Compte cloture");
		if(montant<=0) throw new Exception("le montant doit etre strictement positif");
		if(montant-solde>this.getDecouvertMax()) throw new Exception("Solde insuffisant");
		
		this.solde -= montant;
	}
	
	public boolean equals(Object autre){
		if(!(autre instanceof CompteCourant)) return false;
		CompteCourant autreCompte = (CompteCourant) autre;
		return autreCompte.getNumero().equals(this.numero);
	}

	@Override
	public RegistreOperations getRegistreOperations() {
		return this.registreOperations;
	}

}
