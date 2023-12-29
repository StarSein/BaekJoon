// 5! = 120
// 6! = 720
// 7! = 5040
// 8! = 40320
// 9! = 약 36만
// 9 * 9! = 약 300만
// 9876543210 이 최댓값이므로 단순 반복문에서 조건 검증을 통해 수를 만들어가는 것은 시간 제한을 초과한다
// 순열을 이용해 100만 가지의 반복 숫자 없는 수를 만들어 놓자
// 정렬을 위해 100만 * log(100만) 만큼의 연산을 하기엔 다소 빠듯하므로,
// 순열을 오름차순으로 만들자

import java.io.*;
import java.util.*;


public class Main {

    static int n, numCount;
    static long[] nums = new long[1_000_001];
    static boolean[] selected = new boolean[10];

    public static void main(String[] args) throws Exception {
        // 반복 숫자 없는 수 만들어 놓기
        for (int numDigit = 1; numDigit <= 10; numDigit++) {
            perm(0, 0, numDigit);
        }

        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while ((n = Integer.parseInt(br.readLine())) != 0) {
            sb.append(nums[n]).append('\n');
        }

        // 정답 출력
        System.out.println(sb);
    }

    static void perm(int depth, int num, int numDigit) {
        if (numCount == 1_000_000) {
            return;
        }
        if (depth == numDigit) {
            nums[++numCount] = num;
            return;
        }

        int start = (depth == 0 ? 1 : 0);
        for (int i = start; i < 10; i++) {
            if (selected[i]) {
                continue;
            }
            selected[i] = true;
            perm(depth + 1, 10 * num + i, numDigit);
            selected[i] = false;
        }
    }
}
