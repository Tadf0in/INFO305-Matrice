package matrice;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		
		Flotte flotte = new Flotte();
		
		// Liste temporaire des vaisseaux qu'on souhaite ajouter
		ArrayList<Vaisseau> vaisseauxAAjouter = new ArrayList<Vaisseau>();
		vaisseauxAAjouter.add(new Vaisseau("Vaisseau 1", "guerre"));
		vaisseauxAAjouter.add(new Vaisseau("Vaisseau 2", "guerre"));
		vaisseauxAAjouter.add(new Vaisseau("Vaisseau 1", "transport"));
		vaisseauxAAjouter.add(new Vaisseau("Vaisseau 3", "transport"));
		// Ajout de la liste temporaire dans la flotte
		flotte.addVaisseau(vaisseauxAAjouter);
		
		
		// Liste temporaire du personnel qu'on souhaite ajouter
		ArrayList<Personnel> personnelAAjouter = new ArrayList<Personnel>();
		personnelAAjouter.add(new OperateurSION("Morpheus", false, 50, "commandant", "communication"));
		personnelAAjouter.add(new MembreLibere("Neo", false, 25, "lieutenant"));
		personnelAAjouter.add(new MembreLibere("Neo", false, 26, "colonel")); // Test nom déjà utilisé
		personnelAAjouter.add(new MembreLibere("Noe", false, 27, "commandant")); // Test si continue d'ajouter malgré nom déjà utilisé plus haut
		// Ajout de la liste temporaire dans la flotte
		flotte.addPersonnel(personnelAAjouter);
		
		System.out.println(flotte);
		
		Vaisseau v1 = flotte.getVaisseau("Vaisseau 1");
		System.out.println(v1.listePersonnel());
		try {
			// Assigne personnel à v1
			v1.affecter(flotte.getPersonnel("Morpheus"));
			v1.affecter(flotte.getPersonnel("Neo"));
		} catch (MatriceException e) {
			System.out.println(e);
		}
		System.out.println(v1.listePersonnel());
		v1.desaffecter(flotte.getPersonnel("Morpheus"));
		System.out.println(v1.listePersonnel());
		
		System.out.println(flotte);
		
		System.out.println(flotte.getPersonnel("Morpheus").isAffected());
		System.out.println(flotte.getPersonnel("Neo").isAffected());
		
		
		Matrice m = new Matrice();
		System.out.println(m);
	}
	
}
