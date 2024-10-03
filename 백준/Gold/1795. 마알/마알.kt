import kotlin.math.ceil
import kotlin.math.min

/**
 * 체스판 위의 모든 점들을 하나씩 집결 지점으로 지정하고
 * 해당 지점에서 너비 우선 탐색을 통해 이동 횟수를 구한다
 * 구한 이동 횟수의 최솟값을 정답으로 출력한다
 * 어떤 점에서도 집결할 수 없을 경우 -1을 출력한다
 */

fun main() = with(System.`in`.bufferedReader()) {
    // 입력을 받는다
    val (N, M) = readLine().split(" ").map { it.toInt() }
    val board = Array(N) { readLine().toCharArray() }

    // 마알의 개수를 센다
    val malCount = board.sumOf { line ->
        line.count { spot ->
            spot in '1'..'9'
        }
    }

    /**
     * 시작점에서 모든 마알까지 도달하기 위한 이동 횟수의 총합을 반환한다
     * 도달하지 못한 마알이 있을 경우 -1을 반환한다
     */
    fun bfs(start: Pair<Int, Int>): Int {
        var totalMoveCount = 0
        var malVisitCount = 0
        val dr = listOf(-2, -1, 1, 2, 2, 1, -1, -2)
        val dc = listOf(1, 2, 2, 1, -1, -2, -2, -1)

        val visited = Array(N) { Array(M) { false } }
        val dq = ArrayDeque(elements = listOf(start))

        visited[start.first][start.second] = true

        var moveCount = 0
        while (dq.isNotEmpty()) {
            val size = dq.size
            repeat(size) {
                val (r, c) = dq.removeFirst()

                if (board[r][c] != '.') {
                    val k = board[r][c] - '0'
                    totalMoveCount += ceil(moveCount.toDouble() / k).toInt()
                    malVisitCount++
                }

                repeat(8) {
                    val nr = r + dr[it]
                    val nc = c + dc[it]
                    if (nr in 0..<N && nc in 0..<M && !visited[nr][nc]) {
                        dq.addLast(nr to nc)
                        visited[nr][nc] = true
                    }
                }
            }
            moveCount++
        }

        return if (malVisitCount == malCount) totalMoveCount
        else -1
    }

    // 모든 지점에 대해 너비 우선 탐색을 하며 최솟값을 갱신한다
    var answer = Int.MAX_VALUE
    repeat(N) { r ->
        repeat(M) { c ->
            val result = bfs(start = r to c)
            if (result != -1) {
                answer = min(answer, result)
            }
        }
    }

    // 최솟값을 출력한다
    println(
        if (answer == Int.MAX_VALUE) - 1
        else answer
    )
}