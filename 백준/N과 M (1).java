import java.io.*;

public class Main {
    
    static int N,M;
    static int[] arr, visited, output;
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] inputString = br.readLine().split(" ");
        N = Integer.parseInt(inputString[0]);
        M = Integer.parseInt(inputString[1]);
        
        arr = new int[N];
        visited = new int[N];
        output = new int[M];
        
        for(int i=0; i<N; i++) {
            arr[i] = i+1;
        }
        
        permutation(0);
    }
    
    static void permutation(int depth) {
        if(depth == M) {
            for(int i=0; i<M; i++) {
                System.out.print(output[i] + " ");
            }
            System.out.println();
            return;
        }
        for(int i=0; i<N; i++) {
            if(visited[i] == 0) {
                output[depth] = arr[i];
                visited[i] = 1;
                permutation(depth+1);
                visited[i] = 0;
            }
        }
    }
}
