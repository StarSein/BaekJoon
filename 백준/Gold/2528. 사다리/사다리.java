import java.io.*;
import java.util.*;

/*
시간이 1초씩 흐를 때마다 모든 층의 막대기 위치를 갱신하는 것은 시간 초과로 이어질 것이다
이를 해결하기 위한 방법이 두 가지 있는데
1. 현재 층에서 이동이 가능해지기 위해 흘러야 하는 시간 t를 계산해서 그만큼 한 번에 경과시키는 것
    + 모든 층 막대기 갱신
2. 1초씩 경과
    + 현재 층과 바로 위 층만 갱신 (그 위의 층들은 lazy하게 갱신)
방법 1의 경우 t를 계산하는 것이 복잡하다
더 간단한 방법 2를 채택하자
 */

public class Main {

    static class Stick {
        int s, e, d;

        Stick(int s, int e, int d) {
            this.s = s;
            this.e = e;
            this.d = d;
        }

        void move() {
            if (e - s == L) {
                return;
            }
            s += dx[d];
            e += dx[d];
            if (s == 0 || e == L) {
                d = 1 - d;
            }
        }

        void move(int time) {
            if (e - s == L) {
                return;
            }
            int cycle = 2 * (L - (e - s));
            time %= cycle;
            while (time-- > 0) {
                move();
            }
        }

        boolean intersect(Stick other) {
            return s <= other.s && other.s <= e ||
                    s <= other.e && other.e <= e ||
                    other.s <= s && s <= other.e;
        }
    }
    static int N, L;
    static int[] dx = {1, -1};
    static Stick[] sticks;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        sticks = new Stick[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            if (d == 0) {
                // ->
                sticks[i] = new Stick(0, l, 0);
            } else {
                // <-
                sticks[i] = new Stick(L - l, L, 1);
            }
        }

        if (N <= 1) {
            System.out.println(0);
            return;
        }

        // 1초씩 경과시키고 층을 올라가면 새로운 위 층은 lazy하게 현재까지 경과한 시간만큼 이동시킨다
        int time = 0;
        int floor = 1;
        while (true) {
            Stick cur = sticks[floor];
            Stick next = sticks[floor + 1];
            if (cur.intersect(next)) {
                // 바로 위층으로 이동할 수 있는 경우 현재 층을 이동시키고 그 위의 층의 위치를 갱신한다
                if (++floor == N) {
                    // N층에 도착한 경우 경과 시간을 출력하고 프로그램을 종료한다
                    System.out.println(time);
                    return;
                }
                sticks[floor + 1].move(time);
            } else {
                // 바로 위층으로 이동할 수 없는 경우 현재 층과 바로 위층을 1만큼 이동시킨다
                cur.move();
                next.move();
                time++;
            }
        }
    }
}
