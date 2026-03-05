
package labb6;

import java.util.Random;

/**
 * Klass gjord för att kunna slumpa tal som ska användas i simuleringen
 * 
 * @author André Angeria
 * @author Ekram Alhroub
 * @author Norhan Mousa
 */
public class ExponentialRandomStream {

	private Random rand;
	private double lambda;

	/**
	 * @param lambda för slumpatal
	 * @param seed   Simuleringens seed
	 */
	public ExponentialRandomStream(double lambda, long seed) {
		rand = new Random(seed);
		this.lambda = lambda;
	}

	/**
	 * @param lambda slumpa tal
	 */
	public ExponentialRandomStream(double lambda) {
		rand = new Random();
		this.lambda = lambda;
	}

	/**
	 * @return skapa nästa slumptal som ska användas
	 */
	public double next() {
		return -Math.log(rand.nextDouble()) / lambda;
	}
}
