package labb6;

import simState;
import sim.CarWashState;

public class EventStop extends Events {
	public EventStop(double time) {
		super(time);
	}

	@Override
	public void execute(Simstate state, EventQueue queue) {

		CarWashState CWState = (CarWashState) state;
		
		
		//STOPPAR SIMULERINGEN
		CWState.setStopped(true); //Uppdaterar CWState att avsulta tiden är inne

	}

}
