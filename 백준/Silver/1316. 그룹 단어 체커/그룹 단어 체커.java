import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int answer = 0;
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        for(int i=0; i<N; i++) {
            if(isGroupWord(br.readLine())) {
                answer+=1;
            }
        }
        
        System.out.println(answer);
    }
    
    static Boolean isGroupWord(String word) {
        HashMap<String, Integer> map = new HashMap<>();
        for(int i=0; i<word.length(); i++) {
            String c = String.valueOf(word.charAt(i));
            if(!map.containsKey(c)) {
                map.put(c, 1);
            } else {
                if(!c.equals(String.valueOf(word.charAt(i-1)))) {
                    return false;
                }
            }
        }
        return true;
    }
}