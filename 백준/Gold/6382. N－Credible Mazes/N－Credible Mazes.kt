fun main() {
    val sb = StringBuilder()
    val br = System.`in`.bufferedReader()

    val listToLong: (Int, Int, List<String>) -> Long = { s, e, ls ->
        (s..<e).toList()
            .map { ls[it].toLong() }
            .fold(0L) { acc, i -> acc * 11L + i }
    }

    var tc = 1
    while (true) {
        val n = br.readLine().toInt()
        if (n == 0) {
            break
        }

        val tokens = br.readLine().split(" ")
        val start = listToLong(0, n, tokens)
        val end = listToLong(n, 2 * n, tokens)

        val graph = mutableMapOf<Long, MutableList<Long>>()

        while (true) {
            val line = br.readLine()
            if (line == "-1") {
                break
            }
            val tokens2 = line.split(" ")
            val nodeA = listToLong(0, n, tokens2)
            val nodeB = listToLong(n, 2 * n, tokens2)
            graph.getOrPut(nodeA) { mutableListOf() }.add(nodeB)
            graph.getOrPut(nodeB) { mutableListOf() }.add(nodeA)
        }

        fun connected(start: Long, end: Long): Boolean {
            val dq = ArrayDeque<Long>()
            val visitedSet = mutableSetOf<Long>()

            dq.addLast(start)
            visitedSet.add(start)

            while (dq.isNotEmpty()) {
                val cur = dq.removeFirst()

                graph[cur]?.filter { next -> !visitedSet.contains(next) }
                    ?.forEach {
                        if (it == end) {
                            return true
                        }
                        dq.addLast(it)
                        visitedSet.add(it)
                    }
            }

            return false
        }

        val middlePart = if (connected(start, end)) "can be" else "cannot be"
        sb.append("Maze #$tc $middlePart travelled\n")
        tc++
    }

    println(sb)
}