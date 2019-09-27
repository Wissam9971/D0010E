
package lab4;

import lab4.client.GomokuClient;
import lab4.gui.GomokuGUI;
import lab4.data.GomokuGameState;

public class GomokuMain {
	public static void main(String[] args) {
		int port;
		// checks if there's exactly one argument, if not, port 4000 is chosen by
		// default
		if (args.length == 1) {
			port = Integer.parseInt(args[0]);
		} else {
			port = 4000;
		}
		// a GomokuClient, to which the port number is given as an
		// argument,
		GomokuClient GClient = new GomokuClient(port);
		// a GomokuGameState that takes the client as its argument
		GomokuGameState GGState = new GomokuGameState(GClient);
		// a GomokuGUI that takes
		// the game state and the client as its arguments
		GomokuGUI GGUI = new GomokuGUI(GGState, GClient);
	}
}