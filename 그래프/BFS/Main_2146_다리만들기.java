package day1004;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2146_다리만들기 {
	
	public static int N, map[][], MIN;
	public static ArrayList<int[]> list = new ArrayList<>();
	public static int X[] = {0,0,1,-1};
	public static int Y[] = {1,-1,0,0};
	public static boolean visited[][];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		MIN = Integer.MAX_VALUE;
		map = new int[N][N];
		visited = new boolean [N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int num = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 1 && !visited[i][j]) {
					setIsland(i, j, num++);
				}
			}
		}
		
		bfs();

		System.out.println(MIN);
	}
	
	public static void setIsland(int x, int y, int num) {
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[] {x, y});
		map[x][y] = num;
		
		while(!queue.isEmpty()) {
			int[] tmp = queue.poll();
			for (int i = 0; i < 4; i++) {
				int dx = tmp[0] + X[i];
				int dy = tmp[1] + Y[i];
				
				if(check(dx, dy)) {
					if(map[dx][dy] == 1) {
						map[dx][dy] = num;
						visited[dx][dy] = true;
						queue.add(new int[] {dx, dy});	
					}
					else if(map[dx][dy] == 0) {
						list.add(new int[] {dx, dy, num, 1});
					}
				}
			}
		}
	}
	
	public static void bfs(){
		for (int li = 0; li < list.size(); li++) {
			visited = new boolean[N][N];
			
			Queue<int[]> queue = new LinkedList<int[]>();
			queue.add(list.get(li));
			visited[list.get(li)[0]][list.get(li)[1]] = true;
			// {x좌표, y좌표, 섬 번호, 거리}
			
			while(!queue.isEmpty()) {
				int[] tmp = queue.poll();
                if(tmp[3] > MIN) break;
				for (int i = 0; i < 4; i++) {
					int dx = tmp[0] + X[i];
					int dy = tmp[1] + Y[i];
					
					if(check(dx, dy)) {
						
						if(map[dx][dy] == 0) {
							queue.add(new int[] {dx, dy, tmp[2], tmp[3]+1});
							
							
							visited[dx][dy] = true;
						} else if(map[dx][dy] != tmp[2]) {
							MIN = Math.min(MIN, tmp[3]);
							break;
						}
					}
				}
			}
		}
	}
	
	public static boolean check(int x, int y) {
		if(x<0 || x>=N || y<0 || y>=N || visited[x][y]) {
			return false;
		}
		return true;
	}
}
