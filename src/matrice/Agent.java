package matrice;

public class Agent extends MembreLibere { // classe parent MembreLibere car peut aussi entrer dans la matrice
	
	// Attributs
	private int degreEfficacite;
	
	// Constructeur
	public Agent(String nom, boolean genre, int degre) {
		super(nom, genre, 0, "agent"); // Initalisation de la classe parent (MembreLibere) avec le grade d'agent
		this.degreEfficacite = degre;
		super.setIcone("A" + degreEfficacite + " ");
	}
	
	public String toString() {
		return super.toString() + ", degré d'efficacité : " + degreEfficacite;
	}
}
