package labb6;

/**
 * Denna klass har i uppgift att hantera när en bil kommer till vår billtvätt
 * Detta sker i olika steg. Börjar med att uppdatera simulatorns till nutid
 * Skapar en bil samt kopplar ett id till den, därefter skapar ny händelse
 * Därefter kontrolleras ifall lediga maskiner finns, först snabba sen söliga Om
 * inga lediga in till kön.
 *
 * @author André Angeria
 * @author Ekram Alhroub
 * @author Norhan Mousa
 */

public class EventArrive extends Event {

	/**
	 * Skapar händelsen
	 * 
	 * @param time Aktuella tidpunkten
	 */
	public EventArrive(double time) {
		super(time);
	}

	/**
	 * Denna metod har logiken vad Arrive ska göra när en bil anlänt
	 */
	@Override
	public void execute(SimState state, EventQueue queue) {

		CarWashState CWState = (CarWashState) state;

		CWState.updateStats(this.getTime()); //

		// Skapar bilen med ID (t.ex. 0, 1, 2...)
		Car currentCar = new Car(CWState.getNextId());

		// Planera nästa ankomst
		double nextArrivalTime = CWState.getTime() + CWState.getNextArrivalTime();
		queue.add(new EventArrive(nextArrivalTime)); //

		// PRIO Snabb maskin först
		if (CWState.hasFreeFastMachine()) { // har koll på fast som prio
			CWState.occupyFastMachine(); // Ändrar tillstånded att tvätt blvit tagen

			// Skapar tiden för hur länge den tvättas
			double finishTime = CWState.getTime() + CWState.getFastWashTime();
			// Skapar nytt event med tvätttiden
			queue.add(new EventLeave(finishTime, "fast")); //

			// Ifall fast upptagna körs detta
		} else if (CWState.hasFreeSlowMachine()) {
			CWState.occupySlowMachine();

			double finishTime = CWState.getTime() + CWState.getSlowWashTime();
			// Skapar nytt event med söligatvät tiden
			queue.add(new EventLeave(finishTime, "slow")); //

		} else {
			// Stoppar bilen i kö samt har metoden för Rejected:
			CWState.addToQueue(currentCar); //
		}
	}
}