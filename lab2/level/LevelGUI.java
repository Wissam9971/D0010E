
package lab2.level;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class LevelGUI implements Observer {
	private Level lv;
	private Display d;

	public LevelGUI(Level level, String name) {
		this.lv = level;

		JFrame frame = new JFrame(name);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// TODO: You should change 200 to a value
		// depending on the size of the level
		d = new Display(lv, 1000, 1000);

		frame.getContentPane().add(d);
		frame.pack();
		frame.setLocation(0, 0);
		frame.setVisible(true);
		lv.addObserver(this);
	}

	public void update(Observable arg0, Object arg1) {
		d.repaint();
	}

	private class Display extends JPanel {

		public Display(Level fp, int x, int y) {

			addKeyListener(new Listener());

			setBackground(Color.white);
			setPreferredSize(new Dimension(x + 20, y + 20));
			setFocusable(true);
		}

		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			for (Room r : lv.lista) {
				r.Draw(g, lv.playerLocation);

			}
		}

		/*
		 * Key listener användas bara för att göra action när man trycker på motsvarnde
		 * kanppen
		 */
		private class Listener implements KeyListener {

			public void keyPressed(KeyEvent arg0) {
			}

			public void keyReleased(KeyEvent arg0) {
			}

			public void keyTyped(KeyEvent event) {
				switch (event.getKeyChar()) {
				case 'w':
					lv.moveNorth();
					break;
				case 'd':
					lv.moveEast();
					break;
				case 's':
					lv.moveSouth();
					break;
				case 'a':
					lv.moveWest();
					break;
				default:
					break;
				}
			}
		}
	}

}
