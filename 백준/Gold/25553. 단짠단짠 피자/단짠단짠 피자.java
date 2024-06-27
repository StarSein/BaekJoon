import java.io.*;
import java.util.*;

/*

1 2 3 4 5 6 7

1. N, K의 홀짝 여부에 따라 1,2번 쿼리에 따른 처리가 달라진다
    1) N이 짝수, K가 짝수인 경우
        - 1,2번 쿼리가 주어져도 결과는 변함 없으므로
          시작 조각의 번호가 항상 일정하다
          만족도 a += K/2 * x
    2) N이 짝수, K가 홀수인 경우
        - 시작 번호가 홀수인 것과 짝수인 것 각각의 최대 만족도 a, b를 구해 놓는다
        - 1번 쿼리가 주어질 때
            a += (K/2 + 1) * x
            b += K/2 * x
          2번 쿼리가 주어질 때
            a += K/2 * x
            b += (K/2 + 1) * x
        - 3번 쿼리가 주어지면 max(a, b)를 출력한다
    3) N이 홀수, K가 홀수인 경우
        - 홀수 번째 조각이 1개 더 많은 것과 짝수 번째 조각이 1개 더 많은 것 각각의 최대 만족도 a, b를 구해 놓는다
        - 나머지는 2)와 같다
    4) N이 홀수, K가 짝수인 경우
        - 홀수 번째 조각이 2개, 1개 더 많은 것, 짝수 번째 조각이 1개 더 많은 것이 존재하여 각각의 최대 만족도 a, b, c를 구해 놓는다
        - 나머지는 2)와 같다
2. 위 네 가지 경우를 포괄하는 일반화된 풀이는 아래와 같다
    정답을 순서쌍 Pair(시작 조각 번호, 만족도)로 관리한다
    answer = new Pair[4]
    answer[0]: 짝수 번째 조각이 1개 더 많은 것
    answer[1]: 홀짝 번째 조각의 개수가 동일한 것
    answer[2]: 홀수 번째 조각이 1개 더 많은 것
    answer[3]: 홀수 번째 조각이 2개 더 많은 것
 */

public class Main {

    static class Pair {
        int startIndex;
        long intervalSum;

        Pair(int startIndex, long intervalSum) {
            this.startIndex = startIndex;
            this.intervalSum = intervalSum;
        }
    }
    static final long NINF = Long.MIN_VALUE;
    static int N, Q, K;
    static int[] A;
    static Pair[] answers = new Pair[4];

    public static void main(String[] args) throws Exception {
        // 입력을 받는다. 이때 원순열의 처리를 위해 A는 2배의 길이로 한다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new int[2 * N];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = N + 1; i < A.length; i++) {
            A[i] = A[i - N];
        }

        // A에서 길이가 K인 모든 구간에 대해 만족도를 계산하여
        // 홀짝 번호 조각의 개수마다 만족도의 최댓값을 구해 놓는다
        for (int i = 0; i < 4; i++) {
            answers[i] = new Pair(-1, NINF);
        }
        long intervalSum = 0L;
        for (int i = 1; i <= K; i++) {
            intervalSum += A[i];
        }
        int answerIndex = (K % 2 == 1 ? 2 : 1);
        answers[answerIndex] = new Pair(1, intervalSum);
        for (int i = 1; i < N; i++) {
            intervalSum -= A[i];
            intervalSum += A[i + K];

            answerIndex -= (i % 2 == 1 ? 1 : -1);
            answerIndex += (validIndex(i + K) % 2 == 1 ? 1 : -1);

            Pair pair = answers[answerIndex];
            if (intervalSum > pair.intervalSum) {
                pair.startIndex = i + 1;
                pair.intervalSum = intervalSum;
            }
        }

        /*
        half = K / 2                        (홀 개수, 짝 개수)
        answer[0]: 짝수 번째 조각이 1개 더 많은 것 (half, half + 1)
        answer[1]: 홀짝 번째 조각의 개수가 동일한 것 (half, half)
        answer[2]: 홀수 번째 조각이 1개 더 많은 것 (half + 1, half)
        answer[3]: 홀수 번째 조각이 2개 더 많은 것 (half + 1, half - 1)
         */
        StringBuilder sb = new StringBuilder();
        long half = K / 2;
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            if (t == 1) {
                int x = Integer.parseInt(st.nextToken());
                // 1번 쿼리가 주어지는 경우
                for (int i = 0; i < 4; i++) {
                    if (answers[i].intervalSum == NINF) {
                        continue;
                    }
                    if (i <= 1) {
                        answers[i].intervalSum += half * x;
                    } else {
                        answers[i].intervalSum += (half + 1) * x;
                    }
                }
            } else if (t == 2) {
                int x = Integer.parseInt(st.nextToken());
                // 2번 쿼리가 주어지는 경우
                for (int i = 0; i < 4; i++) {
                    if (answers[i].intervalSum == NINF) {
                        continue;
                    }
                    if (i == 1 || i == 2) {
                        answers[i].intervalSum += half * x;
                    } else if (i == 0) {
                        answers[i].intervalSum += (half + 1) * x;
                    } else {
                        answers[i].intervalSum += (half - 1) * x;
                    }
                }
            } else {
                // 3번 쿼리가 주어지는 경우
                Pair best = new Pair(-1, NINF);
                for (Pair answer : answers) {
                    if (answer.intervalSum > best.intervalSum ||
                        answer.intervalSum == best.intervalSum && answer.startIndex < best.startIndex) {
                        best = answer;
                    }
                }
                sb.append(best.startIndex + " " + best.intervalSum + "\n");
            }
        }
        
        // 정답 문자열을 출력한다
        System.out.print(sb);
    }

    static int validIndex(int index) {
        return index > N ? index - N : index;
    }
}
