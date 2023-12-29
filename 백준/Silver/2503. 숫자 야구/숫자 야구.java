import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static char[][] queries;
    static int[] strikeCounts;
    static int[] ballCounts;
    static boolean[] selected = new boolean[10];
    static char[] target = new char[3];

    public static void main(String[] args) throws Exception {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        queries = new char[N][];
        strikeCounts = new int[N];
        ballCounts = new int[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            queries[i] = st.nextToken().toCharArray();
            strikeCounts[i] = Integer.parseInt(st.nextToken());
            ballCounts[i] = Integer.parseInt(st.nextToken());
        }

        // 9P3 만큼 완전탐색하면서 시뮬레이션 했을 때 질문/대답과 일치하는지 여부 체크
        int answer = perm(0);

        // 정답 출력
        System.out.println(answer);
    }

    static int perm(int depth) {
        if (depth == 3) {
            boolean correct = true;
            for (int i = 0; i < N; i++) {
                char[] query = queries[i];
                int strikeCount = 0;
                int ballCount = 0;
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++) {
                        if (query[j] == target[k]) {
                            if (j == k) {
                                strikeCount++;
                            } else {
                                ballCount++;
                            }
                        }
                    }
                }
                if (strikeCount != strikeCounts[i] || ballCount != ballCounts[i]) {
                    correct = false;
                    break;
                }
            }
            return correct ? 1 : 0;
        }

        int ret = 0;
        for (int i = 1; i <= 9; i++) {
            if (selected[i]) {
                continue;
            }

            target[depth] = (char) (i + '0');
            selected[i] = true;
            ret += perm(depth + 1);
            selected[i] = false;
        }

        return ret;
    }

}
