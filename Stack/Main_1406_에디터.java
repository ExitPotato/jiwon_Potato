package day0907;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_1406_에디터 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		String str = br.readLine();
		Stack<Character> Lstack = new Stack<Character>();
		Stack<Character> Rstack = new Stack<Character>();
		for (int i = 0; i < str.length(); i++) {
			Lstack.add(str.charAt(i));
		}
		
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			switch(st.nextToken().charAt(0)) {
			case 'L':
				if(!Lstack.isEmpty()) {
					Rstack.add(Lstack.pop());
				}
				break;
			case 'D':
				if(!Rstack.isEmpty()) {
					Lstack.add(Rstack.pop());
				}
				break;
			case 'B':
				if(!Lstack.isEmpty()) {
					Lstack.pop();
				}
				break;
			case 'P':
				Lstack.add(st.nextToken().charAt(0));
				break;
			}
		}
		int size = Lstack.size();
		for (int i = 0; i < size; i++) {
			Rstack.add(Lstack.pop());
		}
		size = Rstack.size();
		for (int i = 0; i < size; i++) {
			sb.append(Rstack.pop());
		}
		System.out.println(sb);
	}
}
