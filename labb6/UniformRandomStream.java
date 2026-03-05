
package labb6;

import java.util.Random;


/**
 * Skapar slumtal som används i simulationen
 * Används för att skapa slumptider, max och min tider för tvätt av en bil
 * @author André Angeria
 * @author Ekram Alhroub
 * @author Norhan Mousa
 */
public class UniformRandomStream {

	private Random rand;
	private double lower, width;
	
	
	
	/**
	 * @param lower kortaste tiden
	 * @param upper högsta tiden
	 * @param seed  seed för simulationen
	 */
	public UniformRandomStream(double lower, double upper, long seed) {
		rand = new Random(seed);
		this.lower = lower;
		this.width = upper-lower;
	}
	
	/**
	 * @param lower lägsta tid
	 * @param upper högsta tid
	 */
	public UniformRandomStream(double lower, double upper) {
		rand = new Random();
	    this.lower = lower;
	    this.width = upper-lower;
	}
	
	/**
	 * Skapar nästa slumptal
	 * @return ett tal 
	 */
	public double next() {
	    return lower+rand.nextDouble()*width;
	}
}

