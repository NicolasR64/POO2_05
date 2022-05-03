package be.iesca.domaine;

public class Biere  {
	private String nom;
	private String type;
	private String couleur;
	private String brasserie;

	public Biere(String nom, String type, String couleur, String brasserie) {
		super();
		this.nom = nom;
		this.type = type;
		this.couleur = couleur;
		this.brasserie = brasserie;
	}

	public Biere(Biere biere) {
		super();
		this.nom = biere.nom;
		this.type = biere.type;
		this.couleur = biere.couleur;
		this.brasserie = biere.brasserie;
	}

	public String getNom() {
		return nom;
	}

	public String getType() {
		return type;
	}

	public String getCouleur() {
		return couleur;
	}

	public String getBrasserie() {
		return brasserie;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	@Override
	public String toString() {
		return "Biere [nom=" + nom + ", type=" + type + ", couleur=" + couleur
				+ ", brasserie=" + brasserie + "]";
	}

	public void setBrasserie(String brasserie) {
		this.brasserie = brasserie;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Biere other = (Biere) obj;
		if (brasserie == null) {
			if (other.brasserie != null)
				return false;
		} else if (!brasserie.equals(other.brasserie))
			return false;
		if (couleur == null) {
			if (other.couleur != null)
				return false;
		} else if (!couleur.equals(other.couleur))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
	
}
