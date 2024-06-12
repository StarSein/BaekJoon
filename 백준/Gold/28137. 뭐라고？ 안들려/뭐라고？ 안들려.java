import java.io.*;
import java.util.*;

/*
통화가 끊기지 않는 서로 다른 두 점 (x1, y1)과 (x2, y2)에 대해
(y2 - y1) = K(x2 - x1) 가 성립한다
이는 y2 - Kx2 = y1 - Kx1 와 같다
모든 좌표에 대해 (y - Kx) 값을 계산한 후 그 값의 개수를 세면
O(N)에 이 문제를 해결할 수 있다
 */

public class Main {

    static int N, K;
    static HashMap<Long, Integer> countMap = new HashMap<>();

    public static void main(String[] args) throws Exception {
        // 입력을 받으면서 (y - Kx) 값마다 그 개수를 세어 해쉬맵에 저장한다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            long key = y - (long) K * x;
            countMap.put(key, countMap.getOrDefault(key, 0) + 1);
        }

        // 해쉬맵의 모든 (key, value)에 대해 {value}_P_{2} 값을 정답에 합산한다
        long answer = 0L;
        for (int count : countMap.values()) {
            answer += (long) count * (count - 1);
        }

        // 정답을 출력한다
        System.out.println(answer);
    }
}
