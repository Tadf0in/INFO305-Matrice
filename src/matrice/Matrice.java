package matrice;
import java.util.ArrayList;

public class Matrice {

	// Attributs
	private ArrayList<MembreLibere> presents;
	private String[][] grille;
	
	// Constructeur
	public Matrice() {
		// Initialise la grille de 10x10 points
		grille = new String[10][10];
		for (int i=0; i<10; i++) {
			String[] row = new String[10];
			for (int j=0; j<10; j++) {
				row[j] = ".  ";
			}
			grille[i] = row;
		}
		
		presents = new ArrayList<MembreLibere>();
		for (int i=0; i<3; i++) { // 3 tours de boucle pour ajouter 3 agents
			// Ajoute l'agent dans la matrice
			this.entrer(new Agent("agent_"+(3-i), false, (int)(Math.random() * 5))); 
		}
	}
	
	public String toString() {
		return this.afficher();
	}
	
	// Retourne ce qu'il doit être affiché à une certaine position x, y
	public String atPosition(int x, int y) {
		for (MembreLibere membre: this.presents) { // Parcours les membres à l'intérieur de la matrice
			if (membre.getX() == x && membre.getY() == y) { // Si membre se trouve aux coordonées demandées
				return membre.getIcone();
			}
		}
		return ".  ";
	}
	
	// Met à jour la grille avec les nouvelles positions
	public void updateGrille() {
		for (int i=0; i<10; i++) { // Parcours les colonnes
			for (int j=0; j<10; j++) { // Parcours les lignes
				grille[i][j] = this.atPosition(i, j);
			}
		}
	}
	
	// Affiche la matrice
	public String afficher() {
		this.updateGrille();
		String out = "   0  1  2  3  4  5  6  7  8  9 \n";
		for (int i=0; i<10; i++) { // Parcours les colonnes
			out += i + "  ";
			for (int j=0; j<10; j++) { // Parcours les lignes
				out += grille[i][j];
			}
			out += "\n";
		}
		return out;
	}
	
	// Affiche la liste des membres présents à l'interieur de la matrice
	public String listeMembres() {
		// -> A FAIRE : trier par ordre laphabétique
		String out = "Membres présents dans la matrice :\n";
		java.util.Collections.sort(presents); // Tri la liste par ordre alphabétique
		for (MembreLibere membre: this.presents) {
			out += "\t- " + membre.getNom() + ", (" + membre.getX() + ", " + membre.getY() + ")\n";
		}
		return out;
	}
	
	// Fait rentrer un membre dans la matrice
	public void entrer(MembreLibere m) {
		int x; int y;
		// Choisit des coordonées aléatoire et en rechoisis d'autre juqu'à en trouver des libres
		do {	
			x = (int)(Math.random() * 10);
			y = (int)(Math.random() * 10);
		} while (atPosition(x, y) != ".  ");
		m.setCoordinates(x, y); // Assigne les coordonées libres au membre
		presents.add(m); // Ajoute le membre dans la liste des membres présents à l'intérieur de la matrice
	}
}
