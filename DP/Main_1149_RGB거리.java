package day1001;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1149_RGB거리 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int memo[][] = new int[N+1][3];
		
		int data[][] = new int[N+1][3];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				data[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 1; i <= N; i++) {
			memo[i][0] = Math.min(memo[i-1][1], memo[i-1][2]) + data[i][0];
			memo[i][1] = Math.min(memo[i-1][0], memo[i-1][2]) + data[i][1];
			memo[i][2] = Math.min(memo[i-1][0], memo[i-1][1]) + data[i][2];
		}
		
		int min = Math.min(memo[N][0], memo[N][1]);
		min = Math.min(min, memo[N][2]);
		System.out.println(min);
		
	}
}
