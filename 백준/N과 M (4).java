import java.io.*;

public class Main {
    
    static int N,M;
    static int[] arr, output;
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] info = br.readLine().split(" ");
        
        N = Integer.parseInt(info[0]);
        M = Integer.parseInt(info[1]);
        
        arr = new int[N];
        output = new int[M];
        
        for(int i=0; i<N; i++) {
            arr[i] = i+1;
        }
        
        permutationWithRepitition(0, 0);
        
        System.out.println(sb.toString());
    }
    
    static void permutationWithRepitition(int depth, int start) {
        if(depth == M) {
            for(int i=0; i<M; i++) {
                sb.append(output[i] + " ");
            }
            sb.append("\n");
            return;
        }
        
        for(int i=start; i<N; i++) {
            output[depth] = arr[i];
            permutationWithRepitition(depth+1, i);
        }
    }
}
