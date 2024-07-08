import java.io.*;
import java.util.*;

/*
4와 44는 4를 갖고 있고 다른 숫자들은 가지고 있지 않다는 점에서 같다
다시 말해 4를 20과 비교하는 것이나 44를 20과 비교하는 것이나 같은 결과로 이어진다
따라서 다른 수와의 비교에서 같은 결과를 내는 것끼리 묶으면 비교 대상의 수를 줄일 수 있다
모든 수를 구간 [0, 2^10) 범위 안의 정수로 변환하고 이 정수를 비트마스크로 사용하자
각 정수의 개수를 세어 비교하여 친구 쌍의 개수를 구할 때 사용하면 된다
그러면 O(N^2)이 아니라 O(2^T) (T = 20)에 문제를 해결할 수 있다
 */

public class Main {

    static int N;
    static int[] counts = new int[1 << 10];

    public static void main(String[] args) throws IOException {
        // 입력을 받고 각 비트마스크별 개수를 센다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            int bitmask = s.chars().reduce(0, (b, c) -> b | 1 << (c - '0'));
            counts[bitmask]++;
        }

        // 동일한 비트마스크를 가진 친구 쌍의 개수를 정답에 합산한다
        long answer = 0L;
        for (int i = 0; i < counts.length; i++) {
            answer += (long) counts[i] * (counts[i] - 1L) / 2L;
        }

        // 모든 비트마스크 쌍들에 대해 친구 여부를 확인하고, 해당 쌍에 대응되는 친구 쌍의 개수를 정답에 합산한다
        for (int i = 1; i < counts.length; i++) {
            for (int j = 0; j < i; j++) {
                boolean isFriend = false;
                int a = i;
                int b = j;
                while (a > 0) {
                    if (((a & 1) & (b & 1)) == 1) {
                        isFriend = true;
                        break;
                    }
                    a >>= 1;
                    b >>= 1;
                }

                if (isFriend) {
                    answer += (long) counts[i] * counts[j];
                }
            }
        }

        // 정답을 출력한다
        System.out.println(answer);
    }
}
