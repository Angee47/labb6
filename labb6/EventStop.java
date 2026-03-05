package labb6;

/**
 * Denna klass har i uppgift att stoppa simuleringen X 
 * När EventStop ska simuleringen statistik uppdateras en sista gång,
 * och skicka signalen att stanna simulationen.
 * 
 * @author André Angeria
 * @author Ekram Alhroub
 * @author Norhan Mousa
 */
public class EventStop extends Event {
	
	
	/**
	 * Skapar Eventet/Händlse stop vid en tidpunkt
	 * @param time tiden simulationen ska stoppas
	 */
	public EventStop(double time) {
		super(time);
	}

	
	
	/**
	 *Statistiken uppdateras en sista gång.
	 *Därefter skickas singalen att STANNA simuleringen
	 */
	@Override
	public void execute(SimState state, EventQueue queue) {

		CarWashState CWState = (CarWashState) state;
		
		// Uppdaterar statistik en sista gång vid stopp-tiden
		CWState.updateStats(this.getTime());
		
		//STOPPAR SIMULERINGEN
		CWState.setStopped(true); //Uppdaterar CWState att avsulta tiden är inne

	}

}