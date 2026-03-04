package labb6;

import events.Event;
import events.EventQueue;


// Klassen Simulator är "motorn" i simuleringen.
// Den kör alla händelser i ordning och uppdaterar tillståndet.
public class Simulator {

    // Det aktuella tillståndet i simuleringen, t.ex. Carwash
    private SimState state;

    // Prioritetskö med alla framtida händelser i simuleringen
    private EventQueue queue;

    // View som visar statistik och händelser
    private SimView view;

    // Konstruktor som tar emot simuleringens starttillstånd,
    // händelsekön och view
    public Simulator(SimState state, EventQueue queue, SimView view) {
        this.state = state; // Spara tillståndet
        this.queue = queue; // Spara kön med händelser
        this.view = view;   // Spara view som visar statistik
    }

    // Huvudloop som kör simuleringen
    public void run() {
        // Loopen fortsätter så länge det finns händelser kvar
        // och simuleringen inte har stoppats
        while (!queue.isEmpty() && !state.isStopped()) {

            // Hämtar nästa händelse från kön (den med lägst tid)
            Events nextEvent = queue.getNextEvent();

            // Uppdaterar simuleringens nuvarande tid till händelsens tid
            state.setTime(nextEvent.getTime());

            // Utför själva händelsen
            // Den kan ändra tillståndet, lägga till nya händelser i kön, osv.
            nextEvent.execute(state, queue);

            // Uppdaterar vyn med aktuell statistik och den senaste händelsen
            view.update(state, nextEvent);
        }
        // När loopen avslutas har simuleringen stoppats
        // eller alla planerade händelser har körts
    }
}