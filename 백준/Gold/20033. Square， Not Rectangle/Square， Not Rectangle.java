import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static int[] H;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        H = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            H[i] = Integer.parseInt(st.nextToken());
        }

        // 구간 [1, N]에서 정사각형의 변의 길이가 될 수 있는 최댓값을 이분 탐색으로 찾는다
        int s = 1;
        int e = N;
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

    static boolean able(int length) {
        int count = 0;
        for (int height : H) {
            if (height >= length) {
                if (++count == length) {
                    return true;
                }
            } else {
                count = 0;
            }
        }
        return false;
    }
}
