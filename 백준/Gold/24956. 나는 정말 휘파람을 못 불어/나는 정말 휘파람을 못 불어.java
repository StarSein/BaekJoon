import java.io.*;
import java.util.*;


public class Main {

    static final int MOD = 1_000_000_007;
    static int N;
    static char[] S;
    static int[] prefixW, suffixE, twoPow;

    public static void main(String[] args) throws Exception {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        S = br.readLine().toCharArray();

        // 문자열 S에서 'W'의 개수에 관한 왼쪽 누적합 배열 만들기
        prefixW = new int[S.length];
        prefixW[0] = (S[0] == 'W' ? 1 : 0);
        for (int i = 1; i < S.length; i++) {
            prefixW[i] = prefixW[i - 1] + (S[i] == 'W' ? 1 : 0);
        }

        // 문자열 S에서 'E'의 개수에 관한 오른쪽 누적합 배열 만들기
        suffixE = new int[S.length];
        suffixE[S.length - 1] = (S[S.length - 1] == 'E' ? 1 : 0);
        for (int i = S.length - 2; i >= 0; i--) {
            suffixE[i] = suffixE[i + 1] + (S[i] == 'E' ? 1 : 0);
        }

        // 2^1 ~ 2^200000까지 값을 저장해놓은 배열 만들기
        twoPow = new int[200001];
        twoPow[0] = 1;
        for (int i = 1; i < twoPow.length; i++) {
            twoPow[i] = (twoPow[i - 1] * 2) % MOD;
        }

        // 문자열 S에서 모든 'H'의 인덱스를 순회하면서
        int answer = 0;
        for (int i = 0; i < S.length; i++) {
            if (S[i] == 'H') {
                // 해당 'H'가 포함된 유사 휘파람 문자열인 부분 수열의 개수 합산
                int prefixCount = prefixW[i];
                long suffixCount = (twoPow[suffixE[i]] - suffixE[i] - 1 + MOD) % MOD;
                answer = (int) ((answer + prefixCount * suffixCount) % MOD);
            }
        }

        // 합산한 값 출력
        System.out.println(answer);
    }
}
