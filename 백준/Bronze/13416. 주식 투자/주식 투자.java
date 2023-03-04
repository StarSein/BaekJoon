import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());

            int ans = 0;
            for (int i = 0; i < N; i++) {
                int max = 0;
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 3; j++) {
                    max = Math.max(max, Integer.parseInt(st.nextToken()));
                }
                ans += max;
            }

            answer.append(ans).append('\n');
        }
        System.out.print(answer);
    }
}
