import java.io.*;
import java.util.*;

public class Main {
    
    static int N, target1, target2, gap;
    static int[] arr;
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int start = 0;
        int end = N-1;
        
        arr = new int[N];
        String[] info = br.readLine().split(" ");
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(info[i]);
        }
        
        Arrays.sort(arr);
        
        target1 = arr[0];
        target2 = arr[N-1];
        gap = Math.abs(target1 + target2);
        
        while(start < end) {
            int currentGap = arr[start] + arr[end];
            if(Math.abs(currentGap) < gap) {
                target1 = arr[start];
                target2 = arr[end];
                gap = Math.abs(currentGap);
            }
            if(currentGap < 0) {
                start+=1;
            } else {
                end-=1;
            }
        }
        
        System.out.println(target1 + " " + target2);
    }
}