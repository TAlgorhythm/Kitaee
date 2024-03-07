import java.io.*;

public class Main {
    
    static int X,Y;
    static long answer = 0L;
    static long N = 0L;
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        
        X = Integer.parseInt(info[0]);
        Y = Integer.parseInt(info[1]);
        int sub = Y-X;
        
        if(sub == 0) {
            System.out.println(0);
            return;
        }
        
        while(N*N <= sub) {
            N+=1;
        }
        
        N-=1;
        answer = 2*N - 1;
        sub -= N*N;
        
        while(sub > 0) {
            for(long i=N; i>=1; i--) {
                if(i <= sub) {
                    answer+=1;
                    sub -= i;
                    break;
                }
            }
        }
        
        System.out.println(answer);
    }
}