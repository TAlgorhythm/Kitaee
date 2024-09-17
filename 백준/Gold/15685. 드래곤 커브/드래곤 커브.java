import java.io.*;
import java.util.*;

public class Main {
    
    static int N;
    static int x,y,d,g;
    static int answer = 0;
    static int[][] graph = new int[101][101];
    static int[] dx = {0,-1,0,1};
    static int[] dy = {1,0,-1,0};
    static List<Integer> directions;
    
	public static void main (String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    N = Integer.parseInt(br.readLine());
	    for(int i=0; i<N; i++) {
	        String[] info = br.readLine().split(" ");
	        x = Integer.parseInt(info[1]);
	        y = Integer.parseInt(info[0]);
	        d = Integer.parseInt(info[2]);
	        g = Integer.parseInt(info[3]);
	        
	        graph[x][y] = 1;
	        getDirections();
	        draw();
	    }
	    checkSquare();
	    System.out.println(answer);
	}
	
	static void draw() {
	    for(int direction : directions) {
	        x = x + dx[direction];
	        y = y + dy[direction];
	        graph[x][y] = 1;
	    }
	}
	
	static void getDirections() {
	    directions = new ArrayList<>();
	    directions.add(d);
	    
	    while(g > 0) {
	        List<Integer> tempDirections = new ArrayList<>();
	        for(int i=directions.size()-1; i>=0; i--) {
	            int direction = directions.get(i)+1;
	            if(direction > 3) {
	                direction = 0;
	            }
	            tempDirections.add(direction);
	        }
	        directions.addAll(tempDirections);
	        g-=1;
	    }
	}
	
	static void checkSquare() {
	    for(int i=0; i<100; i++) {
	        for(int j=0; j<100; j++) {
	            if(graph[i][j]==1 && graph[i+1][j]==1 && graph[i][j+1]==1 && graph[i+1][j+1]==1) {
	                answer+=1;
	            }
	        }
	    }
	}
}