import java.io.*;
import java.util.*;


public class Main {

    static char[] S;
    static int[] prefixSum, suffixSum;
    static boolean[] prefixValid, suffixValid;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine().toCharArray();

        // prefixSum[i]: 구간 [1, i]에서 (여는 괄호의 개수 - 닫는 괄호의 개수)의 누적합
        // suffixSum[i]: 구간 [i, |S|]에서 (여는 괄호의 개수 - 닫는 괄호의 개수)의 누적합
        // prefixValid[i]: t <= i 일 때, 구간 [1, t]에서 닫는 괄호가 여는 괄호보다 많은 t가 존재할 경우 false
        // suffixValid[i]: t <= i 일 때, 구간 [t, |S|]에서 닫는 괄호가 여는 괄호보다 적은 t가 존재할 경우 false
        prefixSum = new int[S.length + 1];
        suffixSum = new int[S.length + 2];
        prefixValid = new boolean[S.length + 1];
        suffixValid = new boolean[S.length + 2];
        prefixValid[0] = true;
        suffixValid[S.length + 1] = true;
        for (int i = 0; i < S.length; i++) {
            prefixSum[i + 1] = prefixSum[i] + (S[i] == '(' ? 1 : -1);
            prefixValid[i + 1] = prefixValid[i] && (prefixSum[i + 1] >= 0);
        }
        for (int i = S.length - 1; i >= 0; i--) {
            suffixSum[i + 1] = suffixSum[i + 2] + (S[i] == '(' ? 1 : -1);
            suffixValid[i + 1] = suffixValid[i + 2] && (suffixSum[i + 1] <= 0);
        }
        // 특정 인덱스 i에서 prefixSum[i - 1] + suffixSum[i + 1] == 0 이면서
        // prefixValid[i - 1]과 suffixValid[i + 1] 모두 true 일 경우
        // 해당 인덱스의 문자를 지우면 올바른 문자열이다
        int answer = 0;
        for (int i = 1; i <= S.length; i++) {
            if (prefixSum[i - 1] == -suffixSum[i + 1] && prefixValid[i - 1] && suffixValid[i + 1]) {
                answer++;
            }
        }

        // 경우의 수를 출력한다
        System.out.println(answer);
    }
}
