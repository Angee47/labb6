package labb6;

import java.util.ArrayList;

/**
 * CarWashState är som en hanterare. Det håller koll på vilket tillstånd
 * simulering är i. Den hanterar billtvättens kö, maskiner, samt statistiken som
 * SimView behöver för att skriva tabbelen. Där ingår tider, storlek på kön,
 * rejected antal tvättadebilar m.m
 * 
 * @author André Angeria
 * @author Ekram Alhroub
 * @author Norhan Mousa
 */
public class CarWashState extends SimState {

	// Private tvättider samt kö storlek
	private int fastMachines;
	private int slowMachines;
	private int maxQueueSize;

	// Private variabler som ändras under programmets simulering
	private ExponentialRandomStream arrivalStream;
	private UniformRandomStream fastWashStream;
	private UniformRandomStream slowWashStream;

	private int rejectedCars = 0;
	private int nextCarId = 0; //
	private int washedCars = 0;
	private int totalQueuedCars = 0;
	private int finishedQueuing = 0;

	// Variabler för tider
	private double lastEventTime = 0;
	private double totalWaitingTime = 0;
	private double fastBusyTime = 0;
	private double slowBusyTime = 0;
	private double totalIdleTime = 0;

	private final int initialFastMachines;
	private final int initialSlowMachines;

	// FIRST IN FIRST OUT genom array

	private ArrayList<Car> queue = new ArrayList<>();

	public CarWashState(int fast, int slow, int maxQueue, double lambda, double fastMin, double fastMax, double slowMin,
			double slowMax, long seed) {

		this.initialFastMachines = fast;
		this.initialSlowMachines = slow;
		this.fastMachines = fast;
		this.slowMachines = slow;
		this.maxQueueSize = maxQueue;

		this.arrivalStream = new ExponentialRandomStream(lambda, seed);
		this.fastWashStream = new UniformRandomStream(fastMin, fastMax, seed);
		this.slowWashStream = new UniformRandomStream(slowMin, slowMax, seed);

	}

	// Hämtar händelser

	/**
	 * @return Tiden som går till nästa bil kommer till simulatorn
	 */
	public double getNextArrivalTime() {
		return arrivalStream.next();
	}

	/**
	 * @return Tvättiden för en snabb maskin (slumpmässig)
	 */
	public double getFastWashTime() {
		return fastWashStream.next();
	}

	/**
	 * @return Tvättiden för en sölig maskin (slumpmässig)
	 */
	public double getSlowWashTime() {
		return slowWashStream.next();
	}

	/**
	 * @return check om det finns ledig maskin
	 */
	public boolean hasFreeFastMachine() {
		return fastMachines > 0;
	}

	/**
	 * Visar att en bil åkt in i snabbmaskin
	 */
	public void occupyFastMachine() {
		fastMachines--;
	}

	/**
	 * Bil lämnar snabb maskin
	 */
	public void leaveFastMachine() {
		fastMachines++;
		washedCars++;
	}

	/**
	 * @return Check om lediga maskiner finns
	 */
	public boolean hasFreeSlowMachine() {
		return slowMachines > 0;
	}

	/**
	 * Bil in i -> slowmachine
	 */
	public void occupySlowMachine() {
		slowMachines--;
	}

	/**
	 * Bil lämnar SlowMachine
	 */
	public void leaveSlowMachine() {
		slowMachines++;
		washedCars++;
	}

	/**
	 * @param car Bil hamnar i kön om plats annars -> rejected
	 */
	public void addToQueue(Car car) {
		if (queue.size() < maxQueueSize) {
			queue.add(car);
			totalQueuedCars++;
		} else {
			rejectedCars++;
		}
	}

	/**
	 * @return Ta bort bil från kön (första bilen)
	 */
	public Car removeFromQueue() {
		finishedQueuing++;
		return queue.remove(0);
	}

	/**
	 * @return retunerar ett unikt id för nästa bil
	 */
	public int getNextId() {
		return nextCarId++;
	}

