public class EventArrive extends Event {

	public EventArrive(double time) {
		super(time);
	}

	@Override 
	public void execute(SimState state, EventQueue queue) {
		
		CarWashState CWState = (CarWashState) state;
		
		// Skapar bilen med ID (t.ex. 0, 1, 2...)
		Car currentCar = new Car(CWState.getNextId());
		
		// Planera nästa ankomst
		double nextArrivalTime = CWState.getTime() + CWState.getNextArrivalTime();
		queue.addEvent(new EventArrive(nextArrivalTime));
		
		// PRIORITERING: Snabb maskin först
		if (CWState.hasFreeFastMachine()) { // Fixat: Parentes och CWState
			CWState.occupyFastMachine();
			
			double finishTime = CWState.getTime() + CWState.getFastWashTime();
			queue.addEvent(new EventLeave(finishTime, currentCar));
	
		} else if (CWState.hasFreeSlowMachine()) {
			CWState.occupySlowMachine();
			
			double finishTime = CWState.getTime() + CWState.getSlowWashTime();
			queue.addEvent(new EventLeave(finishTime));
            
		} else if (CWState.hasQueueSpace()) {
			CWState.addToQueue(currentCar);
            
		} else {
			CWState.registerRejected();
		}
	}
}