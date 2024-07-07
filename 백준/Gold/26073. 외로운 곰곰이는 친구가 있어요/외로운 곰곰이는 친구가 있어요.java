import java.io.*;
import java.util.*;

/*
베주 항등식(증명 출처: https://namu.wiki/w/%EB%B2%A0%EC%A3%BC%20%ED%95%AD%EB%93%B1%EC%8B%9D)
1. 둘 중 적어도 하나는 0이 아닌 정수 a, b 가 있다고 하자
   m = ax + by > 0(x, y는 정수)인 m 이 반드시 존재하는가?
   a > 0 이면 (x, y) = (1, 0)으로, a < 0 이면 (x, y) = (-1, 0)으로,
   a == 0 이면 b > 0 또는 b < 0 이므로 각각 (x, y) = (0, 1), (x, y) = (0, -1)으로
   m이 반드시 존재한다
   그런 m들의 집합을 S 라고 하자
   그리고 S 에 속한 원소들 중 최솟값을 d 라고 하자
2. d = ak + bl 이라고 하자
   S 에 속하는 임의의 원소 x에 대하여 x가 d의 배수가 아니라고 가정하자
   그러면 x = qd + r (0 <= r < d)
   이때 x는 S에 속하므로 x = au + bv
   r = m - qd = au + bv - q(ak + bl) = a(u - qk) + b(v - ql) 로 r 또한 S에 속한다
   이는 S에 속한 원소들 중 최솟값이 d라는 가정에 모순되므로 "x가 d의 배수가 아니다"는 거짓이다
   따라서 x는 d의 배수이다
   즉 S의 모든 원소는 d의 배수이다
   1) a, b 둘 다 0이 아닌 경우
   (x, y)의 순서쌍 (1, 0), (-1, 0) (0, 1) (0, -1) 중 적어도 두 개는 ax + by 를 d의 배수로 만든다
   즉 |a|, |b|가 S의 원소로서 d의 배수이므로
   따라서 d는 |a|와 |b|의 공약수이다
3. e가 a, b의 공약수라고 하자. a = a'e, b = b'e 이면 d = ak + bl = e(a'k + b'l) 이므로
   e는 d의 약수이다. 따라서 a, b의 모든 공약수는 d의 약수이므로, d는 a, b의 최대공약수이다.

베주 방정식에 따라 a, b를 덧셈과 뺄셈에 무한히 사용할 수 있다면 g = gcd(a, b)를 표현할 수 있다
g를 표현할 수 있으므로 g의 배수 또한 표현할 수 있다
 */

public class Main {

    static int N, X, Y, K;
    static int[] A;

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        while (N-- > 0) {
            // 각 테스트케이스에 대해 입력을 받는다
            StringTokenizer st = new StringTokenizer(br.readLine());
            X = Integer.parseInt(st.nextToken());
            Y = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            A = new int[K];
            for (int i = 0; i < K; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }

            // A의 모든 원소의 최대공약수 g를 구한다
            int g = A[0];
            for (int i = 1; i < K; i++) {
                g = gcd(g, A[i]);
            }

            // X, Y 모두 g의 배수일 경우에는 (X, Y)에서 원점으로 갈 수 있다
            // 그렇지 않을 경우 갈 수 없다
            sb.append(X % g == 0 && Y % g == 0 ? "Ta-da\n" : "Gave up\n");
        }

        System.out.print(sb);
    }

    static int gcd(int a, int b) {
        while (b != 0) {
            int temp = a;
            a = b;
            b = temp % b;
        }
        return a;
    }
}
