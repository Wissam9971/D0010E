

package lab2.level;

import java.util.ArrayList;
import java.util.Observable;

public class Level extends Observable {
	public ArrayList<Room> lista = new ArrayList<Room>(); // skappa en lista för att spara info om rummen.
	Room playerLocation;

	public boolean place(Room r, int x, int y) {
		r.x = x;
		r.y = y;
		if (overlap(r.x, r.y, r.height, r.width)) {
			return false;
		} else {
			lista.add(r);
			return true;
		}
	}

	/*
	 * Metoden overlap användas för att kolla om man kan ligga ett nytt rum på en
	 * viss plats om det är okej så adderar vi dimetioner i listan genom att använda
	 * metoden "palce" annars returner vi false
	 */
	public boolean overlap(int x, int y, int height, int width) {
		for (Room r : lista) {
			if (r.y + r.height >= y && r.x + r.width >= x && r.x <= x + width && r.y <= y + height)
				return true;
		}
		return false;
	}

	void moved() {
		setChanged();
		notifyObservers();
	}

	/**
	 *
	 */
	public void moveNorth() {
		if (playerLocation.northDoor != null) {
			playerLocation = playerLocation.northDoor;
			moved();
		}
	}

	/*
	 * If satsen här kollar först ifall dörren är inte stängt dvs inte = null om det
	 * var sant så går player till det andra rummet
	 */
	public void moveEast() {
		if (playerLocation.eastDoor != null) {
			playerLocation = playerLocation.eastDoor;
			moved();
		}
	}

	public void moveSouth() {
		if (playerLocation.southDoor != null) {
			playerLocation = playerLocation.southDoor;
			moved();
		}
	}

	public void moveWest() {
		if (playerLocation.westDoor != null) {
			playerLocation = playerLocation.westDoor;
			moved();
		}
	}

	public void firstLocation(Room r) {
		playerLocation = r;
	}
}
