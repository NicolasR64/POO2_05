package be.iesca.domaine;

public class User {
	private String email; // identifiant unique
	private String nom;
	private String password;
	
	public User() {
		super();
		this.email = "";
		this.nom="";
		this.password = "";
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

}