	/**
	 * Uppdaterar simulationenstillstånd, kopplat till tid som passerat sen senaste
	 * event. Hanterar väntetiden i kön, tvättiden för snabb/sölig och tiden när de
	 * inte använts.
	 * 
	 * 
	 * @param currentTime Nuvarande tidspunkten i simulationen
	 */
	public void updateStats(double currentTime) {

		// tid som gått sedan senaste event
		double deltaTime = currentTime - lastEventTime;

		// Total väntetid
		totalWaitingTime += deltaTime * queue.size();

		// Uppdaterar tvättdien för snabb maskin
		fastBusyTime += deltaTime * (initialFastMachines - fastMachines);
		// uppdaterar tvättdiden för sölig maskin
		slowBusyTime += deltaTime * (initialSlowMachines - slowMachines);

		// Uppdaterar tiden som går då maskin inte använts
		totalIdleTime += deltaTime * (fastMachines + slowMachines);

		// Sätter nuvarande tid som refferens till senaste event
		lastEventTime = currentTime;
	}

	/**
	 * Könsstorlek
	 * @return Könsstorlek
	 */
	public int getQueueSize() {
		return queue.size();
	}

	/**
	 * Könsmaximala storlek
	 * @return Könsmaximala storlek
	 */
	public int getMaxQueueSize() {
		return maxQueueSize;
	}

	/**
	 * Antal bilar som blivit Rejected
	 * @return Antal bilar som blivit Rejected :/
	 */
	public int getRejectedCars() {
		return rejectedCars;
	}

	/**
	 * Antal tvättade bilar
	 * @return Antal tvättade bilar
	 */
	public int getWashedCars() {
		return washedCars;
	}

	/**
	 * Antal i kön under hela simuleringen
	 * @return Antal i kön under hela simuleringen
	 */
	public int getTotalQueuedCars() {
		return totalQueuedCars;
	}

	/**
	 * Antal snabba maskiner
	 * @return Antal snabba maskiner
	 */
	public int getFastMachines() {
		return fastMachines;
	}

	/**
	 * Antal söliga maskiner
	 * @return Antal söliga maskiner
	 */
	public int getSlowMachines() {
		return slowMachines;
	}

	/**
	 * Totala väntetiden
	 * @return Totala väntetiden
	 */
	public double getTotalWaitingTime() {
		return totalWaitingTime;
	}

	/**
	 * Medel väntetiden
	 * @return Medel väntetiden (Totaltid som gått / Alla bilar i som varit i
	 *         simuleringen
	 */
	public double getAverageWaitingTime() {
		int fastInMachine = initialFastMachines - fastMachines; // Inuti fast
		int slowInMachine = initialSlowMachines - slowMachines; // initu slow
		int totalInSystem = washedCars + fastInMachine + slowInMachine + queue.size(); // Totalla bilar i HELA systemet
		if (washedCars == 0)
			return 0;
		return totalWaitingTime / totalInSystem;
	}

	/**
	 * Tiden som snabb maskin arbetat
	 * @return Tiden som snabb maskin arbetat
	 */
	public double getFastBusyTime() {
		return fastBusyTime;
	}

	/**
	 * Tiden som sölig maskin arbetat
	 * @return Tiden som sölig maskin arbetat
	 */
	public double getSlowBusyTime() {
		return slowBusyTime;
	}

	/**
	 * Totala tiden maskiner arbetat
	 * @return Tiden som snabb+sölig arbetat
	 */
	public double getTotalBusyTime() {
		return fastBusyTime + slowBusyTime;
	}

	/**
	 * Totala tiden maskinerna stått stilla
	 * @return Totala tiden maskinerna stått stilla
	 */
	public double getTotalIdleTime() {
		return totalIdleTime;
	}

	/**
	 * Hur effektiva tvättarna varit igång
	 * @return Hur aktiva tvättarna har varit under simulering (Är som en bedöming
	 *         0.0 -> 1.0)
	 */
	public double getUtilization() {
		if (getTime() == 0)
			return 0;
		return (fastBusyTime + slowBusyTime) / (getTime() * (initialFastMachines + initialSlowMachines));
	}
}
