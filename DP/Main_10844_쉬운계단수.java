package day0907;

import java.util.Scanner;

public class Main_10844_쉬운계단수 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		long dp[][] = new long[N][10];
		for (int i = 1; i < 10; i++) {
			dp[0][i] = 1;
		}
		
		int mod = 1000000000;
		
		for (int i = 1; i < N; i++) {
			dp[i][0] = dp[i-1][1] % mod;
			
			for (int j = 1; j < 9; j++) {
				dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1])%mod;
			}
			dp[i][9] = dp[i-1][8]%mod;			
		}
		long sum = 0;
		for (int i = 0; i < 10; i++) {
			sum += dp[N-1][i];
		}
		System.out.println(sum%mod);
	}
}