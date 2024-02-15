import java.io.*;
import java.util.*;

public class Main {
    
    static int N,M;
    static long answer;
    static int[] arr;
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        
        N = Integer.parseInt(info[0]);
        M = Integer.parseInt(info[1]);
        answer = 0;
        
        arr = new int[N];
        info = br.readLine().split(" ");
        
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(info[i]);
        }
        Arrays.sort(arr);
        
        long start = 1;
        long end = 2000000000;
        
        while(start <= end) {
            long mid = start + (end-start)/2;
            long sum = 0;
            for(int i=0; i<N; i++) {
                if(arr[i] > mid) {
                    sum += (arr[i]-mid);
                }
            }
            
            if(sum >= M) {
                answer = mid;
                start = mid+1;
            } else {
                end = mid-1;
            }
        }
        
        System.out.println(answer);
    }
}