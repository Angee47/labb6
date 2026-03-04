package labb6;


import sim.Simstate;

public abstract class Events {
	
	public Events(double time) {
		this.time = time; //sparar tiden
	}
	
	public double getTime() {
		return time;
	}
	
	public abstract void execute(Simstate state, EventQueue queue);
	

}
