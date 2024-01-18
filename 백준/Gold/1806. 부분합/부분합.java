import java.io.*;
import java.util.*;


public class Main {

    static int N, S;
    static int[] seq;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        seq = new int[N];
        for (int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        // 부분합에 해당하는 구간의 두 포인터 s, e 를 우측으로 움직이며 정답을 갱신한다
        int answer = Integer.MAX_VALUE;
        int s = 0;
        int partialSum = 0;
        for (int e = 0; e < N; e++) {
            partialSum += seq[e];

            // 구간합이 S 미만이 되지 않는 선에서 s를 우측으로 움직인다
            while (partialSum - seq[s] >= S) {
                partialSum -= seq[s++];
            }

            // 부분합이 S 이상일 때에만 정답을 갱신한다
            if (partialSum < S) {
                continue;
            }
            answer = Math.min(answer, e - s + 1);
        }

        // 정답을 출력한다
        System.out.println(answer == Integer.MAX_VALUE ? 0 : answer);
    }
}
