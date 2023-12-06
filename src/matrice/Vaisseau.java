package matrice;

public class Vaisseau {
	
	// Attributs
	private String nom;
	private String type;
	private int MAX_MEMBRES;
	private Personnel[] equipage;
	private int nb_equipage;
	
	// Constructeur
	public Vaisseau(String nom, String type) {
		this.nom = nom;
		this.type = type;
		this.MAX_MEMBRES = 5;
		this.equipage = new Personnel[MAX_MEMBRES];
		this.nb_equipage = 0;
	}
	
	// Getters
	public String getNom() {
		return this.nom;
	}
	
	public String toString() {
		return nom + ", vaisseau de " + type;
	}

	// Ajoute un membre à l'équipage
	public void affecter(Personnel p) throws MatriceException {
		if (nb_equipage < MAX_MEMBRES) {
			// -> A FAIRE : vérifier si membre pas déjà dans equipage
			equipage[nb_equipage] = p;
			p.setVaisseau(this);
			nb_equipage += 1;			
		} else {
			throw new MatriceException("Equipage déjà au complet");
		}
	}
	
	// Supprime un membre d'équipage à partir de son nom
	public void desaffecter(Personnel p) {
		for (int i=0; i<nb_equipage; i++) {
			if (equipage[i] == p) {
				equipage[i] = equipage[nb_equipage-1];
				equipage[nb_equipage-1] = null;
				p.setVaisseau(null);
				nb_equipage -= 1;
			}
		}
	}
	
	// Parcours et ajoute dans un String chaque personne de l'équipage
	public String listePersonnel() {
		String out = nb_equipage + " personne(s) dans l'équipage : \n";
		for (int i=0; i<nb_equipage; i++) {
			out += (i+1) + ". " + equipage[i] + "\n";
		}
		return out;
	}
	
	// Vérifie qu'il y a bien un Operateur SION et un membre libéré dans l'équipage
	public boolean checkPersonnel() {
		boolean op = false;
		boolean ml = false;
		for (int i=0; i<nb_equipage; i++) {
			if (equipage[i] instanceof OperateurSION) {
				op = true;
			}
			else if (equipage[i] instanceof MembreLibere) {
				ml = true;
			}
		}
		return op && ml;
	}
}
