import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        long answer = 0;
        int rage = 0;
        for (int score : arr) {
            if (score >= rage + 1) {
                answer += score - rage - 1;
                rage++;
            }
        }
        System.out.println(answer);
    }
}
