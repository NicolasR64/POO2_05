package be.iesca.domaine;

public class User {
	private String email; // identifiant unique
	private String nom;
	private String password;
	private int idCompte;
	private CompteEnBanque compteCourant;
	
	public User() {
		super();
		this.email = "";
		this.nom = "";
		this.password = "";
		this.idCompte = 0;
	}
	
	

	@Override
	public String toString() {
		return "User [email=" + email + ", nom=" + nom + ", motDePasse="
				+ password + "]";
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
	
	public int getIdCompte() {
		return idCompte;
	}
	
	public void setIdCompte(int idCompte) {
		this.idCompte = idCompte;
	}

	public CompteEnBanque getCompteCourant() {
		return compteCourant;
	}

	public void setCompteCourant(CompteEnBanque compteCourant) {
		this.compteCourant = compteCourant;
	}

	public boolean equals(Object autre){
		if(!(autre instanceof User)) return false;
		User autreClient = (User) autre;
		if(!autreClient.getNom().equals(this.getNom())) return false;		
		if(!autreClient.getEmail().equals(this.getEmail())) return false;		
		if(!autreClient.getPassword().equals(this.getPassword())) return false;	
		return true;
	}
}