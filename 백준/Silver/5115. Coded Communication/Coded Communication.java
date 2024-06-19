import java.io.*;
import java.util.*;


public class Main {

    static int K;
    static int n, b;
    static char[][] codewords;
    static char[] message;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        K = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= K; tc++) {
            // 각 데이터 셋마다
            // 입력을 받는다
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            codewords = new char[n][];
            for (int i = 0; i < n; i++) {
                codewords[i] = br.readLine().toCharArray();
            }
            message = br.readLine().toCharArray();

            // 메시지와 유효한 후보군 사이 해밍 거리의 최솟값을 정답 문자열에 추가한다
            int minDist = b;
            for (char[] codeword : codewords) {
                int curDist = 0;
                for (int i = 0; i < b; i++) {
                    if (codeword[i] != message[i]) {
                        curDist++;
                    }
                }
                minDist = Math.min(minDist, curDist);
            }
            sb.append("Data Set " + tc + ":\n" + minDist + "\n\n");
        }

        // 정답 문자열을 출력한다
        System.out.print(sb);
    }
}
