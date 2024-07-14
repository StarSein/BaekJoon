import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/*
i가 N 또는 N+1일 때는 i가 반드시 출력된다

10, 1, 5, 2, 3
1, 5, 2, 3, 10 (i=1)
1, 2, 3, 5, 10 (i=2)

1, 2, 3, 1, 2, 3, 1, 2, 3
1, 2, 1, 2, 3, 1, 2, 3, 3 (i=1)
1, 1, 2, 2, 1, 2, 3, 3, 3 (i=2)
1, 1, 2, 1, 2, 2, 3, 3, 3 (i=3)
1, 1, 1, 2, 2, 2, 3, 3, 3 (i=4)

1, 3, 2, 3, 4, 3, 5, 3, 6
1, 2, 3, 3, 3, 4, 3, 5, 6 (i=1)
1, 2, 3, 3, 3, 3, 4, 5, 6 (i=2)

어떤 수든 왼쪽으로 이동은 한 번의 i 루프당 최대 한 번만 일어남을 이용하자
정렬 전과 후를 기준으로 특정 원소의 좌측 방향 이동 거리의 최댓값이 i 루프 횟수가 된다
 */


public class Main {

    static int N;
    static int[] A, sortedA;
    static ArrayDeque<Integer>[] poses = new ArrayDeque[1_000_001];

    public static void main(String[] args) throws IOException {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }

        // 배열 A를 정렬했을 때 각 수가 위치하는 인덱스를 poses 배열에 저장한다
        for (int i = 0; i <= 1_000_000; i++) {
            poses[i] = new ArrayDeque<>();
        }
        sortedA = Arrays.copyOf(A, N);
        Arrays.sort(sortedA);
        for (int i = 0; i < N; i++) {
            int e = sortedA[i];
            poses[e].offerLast(i);
        }

        // 배열 A를 순회하며 원소의 좌측 이동 거리의 최댓값을 갱신한다
        int maxDist = 0;
        for (int i = 0; i < N; i++) {
            int e = A[i];
            int curDist = i - poses[e].pollFirst();
            maxDist = Math.max(maxDist, curDist);
        }

        // (좌측 이동 거리의 최댓값 + 1)을 출력한다
        System.out.println(maxDist + 1);
    }
}
