import java.io.*;
import java.util.*;

public class Main {
    
    static int K,N;
    static long answer;
    static int[] arr;
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        
        K = Integer.parseInt(info[0]);
        N = Integer.parseInt(info[1]);
        arr = new int[K];
        answer = 0;
        
        for(int i=0; i<K; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        
        long start = 1;
        long end = arr[K-1];
        
        while(start <= end) {
            long mid = start + (end-start)/2;
            long count = 0;
            for(int i=0; i<K; i++) {
                count+= (arr[i]/mid);
            }
            
            if(count >= N) {
                answer = mid;
                start = mid+1;
            } else {
                end = mid-1;
            }
        }
        
        System.out.println(answer);
    }
}