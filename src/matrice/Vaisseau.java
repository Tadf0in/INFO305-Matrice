package matrice;

public class Vaisseau {
	
	// Attributs
	public String nom;
	private String type;
	private int MAX_MEMBRES;
	private Personnel[] equipage;
	private int nb_equipage;
	
	// Générateur
	public Vaisseau(String nom, String type) {
		this.nom = nom;
		this.type = type;
		this.MAX_MEMBRES = 5;
		this.equipage = new Personnel[MAX_MEMBRES];
		this.nb_equipage = 0;
	}
	
	public String toString() {
		return nom + ", vaisseau de " + type;
	}

	// AJotue un membre à l'équipage
	public void addPersonnel(Personnel pToAdd) throws MatriceException {
		if (nb_equipage < MAX_MEMBRES) {
			// -> A FAIRE : vérifier si membre pas déjà dans equipage
			equipage[nb_equipage] = pToAdd;
			nb_equipage += 1;			
		} else {
			throw new MatriceException("Equipage déjà au complet");
		}
	}
	
	public void delPersonnel(String nom) {
		for (int i=0; i<nb_equipage; i++) {
			if (equipage[i].nom == nom) {
				equipage[i] = equipage[nb_equipage-1];
				equipage[nb_equipage-1] = null;
				nb_equipage -= 1;
			}
		}
		// -> A FAIRE : Exception : pas dans l'equipage
	}
	
	// Parcours et ajoute dans un String chaque personne de l'équipage
	public String listePersonnel() {
		String out = nb_equipage + " personne(s) dans l'équipage : \n";
		for (int i=0; i<nb_equipage; i++) {
			out += (i+1) + ". " + equipage[i] + "\n";
		}
		return out;
	}
}
