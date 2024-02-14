import java.io.*;

public class Main {
    
    static int N,M,max;
    static long answer;
    static int[] arr;
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        
        N = Integer.parseInt(info[0]);
        M = Integer.parseInt(info[1]);
        max = -1;
        answer = 0;
        
        arr = new int[N];
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, arr[i]);
        }
        
        long start = max;
        long end = Integer.MAX_VALUE;
        
        
        while(start <= end) {
            long mid = start + (end-start)/2;
            long count = 0;
            long sum = 0;
            
            for(int i=0; i<N; i++) {
                if(sum < arr[i]) {
                    sum = 0;
                    sum += mid;
                    count+=1;
                }
                sum -= arr[i];
            }
            
            if(count <= M) {
                answer = mid;
                end = mid-1;
            } else {
                start = mid+1;
            }
        }
        
        System.out.println(answer);
    }
}