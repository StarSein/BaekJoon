import java.io.*;
import java.util.*;
import java.util.stream.IntStream;


public class Main {

    static char[][] magicStar = new char[5][];
    static int[] seq = new int[12];
    static int[] emptyIndices;
    static boolean[] used = new boolean[13];
    static int[][] line = {
            {0, 5},
            {1, 2}, {0, 1}, {1, 5}, {1, 4},
            {0, 2}, {4, 5},
            {0, 3}, {2, 3}, {3, 4}, {3, 5},
            {2, 4}
    };
    static int[] count = new int[6];
    static int[] sum = new int[6];

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++) {
            magicStar[i] = br.readLine().toCharArray();
        }
        // 매직스타에서 수가 채워진 곳은 정보를 저장해 둔다
        int idx = 0;
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 9; c++) {
                char cur = magicStar[r][c];
                if (cur == '.') {
                    continue;
                }
                if (cur != 'x') {
                    int num = cur - 'A' + 1;
                    seq[idx] = num;
                    used[num] = true;
                    for (int i : line[idx]) {
                        count[i]++;
                        sum[i] += num;
                    }
                }
                idx++;
            }
        }

        // 수열의 채워지지 않은 부분을 백트래킹으로 채운다
        emptyIndices = IntStream.range(0, seq.length).filter(e -> seq[e] == 0).toArray();
        perm(0);

        // 채워진 수열을 매직스타에 배치하여 출력한다
        StringBuilder sb = new StringBuilder();
        idx = 0;
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 9; c++) {
                if (magicStar[r][c] == '.') {
                    sb.append('.');
                } else {
                    sb.append((char) ('A' + seq[idx++] - 1));
                }
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }

    static boolean perm(int depth) {
        // 매직스타에 들어갈 수를 모두 찾았을 경우 백트래킹을 종료한다
        if (depth == emptyIndices.length) {
            return true;
        }

        // 현재의 빈 곳에 들어갈 수를 찾는다
        int idx = emptyIndices[depth];
        for (int num = 1; num <= 12; num++) {
            // 이미 사용한 수는 건너뛴다
            if (used[num]) {
                continue;
            }

            // 유효한 수인지 검증한다
            boolean flag = false;
            for (int i : line[idx]) {
                int nextSum = sum[i] + num;
                if (nextSum > 26 || count[i] == 3 && nextSum < 26) {
                    flag = true;
                    break;
                }
            }

            // 유효하지 않은 수는 건너뛴다
            if (flag) {
                continue;
            }

            // 유효한 수 num을 넣고 탐색을 진행한다
            seq[idx] = num;
            used[num] = true;
            for (int i : line[idx]) {
                count[i]++;
                sum[i] += num;
            }

            // 현재의 빈 곳에 num을 넣고 탐색하여 매직스타가 완성되었다면 재귀 호출을 종료한다
            if (perm(depth + 1)) {
                return true;
            }

            // 현재의 빈 곳에 num을 넣어서는 매직스타를 완성할 수 없다면 다른 수를 넣어본다
            used[num] = false;
            for (int i : line[idx]) {
                count[i]--;
                sum[i] -= num;
            }
        }

        // 현재의 빈 곳에 어떠한 수를 넣어도 매직스타를 완성할 수 없다면
        // 현재 함수를 호출한 부모가 다른 수를 넣어보도록 한다
        return false;
    }
}
