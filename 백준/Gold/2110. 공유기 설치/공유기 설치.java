import java.io.*;
import java.util.*;


public class Main {

    static int N, C;
    static int[] x;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        x = new int[N];
        for (int i = 0; i < N; i++) {
            x[i] = Integer.parseInt(br.readLine());
        }

        // x좌표를 오름차순으로 정렬한다
        Arrays.sort(x);

        // 가능한 두 공유기 사이 거리의 하한선을 구간 [1, 10^9] 사이에서 이분 탐색하며 최댓값을 찾는다
        int s = 1;
        int e = 1_000_000_000;
        while (s <= e) {
            int mid = (s + e) >> 1;
            if (able(mid)) {
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }

        // 최댓값을 출력한다
        System.out.println(e);
    }

    static boolean able(int lowerLimit) {
        // 우선 가장 왼쪽의 집에 공유기를 설치하는 것이 최적이다
        int routerCount = 1;
        int prevRouterX = x[0];

        // 그 다음부터는 가장 최근에 지은 공유기와의 거리가 하한선 이상인 집에 설치하는데
        // 그런 집들 중 가장 왼쪽에 있는 집에 공유기를 설치하는 것이 최적이다
        for (int i = 1; i < N; i++) {
            if (x[i] - prevRouterX >= lowerLimit) {
                routerCount++;
                prevRouterX = x[i];
            }
        }

        return routerCount >= C;
    }
}
