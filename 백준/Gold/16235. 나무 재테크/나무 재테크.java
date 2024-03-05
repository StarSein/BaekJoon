import java.io.*;
import java.util.*;


public class Main {

    static int N, M, K;
    static int[][] grid, A;
    static ArrayList<Integer>[][] trees, deadTrees;
    static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        trees = new ArrayList[N][N];
        deadTrees = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                trees[i][j] = new ArrayList<>();
                deadTrees[i][j] = new ArrayList<>();
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            trees[x - 1][y - 1].add(z);
        }

        grid = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(grid[i], 5);
        }

        for (int i = 0; i < K; i++) {
            spring();
            summer();
            autumn();
            winter();
        }

        int answer = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                answer += trees[r][c].size();
            }
        }
        System.out.println(answer);
    }

    static void spring() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                ArrayList<Integer> treeList = trees[r][c];
                int amount = grid[r][c];
                treeList.sort(Comparator.naturalOrder());
                int size = treeList.size();
                int lackIdx = -1;
                for (int i = 0; i < size; i++) {
                    int age = treeList.get(i);
                    if (age <= amount) {
                        treeList.set(i, age + 1);
                        amount -= age;
                    } else {
                        lackIdx = i;
                        break;
                    }
                }
                grid[r][c] = amount;
                if (lackIdx == -1) {
                    continue;
                }
                ArrayList<Integer> deadTreeList = deadTrees[r][c];
                for (int i = size - 1; i >= lackIdx; i--) {
                    deadTreeList.add(treeList.remove(i));
                }
            }
        }
    }

    static void summer() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                ArrayList<Integer> deadTreeList = deadTrees[r][c];
                int amountPlus = 0;
                for (int age : deadTreeList) {
                    amountPlus += age / 2;
                }
                grid[r][c] += amountPlus;
                deadTreeList.clear();
            }
        }
    }

    static void autumn() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                ArrayList<Integer> treeList = trees[r][c];
                int breedCount = 0;
                for (int age : treeList) {
                    if (age % 5 == 0) {
                        breedCount++;
                    }
                }
                for (int d = 0; d < dr.length; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                        continue;
                    }
                    ArrayList<Integer> nextTreeList = trees[nr][nc];
                    for (int i = 0; i < breedCount; i++) {
                        nextTreeList.add(1);
                    }
                }
            }
        }
    }

    static void winter() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                grid[r][c] += A[r][c];
            }
        }
    }
}
