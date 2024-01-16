import java.io.*;
import java.util.*;


public class Main {

    static int N, M;
    static int[] A;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        // 투 포인터를 이용해 구간 [i:j]의 합이 M이 되는 (i, j)의 개수를 센다
        int answer = 0;
        int i = 0;
        int j = 0;
        int sum = 0;
        while (i < N) {
            if (sum == M) {
                answer++;
                sum -= A[i++];
                if (j < N) {
                    sum += A[j++];
                }
            } else if (sum < M && j < N) {
                sum += A[j++];
            } else {
                sum -= A[i++];
            }
        }

        // 개수를 출력한다
        System.out.println(answer);
    }
}
