
package lab4.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import lab4.data.GameGrid;
import lab4.data.GomokuGameState;

/**
 * A panel providing a graphical view of the game board
 */

public class GamePanel extends JPanel implements Observer {

	public final int UNIT_SIZE = 15;
	private GameGrid grid;

	/**
	 * The constructor
	 * 
	 * @param grid The grid that is to be displayed
	 */
	public GamePanel(GameGrid grid) {
		this.grid = grid;
		grid.addObserver(this);
		Dimension d = new Dimension(grid.getSize() * UNIT_SIZE + 1, grid.getSize() * UNIT_SIZE + 1);
		this.setMinimumSize(d);
		this.setPreferredSize(d);
		this.setBackground(Color.WHITE);
		this.setVisible(true);
	}

	/**
	 * Returns a grid position given pixel coordinates of the panel
	 * 
	 * @param x the x coordinates
	 * @param y the y coordinates
	 * @return an integer array containing the [x, y] grid position
	 */
	public int[] getGridPosition(int x, int y) {
		int[] position = { (int) Math.floor(x / UNIT_SIZE), (int) Math.floor(y / UNIT_SIZE) };
		return position;
	}

	public void update(Observable arg0, Object arg1) {
		this.repaint();
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		for (int i = 0; i < grid.getSize(); i++) {
			for (int j = 0; j < grid.getSize(); j++) {
				g.setColor(Color.black);
				g.drawRect(j * UNIT_SIZE, i * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);

				if (grid.getLocation(j, i) == GameGrid.ME) {
					g.setColor(Color.blue);
					g.drawOval(j * UNIT_SIZE + 1, i * UNIT_SIZE + 1, UNIT_SIZE - 2, UNIT_SIZE - 2);
				}

				if (grid.getLocation(j, i) == GameGrid.OTHER) {
					g.setColor(Color.red);
					g.drawLine(j * UNIT_SIZE, i * UNIT_SIZE, j * UNIT_SIZE + UNIT_SIZE, UNIT_SIZE * i + UNIT_SIZE);
					g.drawLine(j * UNIT_SIZE, UNIT_SIZE * i + UNIT_SIZE, j * UNIT_SIZE + UNIT_SIZE, i * UNIT_SIZE);
				}
			}
		}
		this.repaint();
	}
}
