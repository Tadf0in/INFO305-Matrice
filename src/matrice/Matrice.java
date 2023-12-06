package matrice;
import java.util.ArrayList;

public class Matrice {
	public static void main(String[] args) {
		
		// Liste temporaire des vaisseaux qu'on souhaite ajouter
		ArrayList<Vaisseau> vaisseauxAAjouter = new ArrayList<Vaisseau>();
		vaisseauxAAjouter.add(new Vaisseau("Vaisseau 1", "guerre"));
		vaisseauxAAjouter.add(new Vaisseau("Vaisseau 2", "guerre"));
		vaisseauxAAjouter.add(new Vaisseau("Vaisseau 1", "transport"));
		vaisseauxAAjouter.add(new Vaisseau("Vaisseau 3", "transport"));
		
		// On vérifie chaque élément de la liste temporaire avant de l'ajouter dans la vraie liste
		ArrayList<Vaisseau> vaisseaux  = new ArrayList<Vaisseau>();
		for (Vaisseau v: vaisseauxAAjouter) {
			try {
				addUniqueVaisseau(vaisseaux, v); // Fonction définie plus bas			
			} catch (MatriceException e) {
				System.out.println(e);
			}
		}
		vaisseauxAAjouter = null; // On libère de la place en mémoire
		
		// Affichage de la liste du personnel
		for (Vaisseau v: vaisseaux) {
			System.out.println(v);
		}
		
		
		// Liste temporaire du personnel qu'on souhaite ajouter
		ArrayList<Personnel> personnelAAjouter = new ArrayList<Personnel>();
		personnelAAjouter.add(new OperateurSION("Morpheus", false, 50, "commandant", "communication"));
		personnelAAjouter.add(new MembreLibere("Neo", false, 25, "lieutenant"));
		personnelAAjouter.add(new MembreLibere("Neo", false, 26, "colonel")); // Test nom déjà utilisé
		personnelAAjouter.add(new MembreLibere("Noe", false, 27, "commandant")); // Test si continue d'ajouter malgré nom déjà utilisé plus haut
		
		// On vérifie chaque élément de la liste temporaire avant de l'ajouter dans la vraie liste
		ArrayList<Personnel> personnel = new ArrayList<Personnel>();
		for (Personnel p: personnelAAjouter) {
			try {
				addUniquePersonnel(personnel, p); // Fonction définie plus bas				
			} catch (MatriceException e) {
				System.out.println(e);
			}
		}
		personnelAAjouter = null; // On libère de la place en mémoire
		
		// Affichage de la liste du personnel
		for (Personnel p: personnel) {
			System.out.println(p);
		}
		
		System.out.println(vaisseaux.get(1).listePersonnel());
		try {
			vaisseaux.get(1).addPersonnel(personnel.get(0));
			vaisseaux.get(1).addPersonnel(personnel.get(1));
		} catch (MatriceException e) {
			System.out.println(e);
		}
		System.out.println(vaisseaux.get(1).listePersonnel());
		vaisseaux.get(1).delPersonnel(personnel.get(0).nom);
		System.out.println(vaisseaux.get(1).listePersonnel());
	}
	

	// Vérifie qu'il n'existe pas déjà un vaisseau avec le même nom avant d'ajouter dans la liste
	public static void addUniqueVaisseau(ArrayList<Vaisseau> array, Vaisseau value) throws MatriceException {
		for (Vaisseau v: array) {
			if (v.nom == value.nom) {				
				throw new MatriceException("Un vaisseau porte déjà ce nom");
			}
		}
		array.add(value);
	}
	
	// Vérifie qu'il n'existe pas déjà quelqu'un avec le même nom avant d'ajouter dans la liste
	public static void addUniquePersonnel(ArrayList<Personnel> array, Personnel value) throws MatriceException {
		for (Personnel p: array) {
			if (p.nom == value.nom) {				
				throw new MatriceException("Un membre du personnel porte déjà ce nom");
			}
		}
		array.add(value);
	}
}
