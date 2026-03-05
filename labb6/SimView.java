package labb6;

/**
 * Denna klass har i uppgift att skapa vyn som representerar tillstånded av
 * simulatorn som visas i konsolen i text format. Den tar information och
 * skriver ut det i rätt händelseordning från EventQueue
 * 
 * @author André Angeria
 * @author Ekram Alhroub
 * @author Norhan Mousa
 */
public class SimView {

	private boolean firstUpdate = true;

	/**
	 * @param state Tillståndet vi befinner oss i simulationen
	 * @param event Som har hänt som ska printas ut
	 */
	public void update(SimState state, Event event) {
		CarWashState cw = (CarWashState) state;

		// Skriv ut huvudet endast vid simuleringens start
		if (firstUpdate) {
			printHeader(cw);
			firstUpdate = false;
		}

		// Hämta namnet på händelsen (t.ex. Arrive, Leave, Stop)
		String eventName = event.getClass().getSimpleName().replace("Event", "");

		// Printar ut i tabbelen Start vid tid 0.0, stop vid vald stoppunkt.
		// Och representerar vilket tillstånd bilen är i.
		// Bilen är i Arrive tillstånd tillsden lämnar då blir den -> Leave
		String idStr = (eventName.equals("Start") || eventName.equals("Stop")) ? "-"
				: String.valueOf(cw.getNextId() - 1);

		// (f=float) , (s=string) (d=heltal)
		// %6.2f = state.getTime()
		// %-8s = eventName
		// %3s = idStr
		// %4d = cw.getFastMachines()
		// %4d = cw.getSlowMachines()
		// %10.2f = cw.getTotalIdleTime()
		// %10.2f = cw.getTotalWaitingTime()
		// %10d = cw.getQueueSize()
		// %8d = cw.getRejectedCars()
		// %n = Ny rad
		System.out.printf("%6.2f %-8s %3s %4d %4d %10.2f %10.2f %10d %8d%n", state.getTime(), eventName, idStr,
				cw.getFastMachines(), cw.getSlowMachines(), cw.getTotalIdleTime(), cw.getTotalWaitingTime(),
				cw.getQueueSize(), cw.getRejectedCars());

		// Om simuleringen stannar, skriv ut sammanfattningen
		if (state.isStopped()) {
			printSummary(cw);
		}
	}

	// (s=string)
	// %6s = "Time" (6 bredd)
	// %-8s = "Event" (8 bredd, vänsterjusterad)
	// %3s = "Id" (3 bredd)
	// %4s = "Fast" (4 bredd)
	// %4s = "Slow" (4 bredd)
	// %10s = "IdleTime" (10 bredd)
	// %10s = "QueueTime" (10 bredd)
	// %10s = "QueueSize" (10 bredd)
	// %8s = "Rejected" (8 bredd)
	
	
	/**
	 * @param cw Printar ut information om tvätten, antal masiner, hur stor kön får vara
	 */
	private void printHeader(CarWashState cw) {
		System.out.printf("Fast machines: %d%n", cw.getFastMachines());
		System.out.printf("Slow machines: %d%n", cw.getSlowMachines());
		System.out.printf("Max Queue size: %d%n", cw.getMaxQueueSize());
		System.out.println("--------------------------------------------------------------------------------");
		System.out.printf("%6s %-8s %3s %4s %4s %10s %10s %10s %8s%n", "Time", "Event", "Id", "Fast", "Slow",
				"IdleTime", "QueueTime", "QueueSize", "Rejected");
	}

	
	
	// (f=flyttal, 2 decimaler) (d=heltal) (n=ny rad)
    // %.2f = cw.getTotalIdleTime()
    // %.2f = cw.getTotalWaitingTime()
    // %.2f = cw.getAverageWaitingTime()
    // %d   = cw.getRejectedCars()
	/**
	 * @param cw Printar ut en slutstatistik
	 */
	private void printSummary(CarWashState cw) {
		System.out.println("--------------------------------------------------------------------------------");
		System.out.printf("Total idle machine time: %.2f%n", cw.getTotalIdleTime());
		System.out.printf("Total queueing time: %.2f%n", cw.getTotalWaitingTime());
		System.out.printf("Mean queueing time: %.2f%n", cw.getAverageWaitingTime());
		System.out.printf("Rejected cars: %d%n", cw.getRejectedCars());
	}
}
