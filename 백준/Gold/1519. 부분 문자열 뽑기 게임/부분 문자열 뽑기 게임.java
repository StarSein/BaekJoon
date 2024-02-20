import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static boolean[] dp, checked;

    public static void main(String[] args) {
        // 입력을 받는다
        N = new Scanner(System.in).nextInt();

        // 처음 주어진 수에서 만들 수 있는 진 부분 문자열들 중에서 작은 것부터 골라보면서
        dp = new boolean[N + 1];
        checked = new boolean[N + 1];
        ArrayList<Integer> properSubstrings = getProperSubstringList(N);
        properSubstrings.sort(Comparator.naturalOrder());
        for (int properSubstring : properSubstrings) {
            // 무조건 이길 수 있는 것을 발견한다면 그것을 출력하고 프로그램을 종료한다
            if (!win(N - properSubstring)) {
                System.out.println(properSubstring);
                return;
            }
        }

        // 발견하지 못했다면 -1을 출력한다
        System.out.println(-1);
    }

    static ArrayList<Integer> getProperSubstringList(int n) {
        ArrayList<Integer> ret = new ArrayList<>();

        int numDigit = getNumDigit(n);
        // 진 부분 문자열을 뒤에서 i번째 숫자부터 j번째 숫자까지 사용하여 만든다고 할 때
        // 0과 n이 아닌 것만 리스트에 포함시킨다
        for (int i = 0; i < numDigit; i++) {
            for (int j = i; j < numDigit; j++) {
                int temp = n;
                for (int k = 0; k < i; k++) {
                    temp /= 10;
                }
                int substr = 0;
                int pow = 1;
                for (int k = i; k <= j; k++) {
                    substr += (temp % 10) * pow;
                    temp /= 10;
                    pow *= 10;
                }
                if (substr == 0 || substr == n) {
                    continue;
                }
                ret.add(substr);
            }
        }

        return ret;
    }

    static int getNumDigit(int n) {
        int count = 0;
        while (n > 0) {
            count++;
            n /= 10;
        }
        return count;
    }

    static boolean win(int n) {
        if (n < 10) {
            return false;
        }
        if (checked[n]) {
            return dp[n];
        }
        for (int properSubstring : getProperSubstringList(n)) {
            if (!win(n - properSubstring)) {
                return dp[n] = checked[n] = true;
            }
        }
        checked[n] = true;
        return dp[n] = false;
    }
}
