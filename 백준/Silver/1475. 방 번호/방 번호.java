import java.io.*;
import java.util.*;

public class Main {

    static String N;
    static int answer = 0;
    static Map<Integer, Integer> map = new HashMap<>();

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = br.readLine();
        plusSet();
        for(int i=0; i<N.length(); i++) {
            int num = Character.getNumericValue(N.charAt(i));
            if(num == 9) {
                num = 6;
            }
            if(map.get(num) == 0) {
                plusSet();
            }
            map.put(num, map.get(num)-1);
        }
        System.out.println(answer);
    }

    static void plusSet() {
        for(int i=0; i<=8; i++) {
            if(i == 6) {
                if(map.containsKey(i)) {
                    map.put(i, map.get(i)+2);
                } else {
                    map.put(i, 2);
                }
            } else {
                if(map.containsKey(i)) {
                    map.put(i, map.get(i)+1);
                } else {
                    map.put(i, 1);
                }
            }
        }
        answer+=1;
    }
}