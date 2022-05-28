package be.iesca.domaine;

public class Operation {

	// constantes
	public static final int CREATION = 0;
	public static final int DEPOT = 1;
	public static final int RETRAIT = 2;
	public static final int VIREMENT_DEBIT = 3;
	public static final int VIREMENT_CREDIT = 4;
	public static final int CLOTURE = 5;
	public static final int VIREMENT_EN_ATTENTE = 6;
	
	// attributs
	private double montant; // montant de l'op�ration
	private int autreCompte; // utilis� pour les virements
	private int numero; // num�ro d'identification de l'op�ration
	private double solde; // solde du compte apr�s l'op�ration
	private int type; //type d'op�ration effectu�
	private String numCompteEnBanque;
	
	// construction d'une op�ration, on ne connait pas encore son num�ro (-1)
	// il sera d�termin� lors de l'ajout de l'op�ration � l'historique
	public Operation( int autreCompte, double montant, int type,
            double solde ) {
        this.autreCompte = autreCompte;
        if ( type == Operation.RETRAIT || 
                type == Operation.VIREMENT_DEBIT ) montant = -montant;
        this.montant = montant;
        this.type = type;
        this.solde = solde;
        this.numCompteEnBanque = null;
    }
	
	public Operation( String numeroCompteBancaireAutre, double montant, int type, double solde ) {
        if ( type == Operation.RETRAIT || type == Operation.VIREMENT_DEBIT ) montant = -montant;
        this.montant = montant;
        this.numero = -1;
        this.type = type;
        this.solde = solde;
        this.numCompteEnBanque = numeroCompteBancaireAutre;
    }
	
	public Operation( int numero, double montant, double solde, int compte) {
		this.numero = numero;
		this.montant = montant;
		this.solde = solde;
		this.autreCompte = compte;
	}

	// m�thode qui renvoie les donn�es principales de l'op�ration
	// sous forme d'une cha�ne de caract�res
	public String toString() {
		String message =  "Op�ration n� " + this.numero + " du " + " de type : " + getTypeString()  + 
		", montant : " + this.montant + ", solde : " + this.solde;
		
		if  ( this.type == VIREMENT_CREDIT ) 
			message += " compte d�biteur : " +	this.autreCompte;
		if  ( this.type == VIREMENT_DEBIT ) 
			message += " compte cr�diteur : " + this.autreCompte;
		return message; 
	}

	// renvoie le type d'op�ration sous forme d'une cha�ne de caract�res
	public String getTypeString() {
		switch ( this.type ) {
			case CREATION : return "CREATION";
			case DEPOT : return "DEPOT";
			case RETRAIT : return "RETRAIT";
			case VIREMENT_DEBIT : return "VIREMENT_DEBIT";
			case VIREMENT_CREDIT : return "VIREMENT_CREDIT";
			case CLOTURE : return "CLOTURE";
			default : return "OPERATION INCONNUE"; 
		}
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int num�ro) {
		this.numero = num�ro;
	}

	public int getType() {
		return type;
	}

	public double getMontant() {
		return montant;
	}

	public int getAutreCompte() {
		return autreCompte;
	}

	public double getSolde() {
		return solde;
	}
	
	public String getNumCompteEnBanque() {
		return this.numCompteEnBanque;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Operation other = (Operation) obj;
		if (Double.doubleToLongBits(montant) != Double
				.doubleToLongBits(other.montant))
			return false;
		if (numero != other.numero)
			return false;
		if (Double.doubleToLongBits(solde) != Double
				.doubleToLongBits(other.solde))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
} // fin classe Operation
