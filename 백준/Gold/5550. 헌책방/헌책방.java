import java.io.*;
import java.util.*;


public class Main {
    
    static int N, K;
    static ArrayList<Integer>[] books = new ArrayList[11];
    static int[] dp;

    public static void main(String[] args) throws Exception {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= 10; i++) {
            books[i] = new ArrayList<>();
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            books[g].add(c);
        }

        // 장르별 책들을 가격의 내림차순으로 정렬
        for (int i = 1; i <= 10; i++) {
            books[i].sort(Comparator.reverseOrder());
        }


        // 장르별 책들을 누적합 배열로 만들기
        for (ArrayList<Integer> list : books) {
            int size = list.size();
            for (int i = 1; i < size; i++) {
                list.set(i, list.get(i) + list.get(i - 1));
            }
            list.add(0, 0);
        }

        // dp[i]: 책을 i권 팔 때 총 매입 가격의 최댓값
        dp = new int[K + 1];
        for (ArrayList<Integer> list : books) {
            int size = list.size();
            for (int i = K; i > 0; i--) {
                int maxJ = Math.min(size - 1, i);
                for (int j = 0; j <= maxJ; j++) {
                    dp[i] = Math.max(dp[i], dp[i - j] + list.get(j) + (j - 1) * j);
                }
            }
        }

        // dp[K] 출력
        System.out.println(dp[K]);
    }
}
