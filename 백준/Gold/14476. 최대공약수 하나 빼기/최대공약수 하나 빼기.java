import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static int[] nums, prefixGCD, suffixGCD;

    public static void main(String[] args) throws Exception {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[N + 2];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        // 최대공약수에 관한 왼쪽 누적합 배열과 오른쪽 누적합 배열 만들기
        prefixGCD = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            prefixGCD[i] = getGCD(prefixGCD[i - 1], nums[i]);
        }
        suffixGCD = new int[N + 2];
        for (int i = N; i >= 1; i--) {
            suffixGCD[i] = getGCD(suffixGCD[i + 1], nums[i]);
        }

        // 모든 인덱스를 순회하면서 해당 정수를 뺀다고 가정하고 왼쪽 정수들의 최대공약수와 오른쪽 정수들의 최대공약수의 최대공약수를 정답에 갱신한다
        int maxGCD = -1;
        int removedNum = -1;
        for (int i = 1; i <= N; i++) {
            int gcd = getGCD(prefixGCD[i - 1], suffixGCD[i + 1]);
            int K = nums[i];
            if (K % gcd == 0) {
                continue;
            }
            if (gcd > maxGCD) {
                maxGCD = gcd;
                removedNum = K;
            }
        }

        // 정답을 출력한다
        System.out.println(maxGCD == -1 ? -1 : maxGCD + " " + removedNum);
    }

    static int getGCD(int a, int b) {
        while (b > 0) {
            int temp = a;
            a = b;
            b = temp % b;
        }
        return a;
    }
}
