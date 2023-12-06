package matrice;

public class Personnel {
	
	// Attributs
	public String nom;
	private boolean genre; // false = Homme ; true = Femme
	private int age;
	private String grade;
	
	// Générateur
	public Personnel(String nom, boolean genre, int age, String grade) {
		this.nom = nom;
		this.genre = genre;
		this.age = age;
		this.grade = grade;
	}
	
	public String toString() {
		return nom + ", " + age + " ans" + ", " + grade + (genre ? "e" : "");
	}
}
