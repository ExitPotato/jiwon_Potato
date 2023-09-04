package day0903;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제]
 * 자연수 A를 B번 곱한 수를 알고 싶다. 
 * 단 구하려는 수가 매우 커질 수 있으므로 이를 C로 나눈 나머지를 구하는 프로그램을 작성하시오.
 * 
 * [풀이]
 * A, B, C <= 2,147,483,647 => 일반적으로 문제를 풀면 시간초과
 * 분할정복을 이용해서 문제 풀이.
 * 10^6 = 10^3 * 10^3 = 10*10*10*10*10*10
 * 
 */

public class Main_1629_곱셈 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		System.out.println(pow(A, B, C));
	}

	private static long pow(int a, int b, int c) {
		if(b == 0) {
			return 1;
		}
		
		long n = pow(a, b/2, c);
		if(b % 2 == 1) {
			return (n * n % c) * a % c; 
		}else {
			return n*n%c;
		}
	}
}
