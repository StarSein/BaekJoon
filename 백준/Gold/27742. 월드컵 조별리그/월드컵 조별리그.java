import java.io.*;
import java.util.*;


public class Main {

    static class Team implements Comparable<Team> {
        int point, rank;
        long goalDiff, goal;

        Team(int rank) {
            this.rank = rank;
        }

        void clear() {
            this.point = 0;
            this.goalDiff = 0L;
            this.goal = 0L;
        }

        @Override
        public int compareTo(Team t) {
            if (point != t.point) return point < t.point ? 1 : -1;
            if (goalDiff != t.goalDiff) return goalDiff < t.goalDiff ? 1 : -1;
            if (goal != t.goal) return goal < t.goal ? 1 : -1;
            return rank > t.rank ? 1 : -1;
        }

        @Override
        public String toString() {
            return "Team{" +
                    "point=" + point +
                    ", rank=" + rank +
                    ", goalDiff=" + goalDiff +
                    ", goal=" + goal +
                    '}';
        }
    }
    static int row, col;
    static long K;
    static long[][] board = new long[4][4];
    static Team[] teams = new Team[4];

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken()) - 1;
        K = Long.parseLong(st.nextToken());
        for (int r = 0; r < 4; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < 4; c++) {
                board[r][c] = Long.parseLong(st.nextToken());
            }
        }

        // 누락된 득점의 열 좌표를 찾는다
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                if (board[r][c] == -1) {
                    col = c;
                }
            }
        }

        // 0 ~ K 범위에서 이분 탐색으로 팀 T가 진출 자격 획득 가능한 득점의 최솟값을 찾는다
        // 최솟값이 존재하지 않을 경우 "-1"을 출력한다

        for (int i = 0; i < teams.length; i++) {
            teams[i] = new Team(i);
        }
        long s = 0L;
        long e = K;
        long ans = -1L;
        while (s <= e) {
            long mid = (s + e) >> 1L;
            if (able(mid)) {
                ans = mid;
                e = mid - 1L;
            } else {
                s = mid + 1L;
            }
        }
        System.out.println(ans);
    }

    static boolean able(long score) {
        for (int i = 0; i < teams.length; i++) {
            teams[i].clear();
            teams[i].rank = i;
        }

        // 득점표의 누락된 부분을 score 로 채운다
        board[row][col] = score;

        // 득점표를 바탕으로 각 팀의 정보를 저장한다
        for (int i = 1; i < teams.length; i++) {
            Team teamI = teams[i];
            for (int j = 0; j < i; j++) {
                Team teamJ = teams[j];
                long goalI = board[i][j];
                long goalJ = board[j][i];

                teamI.goalDiff += goalI - goalJ;
                teamI.goal += goalI;
                teamJ.goalDiff += goalJ - goalI;
                teamJ.goal += goalJ;

                if (goalI > goalJ) {
                    teamI.point += 3;
                } else if (goalI < goalJ) {
                    teamJ.point += 3;
                } else {
                    teamI.point += 1;
                    teamJ.point += 1;
                }
            }
        }

        // 팀의 정보를 바탕으로 각 팀의 순위를 매긴다
        Arrays.sort(teams);

        // T팀이 2위 이내에 해당하는지 여부를 반환한다
        for (int i = 0; i < 2; i++) {
            if (teams[i].rank == row) {
                return true;
            }
        }
        return false;
    }
}
