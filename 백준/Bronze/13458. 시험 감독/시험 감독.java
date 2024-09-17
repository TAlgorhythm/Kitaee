import java.io.*;
import java.util.*;

public class Main {
    
    static int N;
    static Long[] tester;
    static Long B,C;
    static Long answer = 0L;
    
	public static void main (String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    N = Integer.parseInt(br.readLine());
	    tester = new Long[N];
	    String[] info = br.readLine().split(" ");
	    for(int i=0; i<N; i++) {
	        tester[i] = Long.parseLong(info[i]);
	    }
	    info = br.readLine().split(" ");
	    B = Long.parseLong(info[0]);
	    C = Long.parseLong(info[1]);
	    
	    for(int i=0; i<N; i++) {
	        tester[i]-=B;
	        answer+=1;
	        if(tester[i] > 0) {
	           Double plus = Math.ceil(((double)tester[i]/C));
	           answer+=plus.longValue();
	        }
	    }
	    
	    System.out.println(answer);
	}
}