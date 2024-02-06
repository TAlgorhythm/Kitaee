import java.io.*;
import java.util.*;

public class Main {
    
    static int N,M,answer;
    static int[] arr;
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        
        N = Integer.parseInt(info[0]);
        M = Integer.parseInt(info[1]);
        answer = 0;
        
        arr = new int[N];
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        
        Arrays.sort(arr);
        
        int start = 1;
        int end = arr[N-1]-arr[0];
        while(start <= end) {
            int mid = (start+end)/2;
            int count = 1;
            int lastIndex = arr[0];
            for(int i=1; i<N; i++) {
                if(arr[i] >= lastIndex+mid) {
                    count+=1;
                    lastIndex = arr[i];
                }
            }
            
            if(count >= M) {
                answer = mid;
                start = mid+1;
            } else {
                end = mid-1;
            }
        }
        
        System.out.println(answer);
    }
}