package labb6;

/**
 * @author André Angeria
 * @author Ekram Alhroub
 * @author Norhan Mousa
 */

/**
 * Anvarar för logiken bakom Leave Eventet, som är när en bil blivit
 * färdigtvättat. Då sker följande: Börjar med att uppdatera statistiken. Säger
 * till tillstånded(CarWashState) att ledig tvätt finns Finns det bilar i kö ta
 * in ny bil
 *
 */
public class EventLeave extends Event {

	// Variabel för maskintyp
	private String machineType;

	/**
	 * Skapar Leave eventet
	 * 
	 * @param time Tiden just då i simuleringen
	 * @param type Vilken maskin som använts av bilen som tvättas
	 */
	public EventLeave(double time, String type) {
		super(time);
		this.machineType = type;
	}

	/**
	 * Här finns vad leave event ska utföra. Uppdatera statisitk Frigöra maskiner ->
	 * kolla om nya bil ska in
	 *
	 */
	@Override
	public void execute(SimState state, EventQueue queue) {
		CarWashState cw = (CarWashState) state;

		// Uppdatera statistiken

		cw.updateStats(this.getTime());

		// 2. Frigör maskin fast samt slow
		if (this.machineType.equals("fast")) {
			cw.leaveFastMachine();
		} else {
			cw.leaveSlowMachine();
		}

		// 3. Bilar i kö -> in i tvätten
		if (cw.getQueueSize() > 0) {
			// Hämtar första bilen i kön
			Car nextCar = cw.removeFromQueue();

			// Kolla vilken maskin som precis blev ledig (eller om en snabb finns)
			// Prioritering enligt labbanvisning: Snabb maskin först
			if (cw.hasFreeFastMachine()) {
				cw.occupyFastMachine();

				// Tiden det tog för en snabbtvätt, skapar Leaeve eventet
				double finishTime = cw.getTime() + cw.getFastWashTime();
				queue.add(new EventLeave(finishTime, "fast"));

			} else if (cw.hasFreeSlowMachine()) {
				cw.occupySlowMachine();
				// Tiden för söligtvätt, skapa leave event
				double finishTime = cw.getTime() + cw.getSlowWashTime();
				queue.add(new EventLeave(finishTime, "slow"));
			}
		}
	}
}