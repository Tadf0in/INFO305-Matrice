package matrice;
import java.util.ArrayList;

public class Matrice {

	// Attributs
	private ArrayList<Personnel> presents;
	private String[][] grille;
	
	// Constructeur
	public Matrice() {
		grille = new String[10][10];
		for (int i=0; i<10; i++) {
			String[] row = new String[10];
			for (int j=0; j<10; j++) {
				row[j] = ".";
			}
			grille[i] = row;
		}
		
		presents = new ArrayList<Personnel>();
		presents.add(new Agent("agent_0", false, 0, 0, 0));
		presents.add(new Agent("agent_1", false, 0, 0, 0));
		presents.add(new Agent("agent_2", false, 0, 0, 0));
	}
	
	public String toString() {
		return this.afficher();
	}
	
	// Affiche la matrice
	public String afficher() {
		String out = "   0  1  2  3  4  5  6  7  8  9 \n";
		for (int i=0; i<10; i++) {
			out += i + "  ";
			for (int j=0; j<10; j++) {
				out += grille[i][j] + "  ";
			}
			out += "\n";
		}
		return out;
	}
}
