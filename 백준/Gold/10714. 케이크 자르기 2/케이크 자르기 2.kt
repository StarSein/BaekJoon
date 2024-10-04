import kotlin.math.max

/**
 * dp[i][j] = A_i 부터 A_j 까지의 케이크가 이미 선택됐을 때,
 * JOI 군이 남은 선택들에서 추가로 가져갈 수 있는 A값의 합의 최댓값
 * 하나의 순서쌍 (i, j)에 대해 오직 한 사람만이 자신의 차례일 수 있다
 *
 * JOI 군의 차례일 때에는, dp[i][j] = max(A[i-1] + dp[i-1][j], A[j+1] + dp[i][j+1])
 * IOI 군의 차례일 때에는, dp[i][j] = if (A[i-1] > A[j+1]) dp[i-1][j] else dp[i][j+1]
 * (A는 모두 서로 다르다)
 *
 * 시간 복잡도 O(N^2)
 * 공간 복잡도 O(N^2)
 */

fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    val A = Array(N + 1) {
        if (it == 0) 0L
        else readLine().toLong()
    }

    val dp = Array(N + 1) { LongArray(N + 1) { -1L } }

    fun recur(i: Int, j: Int, turnJOI: Boolean, turnCount: Int): Long {
        if (turnCount == N) {
            return 0L
        }
        if (dp[i][j] != -1L) {
            return dp[i][j]
        }
        
        val ni = if (i == 1) N else i - 1
        val nj = if (j == N) 1 else j + 1
        
        if (turnJOI) {
            dp[i][j] = max(A[ni] + recur(ni, j, false, turnCount + 1), A[nj] + recur(i, nj, false, turnCount + 1))
        } else {
            dp[i][j] = if (A[ni] > A[nj]) recur(ni, j, true, turnCount + 1) else recur(i, nj, true, turnCount + 1)
        }
        
        return dp[i][j]
    }

    val answer = (1..N).maxOf { start ->
        A[start] + recur(i = start, j = start, turnJOI = false, turnCount = 1)
    }
    println(answer)
}