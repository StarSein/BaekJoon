import java.io.*;
import java.util.*;


public class Main {

    static int N, K;
    static int[] liquors;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        liquors = new int[N];
        for (int i = 0; i < N; i++) {
            liquors[i] = Integer.parseInt(br.readLine());
        }

        // [1 ~ 2^31-1] 중 친구들에게 모두 나눠줄 수 있는 정량의 최댓값을 찾는다
        int s = 1;
        int e = Integer.MAX_VALUE - 1;
        int answer = 0;
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (able(mid)) {
                answer = mid;
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }

        // 최댓값을 출력한다
        System.out.println(answer);
    }

    static boolean able(int quota) {
        long capacity = 0L;
        for (int liquor : liquors) {
            capacity += liquor / quota;
        }
        return capacity >= K;
    }
}
