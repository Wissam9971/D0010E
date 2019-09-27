﻿package simulator;

public class Simulator {

	private SimState state;
	private EventQueue queue;

	/**
	 * Create a new instance of Simulator
	 * 
	 * @param state A simulator state
	 */
	public Simulator(SimState state, EventQueue queue) {
		this.state = state;
		this.queue = queue;
	}

	/**
	 * Run simulator
	 */
	public void run(){
		state.runSim();

		while (state.simulatorIsRunning) {
			queue.getEvent().runEvent();
			queue.removeEvent();
		}
	}

}
