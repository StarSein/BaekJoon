import java.io.*;
import java.util.*;

/*
(1, 1) -> (N, N) 으로 가는 경우
가능한 경로의 수 = comb(2N-2, N-1)
N = 18 로 최대인 경우 comb(36, 17) 로 약 86억 가지 경로가 존재한다

양 끝점에서 시작해서 중간(격자의 우상향 대각선 위의 좌표들)에서 만나면 어떨까?
어느 한쪽에서 중간까지 가는 경로의 수 = 2^(N-1)
N = 18 로 최대인 경우 2^17 로 약 10만 가지 경로가 존재한다
그리고 비교 대상이 되는 문자열의 길이가 최대 18 이므로 시간상 충분하다
 */

public class Main {

    static int N;
    static char[][] grid;
    static char[] route;
    static HashSet<String>[] routeSets;
    static HashSet<String> palindromeSet;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        grid = new char[N + 1][N + 1];
        for (int r = 1; r <= N; r++) {
            char[] line = br.readLine().toCharArray();
            for (int c = 1; c <= N; c++) {
                grid[r][c] = line[c - 1];
            }
        }

        // (1, 1) 에서 중간 지점으로 가면서 만들 수 있는 문자열을
        // 중간 지점의 각 좌표에 대응하는 집합에 저장한다
        routeSets = new HashSet[N + 1];
        for (int i = 1; i <= N; i++) {
            routeSets[i] = new HashSet<>();
        }
        route = new char[N];
        dfs1(0, 1, 1);

        // (N, N) 에서 중간 지점으로 가면서 만들 수 있는 문자열이
        // 중간 지점의 각 좌표에 대응하는 집합에 존재할 경우 팰린드롬 집합에 추가한다
        palindromeSet = new HashSet<>();
        dfs2(0, N, N);

        // 팰린드롬 집합의 크기를 출력한다
        System.out.println(palindromeSet.size());
    }

    static void dfs1(int i, int r, int c) {
        route[i] = grid[r][c];
        if (i == N - 1) {
            routeSets[r].add(String.valueOf(route));
            return;
        }
        dfs1(i + 1, r + 1, c);
        dfs1(i + 1, r, c + 1);
    }

    static void dfs2(int i, int r, int c) {
        route[i] = grid[r][c];
        if (i == N - 1) {
            String routeStr = String.valueOf(route);
            if (routeSets[r].contains(routeStr)) {
                palindromeSet.add(routeStr);
            }
            return;
        }
        dfs2(i + 1, r - 1, c);
        dfs2(i + 1, r, c - 1);
    }
}
