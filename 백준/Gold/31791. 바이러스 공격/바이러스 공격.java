import java.io.*;
import java.util.*;


public class Main {

    static class Location {
        int row;
        int col;

        Location(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    static class Building {
        Location location;
        int endTime;

        Building(Location location, int endTime) {
            this.location = location;
            this.endTime = endTime;
        }
    }
    static int N, M, TG, TB, X, B;
    static char[][] grid;
    static ArrayDeque<Building> delayQ = new ArrayDeque<>();
    static ArrayDeque<Location> virusQ = new ArrayDeque<>();
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        TG = Integer.parseInt(st.nextToken());
        TB = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        grid = new char[N][];
        for (int r = 0; r < N; r++) {
            grid[r] = br.readLine().toCharArray();
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (grid[r][c] == '*') {
                    virusQ.offerLast(new Location(r, c));
                }
            }
        }

        // 현재 시간을 TG 까지 1 씩 증가시키면서
        for (int curTime = 0; curTime < TG; curTime++) {

            // 현 시점에 바이러스 큐에 존재하는 모든 바이러스에 대해
            int size = virusQ.size();
            while (size-- > 0) {
                Location virus = virusQ.pollFirst();

                for (int d = 0; d < 4; d++) {
                    int nr = virus.row + dr[d];
                    int nc = virus.col + dc[d];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                        continue;
                    }
                    char val = grid[nr][nc];
                    if (val == '*') {
                        continue;
                    }

                    if (val == '.') {
                        // 해당 바이러스에 인접한 구역이 감염되지 않은 빈 곳일 경우 바이러스 큐에 추가한다
                        virusQ.offerLast(new Location(nr, nc));
                        grid[nr][nc] = '*';
                    } else if (val == '#') {
                        // 해당 바이러스에 인접한 구역이 감염되지 않은 빌딩일 경우 빌딩 큐에 추가한다 (위치, 현재 시간 + TB)
                        delayQ.offerLast(new Building(new Location(nr, nc), curTime + TB));
                        grid[nr][nc] = '~';
                    }
                }
            }

            // 빌딩 큐의 최상위 원소의 감염 완료 시간이 현재 시간과 동일할 경우 빌딩 큐에서 제거하고 바이러스 큐에 추가한다
            while (!delayQ.isEmpty() && delayQ.peekFirst().endTime == curTime) {
                Location cur = delayQ.pollFirst().location;
                virusQ.offerLast(cur);
                grid[cur.row][cur.col] = '*';
            }
        }

        // TG 시점까지의 전파가 완료되었을 때 안전한 구역을 모두 정답 문자열에 추가한다
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (grid[r][c] == '*') {
                    continue;
                }
                sb.append(r + 1).append(' ').append(c + 1).append('\n');
            }
        }

        // 안전한 구역이 없다면 -1을 정답 문자열에 추가한다
        if (sb.length() == 0) {
            sb.append(-1);
        }

        // 정답 문자열을 출력한다
        System.out.print(sb);
    }
}
