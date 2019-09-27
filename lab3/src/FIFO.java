
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class FIFO implements Queue {

	private ArrayList<Object> myList = new ArrayList<Object>();
	private int max_Size = 0;

	public void add(Object item) {
		if (myList.size() > max_Size) {
			max_Size++;
		}
		myList.add(item);
	}

	public Object first() throws NoSuchElementException {
		if (myList.size() == 0) {
			throw new NoSuchElementException();
		}
		return myList.get(0);
	}

	public boolean isEmpty() {
		if (myList.size() == 0) {
			return true;
		}
		return false;
	}

	public int maxSize() {
		return max_Size;
	}

	public void removeFirst() throws NoSuchElementException {
		if (myList.size() == 0) {
			throw new NoSuchElementException();
		}
		myList.remove(0);
	}

	public int size() {
		return myList.size();
	}

	public boolean equals(Object f) throws ClassCastException {
		if (f instanceof FIFO) {
			if (this.size() == ((FIFO) f).size()) {
				for (int i = 0; i < myList.size(); i++) {

					if ((myList.get(i) != null && ((FIFO) f).myList.get(i) == null)
							|| (myList.get(i) == null && ((FIFO) f).myList.get(i) != null)
									&& (myList.get(i) != ((FIFO) f).myList.get(i))) {
						return false;
					}
				}
			} else {
				return false;
			}
		} else {
			throw new ClassCastException("Object har inte samma typ");
		}
		return true;
	}

	public String toString() {
		String s = " ";
		for (int i = 0; i < myList.size(); i++) {
			s += "(" + String.valueOf(myList.get(i)) + ") ";
		}
		return "Queue:" + s;
	}
}
