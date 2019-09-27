
package lab1;

public class Part1 {

	public static void main(String[] args) {

		// Switch-statement

		int n = 6;
		switch (n) {
		case 1:
			task1(args);
			break;
		case 2:
			task2(42);
			break;
		case 3:
			task3();
			break;
		case 4:
			task4();
			break;
		case 6:
			task6();
			break;
		default:
			System.out.println(
					n + " is invalid choice, Please choose valid value.\r\n" + "Hint: 1,2,3,4,6 are valid choices. ");
			break;
		}
	}

	// problem 1
	public static int f1(int a0) {
		if (a0 == 1)
			return 1;
		if (a0 % 2 == 0) {
			return (a0 / 2);
		} else
			return ((3 * a0) + 1);
	}

	public static void task1(String[] args) {
		// Print method for problem 1
		System.out.println("Problem 1");
		int second = Integer.parseInt(args[0]);
		int first = Integer.parseInt(args[1]);
		System.out.println("> java LifeLength " + first);
		System.out.println(f1(first));

		System.out.println("> java LifeLength " + second);
		System.out.println(f1(second));
		System.out.println("______________________________________________\n");
	}

	// problem 2
	public static int f2(int a0) {
		return f1(f1(a0));
	}

	public static int f4(int a0) {
		return f2(f2(a0));
	}

	public static int f8(int a0) {
		return f4(f4(a0));
	}

	public static int f16(int a0) {
		return f8(f8(a0));
	}

	public static int f32(int a0) {
		return f16(f16(a0));
	}

	public static void task2(int a0) {
		// Print method for problem 2
		System.out.println("Problem 2");
		System.out.println("> java LifeLength " + a0);
		System.out.print("f1:" + f1(a0) + " ");
		System.out.print("f2:" + f2(a0) + " ");
		System.out.print("f4:" + f4(a0) + " ");
		System.out.print("f8:" + f8(a0) + " ");
		System.out.print("f16:" + f16(a0) + " ");
		System.out.print("f32:" + f32(a0) + " \n");
		System.out.println("______________________________________________\n");
	}

	// problem 3
	public static int iterateF1(int a0, int n) {
		int svar = a0;
		for (int i = 0; i < n; i++) {
			svar = f1(svar);
		}
		return svar;
	}

	public static void task3() {
		// Print method for problem 3
		System.out.println("Problem 3");
		System.out.println(iterateF1(3, 5));
		System.out.println(iterateF1(42, 3));
		System.out.println(iterateF1(1, 3));
		System.out.println("______________________________________________\n");

	}

	// Problem 4
	public static int iterLifeLength(int a0) {
		int count = 0;
		while (a0 != 1) {
			a0 = f1(a0);
			count++;

		}
		return count;
	}

	public static void task4() {
		// Print method for problem 4
		System.out.println("Problem 4");
		for (int i = 1; i <= 15; i++) {
			System.out.println(intsToString4(i));
		}
		System.out.println("______________________________________________\n");

	}

	public static String intsToString4(int a0) {
		return "The life length of " + a0 + " is " + iterLifeLength(a0);
	}

	// problem 6
	public static int recLifeLength(int a0) {
		if (a0 == 1) {
			return 0;
		} else {
			return (recLifeLength(f1(a0))) + 1;

		}
	}

	public static void task6() {
		// Print method for problem 6
		System.out.println("Problem 6");
		for (int i = 1; i <= 15; i++) {
			System.out.println(intsToString(i));
		}
	}

	public static String intsToString(int a0) {
		return "The life length of " + a0 + " is " + iterLifeLength(a0) + ". \t" + "The life length of " + a0 + " is "
				+ recLifeLength(a0) + ".";
	}

}
