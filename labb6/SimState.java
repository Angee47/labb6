package labb6;

import java.util.Observable;

/**
 * Denna klass hanterar vilket tillstånd simulationen befinner sig i.
 * Klassen håller reda på tiden, om det finns mer evetnt att utföra forstätter den, annars avsluta.
 * När Tillståndet uppdateras skickas information till Vyn att nya events har skett
 * 
 * @author André Angeria
 * @author Ekram Alhroub
 * @author Norhan Mousa
 */
public class SimState extends Observable {

	//Private variablel för hålla koll på tiden.
	private double currentTime = 0;
	//Gör att simulationen ska stoppas
	private boolean stopped = false;

	
	
	/**
	 * @return Tiden i simulationen
	 */
	public double getTime() {
		return currentTime;
	}

	/**
	 * Uppdaterar tiden
	 * @param time Nya tiden
	 */
	public void setTime(double time) {
		this.currentTime = time;
	}

	/**
	 * Kontrollerar att simulationen har stoppas
	 * @return True om den har stoppats
	 */
	public boolean isStopped() {
		return stopped;
	}

	/**
	 * Sätter ett stopp att simuleringn ska avsluta
	 * @param stopped True om den har stoppats
	 */
	public void setStopped(boolean stopped) {
		this.stopped = stopped;
	}

	/**
	 * Meddelar observatören vår vy att tillståndet i simulatorn har ändrats.
	 * Vilket leder till att vyn skriver ut ny händelsen
	 */
	public void updateView() {
		setChanged();
		notifyObservers();
	}

}
