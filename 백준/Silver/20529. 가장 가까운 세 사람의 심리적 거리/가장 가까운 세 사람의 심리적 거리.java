import java.io.*;
import java.util.*;


public class Main {

    static int T, N;
    static HashMap<String, Integer> counts = new HashMap<>();

    public static void main(String[] args) throws Exception {
        // 테스트케이스마다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < T; tc++) {
            counts.clear();
            // 입력을 받으면서 MBTI 유형별 개수를 센다
            N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                String MBTI = st.nextToken();
                counts.put(MBTI, 1 + counts.getOrDefault(MBTI, 0));
            }


            // 1개 이상의 MBTI 유형들에 대해 아래 세 가지 경우의 심리적 거리를 계산하여 최솟값을 갱신한다
            // 1) 세 유형 모두 같은 경우
            boolean flag = false;
            for (int count : counts.values()) {
                if (count >= 3) {
                    sb.append("0\n");
                    flag = true;
                    break;
                }
            }
            if (flag) {
                continue;
            }

            final int SIZE = counts.size();
            String[] keys = new String[SIZE];
            counts.keySet().toArray(keys);
            int minCount = Integer.MAX_VALUE;
            // 2) 세 유형 모두 다른 경우
            for (int i = 2; i < SIZE; i++) {
                for (int j = 1; j < i; j++) {
                    int dist1 = getDist(keys[i], keys[j]);
                    for (int k = 0; k < j; k++) {
                        int dist2 = getDist(keys[j], keys[k]);
                        int dist3 = getDist(keys[i], keys[k]);
                        minCount = Math.min(minCount, dist1 + dist2 + dist3);
                    }
                }
            }
            // 3) 두 유형만 서로 같은 경우
            for (int i = 0; i < SIZE; i++) {
                if (counts.get(keys[i]) < 2) {
                    continue;
                }
                for (int j = 0; j < SIZE; j++) {
                    if (i == j) {
                        continue;
                    }
                    minCount = Math.min(minCount, 2 * getDist(keys[i], keys[j]));
                }
            }

            // 2) 또는 3) 에 대해 최솟값을 정답 문자열에 추가한다
            sb.append(minCount).append('\n');
        }

        // 정답 문자열을 출력한다
        System.out.print(sb);
    }

    static int getDist(String s1, String s2) {
        int dist = 0;
        for (int i = 0; i < 4; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                dist++;
            }
        }
        return dist;
    }
}
