import java.io.*;
import java.util.*;

public class Main {
    
    static int N,M;
    static int[] arr;
    static String[] info;
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        info = br.readLine().split(" ");
        arr = new int[N];
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(info[i]);
        }
        
        Arrays.sort(arr);
        
        M = Integer.parseInt(br.readLine());
        info = br.readLine().split(" ");
        for(int i=0; i<M; i++) {
            int target = Integer.parseInt(info[i]);
            sb.append(String.valueOf(upperBound(target)-lowerBound(target)) + " ");
        }
        
        System.out.println(sb.toString());
    }
    
    static int lowerBound(int target) {
        int start = 0;
        int end = N;
        while(start < end) {
            int mid = (start+end)/2;
            if(arr[mid] >= target) {
                end = mid;
            } else {
                start = mid+1;
            }
        }
        return start;
    }
    
    static int upperBound(int target) {
        int start = 0;
        int end = N;
        while(start < end) {
            int mid = (start+end)/2;
            if(arr[mid] > target) {
                end = mid;
            } else {
                start = mid+1;
            }
        }
        return start;
    }
}