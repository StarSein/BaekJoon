// 조건에 맞는 정사각형 중 가장 넓이가 작은 것은
// 네 변 중 적어도 하나는 내부의 점들 중 하나 이상과 인접해 있다


import java.io.*;
import java.util.*;


public class Main {

    static int N, K;
    static int[] x, y;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        x = new int[N];
        y = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
        }

        // 기준점으로 삼을 모든 (x, y)에 대해 - O(N^2)
        // 해당 점을 정사각형 내부의 왼쪽 위에 둔다고 할 때
        // f(t) : 정사각형의 변의 길이가 t라고 할 때 내부에 존재하는 점의 개수
        // f(t) >= K 인 t의 최솟값을 구하고 정답을 갱신한다 - O(NT) (T = 31)
        int minLength = Integer.MAX_VALUE;
        for (int xi : x) {
            for (int yi : y) {
                int s = 2;
                int e = 2_000_000_002;
                while (s <= e) {
                    int mid = s + (e - s) / 2;
                    if (f(mid, xi - 1, yi - 1) >= K) {
                        minLength = Math.min(minLength, mid);
                        e = mid - 1;
                    } else {
                        s = mid + 1;
                    }
                }
            }
        }

        // 정답을 출력한다
        System.out.println((long) minLength * minLength);
    }

    static int f(int length, long sx, long sy) {
        int innerCount = 0;
        for (int i = 0; i < N; i++) {
            if (sx < x[i] && x[i] < sx + length && sy < y[i] && y[i] < sy + length) {
                innerCount++;
            }
        }
        return innerCount;
    }
}
