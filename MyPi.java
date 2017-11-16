import java.util.Scanner;

public class MyPi {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		System.out.println(String.valueOf(computePi(n)));
	}
	
	public static double computePi(int n) {
		double sequenceFormula = 0;
		for (int counter = 1; counter < n; counter += 2) {
			sequenceFormula = sequenceFormula
					+ ((1.0 / (2.0 * counter - 1)) - (1.0 / (2.0 * counter + 1)));
		}
		double pi = 4 * sequenceFormula;
		return pi;
	}

}
