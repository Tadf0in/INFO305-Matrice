package matrice;

public class MembreLibere extends Personnel {
	
	// Attributs
	private int nb_infiltration;
	private int x;
	private int y;
	private String icone; // Ce qui va être afficher aux coordonées du membre dans la matrice
	private boolean infiltred;
	private boolean disconnected;
	
	// Constructeur
	public MembreLibere (String nom, boolean genre, int age, String grade) {
		super(nom, genre, age, grade); // Initialisation de la calsse parent (Personnel)
		this.nb_infiltration = 0;
		this.infiltred = false;
		this.disconnected = false;
		this.icone = "M  ";
	}
	
	// Getters
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public String getIcone() {
		return icone;
	}
	public int getNbInfiltration() {
		return nb_infiltration;
	}
	
	// Setters
	// Set les 2 coordonnées d'un seul coup
	public void setCoordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public void setIcone(String icone) {
		this.icone = icone;
	}
	public void setInfiltred(boolean bool) {
		this.infiltred = bool;
	}
	public void incrementNbInfiltration() {
		this.nb_infiltration += 1;
	}
	
	// Méthodes
	public String toString() {
		return super.toString() + ", Infiltrations : " + nb_infiltration + " fois.";
	}
	
	// Infiltre la matrice
	public void infiltrer(Matrice M) throws MatriceException {
		if (super.getVaisseau().isSafe()) {
			M.entrer(this);
			setInfiltred(true);
		} else {
			throw new MatriceException("Infiltration impossible, le vaisseau n'est pas sécurisé");
		}
	}
	
	// Sort de la matrice -> nb infiltration augmente
	public void exfiltrer(Matrice M) {
		if (!disconnected) { // Si n'est pas déconnecté		
			M.sortir(this);
			setInfiltred(false);
			incrementNbInfiltration();
		}
	}
	
	// Est dans la matrice ?
	public boolean isInfiltred() {
		return infiltred;
	}
	
	// Déconnecte quand se fait infecté
	public void disconnect() {
		System.out.println("Deconnexion");
		disconnected = true;
		setIcone("m  ");
	}
}
