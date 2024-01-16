// 1.
// (X/2) 이상의 용량은 무엇과 합쳐도 X의 용량이 되므로, 남은 것들 중 가장 낮은 용량과 합친다
// 2.
// 나머지 용량은 자신과 합해져 X의 용량이 되는 최소의 용량과 합친다
// 각 용량의 최적의 상대를 찾는 문제와 같다
// S, E 를 양 끝점에서 시작하여 움직여 오면서
// (S + E) >= (X / 2)이면 합치는 것이 최적이다
// S의 왼쪽 용량은 이미 다른 용량과 합쳐졌거나, E와 합쳤을 때 (X / 2) 미만이기 때문이다
// S의 오른쪽 용량은 E보다 낮은 용량과 합쳐져 (X / 2) 이상일 확률이 S보다 높기 때문이다
// 3.
// 합쳐지지 못한, 나머지 용량을 서로 합쳐도 (X / 2) 미만이다
// 다만 합친 용량과 다른 용량을 합치면 무조건 X의 용량이 되므로, 용량 3개씩 짝지어 X의 용량을 만들 수 있다


import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static long X;
    static long[] C;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Long.parseLong(st.nextToken());
        C = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            C[i] = Long.parseLong(st.nextToken());
        }

        // 용량을 오름차순으로 정렬한다
        Arrays.sort(C);

        // 두 포인터 s, e를 양 끝점에 두고
        int answer = 0;
        int s = 0;
        int e = N - 1;

        // X와 같은 용량의 개수만큼 정답을 증가시키고, e를 감소시키고, 용량의 남은 개수를 감소시킨다
        while (e >= 0 && C[e] == X) {
            answer++;
            e--;
            N--;
        }

        while (s < e && 2 * C[e] >= X) {
            // e의 용량이 (X/2) 이상이면 s와 정답을 1만큼 증가시키고, e를 1만큼 감소시키고, 남은 용량 수를 2만큼 감소시킨다
            answer++;
            s++;
            e--;
            N -= 2;
        }

        while (s < e) {
            // e와 합쳐져 X를 만들 수 있을 때까지 s를 1만큼 증가시킨다
            if (2 * C[s] >= X - 2 * C[e]) {
                // 그런 s가 존재할 경우 s와 e를 움직이고, 정답을 1만큼 증가시키고, 남은 용량 수를 2만큼 감소시킨다
                answer++;
                e--;
                N -= 2;
            }
            s++;
        }

        // (남은 용량 / 3)만큼 정답을 증가시킨다
        answer += N / 3;

        // 정답을 출력한다
        System.out.println(answer);
    }
}
