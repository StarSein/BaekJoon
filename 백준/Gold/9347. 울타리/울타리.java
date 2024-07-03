import java.io.*;
import java.util.*;

/*
어느 땅이든 항상 울타리를 최적으로 부숴서 도달할 수 있을 때
울타리를 부순 횟수가 가장 큰 땅들에 존재하는 꽃의 개수의 합을 구하는 문제다

1. 2차원 배열의 외곽을 시작점으로 삼고 모든 땅에 대해 그곳에 도달하기 위해 부숴야 하는 울타리 개수의 최솟값을 저장한다
    -> 0-1 너비 우선 탐색으로 O(RC)에 해결하자
2. 울타리 개수의 최솟값이 가장 큰 땅들 중 꽃이 심어져 있는 땅들의 개수를 출력한다
 */

public class Main {

    static class Node {
        int r, c, cost;

        Node(int r, int c, int cost) {
            this.r = r;
            this.c = c;
            this.cost = cost;
        }
    }
    static int T, R, C;
    static int[][] grid, minCost;
    static ArrayDeque<Node> dq = new ArrayDeque<>();
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            // 각 테스트 케이스에 대해
            // 입력을 받는다
            StringTokenizer st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            grid = new int[R + 1][C + 1];
            for (int r = 1; r <= R; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 1; c <= C; c++) {
                    grid[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            // minCost 배열을 초기화한다
            minCost = new int[R + 1][C + 1];
            for (int r = 1; r <= R; r++) {
                for (int c = 1; c <= C; c++) {
                    minCost[r][c] = -1;
                }
            }

            // 외곽 지점들을 큐에 넣는다
            for (int r = 1; r <= R; r++) {
                addStartPoint(r, 1);
                addStartPoint(r, C);
            }
            for (int c = 2; c < C; c++) {
                addStartPoint(1, c);
                addStartPoint(R, c);
            }

            // 0-1 너비 우선 탐색을 통해 각 지점에 도달하기 위한 최소 비용을 저장한다
            while (!dq.isEmpty()) {
                Node cur = dq.pollFirst();

                if (minCost[cur.r][cur.c] != -1) {
                    continue;
                }
                minCost[cur.r][cur.c] = (grid[cur.r][cur.c] == 0 ? cur.cost : -2);

                for (int d = 0; d < 4; d++) {
                    int nr = cur.r + dr[d];
                    int nc = cur.c + dc[d];
                    if (nr < 1 || nr > R || nc < 1 || nc > C) {
                        continue;
                    }
                    if (minCost[nr][nc] != -1) {
                        continue;
                    }
                    if (grid[nr][nc] == 1) {
                        dq.offerLast(new Node(nr, nc, cur.cost + 1));
                    } else {
                        dq.offerFirst(new Node(nr, nc, cur.cost));
                    }
                }
            }

            // 최소 비용이 가장 큰 지점들 중 꽃이 심어진 지점의 개수를 센 뒤 정답 문자열에 추가한다
            int maxCost = 0;
            int flowerCount = 0;
            for (int r = 1; r <= R; r++) {
                for (int c = 1; c <= C; c++) {
                    if (minCost[r][c] > maxCost) {
                        maxCost = minCost[r][c];
                        flowerCount = 1;
                    } else if (minCost[r][c] == maxCost){
                        flowerCount++;
                    }
                }
            }
            sb.append(maxCost + " " + flowerCount + "\n");
        }

        // 정답 문자열을 출력한다
        System.out.print(sb);
    }

    static void addStartPoint(int r, int c) {
        if (grid[r][c] == 1) {
            dq.offerLast(new Node(r, c, 1));
        } else {
            dq.offerFirst(new Node(r, c, 0));
        }
    }
}
