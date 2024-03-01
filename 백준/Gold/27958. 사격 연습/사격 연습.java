/*
총알 하나씩 몇 번 행에 쏠지 결정하는 즉시 변화를 반영했다가 취소하는 방식으로 하는 것보다는,
모든 총알의 발사 위치를 결정한 다음에 일괄적으로 변화를 반영하는 방법이 더 간단하겠다.
 */


import java.io.*;
import java.util.*;


public class Main {

    static int N, K;
    static int[][] board, initialHealths, currentHealths;
    static int[] damages, fireRows;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        board = new int[N][N];
        for (int r = 0; r < N; r++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        damages = new int[K];
        for (int i = 0; i < K; i++) {
            damages[i] = Integer.parseInt(st.nextToken());
        }

        initialHealths = new int[N][];
        currentHealths = new int[N][];
        fireRows = new int[K];
        System.out.println(permWithRep(0));
    }

    static int permWithRep(int turn) {
        if (turn == K) {
            return simulate();
        }
        int ret = -1;
        for (int r = 0; r < N; r++) {
            fireRows[turn] = r;
            ret = Math.max(ret, permWithRep(turn + 1));
        }
        return ret;
    }

    static int simulate() {
        for (int r = 0; r < N; r++) {
            initialHealths[r] = Arrays.copyOf(board[r], N);
            currentHealths[r] = Arrays.copyOf(board[r], N);
        }

        int score = 0;
        for (int i = 0; i < K; i++) {
            int r = fireRows[i];
            int damage = damages[i];

            for (int c = 0; c < N; c++) {
                if (currentHealths[r][c] >= 10) {
                    score += currentHealths[r][c];
                    currentHealths[r][c] = 0;
                    break;
                }
                if (currentHealths[r][c] > 0) {
                    if (currentHealths[r][c] <= damage) {
                        int initialHealth = initialHealths[r][c];
                        score += initialHealth;
                        int newHealth = initialHealth / 4;
                        for (int d = 0; d < 4; d++) {
                            int nr = r + dr[d];
                            int nc = c + dc[d];
                            if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                                continue;
                            }
                            if (currentHealths[nr][nc] > 0) {
                                continue;
                            }
                            initialHealths[nr][nc] = newHealth;
                            currentHealths[nr][nc] = newHealth;
                        }
                    }
                    currentHealths[r][c] -= damage;
                    break;
                }
            }
        }

        return score;
    }
}
