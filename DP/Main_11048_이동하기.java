package day1001;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11048_이동하기 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int map[][] = new int[N+1][M+1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				int num = Integer.parseInt(st.nextToken());
				
				if(i==1) {
					map[1][j] = map[1][j-1] + num;
				}else if(j==1) {
					map[i][1] = map[i-1][1] + num;
				}else {
					int max = Math.max(map[i-1][j], map[i][j-1]);
					max = Math.max(max, map[i-1][j-1]);
					map[i][j] = max + num;
				}
			}
		}

		System.out.println(map[N][M]);
	}
}
