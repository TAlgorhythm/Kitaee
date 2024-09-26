import java.io.*;
import java.util.*;

public class Main {

    static int N,B,C;
    static Long[] arr;
    static Long answer = 0L;

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String[] info = br.readLine().split(" ");
        arr = new Long[N];

        for(int i=0; i<N; i++) {
            arr[i] = Long.valueOf(info[i]);
        }
        info = br.readLine().split(" ");
        B = Integer.parseInt(info[0]);
        C = Integer.parseInt(info[1]);

        for(int i=0; i<arr.length; i++) {
            arr[i] -= B;
            answer+=1;
            if(arr[i] <= 0) {
                continue;
            } else {
                answer += (long)(Math.ceil((double)arr[i]/C));
            }
        }
        System.out.println(answer);
    }
}