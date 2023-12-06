package matrice;

public class MembreLibere extends Personnel {
	
	// Attributs
	private int nb_infiltration;
	private int x;
	private int y;
	private boolean infiltred;
	
	// Constructeur
	public MembreLibere (String nom, boolean genre, int age, String grade) {
		super(nom, genre, age, grade); // Initialisation de la calsse parent (Personnel)
		this.nb_infiltration = 0;
		this.infiltred = false;
	}
	
	// Méthodes
	public String toString() {
		return super.toString() + ", Infiltrations : " + nb_infiltration + " fois.";
	}
	
	// Infiltre la matrice
	public void infiltrer() {
		nb_infiltration += 1;
		infiltred = true;
	}
	
	// Est dans la matrice ?
	public boolean isInfiltred() {
		return infiltred;
	}
	
	// Retourne les coordonées
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public void setCoordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	// Retourne ce qui va être afficher aux coordonées du membre dans la matrice
	public String getIcone() {
		return "M  ";
	}
}
