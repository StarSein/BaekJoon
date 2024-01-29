import java.io.*;
import java.util.*;


public class Main {

    static final int MOD = 900_528;
    static HashMap<Character, Integer> charIndices = new HashMap<>(); // key: 문자, value: 문자 집합에서 각 문자별 순서
    static char[] pwd;
    static int[] pow;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] charSet = br.readLine().toCharArray();
        for (int i = 0; i < charSet.length; i++) {
            charIndices.put(charSet[i], i);
        }
        pwd = br.readLine().toCharArray();

        // 문자집합의 원소 개수를 n이라고 할 때 n의 거듭제곱을 암호의 길이만큼 저장해 놓는다
        int numChar = charIndices.size();
        pow = new int[pwd.length];
        pow[0] = 1;
        for (int i = 1; i < pow.length; i++) {
            pow[i] = (pow[i - 1] * numChar) % MOD;
        }

        // 실제 암호보다 짧은 문자열을 시도하는 경우의 수를 합산해 놓는다
        int answer = 1;
        for (int size = 1; size < pwd.length; size++) {
            answer = (answer + pow[size]) % MOD;
        }

        // 실제 암호와 같은 길이의 문자열을 시도하는 경우의 수를 구한다
        for (int i = 0; i < pwd.length; i++) {
            char c = pwd[i];
            answer = (answer + charIndices.get(c) * pow[pwd.length - 1 - i]) % MOD;
        }

        // 정답을 출력한다
        System.out.println(answer);
    }
}
