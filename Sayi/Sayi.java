package sayi_prob;

import java.util.Scanner;

public class Sayi {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long L = sc.nextLong();
		long H = sc.nextLong();
		boolean ok = solve(L, H);
		if(ok) {
			System.out.println("YES");
		}else {
			System.out.println("NO");
		}
		
		
	}
	
	
	public static boolean solve(long L, long H) {
		if(H < L){
			return false;
		}
		if(H == L) return true;
		
		if(H % 2 == 0) {
			return solve(L, H / 2);
		}else {
			return solve(L, (H-1) / 2);
		}
		
	}
}
