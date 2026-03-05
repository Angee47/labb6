package labb6;

/**
 * Denna klass har inställningarna för simulatorn
 * Gjord för att kunna skapa ny sorts simulation
 * 
 * @author André Angeria
 * @author Ekram Alhroub
 * @author Norhan Mousa
 */
public class SimSettings {

	// Antal maskiner för snabb/sölig samt köstorlek
	public static final int FAST_MACHINES = 2;
	public static final int SLOW_MACHINES = 2;
	public static final int MAX_QUEUE_SIZE = 5;

	// Tids och slumpinställningar
	public static final double LAMBDA = 2.0;
	public static final double FAST_MIN = 2.8;
	public static final double FAST_MAX = 4.6;
	public static final double SLOW_MIN = 3.5;
	public static final double SLOW_MAX = 6.7;

	public static final long SEED = 1234L;
	public static final double START_TIME = 0.0;
	public static final double STOP_TIME = 15.0;

}
