
package lab4.data;

import java.util.Observable;
import java.util.Observer;

import lab4.client.GomokuClient;

/**
 * Represents the state of a game
 */

public class GomokuGameState extends Observable implements Observer {

	// Game variables
	public final int DEFAULT_SIZE = 20;
	private GameGrid gameGrid;

	// Possible game states
	private final int NOT_STARTED = 0;
	private final int MY_TURN = 1;
	private final int OTHER_TURN = 2;
	private final int FINISHED = 3; // i stället för att skriva jättelång if sats så skappade jag denna variabel som
									// jag använder när ingen av de andra tre final varibeler stämmer!
	private int currentState;

	private GomokuClient client;

	private String message;

	/**
	 * The constructor
	 * 
	 * @param gc The client used to communicate with the other player
	 */
	public GomokuGameState(GomokuClient gc) {
		client = gc;
		client.addObserver(this);
		gc.setGameState(this);
		currentState = NOT_STARTED;
		gameGrid = new GameGrid(DEFAULT_SIZE);
	}

	/**
	 * Returns the message string
	 * 
	 * @return the message string
	 */
	public String getMessageString() {
		return message;
	}

	/**
	 * Returns the game grid
	 * 
	 * @return the game grid
	 */
	public GameGrid getGameGrid() {
		return gameGrid;
	}

	/**
	 * This player makes a move at a specified location
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public void move(int x, int y) {

		if (currentState == FINISHED) { // kolla i variabler för att förstå vad är finished!
			message = "The game is alrady finshed";
			setChanged();
			notifyObservers();
		}

		else if (currentState == NOT_STARTED) {
			message = "The game is not started!";
			setChanged();
			notifyObservers();
		}

		else {
			/*
			 * Först if sats kollar i fall det va min tur andra if satsen kollar i fall
			 * platsen är tom annars kör vi dirkt else sats obs: comment med
			 * "plats är inte tom finns där" så skicker vi en move med x,y och den som
			 * spelar (jag i det här fallet). vi ändrar messege vi ändrar currentState och
			 * vi skickar the move x,y till andra spelare med
			 */
			if (currentState == MY_TURN) {
				if (gameGrid.move(x, y, gameGrid.ME)) {
					receivedMove(x, y);
					message = "Move is made!";
					client.sendMoveMessage(x, y);
					currentState = OTHER_TURN;

					/*
					 * här kollar vi om sista rörlse gjorde mig till theWinner så skriver vi ett
					 * medleande och vi ändrar currentState annars ändrar vi currentState till
					 * OTHER_TURN.
					 */
					if (gameGrid.isWinner(gameGrid.ME)) {
						message = "WOOHOOOO you Won!";
						currentState = FINISHED;
						setChanged();
						notifyObservers();
					} else {
						currentState = OTHER_TURN;
						setChanged();
						notifyObservers();
					}

				} else { // plats är inte tom
					message = "Square is not empty, move is not made!";
					setChanged();
					notifyObservers();
				}
			} else { // om det är inte min tur så skriver bara ett medelande med detta
				message = "It is not your turn, move is not made!";
				setChanged();
				notifyObservers();

			}
		}
	}

	/**
	 * Starts a new game with the current client
	 */

	/*
	 * vi tar bort allt i spelet det blir "the other" tur vi sitter ett messege och
	 * vi skikar ett nytt spel medelande till den andra spelare
	 * 
	 */
	public void newGame() {
		gameGrid.clearGrid();
		currentState = OTHER_TURN;
		message = "A new game has started by you!";
		client.sendNewGameMessage();
		setChanged();
		notifyObservers();
	}

	/**
	 * Other player has requested a new game, so the game state is changed
	 * accordingly
	 */

	// lika till newGame men lite ändringar
	public void receivedNewGame() {
		gameGrid.clearGrid();
		currentState = MY_TURN;
		message = "A new game has started by the other player!";
		setChanged();
		notifyObservers();

	}

	/**
	 * The connection to the other player is lost, so the game is interrupted
	 */

	/*
	 * vi tar bort allt i spelaet vi ändrar status till not starterd vi skriver ut
	 * ett medelande
	 */
	public void otherGuyLeft() {
		gameGrid.clearGrid();
		currentState = NOT_STARTED;
		message = "The other player has left the game!";
		setChanged();
		notifyObservers();
	}

	/**
	 * The player disconnects from the client
	 */

	// lika till otherGuyLeft men vi skicker ett medelande till the other guy
	public void disconnect() {
		gameGrid.clearGrid();
		currentState = NOT_STARTED;
		message = "You have left the game";
		client.disconnect();
		setChanged();
		notifyObservers();
	}

	/**
	 * The player receives a move from the other player
	 * 
	 * @param x The x coordinate of the move
	 * @param y The y coordinate of the move
	 */
	
	public void receivedMove(int x, int y) {
		gameGrid.move(x, y, gameGrid.OTHER);
		if (gameGrid.isWinner(gameGrid.OTHER)) {
			message = "The other player won this game! Good luck next time";
			currentState = FINISHED;
			setChanged();
			notifyObservers();
		} else {
			message = "The other player has made his move, it is your turn!";
			currentState = MY_TURN;
			setChanged();
			notifyObservers();

		}

	}

	public void update(Observable o, Object arg) {

		switch (client.getConnectionStatus()) {
		case GomokuClient.CLIENT:
			message = "Game started, it is your turn!";
			currentState = MY_TURN;
			break;
		case GomokuClient.SERVER:
			message = "Game started, waiting for other player...";
			currentState = OTHER_TURN;
			break;
		}
		setChanged();
		notifyObservers();

	}

}
