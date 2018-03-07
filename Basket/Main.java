import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		int n;
		int k = 0;
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		int n2 = n;

		int c = (int) Math.pow(10, Math.floor(Math.log10(n)));
		while (n != 0) {
			int mod = n % 10;
			n -= mod;
			mod *= c;
			k += mod;
			n = n / 10;
			c = c / 10;
		}

		if (k == n2) {
			System.out.println("No");
		} else if (k % 2 == 0 || n2 % 2 == 0) {
			System.out.println("No");
		} else {
			boolean bothPrime = true;
			for (int i = 3; i < Math.sqrt(k) + 1; i += 2) {
				if (k % i == 0) {
					bothPrime = false;
					break;
				}
			}
			if (bothPrime) {
				for (int i = 3; i < Math.sqrt(n2) + 1; i += 2) {
					if (n2 % i == 0) {
						bothPrime = false;
						break;
					}
				}
			}
			if (bothPrime) {
				System.out.println("Yes");
			} else {
				System.out.println("No");
			}
		}

		sc.close();
	}
}
