import java.io.*;
import java.util.*;

/*
문제의 조건을 따르면 구간 [l, r]을 한 번 뒤집고 나면
절대 [0, l-1] 과 [r+1, N-1] 중 일부가 뒤집혀서는 안 된다
1. [l, r]에서 뒤집기를 하거나 하지 않은 두 가지 상황에서
   [l+1, r], [l, r-1]로 진행한다
2. [l, r]에서 구간 [0, l-1]과 [r+1, N-1]에서 A==B 여부를 체크한다
   일치하지 않을 경우 더 이상 진행하지 않는다
3. long 타입 정수와 비트마스킹을 이용해서 오버헤드를 줄이자
 */

public class Main {

    static class Tuple {
        long x;
        int s, e;

        Tuple(long x, int s, int e) {
            this.x = x;
            this.s = s;
            this.e = e;
        }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Tuple)) {
                return false;
            }
            Tuple t = (Tuple) other;
            return x == t.x && s == t.s && e == t.e;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, s, e);
        }
    }

    static final int INF = 1_000_000;
    static long A, B;
    static HashMap<Tuple, Integer> cache = new HashMap<>();

    public static void main(String[] args) throws Exception {
        // 입력을 받는다. 단 문자열을 좌우반전된 이진수로 간주하여 정수로 저장한다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        A = 0L;
        char[] a = br.readLine().toCharArray();
        for (int i = 0; i < a.length; i++) {
            if (a[i] == '1') {
                A += 1L << i;
            }
        }
        B = 0L;
        char[] b = br.readLine().toCharArray();
        for (int i = 0; i < b.length; i++) {
            if (b[i] == '1') {
                B += 1L << i;
            }
        }

        // A를 B로 만드는 최소 횟수를 계산해서 출력한다
        int answer = dfs(A, 0, a.length - 1);
        System.out.println(answer == INF ? -1 : answer);
    }

    static int dfs(long bitmask, int s, int e) {
        // 이미 탐색한 경우
        Tuple t = new Tuple(bitmask, s, e);
        if (cache.containsKey(t)) {
            return cache.get(t);
        }

        // B로 바꾸는 것이 더 이상 불가능한 경우
        if (!equal(bitmask, B, s, e)) {
            cache.put(t, INF);
            return INF;
        }

        // B와 일치하는 경우
        if (bitmask == B) {
            cache.put(t, 0);
            return 0;
        }

        // 구간 [s, e]를 뒤집지 않고 탐색을 진행하는 경우
        int ret = INF;
        ret = Math.min(ret, dfs(bitmask, s + 1, e));
        ret = Math.min(ret, dfs(bitmask, s, e - 1));

        // 구간 [s, e]를 뒤집은 후 탐색을 진행하는 경우
        long flippedBitmask = getFlippedBitmask(bitmask, s, e);
        ret = Math.min(ret, 1 + dfs(flippedBitmask, s + 1, e));
        ret = Math.min(ret, 1 + dfs(flippedBitmask, s, e - 1));

        cache.put(t, ret);
        return ret;
    }

    static boolean equal(long x, long y, int s, int e) {
        return (x >> (e + 1)) == (y >> (e + 1))
                && (x & ((1L << s) - 1)) == (y & ((1L << s) - 1));
    }

    static long getFlippedBitmask(long x, int s, int e) {
        while (s < e) {
            boolean sOn = (x & 1L << s) != 0L;
            boolean eOn = (x & 1L << e) != 0L;
            if (sOn != eOn) {
                // 두 비트가 서로 다를 경우에만 각 비트에 xor 연산을 하면 된다
                x ^= 1L << s;
                x ^= 1L << e;
            }
            s++;
            e--;
        }
        return x;
    }
}
