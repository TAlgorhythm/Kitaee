import java.io.*;
import java.util.*;

public class Main {
    
    static int N,M;
    static int[] arr;
    static String[] info;
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        
        info = br.readLine().split(" ");
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(info[i]);
        }
        Arrays.sort(arr);
        M = Integer.parseInt(br.readLine());
        
        info = br.readLine().split(" ");
        
        for(int i=0; i<M; i++) {
            System.out.println(binarySearch(Integer.parseInt(info[i])));
        }
    }
    
    static int binarySearch(int target) {
        int start = 0;
        int end = N-1;
        
        while(start <= end) {
            int mid = (start+end)/2;
            if(arr[mid] == target) {
                return 1;
            }
            else if(arr[mid] < target) {
                start = mid+1;
            }
            else {
                end = mid-1;
            }
        }
        return 0;
    }
}