import java.io.*;
import java.util.*;


public class Main {

    static final int INF = Integer.MAX_VALUE;
    static int W, H, A;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        Scanner sc = new Scanner(System.in);
        W = sc.nextInt();
        H = sc.nextInt();
        A = sc.nextInt();

        // 넓이 A를 달성하기 위해 가능한 모든 순서쌍 (a, b)에 대해
        // W가 a가 되기 위해 접어야 하는 최소 횟수, H가 b가 되기 위해 접어야 하는 최소 횟수의 합의 최솟값을 정답으로 갱신한다
        // W < a 이거나 H < b 인 경우 불가능하다
        int answer = INF;
        for (int a = 1; a <= A; a++) {
            if (A % a == 0) {
                int b = A / a;

                if (a > W || b > H) {
                    continue;
                }

                answer = Math.min(answer, getFoldCount(a, W) + getFoldCount(b, H));
            }
        }

        // 정답을 출력한다
        System.out.println(answer == INF ? -1 : answer);
    }

    static int getFoldCount(int s, int e) {
        int foldCount = 0;
        while (s < e) {
            s <<= 1;
            foldCount++;
        }
        return foldCount;
    }
}
