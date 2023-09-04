package day0903;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * [문제]
 * N개의 숫자로 구분된 마을 / 도착 마을 X
 * 각각의 마을에서 X까지의 오고 가는데 가장 많은 시간을 소비하는 소요시간 출력
 * 단방향 그래프
 * 
 * 다익스트라 알고리즘을 이용하여 풀이
 * 
 */


public class Main_1238_파티 {
	
	public static class Edge implements Comparable<Edge>{
		int v;
		int w;
		
		public Edge(int v, int w) {
			super();
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}
	}
	
	public static int N, M, X;
	public static ArrayList<Edge>[] graph;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 마을	/ 정점 개수
		M = Integer.parseInt(st.nextToken());	// 길	/ 간선 개수
		X = Integer.parseInt(st.nextToken());	// 도착 지점
		
		
		graph = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<Edge>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph[start].add(new Edge(end, weight));
		}
		
		// 모든 마을 확인해야하고, 다시 돌아올 수 있어야함
		// (1->3) + (3->1) 의 값이 가장 큰 
		int max = 0;
		for (int i = 1; i <= N; i++) {
			int sum = getDistance(i, X) + getDistance(X, i);
			if(max < sum){
				max = sum;
			}
		}
		System.out.println(max);
	}
	public static int getDistance(int start, int end) {
		boolean visited[] = new boolean[N+1];
		
		int distance[] = new int[N+1];
		Arrays.fill(distance, Integer.MAX_VALUE);	// 최소값을 구하는것 -> 최대값으로 초기화
		distance[start] = 0;	// 출발 노드 값 0으로 초기화
		
		
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		pq.add(new Edge(start, 0));
		
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			
			if(visited[now.v]) continue;
			visited[now.v] = true;
			
			if(now.v == end) break;
			
			for (Edge next : graph[now.v]) {
				if(distance[next.v] > distance[now.v] + next.w) {
					distance[next.v] = distance[now.v] + next.w;
					pq.add(new Edge(next.v, distance[next.v]));	
				}
			}
		}
		
		return distance[end] == Integer.MAX_VALUE ? -1: distance[end];
	}
	
}