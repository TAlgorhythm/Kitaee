import java.io.*;

public class Main {
    
    static int N,M;
    static int[] arr, visited, output;
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        
        N = Integer.parseInt(info[0]);
        M = Integer.parseInt(info[1]);
        
        arr = new int[N];
        visited = new int[N];
        output = new int[M];
        
        for(int i=0; i<N; i++) {
            arr[i] = i+1;
        }
        
        combination(0, 0);
    }
    
    static void combination(int depth, int start) {
        if(depth == M) {
            for(int i=0; i<M; i++) {
                System.out.print(output[i] + " ");
            }
            System.out.println();
            return;
        }
        
        for(int i=start; i<N; i++) {
            if(visited[i] == 0) {
                visited[i] = 1;
                output[depth] = arr[i];
                combination(depth+1, i+1);
                visited[i] = 0;
            }
        }
    }
}
