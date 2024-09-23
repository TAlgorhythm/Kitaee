import java.io.*;
import java.util.*;

public class Main {

    static int L,C;
    static int[] visited;
    static String[] result;
    static String[] arr;
    static StringBuilder sb;

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = br.readLine().split(" ");
        L = Integer.parseInt(arr[0]);
        C = Integer.parseInt(arr[1]);
        arr = br.readLine().split(" ");
        Arrays.sort(arr);

        result = new String[L];
        visited = new int[C];

        dfs(0, 0, 0);
    }

    // count1 : 자음 갯수, count2 : 모음 갯수
    static void dfs(int count1, int count2, int start) {
        if(count1>=2 && count2>=1 && count1+count2==L) {
            sb = new StringBuilder();
            for(int i=0; i<L; i++) {
                sb.append(result[i]);
            }
            System.out.println(sb.toString());
            return;
        } else if(count1+count2 == L) {
            return;
        }
        for(int i=start; i<C; i++) {
            if(visited[i] == 0) {
                visited[i] = 1;
                result[count1+count2] = arr[i];
                if(arr[i].equals("a") || arr[i].equals("e") || arr[i].equals("i") || arr[i].equals("o") || arr[i].equals("u")) {
                    dfs(count1, count2+1, i+1);
                } else {
                    dfs(count1+1, count2, i+1);
                }
                visited[i] = 0;
            }
        }
    }
}