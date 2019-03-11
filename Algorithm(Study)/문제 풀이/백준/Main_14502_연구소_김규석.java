package SW;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_14502_연구소_김규석 {

	static class Virus{
		int y;
		int x;
		
		Virus(int y, int x){
			this.y = y;
			this.x = x;
		}
	}
	
	static int N, M;
	static int[][] map;
	static int[][] copymap;
	static ArrayList<Virus> virus;
	static ArrayList<Virus> wall;
	
	static int[] dy = {0,0,-1,1};
	static int[] dx = {-1,1,0,0};
	static int[] set;
	static int max;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		virus = new ArrayList<>();
		wall = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) {
					virus.add(new Virus(i, j)); // 바이러스 저장
				}
				if(map[i][j] == 0) {
					wall.add(new Virus(i,j));
				}
			}
		}
		
		set = new int[3];
		
		int n = wall.size();
		int k = set.length;
		
		max = Integer.MIN_VALUE;
		comb(set, 0, n, k, 0);
		
		System.out.println(max);
	}
	
	public static void dfs(Virus v) {
		int y = v.y;
		int x = v.x;
		
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			if(ny>=0 && nx>=0 && ny<N && nx<M) {
				if(copymap[ny][nx] == 0) {
					copymap[ny][nx] = 2;
					dfs(new Virus(ny,nx));
				}
			}
		}
	}
	
	public static void comb(int[] set, int size, int n, int k, int index) {
		
		if(k==0) {
			copymap = new int[N][M];
			copy();
			for (int i = 0; i < size; i++) {
				Virus v = wall.get(set[i]);
				copymap[v.y][v.x] = 1;
			}
			for (int i = 0; i < virus.size(); i++) {
				dfs(virus.get(i));
			}
			
			
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(copymap[i][j] == 0) cnt++;
				}
			}
			if(max < cnt) max = cnt;
			
			return;
		}
		else if(n==index) return;
		
		set[size] = index;
		comb(set, size+1, n, k-1, index+1);
		comb(set, size, n, k, index+1);
		
	}
	
	public static void checking(int y, int x) {
		
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			if(ny>=0 && nx>=0 && ny<N && nx<M) {
				
				if(map[ny][nx] == 0) {
					wall.add(new Virus(ny,nx));
				}
			}
		}
	}
	
	public static void copy() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copymap[i][j] = map[i][j];
			}
		}
	}
	
}
