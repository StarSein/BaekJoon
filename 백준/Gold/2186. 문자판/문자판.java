import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K;
    static char[][] board;
    static char[] word;
    static int[][][] dp;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new char[N][];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }
        word = br.readLine().toCharArray();

        dp = new int[N][M][word.length];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (board[r][c] == word[0]) {
                    dp[r][c][0] = 1;
                }
            }
        }
        for (int i = 1; i < word.length; i++) {
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    if (board[r][c] == word[i]) {
                        for (int d = 0; d < 4; d++) {
                            for (int k = 1; k <= K; k++) {
                                int nr = r + k * dr[d];
                                int nc = c + k * dc[d];
                                if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                                    break;
                                }
                                dp[r][c][i] += dp[nr][nc][i - 1];
                            }
                        }
                    }
                }
            }
        }

        int tgt = word.length - 1;
        int answer = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                answer += dp[r][c][tgt];
            }
        }
        System.out.println(answer);
    }
}
