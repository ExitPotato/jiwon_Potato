package day0903;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제]
 * N개의 수로 된 수열 
 * A[i] + A[i+1] + … + A[j]가 M이 되는 경우의 수
 * 
 * 누적합을 이용해서 풀이했는데 솔직히 그냥 for문 두개 돌린거랑 똑같음..!
 * -> 투포인터를 이용하여 문제 풀이
 * A[i] + A[i+1] + … + A[j] = sum
 * sum < 0 -> A[end] 값을 더해주고 end 인덱스 증가
 * sum >= 0 || end == N -> A[start] 값을 빼주고 start 인덱스 증가
 * 이러한 연산을 start가 끝에 도착할때까지 반복
 * 
 */

public class Main_2003_수들의합2 {
	
	public static int N, M, A[], cnt;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		A = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		int start = 0, end = 0, sum = 0, cnt = 0;
		
		while(start < N) {
			if(sum >= M || end == N) {
				sum -= A[start++];
			}else if(sum < M) {
				sum += A[end++];
			}
			
			if(sum == M) cnt++;
		}
		System.out.println(cnt);
	}
}


/**
public class Main_2003_수들의합2 {
	
	public static int N, M, A[], cnt;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		A = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			A[i] = A[i-1] + Integer.parseInt(st.nextToken());
		}
		
		int start = 0;
		for (int end = 0; end <= N; end++) {
			for (int i = start; i < end; i++) {
				if(A[end] - A[i] == M) cnt++;
			}
		}
		System.out.println(cnt);
	}
}

 */