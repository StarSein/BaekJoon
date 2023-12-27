import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static int M;
    static int lcm;
    static int[] X;
    static int[] counts;

    public static void main(String[] args) throws Exception {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            X[i] = Integer.parseInt(st.nextToken());
        }

        // N과 M의 최소공배수 구하기
        lcm = getLCM(N, M);

        // 최소공배수만큼의 기간동안 과제 i를 수행한 횟수 count[i] 계산
        counts = new int[N];
        int repeatNum = lcm / N;
        int k = 1;
        for (int t = 0; t < repeatNum; t++) {
            for (int i = 0; i < N; i++) {
                if (k++ % M == 0) {
                    continue;
                }
                counts[i]++;
            }
        }

        // X[i]를 count[i]로 나눈 최솟값 구하기
        int minVal = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            if (counts[i] == 0) {
                continue;
            }
            minVal = Math.min(minVal, X[i] / counts[i]);
        }

        // 최소공배수 * (최솟값 - 1)만큼의 기간이 이미 지났다고 가정하여 X[i] 차감
        for (int i = 0; i < N; i++) {
            X[i] -= counts[i] * (minVal - 1);
        }

        // 최소공배수 만큼 시뮬레이션하며 가장 먼저 끝나는 과제 구하기
        // 최소공배수 <= 1000만 이므로 O(N)의 시뮬레이션을 2번 해도 충분하다
        repeatNum *= 2;
        k = 1;
        for (int t = 0; t < repeatNum; t++) {
            for (int i = 0; i < N; i++) {
                if (k++ % M == 0) {
                    continue;
                }
                if (--X[i] == 0) {
                    System.out.println(i + 1);
                    return;
                }
            }
        }
    }

    static int getLCM(int a, int b) {
        int gcd = getGCD(a, b);
        return a / gcd * b;
    }

    static int getGCD(int a, int b) {
        while (b > 0) {
            int temp = a;
            a = b;
            b = temp % b;
        }
        return a;
    }
}
