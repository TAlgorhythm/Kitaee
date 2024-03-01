import java.io.*;
import java.util.*;

public class Main {
    
    static int N, M, answer;
    static List<Integer> ship = new ArrayList<>();
    static List<Integer> box = new ArrayList<>();
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String[] info = br.readLine().split(" ");
        
        for(int i=0; i<N; i++) {
            ship.add(Integer.parseInt(info[i]));
        }
        
        M = Integer.parseInt(br.readLine());
        info = br.readLine().split(" ");
        
        for(int i=0; i<M; i++) {
            box.add(Integer.parseInt(info[i]));
        }
        
        Collections.sort(ship, Collections.reverseOrder());
        Collections.sort(box, Collections.reverseOrder());
        
        if(box.get(0) > ship.get(0)) {
            System.out.println(-1);
            return;
        }
        
        while(!box.isEmpty()) {
            int boxIndex = 0;
            for(int i=0; i<N;) {
                if(boxIndex == box.size()) {
                    break;
                } else if(box.get(boxIndex) <= ship.get(i)) {
                    box.remove(boxIndex);
                    i+=1;
                } else {
                    boxIndex+=1;
                }
            }
            answer+=1;
        }
        
        System.out.println(answer);
    }
}