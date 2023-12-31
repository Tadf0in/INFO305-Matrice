// Ancienne version du fichier main (avant le menu)
// Je l'ai laissé uniquement pour garder une trace


package matrice;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class AncienMain {
	public static void main(String[] args) {
		
		// Rain();
		
		Flotte flotte = new Flotte();
		
		// Liste temporaire des vaisseaux qu'on souhaite ajouter
		// On passe par un liste pour pas avoir à faire le try catch à chaque ajout
		ArrayList<Vaisseau> vaisseauxAAjouter = new ArrayList<Vaisseau>();
		vaisseauxAAjouter.add(new Vaisseau("Vaisseau 1", "guerre"));
		vaisseauxAAjouter.add(new Vaisseau("Vaisseau 2", "guerre"));
		vaisseauxAAjouter.add(new Vaisseau("Vaisseau 1", "transport"));
		vaisseauxAAjouter.add(new Vaisseau("Vaisseau 3", "transport"));
		// Ajout de la liste temporaire dans la flotte
		flotte.addVaisseau(vaisseauxAAjouter);
		
		
		// Liste temporaire du personnel qu'on souhaite ajouter
		// On passe par un liste pour pas avoir à faire le try catch à chaque ajout
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
		
		
		// Crée et affiche la matrice
		Matrice M = new Matrice();
		System.out.println(M);
		System.out.println(M.listeMembres());
		
		// test infiltration sans vaisseau sécurisé
		try {				
			((MembreLibere) flotte.getPersonnel("Neo")).infiltrer(M); 
		} catch (MatriceException e) {
			System.out.println(e);
		}
		// Sécurise le vaisseau puis retest
		try {
			v1.affecter(flotte.getPersonnel("Morpheus"));
			v1.affecter(flotte.getPersonnel("Neo")); // Test affecter membre déjà affecté
		} catch (MatriceException e) {
			System.out.println(e);
		}
		// infiltre la matrice depuis vaisseau sécurisé
		try {
			((MembreLibere) flotte.getPersonnel("Neo")).infiltrer(M); 
		} catch (MatriceException e) {
			System.out.println(e);
		}

		// Affiche la matrice
		System.out.println(M);
		System.out.println(M.listeMembres());
		
		System.out.println(M.agentPlusProche((MembreLibere)flotte.getPersonnel("Neo"))); // Test la fonction agentPlusProche
		
		// Exfiltre la matrice
		try {
			((MembreLibere) flotte.getPersonnel("Neo")).exfiltrer(M);
		} catch (MatriceException e) {
			System.out.println(e);
		}
		System.out.println(flotte.getPersonnel("Neo")); // Vérifie l'incrément de nb_infiltration
		
		// Affiche la matrice
		System.out.println(M);
		System.out.println(M.listeMembres());
	}
	
	// Bonus : affichage symboles verts comme dans matrix
	public static void Rain() {
		ArrayList<String> chars = new ArrayList<String>(Arrays.asList("&","é","'","(","-","è","_","ç","à",")","=","°","+","/","*","~","#","{","[","|","`","\\","^","@","]","}"));
		
		int NB_COLONNES = 80;
		int NB_LIGNES = 150;
		
		// Définit des tailles aléatoires de colonnes
		int[] long_colonnes = new int[NB_COLONNES];
		for (int j=0; j<NB_COLONNES; j++) {
			long_colonnes[j] = (int) (NB_LIGNES - (Math.random() * (NB_LIGNES - NB_LIGNES*0.2)));
		}
		
		System.out.print("\u001B[32m"); // Couleur verte
		for (int i=0; i<NB_LIGNES; i++) {
			for (int j=0; j<NB_COLONNES; j++) {
				if (long_colonnes[j] >= i) {
					// Affiche selon la taille e la colonne définie plus haut
					System.out.print(chars.get((int)(Math.random() * (chars.size()-1))) + " ");
				} else {	
					// Sinon colonne terminée = affiche un espace
					System.out.print("  ");
				}
			}
			// Attend avant d'afficher la suite pour faire un meilleur effet
			try {						
				TimeUnit.MILLISECONDS.sleep(10);
			} catch (InterruptedException e) {
				
			}
			System.out.print("\n"); // Remet couleur en blanc
		}
		System.out.print("\u001B[0m");
	}
}
