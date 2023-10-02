package day1001;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_1463_1로만들기 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int memo[] = new int[N+1];
		
		memo[1] = 0;
		
		for (int i = 2; i <= N; i++) {
			memo[i] = memo[i-1]+1;
			
			if(i%2 == 0) memo[i] = Math.min(memo[i/2]+1, memo[i]);
			if(i%3 == 0) memo[i] = Math.min(memo[i/3]+1, memo[i]);
		}

		System.out.println(memo[N]);
	}
}
