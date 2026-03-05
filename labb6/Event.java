package labb6;

/**
 * Vi implementerar Comparable så att PriorityQueue kan sortera på tid. Alla
 * olika typer av Events ärver från denna klass (Arrive,leave m.m) Klassen
 * hanteras med tid så event utförs i korrekt ordning.
 * 
 * @author André Angeria
 * @author Ekram Alhroub
 * @author Norhan Mousa
 */

public abstract class Event implements Comparable<Event> {

	protected double time;

	/**
	 * Skapar ny händelse med tid som parameter
	 * 
	 * @param time Tidpunkten händelsen inträffar
	 */
	public Event(double time) {
		this.time = time;
	}

	/**
	 * @return Retunerar händelsens tidpunkt
	 */
	public double getTime() {
		return time;
	}

	/**
	 * Den här metoden talar om för kön hur den ska jämföra två händelser. Genom att
	 * jämföra dess tider och sorterar där att tidigast sker först
	 * 
	 * @param Other är den andra händelsen/event som jämnförs med
	 * @return -1 då andra eventet sker före, 1 då den sker efter och 0 om dem sker
	 *         samtidigt
	 */
	@Override
	public int compareTo(Event other) {
		if (this.time < other.time) {
			return -1;
		} else if (this.time > other.time) {
			return 1;
		}
		return 0;
	}

	/**
	 * Metoden som utför händelsens och dens uppgift och uppdaterar simulatorns
	 * tillstånd. Alla olika events/händelser implenterar denna metod så att
	 * tillstånded kan uppdateras
	 * 
	 * @param state Tillstånded simulatorn är i just då
	 * @param queue Refferar till händelsekön, som inneåller alla händelser som ska
	 *              utföras
	 */
	public abstract void execute(SimState state, EventQueue queue);
}