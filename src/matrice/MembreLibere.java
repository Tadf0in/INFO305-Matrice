package matrice;

public class MembreLibere extends Personnel {
	
	// Attributs
	private int nb_infiltration;
	
	// Constructeur
	public MembreLibere (String nom, boolean genre, int age, String grade) {
		super(nom, genre, age, grade); // Initialisation de la calsse parent (Personnel)
		this.nb_infiltration = 0;
	}
	
	// Méthodes
	public String toString() {
		return super.toString() + ", Infiltrations : " + nb_infiltration + " fois.";
	}
	
	public void infiltrer() {
		nb_infiltration += 1;
	}
}
