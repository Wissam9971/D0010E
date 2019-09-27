
package lab2;

import java.awt.Color;

import lab2.level.Level;
import lab2.level.LevelGUI;
import lab2.level.Room;

public class Driver {

	public void run() {

		Level level = new Level();
		/*
		 * Har skappar vi nya rum med hight och width plus färgen.
//		 */
		Room r1 = new Room(100, 100, Color.red);
		Room r2 = new Room(34, 100, Color.green);
		Room r3 = new Room(134, 100, Color.blue);
		Room r4 = new Room(100, 80, Color.yellow);
		Room r5 = new Room(100, 92, Color.cyan);
		Room r6 = new Room(100, 111, Color.orange);
//
//		/*
//		 * If satsen används för att kolla om man kan skapa ett nytt rum på x,y om
//		 * rummer ligger på varndra så retunerar vi ett fel medelande och vi går ut ur
//		 * programmet
//		 */
	
		
		if (!level.place(r1, 50, 50)) {
			System.out.println("false1");

		}
		if (!level.place(r2, 50, 200)) {
			System.out.println("false2");

		}
		if (!level.place(r3, 50, 350)) {
			System.out.println("false3");

		}
		if (!level.place(r4, 400, 50)) {
			System.out.println("false111");

		}
		if (!level.place(r5, 400, 200)) {
			System.out.println("false5");

		}
		if (!level.place(r6, 400, 350)) {
			System.out.println("false6");

		}

		for (Room r : level.lista) {
			if(r==r2)
				r1.connectNorthTo(r2);
			if(r==r3)
				r2.connectNorthTo(r3);
			if(r==r4)
				r3.connectNorthTo(r4);
			if(r==r5)
				r4.connectNorthTo(r5);
			if(r==r6)
				r5.connectNorthTo(r6);
			if(r==r1)
				r6.connectNorthTo(r1);
			
			
		}
	

		
		//r2.connectNorthTo(r6);

		// FirstLocation kollar var börjar man och det är den svarta bollen
		level.firstLocation(r1);

		LevelGUI gui = new LevelGUI(level, "Spelet");

	}

}