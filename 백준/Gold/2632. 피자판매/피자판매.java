import java.io.*;
import java.util.*;


public class Main {

    static final int MAX_SIZE = 2_000_001;
    static int size, m, n, sumA, sumB, answer;
    static int[] A, B, cntA, cntB;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        size = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        A = new int[2 * m - 1];
        for (int i = 0; i < m; i++) {
            A[i] = Integer.parseInt(br.readLine());
            sumA += A[i];
        }
        for (int i = m; i < A.length; i++) {
            A[i] = A[i - m];
        }
        B = new int[2 * n - 1];
        for (int i = 0; i < n; i++) {
            B[i] = Integer.parseInt(br.readLine());
            sumB += B[i];
        }
        for (int i = n; i < B.length; i++) {
            B[i] = B[i - n];
        }

        cntA = new int[MAX_SIZE];
        cntA[sumA] = cntA[0] = 1;
        for (int s = 0; s < m; s++) {
            int sum = 0;
            for (int e = s; e < s + m - 1; e++) {
                sum += A[e];
                cntA[sum]++;
            }
        }

        cntB = new int[MAX_SIZE];
        cntB[sumB] = cntB[0] = 1;
        for (int s = 0; s < n; s++) {
            int sum = 0;
            for (int e = s; e < s + n - 1; e++) {
                sum += B[e];
                cntB[sum]++;
            }
        }

        for (int a = 0; a <= size; a++) {
            answer += cntA[a] * cntB[size - a];
        }

        System.out.println(answer);
    }
}
