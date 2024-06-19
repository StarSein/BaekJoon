import java.io.*;
import java.util.*;

/*
ex1)
1 = <1:1>
3 = <3:3>
13 = <3:1>
23 = <3:11>
33 = <3:9>

ex2)
1 = <1:1>
7 = <7:7>
17 = <7:5>
27 = <7:3>
37 = <7:1>

ex3)
1 = <1:1>
5 = <5:5>
18 = <5:7>
31 = <5:9>
44 = <5:11>
57 = <5:2>
70 = <5:4>
83 = <5:6>

O(N) 풀이
x를 일치시켜 놓은 다음 lcm(M, N)까지 M씩 증가시키면서 y가 일치하는 순간을 포착한다
 */

public class Main {

    static int T;
    static int M, N, x, y;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        // 각 테스트 케이스에 대해
        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            // 입력을 받는다
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            // M, N 의 최소공배수 lcm를 구한다
            int lcm = getLCM(M, N);
            boolean valid = false;
            for (int t = x; t <= lcm; t += M) {
                int n = t % N;
                if (n == 0) {
                    n = N;
                }
                if (n == y) {
                    valid = true;
                    sb.append(t).append('\n');
                    break;
                }
            }
            // 발견되지 않으면 -1을 정답 문자열에 추가한다
            if (valid) {
                continue;
            }
            sb.append("-1\n");
        }

        // 정답 문자열을 출력한다
        System.out.print(sb);
    }

    static int getGCD(int a, int b) {
        while (b > 0) {
            int temp = a;
            a = b;
            b = temp % b;
        }
        return a;
    }

    static int getLCM(int a, int b) {
        return a * b / getGCD(a, b); // Integer Overflow 위험 없음
    }
}
