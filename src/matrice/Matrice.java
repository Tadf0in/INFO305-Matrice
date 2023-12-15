package matrice;
import java.util.ArrayList;

public class Matrice {

	// Attributs
	private ArrayList<MembreLibere> presents; // Liste des membres présents dans la matrice
	private int TAILLE = 10;
	private int NB_AGENTS = 3;
	
	// Constructeur
	public Matrice() {		
		presents = new ArrayList<MembreLibere>();
		for (int i=0; i<NB_AGENTS; i++) { // n tours de boucle pour ajouter n agents
			// Ajoute l'agent dans la matrice
			try {				
				this.entrer(new Agent("agent_"+(NB_AGENTS-i), false, (int)(Math.random() * 5))); 
			} catch (MatriceException e) {
				System.out.println(e);
			}
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
	
	
	// Affiche la matrice
	public String afficher() {
		String out = "   ";
		for (int i=0; i<TAILLE; i++) { // Premiere ligne de chiffres
			if (i < 10) {				
				out += i + "  "; 
			} else {
				out += i + " ";
			}
		}
		out += "\n";
		for (int i=0; i<TAILLE; i++) { // Parcours les colonnes
			// Colonne de chiffre
			if (i < 10) {				
				out += i + "  "; 
			} else {
				out += i + " ";
			}
			for (int j=0; j<TAILLE; j++) { // Parcours les lignes
				out += this.atPosition(i, j);
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
	public void entrer(MembreLibere m) throws MatriceException {
		if (presents.size() >= TAILLE*TAILLE) { // Si matrice pleine
			throw new MatriceException("La matrice est pleine");
		}
		
		int x; int y;
		// Choisit des coordonées aléatoire et en rechoisis d'autre juqu'à en trouver des libres
		do {	
			x = (int)(Math.random() * TAILLE);
			y = (int)(Math.random() * TAILLE);
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
		double min_distance = TAILLE * Math.sqrt(2) + 1; // distance max possible = 10*sqrt(2) ~= 14.1 < 15
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
			a.setDegreEfficacite((int) Math.floor(de - (double)de/2)); // Math.floor prend le premier entier inférieur à la valeur
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
