import java.io.*;

public class Main {

    static int N,K;
    static int[] arr;
    static int[] robot;
    static int answer = 0;

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        N = Integer.parseInt(info[0]);
        K = Integer.parseInt(info[1]);
        arr = new int[2*N];
        robot = new int[N];
        info = br.readLine().split(" ");
        for(int i=0; i<2*N; i++) {
            arr[i] = Integer.parseInt(info[i]);
        }

        while(true) {
            answer+=1;
            rotateRail();
            rotateRobot();
            moveRobot();
            uploadRobot();
            if(isDurationUnderK()) {
                break;
            }
        }

        System.out.println(answer);
    }

    static void uploadRobot() {
        if(arr[0] > 0) {
            robot[0] = 1;
            arr[0]-=1;
        }
    }

    static void moveRobot() {
        for(int i=N-1; i>=0; i--) {
            if(robot[i]==1 && arr[i+1]>0 && robot[i+1]==0) {
                arr[i+1]-=1;
                robot[i] = 0;
                robot[i+1] = 1;
                robot[N-1] = 0;
            }
        }
    }

    static void rotateRail() {
        int temp = arr[2*N-1];
        for(int i=2*N-1; i>=1; i--) {
            arr[i] = arr[i-1];
        }
        arr[0] = temp;
    }

    static void rotateRobot() {
        for(int i=N-1; i>=1; i--) {
            robot[i] = robot[i-1];
        }
        robot[0] = 0;
        robot[N-1] = 0;
    }

    static Boolean isDurationUnderK() {
        int count = 0;
        for(int i=0; i<2*N; i++) {
            if(arr[i] == 0) {
                count+=1;
                if(count >= K) {
                    return true;
                }
            }
        }
        return false;
    }
}