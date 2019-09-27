
package lab4.data;

import java.util.Arrays;
import java.util.Observable;

/**
 * Represents the 2-d game grid
 */

public class GameGrid extends Observable {

	public final static int EMPTY = 0;
	public final static int ME = 1;
	public final static int OTHER = 2;
	private final static int INROW = 5;
	static int grid[][];

	/**
	 * Constructor
	 * 
	 * @param size The width/height of the game grid
	 */
	public GameGrid(int size) {
		grid = new int[size][size];
		for (int x = 0; x < grid.length; x++) {
			for (int y = 0; y < grid[x].length; y++) {
				grid[x][y] = EMPTY;
			}
		}

	}

	/**
	 * Reads a location of the grid
	 * 
	 * @param x The x coordinate
	 * @param y The y coordinate
	 * @return the value of the specified location
	 */
	public int getLocation(int x, int y) {
		return grid[x][y];
	}

	/**
	 * Returns the size of the grid
	 * 
	 * @return the grid size
	 */
	public int getSize() {
		return grid.length;
	}

	/**
	 * Enters a move in the game grid
	 * 
	 * @param x      the x position
	 * @param y      the y position
	 * @param player
	 * @return true if the insertion worked, false otherwise
	 */
	public boolean move(int x, int y, int player) {
		if (getLocation(x, y) == EMPTY) {
			grid[x][y] = player;
			setChanged();
			notifyObservers();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Clears the grid of pieces
	 */
	public void clearGrid() {
		for (int x = 0; x < grid.length; x++) {
			for (int y = 0; y < grid[x].length; y++) {
				grid[x][y] = EMPTY;
			}
		}
		setChanged();
		notifyObservers();
	}

	/**
	 * Check if a player has 5 in row
	 * 
	 * @param player the player to check for
	 * @return true if player has 5 in row, false otherwise
	 */
	public boolean isWinner(int player) {

		int winnerCounter1 = 0;
		int winnerCounter2 = 0;
		int winnerCounter3 = 0;
		int delta = 0;

//		 * grid [size][size] där size = 4;
//		 * for (int x = 0; x < grid.length; x++)
//		 * for (int y = 0; y < grid[x].length; y++)
//		 * Här förklarar jag hur funkar de här två forloop:
//		 * nummert efter siffran är indexet
//		 * X0Y0 X1Y0 X2Y0 X3Y0
//		 * X0Y1 X1Y1 X2Y1 X3Y1
//		 * X0Y2 X1Y2 X2Y2 X3Y2
//		 * X0Y3 X1Y3 X2Y3 X3Y3

		for (int x = 0; x < getSize(); x++) {
			for (int y = 0; y < getSize(); y++) {
				if (grid[y][x] == player)
					winnerCounter1++;
				else
					winnerCounter1 = 0;
				if (winnerCounter1 >= INROW) {
					return true;
				}

				if (grid[x][y] == player)
					winnerCounter2++;
				else
					winnerCounter2 = 0;
				if (winnerCounter2 >= INROW) {
					System.out.println("2");
					return true;
				}
			}
			winnerCounter1 = 0;
			winnerCounter2 = 0;
		}

		for (int x = 0; x < getSize(); x++) { // diagonally
			for (int y = 0; y < getSize(); y++) {
				while (x + delta < grid.length - 1 && y + delta < grid.length - 1) {
					if (grid[x + delta][y + delta] == player) {
						winnerCounter3++;
						delta++;
						if (winnerCounter3 == INROW) {
							return true;
						}
					} else {
						winnerCounter3 = 0;
						delta++;
					}

				}
				winnerCounter3 = 0;
				delta = 0;
				while (x - delta >= 0 && y + delta < grid.length - 1) {
					if (grid[x - delta][y + delta] == player) {
						winnerCounter3++;
						delta++;
						if (winnerCounter3 == INROW) {
							return true;
						}
					} else {
						winnerCounter3 = 0;
						delta++;
					}

				}

			}
		}
		return false;

	}

}
