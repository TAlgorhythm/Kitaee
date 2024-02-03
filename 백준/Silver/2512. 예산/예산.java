import java.io.*;
import java.util.*;

public class Main {
    
    static int N,M,answer;
    static int[] arr;
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        
        String[] info = br.readLine().split(" ");
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(info[i]);
        }
        
        M = Integer.parseInt(br.readLine());
        answer = 0;
        Arrays.sort(arr);
        
        int start = 1;
        int end = arr[N-1];
        
        while(start<=end) {
            int budget = (start+end)/2;
            if(isOverBudget(budget)) {
                answer = budget;
                start = budget+1;
            } else {
                end = budget-1;
            }
        }
        
        System.out.println(answer);
    }
    
    static boolean isOverBudget(int budget) {
        int sum = 0;
        for(int i=0; i<N; i++) {
            if(arr[i] >= budget) {
                sum += budget;
            } else {
                sum += arr[i];
            }
        }
        
        if(sum > M) {
            return false;
        }
        return true;
    }
}