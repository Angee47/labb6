package labb6;

/**
 * 
 * Denna klass hanterar objektet bil som i simuleringen refflekterar ett
 * id(nummer)
 * 
 * @author André Angeria
 * @author Ekram Alhroub
 * @author Norhan Mousa
 */
public class Car {
	private int id;

	public Car(int id) {
		this.id = id;
	}

	/**
	 * Metod för retunera bilens id
	 * 
	 * @return Bilens id i heltal
	 */
	public int getId() {
		return id;
	}

}
