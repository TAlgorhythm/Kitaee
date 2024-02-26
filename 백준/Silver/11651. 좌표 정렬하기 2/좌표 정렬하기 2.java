import java.io.*;
import java.util.*;

public class Main {
    
    static int N;
    static PriorityQueue <Coordinate> queue = new PriorityQueue<>();
    
    static class Coordinate implements Comparable<Coordinate> {
        int x;
        int y;
        
        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        @Override
        public int compareTo(Coordinate o) {
            if(this.y == o.y) {
                return this.x - o.x;
            }
            return this.y - o.y;
        }
    }
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        for(int i=0; i<N; i++) {
            String[] info = br.readLine().split(" ");
            queue.offer(new Coordinate(Integer.parseInt(info[0]), Integer.parseInt(info[1])));
        }
        
        while(!queue.isEmpty()) {
            Coordinate current = queue.poll();
            System.out.println(current.x + " " + current.y);
        }
    }
}