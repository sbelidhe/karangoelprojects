import java.util.Scanner;

public class WordFactorial {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String input = in.nextLine();
		char[] ar = input.toCharArray();
		String res="";
		for (int i=0;i<ar.length;i++) {
			int val = factor((int)ar[i]) + 32;
			res += (char)val;
		}
		System.out.print(res);
		in.close();
	}
	
	static int factor(int val) {
		int sum=0;
		for(int i = 1; i <= val; i++) {
			if(val%i == 0) {
				sum +=i;
			}
		}
		
		return sum;
	}
}
