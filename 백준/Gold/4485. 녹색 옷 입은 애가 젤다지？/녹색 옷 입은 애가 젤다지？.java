import java.io.*;
import java.util.*;

class Main {
    
    static class Node implements Comparable<Node> {
        int x;
        int y;
        int cost;
        
        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node node) {
            return this.cost - node.cost;
        }
    }
    
    static int N;
    static int[][] graph;
    static int[][] dp;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static PriorityQueue<Node> queue;
    static int count = 1;
    
	public static void main (String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    while(true) {
	        N = Integer.parseInt(br.readLine());
	        if(N == 0) {
	            return;
	        }
	        
	        graph = new int[N][N];
	        for(int i=0; i<N; i++) {
	            String[] info = br.readLine().split(" ");
	            for(int j=0; j<N; j++) {
	                graph[i][j] = Integer.parseInt(info[j]);
	            }
	        }
	        
	        System.out.println("Problem "+count+": "+dikjstra());
	        count+=1;
	    }
	}
	
	static int dikjstra() {
	    setDp();
	    queue = new PriorityQueue<>();
	    dp[0][0] = graph[0][0];
	    queue.offer(new Node(0,0,graph[0][0]));
	    
	    while(!queue.isEmpty()) {
	        Node node = queue.poll();
	        if(node.x == N-1 && node.y == N-1) {
	            return node.cost;
	        }
	        for(int i=0; i<4; i++) {
	            int nx = dx[i] + node.x;
	            int ny = dy[i] + node.y;
	            if(0<=nx && nx<N && 0<=ny && ny<N && dp[nx][ny]>node.cost+graph[nx][ny]) {
	                dp[nx][ny] = node.cost+graph[nx][ny];
	                queue.offer(new Node(nx,ny,dp[nx][ny]));
	            }
	        }
	    }
	    return -1;
	}
	
	static void setDp() {
	    dp = new int[N][N];
	    for(int i=0; i<N; i++) {
	        for(int j=0; j<N; j++) {
	            dp[i][j] = Integer.MAX_VALUE;
	        }
	    }
	}
}