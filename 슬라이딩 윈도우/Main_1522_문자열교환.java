package day0907;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_1522_문자열교환 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		char data[] = str.toCharArray();
		int N = data.length;
		
		int aCnt = 0;
		for (int i = 0; i < N; i++) {
			if(data[i] == 'a') aCnt++;
		}
		
		int bCnt = 0;
		for (int i = 0; i < aCnt; i++) {
			if(data[i] == 'b') bCnt++;
		}
		
		int min = bCnt;
		for (int i = aCnt; i < N + aCnt; i++) {
			if(data[(i-aCnt) % N] == 'b') bCnt--;
			if(data[i % N] == 'b') bCnt++;
			
			if(bCnt < min) min = bCnt;
		}
		
		System.out.println(min);
	}
}
