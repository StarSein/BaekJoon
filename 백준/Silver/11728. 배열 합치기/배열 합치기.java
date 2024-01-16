import java.io.*;
import java.util.*;


public class Main {

    static int N, M;
    static int[] A, B, C;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        B = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        // 머지 소트의 원리를 이용하여 정렬 상태의 배열 C를 만든다
        C = new int[N + M];
        int a = 0;
        int b = 0;
        for (int i = 0; i < C.length; i++) {
            if (b == M || a < N && A[a] < B[b]) {
                C[i] = A[a++];
            } else {
                C[i] = B[b++];
            }
        }

        // 배열 C을 출력한다
        StringBuilder sb = new StringBuilder();
        for (int c : C) {
            sb.append(c).append(' ');
        }
        System.out.println(sb);
    }
}
