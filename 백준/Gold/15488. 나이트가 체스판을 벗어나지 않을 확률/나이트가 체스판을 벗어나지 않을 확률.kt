/**
 * Top-down DP를 이용하여 풀이하자
 * 시간 복잡도: O(N^2 * K)
 * 공간 복잡도: O(N^2 * K)
 */

fun main() = with(System.`in`.bufferedReader()) {
    val (N, x, y, K) = readLine().split(" ").map { it.toInt() }

    val dr = arrayOf(1, 2, 2, 1, -1, -2, -2, -1)
    val dc = arrayOf(2, 1, -1, -2, -2, -1, 1, 2)

    val dp = Array(N + 1) { Array(N + 1) { Array(K + 1) { -1.0 } } }
    // dp[r][c][k]: 현재 나이트의 위치가 (r, c)일 때 k번 이동 후 체스판 위에 있을 확률

    fun dfs(r: Int, c: Int, k: Int): Double {
        val inBoard = (r in 1..N) && (c in 1..N)
        if (inBoard.not()) {
            return 0.0
        }
        if (dp[r][c][k] != -1.0) {
            return dp[r][c][k]
        }
        if (k == 0) {
            dp[r][c][k] = 1.0
            return 1.0
        }
        dp[r][c][k] = (0..7).sumOf {
            val nr = r + dr[it]
            val nc = c + dc[it]
            0.125 * dfs(nr, nc, k - 1)
        }
        return dp[r][c][k]
    }

    println(dfs(x, y, K))
}