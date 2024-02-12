/*
1. 1이 2, 3, 5, 7, 11, 13, 17개 있는 수를 약수로 가진 수의 개수를 각각 센다
2. 포함-배제의 원리에 따라 중복 없이 개수를 합산한다
 */


import java.io.*;
import java.util.*;


public class Main {

    static long N;
    static long[] nums;

    public static void main(String[] args) {
        // 입력을 받는다
        N = new Scanner(System.in).nextLong();

        // 범위 이내에 소수 개 이상의 숫자 1로만 이루어진 수를 저장해 놓는다
        int[] eArr = new int[] {2, 3, 5, 7, 11, 13, 17};
        nums = new long[eArr.length];
        for (int i = 0; i < eArr.length; i++) {
            int e = eArr[i];
            long num = 1L;
            for (int j = 1; j < e; j++) {
                num *= 10L;
                num += 1L;
            }
            nums[i] = num;
        }

        // 정답을 출력한다
        System.out.println(subset(0, 0, 1L));
    }

    static long subset(int idx, int count, long num) {
        if (idx == nums.length) {
            if (count == 0) {
                return 0L;
            }
            return (count % 2 == 1 ? 1L : -1L) * (N / num);
        }
        return subset(idx + 1, count, num)
                + (num <= N / nums[idx]
                ? subset(idx + 1, count + 1, num * nums[idx])
                : 0L);
    }
}
