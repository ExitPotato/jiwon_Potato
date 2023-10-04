package day1004;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17472_다리만들기2 {
	
	public static int N, M, islandNum, map[][], parents[];
	public static boolean visited[][];
	public static Map<Integer, ArrayList<Point>> list = new HashMap<Integer, ArrayList<Point>>();
	public static int X[] = {1,-1,0,0};
	public static int Y[] = {0,0,1,-1};
	
	public static class Point{
		int x;
		int y;
		int d;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public Point(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
	
	public static class Edge implements Comparable<Edge>{
		int a;
		int b;
		int w;
		
		public Edge(int a, int b, int w) {
			this.a = a;
			this.b = b;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}
	}
	
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N+2][M+2];
		visited = new boolean[N+2][M+2];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 섬의 개수 + 맵에 섬 번호 다르게 설정
		islandNum = setMap();
		for (int i = 1; i <= islandNum; i++) {
			list.put(i, new ArrayList<Point>());
		}
		
		
		// 땅과 인접한 바다 리스트 -> 다리 만들거임
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if(map[i][j] == 0) {
					int n = 0, d=0;
					if(map[i-1][j] != 0) {
						n = map[i-1][j];
						d = 0;
						list.get(n).add(new Point(i, j, d));
					}
					if(map[i+1][j] != 0) {
						n = map[i+1][j];
						d = 1;
						list.get(n).add(new Point(i, j, d));
					}
					if(map[i][j-1] != 0) {
						n = map[i][j-1];
						d = 2;
						list.get(n).add(new Point(i, j, d));
					}
					if(map[i][j+1] != 0) {
						n = map[i][j+1];
						d = 3;
						list.get(n).add(new Point(i, j, d));
					}
					else continue;
					
					
				}
			}
		}
		
		ArrayList<Edge> EdgeList = new ArrayList<>();
		for (int i = 1; i <= islandNum; i++) {
			for (int j = 0; j < list.get(i).size(); j++) {
				
				Point p = list.get(i).get(j);
				int dis = 1;
				int islandNo=0;
				int dx = p.x;
				int dy = p.y;
				while(true) {
					dx += X[p.d];
					dy += Y[p.d];
					
					if(check(dx, dy)) {
						if(map[dx][dy] != 0) {
							if(dis > 1) {
								islandNo = map[dx][dy];
								EdgeList.add(new Edge(islandNo, i, dis));
							}
							break;
						}
					}else {
						break;
					}
					dis++;
				}

			}
		}

		make();
		Collections.sort(EdgeList);
		
		int result = 0, cnt = 0;
		for (int i = 0; i < EdgeList.size(); i++) {
			int a = EdgeList.get(i).a;
			int b = EdgeList.get(i).b;
			
			if(cnt == islandNum-1) break;
			if(union(a, b)) {
				result += EdgeList.get(i).w;
				cnt++;
			}
		}

		if(cnt != islandNum-1 || result == 0) {
			System.out.println(-1);
		}else {
			System.out.println(result);
		}
	}

	private static void make() {
		parents = new int[islandNum+1];
		for (int i = 1; i <= islandNum; i++) {
			parents[i] = i;
		}
	}
	
	private static int find(int a) {
		if(parents[a] == a) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}
	
	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot;
		return true;
	}
	
	private static int setMap() {
		int num = 1;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if(map[i][j] == 1 && !visited[i][j]) {
					Queue<Point> queue = new LinkedList<Point>();
					queue.add(new Point(i, j));
					map[i][j] = num;
					visited[i][j] = true;
					
					while(!queue.isEmpty()) {
						Point p = queue.poll();
						
						for (int k = 0; k < 4; k++) {
							int dx = p.x + X[k];
							int dy = p.y + Y[k];
							
							if(check(dx, dy) && !visited[dx][dy] && map[dx][dy] != 0) {
								queue.add(new Point(dx, dy));
								map[dx][dy] = num;
								visited[dx][dy] = true;
							}						
						}
					}
					num++;
				}
				
			}
		}
		return num-1;
	}
	
	
	
	public static boolean check(int x, int y) {
		if(x<1 || x>N || y<1 || y>M) {
			return false;
		}
		return true;
	}
}

