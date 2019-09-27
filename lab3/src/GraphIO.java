

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class GraphIO implements Graph {

	static public void readFile(Graph g, String filename) throws IOException {
		try {
			File filen = new File(filename);
			Scanner scan = new Scanner(filen);
			int firstLine = scan.nextInt();
			int Node = 0;

			while (Node < firstLine) {
				g.addNode(scan.nextInt(), scan.nextInt(), scan.nextInt());
				Node++;
			}
			while (scan.hasNext()) {
				g.addEdge(scan.nextInt(), scan.nextInt(), scan.nextInt());
			}
			scan.close();

		}

		catch (Exception e) {
			throw new IOException("Error!");

		}

	}

	public void addEdge(int arg0, int arg1, int arg2) throws NoSuchElementException {

	}

	public void addNode(int arg0, int arg1, int arg2) {

	}

}
