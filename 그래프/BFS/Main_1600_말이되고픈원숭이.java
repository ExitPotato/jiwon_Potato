package day0901;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [문제 요약]
 * 백준 1600 말이 되고픈 원숭이
 * W*H의 맵에서 인접한 네 방향 or K번 체스의 나이트 처럼 이동
 * 최소한의 동작으로 시작지점에서 도착 지점까지 도착하기 위한 프로그램
 * 
 * [풀이 방법]
 * 최소 이동 횟수를 구하는 문제이기 때문에 BFS를 사용하여 풀이
 * 말처럼 움직이는 배열 / 일반 인접한 네방향으로 이동하는 배열 따로 선언
 * K번까지 말처럼 움직일 수 있기 때문에 결과값을 저장할 배열을 => map[K+1][x좌표][y좌표] 
 * (map[K][x][y] -> 한번도 말처럼 이동하지 않음 // map[K-1][x][y] -> 한 번 말처럼 이동)
 * 
 * [어려웠던 점]
 * 백준 2206 벽 부수고 이동하기 문제를 풀어서 비슷한 방식으로 구현하려고 했는데 층 설정을 잘못해서 하루를 헤맸다...
 * K번 이동 가능하니 K+1개의 층을 만들어줘야하고,
 * 말처럼 이동할때 층을 올라갈지 내려갈지 제대로 정하지 않고 해서 범위 설정을 잘못함
 * 
 */



public class Main_1600_말이되고픈원숭이 {
	
	public static int K, W, H, data[][], map[][][];
	public static int X[] = {0,0,1,-1};
	public static int Y[] = {1,-1,0,0};
	public static int HX[] = {2,2,-2,-2,1,1,-1,-1};
	public static int HY[] = {1,-1,1,-1,2,-2,2,-2};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		K = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		data = new int[H][W];
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				data[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		map = new int[K+1][H][W];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				Arrays.fill(map[i][j], -1);
			}
		}
		
		map[K][0][0] = 0;
		if(!bfs()) System.out.println(-1);
	}
	
	public static boolean bfs() {
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[] {0,0,K});
		
		while(!queue.isEmpty()) {
			int[] tmp = queue.poll();
			int x = tmp[0];
			int y = tmp[1];
			int floor = tmp[2];
			
			if(x == H-1 && y == W-1) {
				System.out.println(map[floor][x][y]);
				return true;
			}
			
			for (int i = 0; i < 4; i++) {
				int dx = x + X[i];
				int dy = y + Y[i];
				
				if(!check(dx, dy, floor)) continue;
				
				map[floor][dx][dy] = map[floor][x][y] + 1;
				queue.add(new int[] {dx, dy, floor});
			}
			
			for (int i = 0; i < 8; i++) {
				int dx = x + HX[i];
				int dy = y + HY[i];
				
				if(floor == 0 || !check(dx, dy, floor-1)) continue;

				map[floor-1][dx][dy] = map[floor][x][y] + 1;
				queue.add(new int[] {dx, dy, floor-1});
			}
		}
		return false;
	}
	
	public static boolean check(int x, int y, int floor) {
		if(x<0 || x>=H || y<0 || y>=W || map[floor][x][y] != -1) return false;
		if(data[x][y] != 0) return false;
		return true;
	}
}
