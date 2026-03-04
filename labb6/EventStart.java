package labb6;
import sim.SimState
import sim.CarWashState;

public class EventStart extends Events {
	
	public EventStart(double time) {
		super(time);
	}
	
	@Override
	public void execute(Simstate state, EventQueue queue) {
		
		CarWashState CWState = (CarWashState) state;
		
		
		//Startar simulatorn med första arrive
		double firstArrival = CWState.getTime() + CWState.getNExtArrivalInterval();
		queue.addEvent(new EventArrive(firstArrival));
	}

}
