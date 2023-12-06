package matrice;

public class OperateurSION extends Personnel {
	
	// Attributs
	private Vaisseau vaisseau;
	private String role;
	
	// Générateur
	public OperateurSION(String nom, boolean genre, int age, String grade, Vaisseau vaisseau, String role) {
		super(nom, genre, age, grade); // Initialisation de la calsse parent (Personnel)
		this.vaisseau = vaisseau;
		this.role = role;
	}
	
	// Méthodes
	public String toString() {
		return super.toString() + ", Role : " + role + ", Affectation : " + vaisseau;
	}
}
