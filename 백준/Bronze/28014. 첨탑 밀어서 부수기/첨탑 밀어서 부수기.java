import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] H;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        H = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            H[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        int prevH = 0;
        for (int curH : H) {
            if (prevH <= curH) answer++;
            prevH = curH;
        }
        System.out.println(answer);
    }
}
