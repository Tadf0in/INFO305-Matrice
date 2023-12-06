package matrice;

public class Vaisseau {
	
	// Attributs
	public String nom;
	private String type;
	
	// Générateur
	public Vaisseau(String nom, String type) {
		this.nom = nom;
		this.type = type;
	}
	
	public String toString() {
		return nom + ", vaisseau de " + type;
	}
}
