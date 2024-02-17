// 거짓말하는 소의 수가 바뀌는 임계점은 항상 p_i 중 하나다


import java.io.*;
import java.util.*;


public class Main {

    static class Answer {
        boolean isL;
        int p;

        Answer(boolean isL, int p) {
            this.isL = isL;
            this.p = p;
        }
    }
    static int N;
    static Answer[] answers;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        answers = new Answer[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            answers[i] = new Answer("L".equals(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        // 모든 p에 대해 해당 p를 실제 위치라고 가정했을 때 거짓말하는 소의 수를 세고, 최솟값을 갱신한다
        int minNumLiar = N + 1;
        for (Answer a : answers) {
            int pos = a.p;
            int numLiar = 0;
            for (Answer ans : answers) {
                if (ans.isL && pos > ans.p || !ans.isL && pos < ans.p) {
                    numLiar++;
                }
            }
            minNumLiar = Math.min(minNumLiar, numLiar);
        }

        // 최솟값을 출력한다
        System.out.println(minNumLiar);
    }
}
