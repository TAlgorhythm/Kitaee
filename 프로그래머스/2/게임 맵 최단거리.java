import java.util.*;

class Solution {
    
    static class Node {
        int x;
        int y;
        int count;
        
        public Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
    
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static int n,m;
    static int[][] visited;
    static LinkedList<Node> queue = new LinkedList<>();
    
    public int solution(int[][] maps) {
        n = maps.length;
        m = maps[0].length;
        
        visited = new int[n][m];
        visited[0][0] = 1;
        queue.offer(new Node(0,0,1));
        
        while(!queue.isEmpty()) {
            Node currentNode = queue.poll();
            if(currentNode.x == n-1 && currentNode.y == m-1) {
                return currentNode.count;
            }
            
            for(int i=0; i<4; i++) {
                int nx = currentNode.x + dx[i];
                int ny = currentNode.y + dy[i];
                
                if(nx>=0 && ny>=0 && nx<n && ny<m && visited[nx][ny]==0 && maps[nx][ny]==1) {
                    visited[nx][ny] = 1;
                    queue.offer(new Node(nx,ny,currentNode.count+1));
                }
            }
        }
        
        return -1;
    }
}
