package matrice;

public class NotUniqueException extends Exception {
	public String toString() {
		return "La valeur à ajouter est déjà présente dans la liste";
	}
}