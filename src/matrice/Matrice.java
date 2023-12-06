package matrice;
import java.util.ArrayList;

public class Matrice {
	public static void main(String[] args) {
		
		// Liste des vaisseaux
		ArrayList<Vaisseau> vaisseaux = new ArrayList<Vaisseau>();
		try {
			addUniqueVaisseau(vaisseaux, new Vaisseau("Vaisseau de guerre 1", "guerre"));
			addUniqueVaisseau(vaisseaux, new Vaisseau("Vaisseau de guerre 2", "guerre"));
			addUniqueVaisseau(vaisseaux, new Vaisseau("Vaisseau de transport 1", "transport"));
		} catch (NotUniqueException e) {
			System.out.println(e);
		}
		
		// Liste temporaire du personnel qu'on souhaite ajouter
		ArrayList<Personnel> personnelAAjouter = new ArrayList<Personnel>();
		personnelAAjouter.add(new OperateurSION("Morpheus", false, 50, "commandant", vaisseaux.get(0), "communication"));
		personnelAAjouter.add(new MembreLibere("Neo", false, 25, "lieutenant"));
		personnelAAjouter.add(new MembreLibere("Neo", false, 26, "colonel")); // Test nom déjà utilisé
		personnelAAjouter.add(new MembreLibere("Noe", false, 27, "commandant")); // Test si continue d'ajouter malgré nom déjà utilisé plus haut
		
		// On vérifie chaque élément de la liste temporaire avant de l'ajouter dans la vraie liste
		ArrayList<Personnel> personnel = new ArrayList<Personnel>();
		for (Personnel p: personnelAAjouter) {
			try {
				addUniquePersonnel(personnel, p);				
			} catch (NotUniqueException e) {
				System.out.println(e);
			}
		}
		personnelAAjouter = null; // On libère de la place en mémoire
		
		// Affichage de la liste du personnel
		for (Personnel p: personnel) {
			System.out.println(p);
		}
	}
	

	// Vérifie qu'il n'existe pas déjà un vaisseau avec le même nom avant d'ajouter dans la liste
	public static void addUniqueVaisseau(ArrayList<Vaisseau> array, Vaisseau value) throws NotUniqueException {
		for (Vaisseau v: array) {
			if (v.nom == value.nom) {				
				throw new NotUniqueException();
			}
		}
		array.add(value);
	}
	
	// Vérifie qu'il n'existe pas déjà quelqu'un avec le même nom avant d'ajouter dans la liste
	public static void addUniquePersonnel(ArrayList<Personnel> array, Personnel value) throws NotUniqueException {
		for (Personnel p: array) {
			if (p.nom == value.nom) {				
				throw new NotUniqueException();
			}
		}
		array.add(value);
	}
}
