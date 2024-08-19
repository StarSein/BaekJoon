/**
 * 물통 A, B에 차 있는 물의 양을 각각 x, y라고 하자
 * 언뜻 보기엔 (x, y)로 가능한 경우의 수가 10^5 * 10^5 로 보인다
 * 하지만 문제의 특성상 (0, y) (a, y) (x, 0) (x, b) 이외의 다른 경우는 존재하지 않는다
 * (0 <= x <= a) (0 <= y <= b)
 * 따라서 (x, y)로 가능한 경우의 수는 4 * 10^5 보다 작거나 같다
 *
 * 따라서 O(N) (N <= 400,000)인
 * 너비 우선 탐색을 통해 최소 작업 수 및 목표 상태 도달 가능 여부를 구하자
 */

fun main() = with(System.`in`.bufferedReader()) {
    // 입력을 받는다
    val (a, b, c, d) = readLine().split(" ").map { it.toInt() }

    // 너비 우선 탐색을 통해 최소 작업 수를 구한다
    val dq = ArrayDeque<Pair<Int, Int>>() // Pair는 kotlin 내장 data class로 hashCode()와 equals() 함수가 정의되어 있다
    val visitSet = HashSet<Pair<Int, Int>>()

    val start = 0 to 0
    dq.addLast(start)
    visitSet.add(start)

    fun addLastIfNotVisited(x: Int, y: Int) {
        val newPair = x to y
        if (visitSet.contains(newPair)) {
            return
        }
        dq.addLast(newPair)
        visitSet.add(newPair)
    }

    var workCount = 0
    while (dq.isNotEmpty()) {
        val size = dq.size

        repeat(size) {
            val (x, y) = dq.removeFirst()

            if (x == c && y == d) {
                println(workCount)
                return
            }

            // A에 물을 가득 채운다
            addLastIfNotVisited(a, y)
            // B에 물을 가득 채운다
            addLastIfNotVisited(x, b)
            // A의 물을 모두 버린다
            addLastIfNotVisited(0, y)
            // B의 물을 모두 버린다
            addLastIfNotVisited(x, 0)
            // A의 물을 B에 붓는다
            (x + y).let { sum ->
                if (sum > b) {
                    addLastIfNotVisited(sum - b, b)
                } else {
                    addLastIfNotVisited(0, sum)
                }
            }
            // B의 물을 A에 붓는다
            (x + y).let { sum ->
                if (sum > a) {
                    addLastIfNotVisited(a, sum - a)
                } else {
                    addLastIfNotVisited(sum, 0)
                }
            }
        }

        workCount++
    }

    // 목표 상태에 도달할 수 없는 경우로, -1을 출력한다
    println(-1)
}