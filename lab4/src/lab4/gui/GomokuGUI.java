

package lab4.gui;

import java.util.Collections;
import java.util.Observable;
import java.util.Observer;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.xml.internal.ws.api.Component;

//import com.sun.glass.events.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;

import lab4.client.GomokuClient;
import lab4.data.GameGrid;
import lab4.data.GomokuGameState;

/*
 * The GUI class
 */

public class GomokuGUI implements Observer {

	private GomokuClient client;
	private GomokuGameState gamestate;
	private JButton connectButton;
	private JButton newGameButton;
	private JButton disconnectButton;
	JLabel messageLabel;

	/**
	 * The constructor
	 * 
	 * @param g The game state that the GUI will visualize
	 * @param c The client that is responsible for the communication
	 */
	public GomokuGUI(GomokuGameState g, GomokuClient c) {
		this.client = c;
		this.gamestate = g;
		JFrame frame = new JFrame("Gomoku");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setFocusable(true);

		GamePanel gameGridPanel = new GamePanel(g.getGameGrid());
		gameGridPanel.setVisible(true);

		frame.setSize(gamestate.DEFAULT_SIZE * gameGridPanel.UNIT_SIZE + 50,
				gamestate.DEFAULT_SIZE * gameGridPanel.UNIT_SIZE + 150);

//		GamePanel gameGridPanel = new GamePanel(g.getGameGrid());
//		gameGridPanel.setVisible(true);

		messageLabel = new JLabel("Welcome to Gomoku");
		connectButton = new JButton("Connect");
		newGameButton = new JButton("New Game");
		disconnectButton = new JButton("Disconnect");
		// connectButton.setLayout(null);

		// connectButton.setBounds(gamestate.DEFAULT_SIZE * gameGridPanel.UNIT_SIZE +
		// 50, gamestate.DEFAULT_SIZE * gameGridPanel.UNIT_SIZE + 100,
		// gameGridPanel.UNIT_SIZE * 6, gameGridPanel.UNIT_SIZE * 3);
		// frame.add(connectButton);

//		 Creating a box with all the buttons
		Box buttonsBox = Box.createHorizontalBox();
		buttonsBox.add(connectButton);
		buttonsBox.add(newGameButton);
		buttonsBox.add(disconnectButton);
		// buttonsBox.setBounds(200, 600, 200, 250);

		// Creating a box for the message
		Box messageBox = Box.createHorizontalBox();
		messageBox.add(messageLabel);

		// Creating a box for the GameGrid.
		Box gridBox = Box.createHorizontalBox();
		gridBox.add(gameGridPanel);

		// Creating panels to put the boxes in
		JPanel gridPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel messagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

		// Adding all the boxes to the panels and then adding all the panels to one main
		// panel
		gridPanel.add(gridBox);
		buttonsPanel.add(buttonsBox);
		messagePanel.add(messageBox);

		// making it so the panels allign to eachother
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.add(gridPanel);
		mainPanel.add(buttonsPanel);
		mainPanel.add(messagePanel);

		// Addin the mainpanel to the frame
		frame.add(mainPanel);

		connectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConnectionWindow connectionWindow = new ConnectionWindow(client);
			}
		});

		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gamestate.newGame();
			}
		});
		disconnectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gamestate.disconnect();
			}
		});
		MouseAdapter mouseListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int x = gameGridPanel.getGridPosition(e.getX(), e.getY())[0];
				int y = gameGridPanel.getGridPosition(e.getX(), e.getY())[1];
				gamestate.move(x, y);

			}
		};

		gameGridPanel.addMouseListener(mouseListener);

		client.addObserver(this);
		gamestate.addObserver(this);

	}

	public void actionPerformed(ActionEvent e) {

	}

	public void update(Observable arg0, Object arg1) {

		// Update the buttons if the connection status has changed
		if (arg0 == client) {
			if (client.getConnectionStatus() == GomokuClient.UNCONNECTED) {
				connectButton.setEnabled(true);
				newGameButton.setEnabled(false);
				disconnectButton.setEnabled(false);
			} else {
				connectButton.setEnabled(false);
				newGameButton.setEnabled(true);
				disconnectButton.setEnabled(true);
			}
		}

		// Update the status text if the gamestate has changed
		if (arg0 == gamestate) {
			messageLabel.setText(gamestate.getMessageString());
		}

	}

}