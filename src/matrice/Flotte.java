package matrice;
import java.util.ArrayList;

public class Flotte {
	
	// Attributs
	private ArrayList<Personnel> personnel;
	private ArrayList<Vaisseau> vaisseaux;
	
	// Constructeur
	public Flotte() {
		this.personnel = new ArrayList<Personnel>();
		this.vaisseaux  = new ArrayList<Vaisseau>();
	}
	
	// Vérifie qu'il n'existe pas déjà un vaisseau avec le même nom avant d'ajouter dans la liste
	public void addUniqueVaisseau(Vaisseau value) throws MatriceException {
		for (Vaisseau v: this.vaisseaux) {
			if (v.getNom().equals(value.getNom())) {				
				throw new MatriceException("Un vaisseau porte déjà ce nom");
			}
		}
		this.vaisseaux.add(value);
	}
	
	// Vérifie qu'il n'existe pas déjà quelqu'un avec le même nom avant d'ajouter dans la liste
	public void addUniquePersonnel(Personnel value) throws MatriceException {
		for (Personnel p: this.personnel) {
			if (p.getNom().equals(value.getNom())) {				
				throw new MatriceException("Un membre du personnel porte déjà ce nom");
			}
		}
		this.personnel.add(value);
	}
	
	// Ajoute une liste de vaisseaux dans la flotte, en vérifiant à chaque fois si il peut être ajouté
	public void addVaisseau(ArrayList<Vaisseau> vaisseauxAAjouter) {
		// On vérifie chaque élément de la liste temporaire avant de l'ajouter dans la vraie liste
		for (Vaisseau v: vaisseauxAAjouter) {
			try {
				addUniqueVaisseau(v); // Fonction définie plus haut		
			} catch (MatriceException e) {
				System.out.println(e);
			}
		}
		vaisseauxAAjouter = null; // On libère de la place en mémoire
	}
	

	// Ajoute une liste de personnel dans la flotte, en vérifiant à chaque fois si il peut être ajouté
	public void addPersonnel(ArrayList<Personnel> personnelAAjouter) {
		// On vérifie chaque élément de la liste temporaire avant de l'ajouter dans la vraie liste	 
		for (Personnel p: personnelAAjouter) {
			try {
				addUniquePersonnel(p); // Fonction définie plus haut				
			} catch (MatriceException e) {
				System.out.println(e);
			}
		}
		personnelAAjouter = null; // On libère de la place en mémoire
	}
	
	// Cherche un vaisseau grâce à son nom
	public Vaisseau getVaisseau(String nom) {
		for (Vaisseau v: this.vaisseaux) {
			if (v.getNom().equals(nom)) {
				return v;
			}
		}
		return null;
	}
	
	// Cherche un membre grâce à son nom
	public Personnel getPersonnel(String nom) {
		for (Personnel p: this.personnel) {
			if (p.getNom().equals(nom)) {
				return p;
			}
		}
		return null;
	}

	// Pour afficher la liste des vaisseaux
	public String listeVaisseaux() {
		String out = "Vaisseaux :\n";
		for (Vaisseau v: this.vaisseaux) {
			out += "\t- " + v + "\n";
		}
		return out;
	}
	
	// Pour afficher la liste du personnel
	public String listePersonnel() {
		String out = "Liste du personnel :";
		for (Personnel p: personnel) {
			out += "\n\t- " + p;
		}
		return out;
	}
	// Pareil mais pour le personnel qui n'est pas affecté à un vaisseau
	public String listeNonAffectes() {
		String out = "Personnel non affecté :";
		for (Personnel p: personnel) {
			if (!p.isAffected()) {				
				out += "\n\t- " + p;
			}
		}
		return out;
	}
	
	// Pour afficher la flotte
	public String toString() {
		return this.listeVaisseaux() + "\n" + this.listeNonAffectes();
	}
}
