package matrice;

public class Agent extends Personnel {
	
	// Attributs
	private int degreEfficacite;
	private int x;
	private int y;
	
	// Constructeur
	public Agent(String nom, boolean genre, int degre, int x, int y) {
		super(nom, genre, 0, "agent"); // Initalisation de la classe parent (Personnel) avec le grade d'agent
		this.degreEfficacite = degre;
		this.x = x;
		this.y = y;
	}
}
