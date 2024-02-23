import java.io.*;
import java.util.*;


public class Main {

    static class Number implements Comparable<Number> {
        int val, row, col;

        Number(int val, int row, int col) {
            this.val = val;
            this.row = row;
            this.col = col;
        }

        @Override
        public int compareTo(Number e) {
            return e.val - val;
        }
    }
    static int n, m;
    static int[][] grid;
    static Number[] numbers;
    static int[] roots, depths;
    static long[] xors;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        grid = new int[n][m];
        numbers = new Number[n * m];
        int numberCount = 0;
        for (int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < m; c++) {
                grid[r][c] = Integer.parseInt(st.nextToken());
                numbers[numberCount++] = new Number(grid[r][c], r, c);
            }
        }

        // 분리 집합(XOR 그룹)의 정보를 저장할 배열을 만든다
        roots = new int[n * m];
        for (int i = 0; i < roots.length; i++) {
            roots[i] = i;
        }
        depths = new int[n * m];
        xors = new long[n * m];
        for (int i = 0; i < xors.length; i++) {
            xors[i] = numbers[i].val;
        }

        // 격자판에 적힌 수들을 내림차순으로 정렬한다
        Arrays.sort(numbers);

        // 격자판에서 큰 수부터 xor 그룹 만들기에 사용하면서
        // xor 그룹 값의 합의 최댓값을 갱신한다
        long maxXOR = -1L;
        long curXOR = 0L;
        for (Number e : numbers) {
            int p = e.row * m + e.col;

            // 우선 현 지점의 수를 새로운 하나의 xor 그룹으로 간주하고 처리한다
            depths[p] = 1;
            curXOR += xors[p];

            // 새로운 그룹이 기존의 xor 그룹에 병합되는지 체크하고 병합시킨다
            for (int d = 0; d < 4; d++) {
                int nr = e.row + dr[d];
                int nc = e.col + dc[d];
                if (nr < 0 || nr >= n || nc < 0 || nc >= m) {
                    continue;
                }

                int q = nr * m + nc;
                if (depths[q] == 0) {
                    continue;
                }

                int pRoot = findRoot(p);
                int qRoot = findRoot(q);

                if (pRoot == qRoot) {
                    continue;
                }

                if (depths[pRoot] < depths[qRoot]) {
                    int temp = pRoot;
                    pRoot = qRoot;
                    qRoot = temp;
                }

                depths[pRoot] = Math.max(depths[pRoot], depths[qRoot] + 1);

                curXOR -= xors[pRoot];
                curXOR -= xors[qRoot];
                xors[pRoot] ^= xors[qRoot];
                curXOR += xors[pRoot];

                roots[qRoot] = pRoot;
            }

            maxXOR = Math.max(maxXOR, curXOR);
        }

        // 최댓값을 출력한다
        System.out.println(maxXOR);
    }

    static int findRoot(int x) {
        if (roots[x] == x) {
            return x;
        }
        return roots[x] = findRoot(roots[x]);
    }
}
