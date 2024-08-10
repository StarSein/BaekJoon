import java.util.PriorityQueue

/**
 * 우선순위 큐를 이용해 최고 가치의 옥수수를 계속 갱신하자
 * 옥수수 하나를 수확하면 우선순위 큐에 최대 3개의 옥수수가 추가된다
 * 옥수수 수확량은 K개이므로, 전체 시간 복잡도는 O(KlogK or KlogN or KlogM)
 */

fun main() = with(System.`in`.bufferedReader()) {
    // 입력을 받는다
    val (N: Int, M: Int) = readLine().split(" ").map { it.toInt() }
    val grid: Array<Array<Int>> = Array(N) { readLine().split(" ").map { it.toInt() }.toTypedArray() }
    val K: Int = readLine().toInt()

    // 모서리의 옥수수들을 모두 우선순위 큐에 넣는다
    val pq = PriorityQueue<Triple<Int, Int, Int>>(Comparator.comparingInt { -it.third })
    val offerRowExp: (Int) -> Unit =
        if (M == 1) { r ->
            pq.offer(Triple(r, 0, grid[r][0]))
            grid[r][0] = 0
        }
        else { r ->
            pq.offer(Triple(r, 0, grid[r][0]))
            pq.offer(Triple(r, M - 1, grid[r][M - 1]))
            grid[r][0] = 0
            grid[r][M - 1] = 0
        }
    val offerColExp: (Int) -> Unit =
        if (N == 1) { c ->
            pq.offer(Triple(0, c, grid[0][c]))
            grid[0][c] = 0
        }
        else { c ->
            pq.offer(Triple(0, c, grid[0][c]))
            pq.offer(Triple(N - 1, c, grid[N - 1][c]))
            grid[0][c] = 0
            grid[N - 1][c] = 0
        }
    (0..<N).forEach { r -> offerRowExp(r) }
    (1..<M - 1).forEach { c -> offerColExp(c) }

    // K그루의 옥수수를 수확하면서 그 위치를 정답 문자열에 추가한다
    val dr = arrayOf(0, 1, 0, -1)
    val dc = arrayOf(1, 0, -1, 0)
    val sb = StringBuilder()
    repeat(K) {
        val (r, c, _) = pq.poll()

        sb.append("${r + 1} ${c + 1}\n")

        repeat(4) {
            val nr = r + dr[it]
            val nc = c + dc[it]
            if (nr in 0..<N && nc in 0..<M && grid[nr][nc] != 0) {
                pq.offer(Triple(nr, nc, grid[nr][nc]))
                grid[nr][nc] = 0
            }
        }
    }

    // 정답 문자열을 출력한다
    print(sb)
}