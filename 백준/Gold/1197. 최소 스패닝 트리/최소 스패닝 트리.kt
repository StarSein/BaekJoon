import kotlin.math.max

fun main() = with(System.`in`.bufferedReader()) {
    // 입력을 받는다
    val (V, E) = readLine().split(" ").map { it.toInt() }
    val edges = Array(E) {
        val (a, b, c) = readLine().split(" ").map { it.toInt() }
        Triple(a, b, c)
    }

    val roots = IntArray(V + 1) { it }
    val heights = IntArray(V + 1) { 1 }

    fun findRoot(x: Int): Int {
        if (roots[x] == x) {
            return x
        }
        return findRoot(roots[x]).also { roots[x] = it }
    }

    fun merge(a: Int, b: Int): Boolean {
        var ra = findRoot(a)
        var rb = findRoot(b)
        if (ra == rb) {
            return false
        }
        if (heights[ra] < heights[rb]) {
            val temp = ra
            ra = rb
            rb = temp
        }
        roots[rb] = ra
        heights[ra] = max(heights[ra], heights[rb] + 1)
        return true
    }

    // 크루스칼 알고리즘을 이용해 MST를 만든다
    var lenMST = edges.sortedBy { it.third }
        .filter {
            val (a, b, _) = it
            merge(a, b)
        }
        .sumOf { it.third.toLong() }

    // MST의 길이를 출력한다
    println(lenMST)
}