import java.io.*;


public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int dir = 0;
        for (int i = 0; i < 10; i++) {
            int t = Integer.parseInt(br.readLine());
            dir = (dir + t) % 4;
        }
        char[] arr = {'N', 'E', 'S', 'W'};
        System.out.println(arr[dir]);
    }
}
