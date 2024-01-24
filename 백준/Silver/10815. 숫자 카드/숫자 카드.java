import java.io.*;
import java.util.*;


public class Main {

    static int N, M;
    static int[] cards;

    public static void main(String[] args) throws Exception {
        // 숫자 카드 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cards = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        // 숫자 카드를 오름차순으로 정렬한다
        Arrays.sort(cards);

        // 쿼리에 대해 이분 탐색으로 존재 여부를 정답 문자열에 추가한다
        StringBuilder sb = new StringBuilder();
        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(st.nextToken());
            sb.append(isExist(num) ? 1 : 0).append(' ');
        }

        // 정답 문자열을 출력한다
        System.out.println(sb);
    }

    static boolean isExist(int x) {
        int s = 0;
        int e = N - 1;
        while (s <= e) {
            int mid = (s + e) >> 1;
            if (cards[mid] == x) {
                return true;
            } else if (cards[mid] > x) {
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        return false;
    }
}
