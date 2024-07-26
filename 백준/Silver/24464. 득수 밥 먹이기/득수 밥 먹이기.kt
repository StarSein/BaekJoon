import kotlin.math.abs

/*

dp[i][j]: i일까지 고려했을 때, i일에 j번 식당을 가는 경우의 수 (j = 0 일 경우 그날 굶은 것)

 */

fun main(args: Array<String>) {
    // 입력을 받는다
    val N = readln().toInt()

    // dp 테이블 및 초기값 세팅
    val mod = 1_000_000_007
    val dp = Array(N + 1) { IntArray(5) { 0 } }
    for (i in 0..4) {
        dp[1][i] = 1
    }

    // dp 점화식 전이
    for (i in 2..N) {
        // 어제 굶고 오늘 식당을 가는 경우
        for (nj in 1..4) {
            dp[i][nj] += dp[i - 1][0]
            dp[i][nj] %= mod
        }

        // 어제 식당을 가는 경우
        for (j in 1..4) {
            // 오늘 식당을 가는 경우
            for (nj in 1..4) {
                if (abs(j - nj) < 2) {
                    continue
                }
                dp[i][nj] += dp[i - 1][j]
                dp[i][nj] %= mod
            }

            // 오늘 굶는 경우
            dp[i][0] += dp[i - 1][j]
            dp[i][0] %= mod
        }
    }

    // sum(dp[N])을 출력한다
    val answer = dp[N].fold(0) { acc, it -> (acc + it) % mod }
    println(answer)
}