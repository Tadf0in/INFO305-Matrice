package matrice;

public class MatriceException extends Exception {
	private String message;
	
	// Générateur avec message personnalisé
	public MatriceException(String message) {
		this.message = message;
	}
	
	// Générateur par défaut
	public MatriceException() {
		this.message = "Il y a un bug dans la matrice";
	}
	
	public String toString() {
		return message;
	}
}