package day0924;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_1755_숫자놀이 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine());
		
		int start = Integer.parseInt(tk.nextToken());
		int end = Integer.parseInt(tk.nextToken());
		
		String[] nums = {"zero" ,"one" ,"two" ,"three" ,"four" ,"five" ,"six" ,"seven" ,"eight" ,"nine"};
 		Map<String, Integer> map = new HashMap<String, Integer>();
		
		for (int i = start; i <= end; i++) {
			String str = "";
			String num = i + "";
			for (int j = 0; j < num.length(); j++) {
				int n = (num).charAt(j) - '0';
				str += nums[n];
			}
			map.put(str, i);
		}
		
		List<String> set = new ArrayList<String>(map.keySet());
		Collections.sort(set);
		
		for (int i = 0; i < set.size(); i++) {
			System.out.print(map.get(set.get(i)) + " ");
			if(i % 10 == 9) System.out.println();
		}
	}
}