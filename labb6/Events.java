package labb6;

public abstract class Events {

	protected double time;
	
	public Events(double time) {
		this.time = time; //sparar tiden i objektet
	}
	
	public double getTime() {
		return time;
	}
	
	public abstract void execute(SimState state, EventQueue queue);
	

}

