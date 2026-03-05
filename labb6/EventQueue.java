package labb6;

import java.util.PriorityQueue;

/**
 * Denna klass hanterar händelsekön. Den har en array med alla event som ska ske
 * och plannerar vilken ordning dem skall utföras, vilket beror på tidpunkt.
 * 
 * @author André Angeria
 * @author Ekram Alhroub
 * @author Norhan Mousa
 */
public class EventQueue {

	/**
	 * Denna metod sorterar händelserna baserat på dess tidpunkt.
	 */
	private PriorityQueue<Event> queue = new PriorityQueue<>();

	/**
	 * Lägger till en ny händelse i kön. e = händelsen som ska läggas in.
	 * 
	 * @param e Händelsen som ska schemaläggas
	 */
	public void add(Event e) {
		queue.add(e);
	}

	/**
	 * Hämtar nästa händelse i kön och tar bort den. Returnerar null om kön är tom.
	 * 
	 * @return
	 */
	public Event getNextEvent() {
		return queue.poll();
	}

	/**
	 * Kontrollerar om kön är tom.
	 * 
	 * @return True/False
	 */
	public boolean isEmpty() {
		return queue.isEmpty();
	}
}
