import java.io.*;
import java.util.*;

/*
모든 조각의 가로 길이와 세로 길이는 카테시안 곱과 같다
즉, 어떤 한 가로 길이는 모든 세로 길이와 연관지어질 수 있고, 어떤 한 세로 길이 역시 모든 가로 길이와 연관지어질 수 있다
따라서 모든 가로 길이 x에 대해 (x * y <= K) 인 y의 최댓값 인덱스를 오름차순 정렬된 세로 길이의 배열에서 찾자
 */

public class Main {

    static int W, H, N, M;
    static long K;
    static int[] widths, heights;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다. 이때 단순 커팅 지점의 x 및 y 좌표가 아니라, 너비 및 높이를 저장한다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        K = Long.parseLong(st.nextToken());

        N = Integer.parseInt(br.readLine());
        heights = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        int prevY = 0;
        for (int i = 0; i < N; i++) {
            int curY = Integer.parseInt(st.nextToken());
            heights[i] = curY - prevY;
            prevY = curY;
        }
        heights[N] = H - prevY;

        M = Integer.parseInt(br.readLine());
        widths = new int[M + 1];
        st = new StringTokenizer(br.readLine());
        int prevX = 0;
        for (int i = 0; i < M; i++) {
            int curX = Integer.parseInt(st.nextToken());
            widths[i] = curX - prevX;
            prevX = curX;
        }
        widths[M] = W - prevX;

        // 높이를 오름차순 정렬한다
        Arrays.sort(heights);

        // 피자 조각의 개수를 센다
        long answer = 0L;
        for (int width : widths) {
            long limit = K / width;
            int s = 0;
            int e = heights.length - 1;
            while (s <= e) {
                int mid = (s + e) / 2;
                if (heights[mid] <= limit) {
                    s = mid + 1;
                } else {
                    e = mid - 1;
                }
            }
            answer += (e + 1);
        }

        // 피자 조각의 개수를 출력한다
        System.out.println(answer);
    }
}
