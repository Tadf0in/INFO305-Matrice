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
		for (MembreLibere membre: presents) {
			out += "\t- " + membre.getNom() + 
					", " + ((membre instanceof Agent) ? ((Agent)membre).getDegreEfficacite() : membre.getNbInfiltration()) + 
					", (" + membre.getX() + ", " + membre.getY() + ")\n";
		}
		return out;
	}
	
	// Fait rentrer un membre dans la matrice -> le rajoute dans la liste
	public void entrer(MembreLibere m) {
		int x; int y;
		// Choisit des coordonées aléatoire et en rechoisis d'autre juqu'à en trouver des libres
		do {	
			x = (int)(Math.random() * 10);
			y = (int)(Math.random() * 10);
		} while (atPosition(x, y) != ".  ");
		m.setCoordinates(x, y); // Assigne les coordonées libres au membre
		presents.add(m); // Ajoute le membre dans la liste des membres présents à l'intérieur de la matrice
		
		// Si membre pas agent -> vérifie si il se fait infecter
		if (!(m instanceof Agent)) {			
			estInfecte(m);
		}
	}
	
	// Membre sort de la matrice -> retiré de la liste
	public void sortir(MembreLibere m) {
		presents.remove(m);
	}
	
	// Caclule la distance entre un membre et un agent
	private double distanceAgent(MembreLibere mbr, Agent agt) {
		int x = agt.getX() - mbr.getX();
		int y = agt.getY() - mbr.getY();
		return Math.sqrt(x*x + y*y);
	}
	
	// Retourne l'agent le plus proche du Membre mbr
	public Agent agentPlusProche(MembreLibere mbr) {
		double min_distance = 15; // distance max possible = 10*sqrt(2) ~= 14.1 < 15
		Agent agentpp = null;
		for (MembreLibere a: presents) { // Parcours tous les membres présents dans la matrice
			if (a instanceof Agent) { // Si bien agent et pas autre membre
				double d = distanceAgent(mbr, (Agent)a); 
				// Garde la plus petite distance et l'agent qui s'y trouve
				if (d < min_distance) {
					min_distance = d;
					agentpp = (Agent)a;
				}
			}
		}
		return agentpp;
	}
	
	// Infecte ou pas lors de l'infiltration
	public void estInfecte(MembreLibere m) {
		Agent a = agentPlusProche(m);
		int de = a.getDegreEfficacite();
		if ((de / distanceAgent(m, a)) > m.getNbInfiltration()) {
			m.disconnect();
		} else {
			a.setDegreEfficacite((int) de - de/2);
			// Si degre tombe à 0, vérifie le peuple de SION a vaincu (= autres agents aussi à 0)
			if (a.getDegreEfficacite() == 0) {
				checkVictory();
			}
		}
	}
	
	// Regarde si le peuple de SION à vaincu
	public boolean checkVictory() {
		boolean v = true;
		// Vérifie si tous les agents ont leur degré d'efficacité = 0
		for (MembreLibere a: presents) {
			if (a instanceof Agent) {
				v = v && ((Agent)a).getDegreEfficacite() == 0;
			}
		}
		return v;
	}
}
