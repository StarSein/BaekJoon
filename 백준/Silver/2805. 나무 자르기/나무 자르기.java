import java.io.*;
import java.util.*;


public class Main {

    static int N, M;
    static int[] heights;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        heights = new int[N];
        st = new StringTokenizer(br.readLine());
        int maxH = 0;
        for (int i = 0; i < N; i++) {
            int h = Integer.parseInt(st.nextToken());
            heights[i] = h;
            maxH = Math.max(maxH, h);
        }

        // [0 ~ 나무 높이의 최댓값] 중 M 이상의 나무를 가져갈 수 있는 최댓값을 이분 탐색으로 찾는다
        int s = 0;
        int e = maxH;
        while (s <= e) {
            int mid = (s + e) >> 1;
            if (able(mid)) {
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }

        // 정답을 출력한다
        System.out.println(e);
    }

    static boolean able(int cutLine) {
        long sum = 0;
        for (int height : heights) {
            sum += Math.max(0, height - cutLine);
        }
        return sum >= M;
    }
}
