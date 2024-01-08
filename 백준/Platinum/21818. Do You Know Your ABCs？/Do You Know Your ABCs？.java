//  A    B    C   A+B    A+C    B+C   A+B+C
// 001  010  100  011    101    110    111
//  1    2    4    3      5      6      7
// 1. 7개의 식 중 N개를 순서를 고려하여 선택한다
// 2. {x}를 순서대로 N개의 식에 할당한다
// 4. 정합성 검증 (7개 중 4개 이상의 식에 값이 할당되면 적어도 A, B, C, A+B+C 는 채울 수 있다)
//      1) 할당되지 않은 식은 0으로 초기화한다
//      2) N개의 식 중 2개를 선택하여
//          'xor 연산 결과 == 덧셈 연산 결과' 일 경우 덧셈 연산 계산
//          'xor 연산 결과 1의 개수 == 1' 일 경우 뺄셈 연산 계산
//          그 이외의 경우 pass
//      3) 채워진 A, B, C 를 이용해 A+B, A+C, B+C 를 마저 채운다
//      4) 모두 채워졌으므로 정합성 검증을 다시 한다


import java.io.*;
import java.util.*;


public class Main {

    static class Triple implements Comparable<Triple> {
        int a, b, c;

        Triple(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public int compareTo(Triple e) {
            if (a != e.a) return a - e.a;
            if (b != e.b) return b - e.b;
            return c - e.c;
        }

        boolean equals(Triple e) {
            return a == e.a && b == e.b && c == e.c;
        }
    }

    static int T, N;
    static long[] nums;
    static int[] order = {1, 2, 4, 3, 5, 6, 7};
    static int[] selectedBits;
    static boolean[] selected;
    static ArrayList<Triple> triples = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            nums = new long[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }

            // 입력받은 수 오름차순 정렬
            Arrays.sort(nums);

            // 7개 식 중 N개를 뽑는 모든 조합에 대해 완전 탐색해 보며 정합한 케이스의 개수 세기
            selectedBits = new int[N];
            selected = new boolean[8];
            triples.clear();
            perm(0);

            // triple 배열 오름차순 정렬
            triples.sort(Comparator.naturalOrder());

            // 중복 제거하고 정합한 Triple 의 개수 세기
            int count = 0;
            Triple prevTriple = new Triple(0, 0, 0);
            for (Triple curTriple : triples) {
                if (curTriple.equals(prevTriple)) {
                    continue;
                }
                count++;
                prevTriple = curTriple;
            }

            sb.append(count).append('\n');
        }
        System.out.print(sb);
    }

    static void perm(int depth) {
        if (depth == N) {
            // 7개의 식 중 할당되지 않은 식은 0으로 초기화한다
            long[] x = new long[8];
            for (int i = 0; i < N; i++) {
                int bit = selectedBits[i];
                x[bit] = nums[i];
            }

            // N개의 식 중 2개를 선택하여
            for (int i = 1; i < N; i++) {
                int bitI = selectedBits[i];
                for (int j = 0; j < i; j++) {
                    int bitJ = selectedBits[j];
                    int xor = bitI ^ bitJ;
                    // 'xor 연산 결과 == 덧셈 연산 결과' 일 경우 덧셈 연산 계산
                    if (xor == bitI + bitJ) {
                        if (x[xor] == 0) {
                            x[xor] = x[bitI] + x[bitJ];
                        }
                    } else {
                        int count = 0;
                        int curBit = xor;
                        while (curBit > 0) {
                            if ((curBit & 1) == 1) {
                                count++;
                            }
                            curBit >>= 1;
                        }

                        // 'xor 연산 결과 1의 개수 == 1' 일 경우 뺄셈 연산 계산
                        if (count == 1) {
                            if (x[xor] == 0) {
                                x[xor] = x[bitI] - x[bitJ];
                            }
                        }
                        // 그 이외의 경우 pass
                    }
                }
            }

            // 비어 있는 식에 값 할당
            if (x[3] == 0) x[3] = x[1] + x[2];
            if (x[5] == 0) x[5] = x[1] + x[4];
            if (x[6] == 0) x[6] = x[2] + x[4];

            // 모든 식들에 대해 등식을 만족하는지 확인
            boolean valid = (
                    x[1] <= x[2] &&
                    x[2] <= x[4] &&
                    x[3] <= x[5] &&
                    x[5] <= x[6] &&
                    x[3] == x[1] + x[2] &&
                    x[5] == x[1] + x[4] &&
                    x[6] == x[2] + x[4] &&
                    x[7] == x[3] + x[4]);

            if (valid) {
                triples.add(new Triple((int) x[1], (int) x[2], (int) x[4]));
            }
            return;
        }

        for (int i = 0; i < 7; i++) {
            if (selected[i]) {
                continue;
            }
            selected[i] = true;
            selectedBits[depth] = order[i];
            perm(depth + 1);
            selected[i] = false;
        }
    }
}
