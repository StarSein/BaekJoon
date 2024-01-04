// 1번 쿼리의 경우 누적합으로도 해결할 수 있겠지만, 등차수열의 합 공식을 이용해도 된다
// {(r - l + 1) * a} + {(l + r - 2) * d * (r - l + 1) / 2}
// = (r - l + 1) * {a + (l + r - 2) * d / 2} -> 2로 나눌 때 버림 문제가 있으므로 위의 식을 그대로 사용하자
// 2번 쿼리의 경우
// a + ld, a + (l+1)d 의 최대공약수를 구하는 것은
// a + ld, d
// a, d 의 최대공약수를 구하는 것과 같다
// 따라서 l == r 인 경우만 제외하고 gcd(a, d) 를 출력하면 된다


import java.io.*;
import java.util.*;


public class Main {

    static long a, d;
    static int q;

    public static void main(String[] args) throws Exception {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Long.parseLong(st.nextToken());
        d = Long.parseLong(st.nextToken());

        // a와 d의 최대공약수 구해 놓기
        long gcd = getGCD(a, d);

        q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            if (cmd == 1) { // 1번 쿼리의 경우
                sb.append(getIntervalSum(l, r)).append('\n');
            } else {        // 2번 쿼리의 경우
                sb.append(l == r ? getA(l) : gcd).append('\n');
            }
        }

        // 정답 문자열 출력하기
        System.out.print(sb);
    }

    static long getGCD(long x, long y) {
        while (y > 0) {
            long temp = x;
            x = y;
            y = temp % y;
        }
        return x;
    }

    static long getIntervalSum(int l, int r) {
        return ((r - l + 1) * a) + ((l + r - 2) * d * (r - l + 1) / 2);
    }

    static long getA(int i) {
        return a + (i - 1) * d;
    }
}
