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

        // 키 배열을 오름차순으로 정렬한다
        Arrays.sort(H);

        // 엘사의 눈사람은 O(N^2) 완전 탐색을 통해 구한다
        int answer = Integer.MAX_VALUE;
        for (int b1 = 1; b1 < N; b1++) {
            for (int h1 = 0; h1 < b1; h1++) {
                int height1 = H[b1] + H[h1];

                // 투 포인터를 이용해 엘사의 눈사람과 키 차이가 최소화되는 안나의 눈사람을 구하고 정답을 갱신한다
                int b2 = b1 - 1;
                int h2 = h1 + 1;
                while (h2 < b2) {
                    int height2 = H[b2] + H[h2];
                    answer = Math.min(answer, Math.abs(height1 - height2));
                    if (height2 >= height1) {
                        b2--;
                    } else {
                        h2++;
                    }
                }
            }
        }

        // 정답을 출력한다
        System.out.println(answer);
    }
}
