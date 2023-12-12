import java.io.*;
import java.util.*;


public class Main {

    static class Checker {
        int x;
        int y;

        Checker(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int getDistanceFrom(int x, int y) {
            return Math.abs(this.x - x) + Math.abs(this.y - y);
        }
    }
    static int N;
    static Checker[] checkers;
    static HashSet<Integer> xLocs = new HashSet<>();
    static HashSet<Integer> yLocs = new HashSet<>();
    static int[] answers;

    public static void main(String[] args) throws Exception {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        checkers = new Checker[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            checkers[i] = new Checker(x, y);
            xLocs.add(x);
            yLocs.add(y);
        }

        // 체커가 위치하는 x좌표와 y좌표의 조합으로 만들 수 있는 모든 순서쌍 (x, y)에 대해 완전 탐색
        answers = new int[N];
        Arrays.fill(answers, Integer.MAX_VALUE);
        for (int x : xLocs) {
            for (int y : yLocs) {
                // 각각의 체커별 거리 배열 저장
                int[] distances = new int[N];
                for (int i = 0; i < N; i++) {
                    Checker checker = checkers[i];
                    distances[i] = checker.getDistanceFrom(x, y);
                }

                // 거리 배열 정렬
                Arrays.sort(distances);

                // k번째 수 갱신
                int moveCount = 0;
                for (int k = 0; k < N; k++) {
                    moveCount += distances[k];
                    answers[k] = Math.min(answers[k], moveCount);
                }
            }
        }

        // k번째 수의 배열 출력
        StringBuilder sb = new StringBuilder();
        for (int answer : answers) {
            sb.append(answer).append(' ');
        }
        System.out.println(sb);
    }
}
