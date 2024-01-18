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

        // 구간합이 S 이상이 될 때까지 구간의 오른쪽 끝점을 우측으로 움직인다
        int s = 0;
        int e = 0;
        int partialSum = 0;
        for (; e < N && partialSum < S; e++) {
            partialSum += seq[e];
        }

        // S 이상의 구간합을 만들 수 없는 경우 0을 출력한다
        if (partialSum < S) {
            System.out.println(0);
            return;
        }

        // 부분합에 해당하는 구간의 두 포인터 s, e 를 우측으로 움직이며 정답을 갱신한다
        int answer = N;
        partialSum -= seq[--e];
        for (; e < N; e++) {
            partialSum += seq[e];
            // 구간합이 S 미만이 되지 않는 선에서 s를 우측으로 움직인다
            while (partialSum - seq[s] >= S) {
                partialSum -= seq[s++];
            }
            answer = Math.min(answer, e - s + 1);
        }

        // 정답을 출력한다
        System.out.println(answer);
    }
}
