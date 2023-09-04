package day0904;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_2493_탑 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		Stack<int[]> stack = new Stack<int[]>();
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int h = Integer.parseInt(st.nextToken());
			
			while(!stack.isEmpty()) {
				int[] pre = stack.peek();
				if(pre[1] > h) {
					sb.append(pre[0]).append(' ');
					stack.add(new int[] {i+1, h});
					break;
				}else {
					stack.pop();
				}
			}
			
			if(stack.isEmpty()) {
				stack.add(new int[] {i+1, h});
				sb.append(0).append(' ');
			}
		}
		System.out.println(sb);
		
	}
}
