package labb6;

/**
 * Denna klass hanterar startpunkten för simueringen Den startar på tidpunkt 0
 * och ska därefter ta in första bilen i tvätten.
 * 
 * @author André Angeria
 * @author Ekram Alhroub
 * @author Norhan Mousa
 */
public class EventStart extends Event {

	/**
	 * Skapar starten vid tiden 0.0
	 * 
	 * @param time tiden simuleringen ska starta
	 */
	public EventStart(double time) {
		super(time);
	}

	/**
	 * Skapar en start händelse som har i uppgift att starta igång simuleringen.
	 * 
	 * @param Simulatorn tillstånd
	 * @param queue      lägger till händelsen till kön
	 */
	@Override
	public void execute(SimState state, EventQueue queue) {

		CarWashState cwState = (CarWashState) state;

		// Beräkna tiden för första bilen som kommer till biltvätten
		double firstArrival = cwState.getTime() + cwState.getNextArrivalTime();

		// Skapar event för första billen för att kicka igång simulerigen
		queue.add(new EventArrive(firstArrival));
	}
}