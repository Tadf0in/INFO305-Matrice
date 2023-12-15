package matrice;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Menu {	
	
	private static Scanner scanner = new Scanner(System.in);
	private static Matrice M = new Matrice();
	private static Flotte flotte = new Flotte();
	
	public static void afficherMenu() {
		System.out.println("\n1 - Ajouter un membre");
		System.out.println("2 - Ajouter un vaisseau");
		System.out.println("3 - Voir la liste de la flotte");
		System.out.println("4 - Affecter un membre à un vaisseau");
		System.out.println("5 - Désaffecter un membre d'un vaisseau");
		System.out.println("6 - Infiltrer un membre dans la matrice");
		System.out.println("7 - Exfiltrer un membre de la matrice");
		System.out.println("8 - Afficher la matrice");
		System.out.println("q - Quitter");
		System.out.print(">>> ");
	}
	
	public static void main(String[] args) {
		// Rain();
		System.out.println("BIENVENUE DANS LA MATRICE");
		
		boolean continuer = true;
		while (continuer) {
			afficherMenu();
			
			String c = scanner.next();
			switch (c) {
				// Ajoute un membre
				case "1":
					createPersonne();
					break;
					
				// Ajoute un vaisseau
				case "2":
					createVaisseau();
					break;
				
				// Voir la liste de la flotte
				case "3":
					System.out.println(flotte);
					break;
					
				// Affecter un membre à un vaisseau
				case "4":
					affecterMembre();
					break;	
					
				case "5":
					desaffecterMembre();
					break;
					
				// Affiche la matrice
				case "8":
					System.out.println(M);
					break;
				
				// Quitter le programme
				case "q":
					continuer = false;
					break;
					
				default:
					System.out.println("Choix invalide");	
			}
		}
		System.out.println("FIN DU PROGRAMME");
	}
	
	// Gère la création d'un nouveau membre
	public static void createPersonne() {
		// OPSion ou MembreLibere
		System.out.println("1 - Ajouter un opérateur SION");
		System.out.println("2 - Ajouter un membre libéré");
		System.out.print(">>> ");
		String category = scanner.next();
		if (!category.equals("1") && !category.equals("2")) {
			System.out.println("Choix invalide");
			return;
		}
		
		System.out.print("Nom : ");
		String nom = scanner.next();
		
		String g;	
		boolean genre;
		System.out.print("Genre (homme ou femme) : ");
		g = scanner.next();
		// Homme doit être soit homme soit femme
		if (g.equals("homme") || g.equals("femme")) {						
			genre = (g == "homme"); // si g = homme alors true sinon false car genre un bool dans Personnel
		} else {
			System.out.println("Genre incompris");
			return;
		}
		
		// Age doit être un entier
		int age;
		try {
			System.out.print("Age : ");
			age = scanner.nextInt();
		} catch (java.util.InputMismatchException e) {
			System.out.println("Âge incompris");
			return;
		}
		
		System.out.print("Grade : ");
		String grade = scanner.next();
		
		// Role pour OPSion
		if (category.equals("1")) {
			System.out.print("Rôle : ");
			String role = scanner.next();
			
			// Crée l'opérateur SION
			try {
				flotte.addUniquePersonnel(new OperateurSION(nom, genre, age, grade, role));
			} catch (MatriceException e) {
				System.out.println(e);
			}
		} else {
			// Crée le membre libéré
			try {
				flotte.addUniquePersonnel(new MembreLibere(nom, genre, age, grade));
			} catch (MatriceException e) {
				System.out.println(e);
			}
		}
	}
	
	// Gère la création d'un vaisseau
	public static void createVaisseau() {
		System.out.print("Nom : ");
		String nom = scanner.next();
		
		System.out.print("Type : ");
		String type = scanner.next();
		
		try {
			flotte.addUniqueVaisseau(new Vaisseau(nom, type));
		} catch (MatriceException e) {
			System.out.println(e);
		}
	}
	
	// Gère l'affectation des membres dans un vaisseau
	public static void affecterMembre() {
		// Vaisseau dans lequel affecter
		System.out.println("Vaisseau : ");
		Vaisseau v = flotte.getVaisseau(scanner.next());
		if (v == null) {
			System.out.print("Ce vaisseau n'existe pas");
			return;
		}
		
		// Membre à affecter
		System.out.print("Membre : ");
		Personnel p = flotte.getPersonnel(scanner.next());
		if (p == null) {
			System.out.println("Ce membre n'existe pas");
			return;
		}
		
		// Affecte le membre dans le vaisseau
		try {			
			v.affecter(p);
		} catch (MatriceException e) {
			System.out.println(e);
		}
	}
	
	// Gère la désaffectation d'un membres de son vaisseau
		public static void desaffecterMembre() {			
			// Membre à désaffecter
			System.out.print("Membre : ");
			Personnel p = flotte.getPersonnel(scanner.next());
			if (p == null) {
				System.out.println("Ce membre n'existe pas");
				return;
			}
			
			// Affecte le membre dans le vaisseau
			if (p.isAffected()) {				
				p.getVaisseau().desaffecter(p);
			}
		}
	
	// Test bonus : affichage symboles verts comme dans matrix
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
