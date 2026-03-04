package labb6;

import java.util.ArrayList;
import labb6.ExponentialRandomStream;
import labb6.UniformRandomStream;

public class CarWashState extends SimState {

	// Private tvättider samt kö storlek
	private int fastMachines;
	private int slowMachines;
	private int maxQueueSize;

	// Private variabler som ändras under programmets simulering
	private ExponentialRandomStream arrivalStream;
	private UniformRandomStream fastWashStream;
	private UniformRandomStream slowWashStream;

	private int currentQueueSize = 0;
	private int rejectedCars = 0;
	private int nextCarId = 0; //

	// FIRST IN FIRST OUT genom array

	private ArrayList<Car> queue = new ArrayList<>();

	public CarWashState(int fast, int slow, int maxQueue, double lambda, double fastMin, double fastMax, double slowMin,
			double slowMax, long seed) {

		this.fastMachines = fast;
		this.slowMachines = slow;
		this.maxQueueSize = maxQueue;

		this.arrivalStream = new ExponentialRandomStream(lambda, seed);
		this.fastWashStream = new UniformRandomStream(fastMin, fastMax, seed);
		this.slowWashStream = new UniformRandomStream(slowMin, slowMax, seed);

	}

	// Hämtar händelser

	public double getNextArrivalTime() {
		return arrivalStream.next();
	}

	public double getFastWashTime() {
		return fastWashStream.next();
	}

	public boolean hasFreeFastMachine() {
		return fastMachines > 0;  //Check om ledig snabbmaskin
	}

	public void occupyFastMachine() {
		fastMachines--; // bil in i snabb maskin
	}
	public void leaveFastMachine() { //Lämna snabb maskin
		fastMachines++;
	}

	public boolean hasFreeSlowMachine() { //Check om lediga maskiner finns
		return slowMachines > 0;
	}

	public void occupySlowMachine() { //Bil in i -> slowmachine
		slowMachines--;
	}

	public void leaveSlowMachine() { //Bil lämnar SlowMachine
		slowMachines++;
	}

	public void addToQueue(Car car) { //Bil in i kö om fullt +1 rejected
		if (queue.size() < maxQueueSize) {
			queue.add(car);
		} else {
			rejectedCars++; // +1 rejected
		}
	}

	public Car removeFromQueue() {
		return queue.remove(0); // Tar bort första bilen i kön
	}

	public int getNextId() {
		return nextCarId;
		;
	}

}
