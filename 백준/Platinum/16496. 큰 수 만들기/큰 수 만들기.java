import java.io.*;
import java.util.*;

public class Main {
    
    static int N;
    static String arr[];
    static StringBuilder answer = new StringBuilder();
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        arr = br.readLine().split(" ");
        Arrays.sort(arr, ((x, y) -> (y + x).compareTo(x + y)));
        
        for(int i=0; i<N; i++) {
            answer.append(arr[i]);
        }
        
        if(answer.charAt(0) == '0') {
            System.out.println(0);
        } else {
            System.out.println(answer);
        }
    }
}