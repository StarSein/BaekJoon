import java.io.*;
import java.util.*;


public class Main {

    static class Pair {
        int player;
        int height;

        Pair(int player, int height) {
            this.player = player;
            this.height = height;
        }
    }
    static int N, M;
    static Pair[] pairs;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        pairs = new Pair[2 * M];
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int h1 = Integer.parseInt(st.nextToken());
            int h2 = Integer.parseInt(st.nextToken());

            pairs[2 * i - 2] = new Pair(i, h1);
            pairs[2 * i - 1] = new Pair(i, h2);
        }

        // (참가자 번호, 높이) 순서쌍의 배열을 높이의 오름차순으로 정렬한다
        Arrays.sort(pairs, Comparator.comparingInt(e -> e.height));

        // N % (2 * M)의 값을 mod 라고 하자
        // mod 가 0 인 경우 마지막 원소가 정답이다
        // 그렇지 않은 경우 (mod - 1)번째 원소가 정답이다
        int mod = N % (2 * M);
        int idx = (mod == 0 ? pairs.length - 1 : mod - 1);
        System.out.println(pairs[idx].player);
    }
}
