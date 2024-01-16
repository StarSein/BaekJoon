import java.io.*;
import java.util.*;


public class Main {

    static int N, K;
    static int[] S;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        S = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            S[i] = Integer.parseInt(st.nextToken());
        }

        // 투 포인터를 이용해, 구간의 홀수를 K개 이내로 유지한 채로 구간 길이를 늘이고 줄이며 정답을 찾는다
        int s = 0;
        int oddCount = 0;
        int answer = 0;
        for (int e = 0; e < N; e++) {
            if (S[e] % 2 == 1) {
                oddCount++;
            }
            while (oddCount > K) {
                if (S[s++] % 2 == 1) {
                    oddCount--;
                }
            }
            answer = Math.max(answer, e - s + 1 - oddCount);
        }

        // 정답을 출력한다
        System.out.println(answer);
    }
}
