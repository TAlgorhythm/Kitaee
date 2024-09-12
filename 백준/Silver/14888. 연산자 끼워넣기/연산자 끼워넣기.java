import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] num;
    static int[] calculate;
    static int[] visited;
    static int[] result;
    static int MAX = Integer.MIN_VALUE;
    static int MIN = Integer.MAX_VALUE;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        num = new int[N];
        calculate = new int[N-1];
        result = new int[N-1];
        visited = new int[N-1];
        int index = 0;

        String[] info = br.readLine().split(" ");
        for(int i=0; i<N; i++) {
            num[i] = Integer.parseInt(info[i]);
        }
        info = br.readLine().split(" ");
        for(int i=0; i<4; i++) {
            int count = Integer.parseInt(info[i]);
            for(int j=0; j<count; j++) {
                calculate[index] = i;
                index+=1;
            }
        }

        permutation(0);
        
        System.out.println(MAX);
        System.out.print(MIN);
    }

    static void permutation(int depth) {
        if(depth == N-1) {
            getCalculation();
            return;
        }

        for(int i=0; i<N-1; i++) {
            if(visited[i] == 0) {
                visited[i] = 1;
                result[depth] = calculate[i];
                permutation(depth+1);
                visited[i] = 0;
            }
        }
    }

    static void getCalculation() {
        // num 배열과 calculate 배열이 들어있음
        int temp = num[0];
        int calculateIndex = 0;
        for(int i=1; i<N; i++) {
            if(result[calculateIndex] == 0) {
                temp = temp + num[i];
            } else if(result[calculateIndex] == 1) {
                temp = temp - num[i];
            } else if(result[calculateIndex] == 2) {
                temp = temp * num[i];
            } else {
                if(temp > 0) {
                    temp = temp / num[i];
                } else {
                    temp = temp * (-1);
                    temp = temp / num[i];
                    temp = temp * (-1);
                }
            }
            calculateIndex+=1;
        }
        MAX = Math.max(MAX, temp);
        MIN = Math.min(MIN, temp);
    }
}