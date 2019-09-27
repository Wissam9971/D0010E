

package lab1;

public class Part2 {

	static int recRaiseOneCount = 0;
	static int recRaiseHalfCount = 0;

	public static double recRaiseOne(double x, int k) {
		recRaiseOneCount++;
		if (k == 0) {
			return 1.0;
		} else {
			return x * recRaiseOne(x, k - 1);
		}
	}

	public static double recRaiseHalf(double x, int k) {
		recRaiseHalfCount++;
		int a;
		int power = k / 2;

		if (k == 0) {
			return 1;
		}
		if (k % 2 == 0) {
			a = (int) recRaiseHalf(x, power);
			return a * a;
		} else {
			a = (int) recRaiseHalf(x, power);
			return a * a * x;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i = 1; i <= 15; i++) {
			recRaiseOneCount = 0;
			recRaiseHalfCount = 0;
			recRaiseOne(1.5, i);
			recRaiseHalf(2, i);
			System.out.println(recRaiseHalf(2, i) + ": " + recRaiseOneCount + " " + recRaiseHalfCount);

		}
	}

}
