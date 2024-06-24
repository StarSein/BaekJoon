import java.util.*;

/*
dfs + 메모이제이션 을 이용해 풀이하자
y = f(x) 이면 g(y) == g(x)
이때 사이클이 생기는 경우에 유의하여 초기값 처리를 하자
계산 필요 = -2
계산 중 = -3 (발견 시 사이클임)
 */

public class Main {

    static int L, R;
    static int[] f, g;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        Scanner sc = new Scanner(System.in);
        L = sc.nextInt();
        R = sc.nextInt();
        f = new int[100_001];
        g = new int[100_001];
        Arrays.fill(f, -2);
        Arrays.fill(g, -2);

        // [L, R]의 x에 대해 g(x)의 총합을 출력한다
        int answer = 0;
        for (int x = L; x <= R; x++) {
            answer += getG(x);
        }
        System.out.println(answer);
    }

    static int getG(int x) {
        if (x > 100_000) {
            return -1;
        }
        if (g[x] == -3) {
            return g[x] = 0;
        }
        if (g[x] != -2) {
            return g[x];
        }

        g[x] = -3;
        if (x == getF(x)) {
            return g[x] = 1;
        } else {
            return g[x] = getG(getF(x));
        }
    }

    static int getF(int x) {
        if (f[x] != -2) {
            return f[x];
        }

        int temp = x;
        int sum = 0;
        int mul = 1;
        while (temp > 0) {
            int digit = temp % 10;
            sum += digit;
            mul *= digit;
            temp /= 10;
        }

        int w = 10;
        while (w <= mul) {
            w *= 10;
        }

        return f[x] = w * sum + mul;
    }
}
