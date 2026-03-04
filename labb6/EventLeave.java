package labb6;

public class EventLeave extends Events {
	
	public EventLeave(double time) {
		super(time);
	}
	
	
	@Override
	public void execute(SimState state, EventQueue queue) {
		
		CarWashState CWState = (CarWashState) state;
		
		
		//Bil färdigtvättat och lämnar -> maskin blir ledig
		CWState.leaveMachine();  //uppdaterar CWState att maskin ledig
		
		if (CWState.getQueueSize() > 0) {
			CWState.removeFromQueue(); //hämtar första bilen i kön
			CWState.occopyMachine(); //Uppdaterar CWState att maskin upptagen
			
			
			double finishTime = CWState.getTime() + CWState.getWashTime();
			queue.addEvent(new EventLeave(finishTime));
		}
		
	}
	

}
