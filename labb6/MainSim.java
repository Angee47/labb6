package labb6;

public class MainSim {

	/**
	 * Startar programmet, skapar tillståndet för simulatorn som finns i SimSettings
	 * samt startar Simulationen
	 * 
	 * @author André Angeria
	 * @author Ekram Alhroub
	 * @author Norhan Mousa
	 */
	public static void main(String[] args) {

		// Hämtar
		CarWashState state = new CarWashState(SimSettings.FAST_MACHINES, SimSettings.SLOW_MACHINES,
				SimSettings.MAX_QUEUE_SIZE, SimSettings.LAMBDA, SimSettings.FAST_MIN, SimSettings.FAST_MAX,
				SimSettings.SLOW_MIN, SimSettings.SLOW_MAX, SimSettings.SEED);

		// Initiera händelsekön med start och stop tider
		EventQueue queue = new EventQueue();
		queue.add(new EventStart(SimSettings.START_TIME));
		queue.add(new EventStop(SimSettings.STOP_TIME));

		// Skapa vår vy för alla statistik
		SimView view = new SimView();
		Simulator sim = new Simulator(state, queue, view);

		sim.run();
	}
}