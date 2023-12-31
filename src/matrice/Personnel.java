package matrice;

public class Personnel implements Comparable<Personnel> { // L'interface Comparable est utilisé dans la méthode compareTo plus bas
	
	// Attributs
	private String nom;
	private boolean genre; // false = Homme ; true = Femme
	private int age;
	private String grade;
	private Vaisseau vaisseau;
	
	// Getters
	public String getNom() {
		return this.nom;
	}
	
	public Vaisseau getVaisseau() {
		return this.vaisseau;
	}
	
	// Setters
	public void setVaisseau(Vaisseau v) {
		this.vaisseau = v;
	}
	
	// Constructeur
	public Personnel(String nom, boolean genre, int age, String grade) {
		this.nom = nom;
		this.genre = genre;
		this.age = age;
		this.grade = grade;
		this.vaisseau = null;
	}
	
	public String toString() {
		return nom + ", " + age + " ans" + ", " + grade + (genre ? "e" : "");
	}
	
	// Vérifie si est affecté à un vaisseau
	public boolean isAffected() {
		return vaisseau != null;
	}
	
	// Utile pour comparer le nom à celui d'une autre personne pour trier alphabétiquement
	@Override
    public int compareTo(Personnel autrePersonnel) {
        return this.nom.compareToIgnoreCase(autrePersonnel.getNom());
    }
}
