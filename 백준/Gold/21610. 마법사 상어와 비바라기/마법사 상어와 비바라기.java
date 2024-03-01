import java.io.*;
import java.util.*;


public class Main {

    static class Cloud {
        int r, c;

        Cloud(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int N, M, d, s;
    static int[][] A;
    static int[] dr = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dc = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static boolean[][] isCloud, wasCloud;
    static ArrayList<Cloud> clouds = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        isCloud = new boolean[N][N];
        wasCloud = new boolean[N][N];
        clouds.add(new Cloud(N - 1, 0));
        clouds.add(new Cloud(N - 1, 1));
        clouds.add(new Cloud(N - 2, 0));
        clouds.add(new Cloud(N - 2, 1));
        syncClouds(clouds);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            d = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());

            moveClouds();
            rain();
            removeClouds();
            copyWater();
            createClouds();
        }

        int answer = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                answer += A[r][c];
            }
        }
        System.out.println(answer);
    }

    static void syncClouds(ArrayList<Cloud> clouds) {
        for (int r = 0; r < N; r++) {
            Arrays.fill(isCloud[r], false);
        }
        for (Cloud cloud : clouds) {
            isCloud[cloud.r][cloud.c] = true;
        }
    }

    static void moveClouds() {
        for (Cloud cloud : clouds) {
            cloud.r = (cloud.r + (dr[d] + N) * s) % N;
            cloud.c = (cloud.c + (dc[d] + N) * s) % N;
        }
        syncClouds(clouds);
    }

    static void rain() {
        for (Cloud cloud : clouds) {
            A[cloud.r][cloud.c]++;
        }
    }

    static void removeClouds() {
        for (int r = 0; r < N; r++) {
            Arrays.fill(wasCloud[r], false);
        }
        for (Cloud cloud : clouds) {
            wasCloud[cloud.r][cloud.c] = true;
        }
        clouds.clear();
        syncClouds(clouds);
    }

    static void copyWater() {
        int[][] plusAmount = new int[N][N];

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (wasCloud[r][c]) {
                    for (int i = 2; i < dr.length; i += 2) {
                        int nr = r + dr[i];
                        int nc = c + dc[i];
                        if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                            continue;
                        }
                        if (A[nr][nc] >= 1) {
                            plusAmount[r][c]++;
                        }
                    }
                }
            }
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                A[r][c] += plusAmount[r][c];
            }
        }
    }

    static void createClouds() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (A[r][c] >= 2 && !wasCloud[r][c]) {
                    clouds.add(new Cloud(r, c));
                    A[r][c] -= 2;
                }
            }
        }
        syncClouds(clouds);
    }
}
