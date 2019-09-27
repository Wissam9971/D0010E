package lab2.level;

import java.awt.Color;
import java.awt.Graphics;

public class Room {
	private int doorWidth = 32;
	private int doorHeight = 16;
	private int playerSize = 16;
	int height, width;
	int x, y;
	Room northDoor, eastDoor, southDoor, westDoor;
	Color color;

	public Room(int width, int height, Color color) {
		this.color = color;
		northDoor = null;
		eastDoor = null;
		southDoor = null;
		westDoor = null;
		this.width = width;
		this.height = height;
		System.out.println("Detta rummet Är: " + width + "bred, " + height + " höjd" + " och har färgen " + color);
	}

	/*
	 * Kan bara linka dörren en gång.
	 */
	public void connectNorthTo(Room r) {
		if (northDoor == null)
			northDoor = r;
	}

	public void connectEastTo(Room r) {
		if (eastDoor == null)
			eastDoor = r;
	}

	public void connectSouthTo(Room r) {
		if (southDoor == null)
			southDoor = r;
	}

	public void connectWestTo(Room r) {
		if (westDoor == null)
			westDoor = r;
	}

	public void Draw(Graphics g, Room playerLocation) {
		g.setColor(this.color);// Rectanglarna
		g.fillRect(this.x, this.y, this.width, this.height);

		g.setColor(color.BLACK);// Border
		g.drawRect(this.x, this.y, this.width, this.height);
		g.drawRect(this.x + 1, this.y + 1, this.width - 2, this.height - 2);

		if (this.northDoor != null) {
			g.drawLine(x + (width / 2), this.y, this.northDoor.x + (this.northDoor.width / 2),
					this.northDoor.y + this.northDoor.height);// Line

			g.setColor(northDoor.color);
			g.fillRect(this.x + (this.width / 2) - (this.doorWidth / 2), this.y - (this.doorHeight / 2), this.doorWidth,
					this.doorHeight);
			g.setColor(color.black);// border
			g.drawRect(this.x + (this.width / 2) - (this.doorWidth / 2), this.y - (this.doorHeight / 2), this.doorWidth,
					this.doorHeight);

		} else {
			g.setColor(color.white);
			g.fillRect(this.x + (this.width / 2) - (this.doorWidth / 2), this.y - (this.doorHeight / 2), this.doorWidth,
					this.doorHeight);
			g.setColor(color.black);// border
			g.drawRect(this.x + (this.width / 2) - (this.doorWidth / 2), this.y - (this.doorHeight / 2), this.doorWidth,
					this.doorHeight);
		}

		if ( this.eastDoor != null) {
			g.drawLine(this.x + this.width, this.y + (this.height / 2), this.eastDoor.x,
					this.eastDoor.y + (this.eastDoor.height / 2));

			g.setColor(eastDoor.color);
			g.fillRect(this.x + this.width - (this.doorHeight / 2), this.y + (this.height / 2) - (this.doorWidth / 2),
					doorHeight, doorWidth);
			g.setColor(color.black);// border
			g.drawRect(this.x + this.width - (this.doorHeight / 2), this.y + (this.height / 2) - (this.doorWidth / 2),
					doorHeight, doorWidth);
		} else {
			g.setColor(color.white);
			g.fillRect(this.x + this.width - (this.doorHeight / 2), this.y + (this.height / 2) - (this.doorWidth / 2),
					doorHeight, doorWidth);
			g.setColor(color.black);// border
			g.drawRect(this.x + this.width - (this.doorHeight / 2), this.y + (this.height / 2) - (this.doorWidth / 2),
					doorHeight, doorWidth);
		}

		if ( this.southDoor != null) {
			g.drawLine(this.x + (this.width / 2), this.y + this.height, this.southDoor.x + (this.southDoor.width / 2),
					this.southDoor.y);

			g.setColor(southDoor.color);
			g.fillRect(this.x + (this.width / 2) - (this.doorWidth / 2), this.y + this.height - (this.doorHeight / 2),
					this.doorWidth, this.doorHeight);
			g.setColor(color.black);
			g.drawRect(this.x + (this.width / 2) - (this.doorWidth / 2), this.y + this.height - (this.doorHeight / 2),
					this.doorWidth, this.doorHeight);
		} else {
			g.setColor(color.white);
			g.fillRect(this.x + (this.width / 2) - (this.doorWidth / 2), this.y + this.height - (this.doorHeight / 2),
					this.doorWidth, this.doorHeight);
			g.setColor(color.black);
			g.drawRect(this.x + (this.width / 2) - (this.doorWidth / 2), this.y + this.height - (this.doorHeight / 2),
					this.doorWidth, this.doorHeight);
		}

		if ( this.westDoor != null) {
			g.drawLine(this.x, this.y + (this.height / 2), this.westDoor.x + this.westDoor.width,
					this.westDoor.y + (this.westDoor.height / 2));

			g.setColor(westDoor.color);
			g.fillRect(this.x - (this.doorHeight / 2), this.y + (this.height / 2) - (this.doorWidth / 2),
					this.doorHeight, this.doorWidth);
			g.setColor(color.black);
			g.drawRect(this.x - (this.doorHeight / 2), this.y + (this.height / 2) - (this.doorWidth / 2),
					this.doorHeight, this.doorWidth);
		} else {
			g.setColor(color.white);
			g.fillRect(this.x - (this.doorHeight / 2), this.y + (this.height / 2) - (this.doorWidth / 2),
					this.doorHeight, this.doorWidth);
			g.setColor(color.black);
			g.drawRect(this.x - (this.doorHeight / 2), this.y + (this.height / 2) - (this.doorWidth / 2),
					this.doorHeight, this.doorWidth);
		}

		if (this == playerLocation) {
			g.fillOval(playerLocation.x + (playerLocation.width / 2) - (playerSize / 2),
					playerLocation.y + (playerLocation.height / 2) - (playerSize / 2), playerSize, playerSize);
		}

	}

}