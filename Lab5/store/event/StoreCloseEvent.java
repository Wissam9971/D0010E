package store.event;

import simulator.Event;
import store.state.StoreState;


public class StoreCloseEvent extends Event {
	
	/**
	 * 
	 * @param state
	 * @param time
	 */

	public StoreCloseEvent(StoreState state, double time) {
		super(state);
		super.eventDescription = "Close";
		super.eventUserDescription = "-";
		super.executeTime = time;
	}
	/**
	 * This event closes the store
	 */
	public void runEvent() {
		StoreState s = (StoreState) state;
		s.updateState(this);
		s.closeStore();
	}

}
