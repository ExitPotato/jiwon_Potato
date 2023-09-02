package day0901;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_2579_계단오르기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int score[] = new int[N+1];
		for (int i = 1; i <= N; i++) {
			score[i] = Integer.parseInt(br.readLine());
		}
		
		int memo[][] = new int[N+1][2];
		memo[0][0] = memo[0][1] = 0;	
		if(N >= 1) memo[1][0] = score[1] = score[1];
		
		for (int i = 2; i <= N; i++) {
			memo[i][0] = Math.max(memo[i-2][1], memo[i-2][0])+score[i];
			memo[i][1] = memo[i-1][0]+score[i];
		}
		
		System.out.println(Math.max(memo[N][0], memo[N][1]));
	}
}
