import java.util.*
import kotlin.collections.ArrayDeque

/**
 * 1. 한별 선배의 경로 찾기
 *  1) 우선순위
 *   (1) 학교까지의 최단 경로
 *   (2) 연결된 도로가 가장 많은 은행나무
 *   (3) 번호가 가장 큰 은행나무
 *  2) 반영 방법
 *   - 간선 정보 입력 받을 때 각 은행나무마다 degree를 저장해 놓는다
 *   - 학교에서 너비 우선 탐색을 하여 학교와 각 은행나무를 연결하는 경로 위의 은행나무 개수 level를 저장한다
 *   - 한별 선배 집에서 우선순위에 따라 경로를 만들어 간다
 *
 * 2. 토카가 기다릴 은행나무 찾기
 *  1) 우선순위
 *   (1) 한별 선배의 경로 위 은행나무
 *   (2) 자신의 집과 가장 가까운 은행나무
 *   (3) 번호가 가장 작은 은행나무
 *  2) 반영 방법
 *   - 토카의 집에서 다익스트라를 이용한 최단 경로 탐색을 통해 각 은행나무마다 최단 거리 dist를 저장한다
 *   - 한별 선배 경로상의 은행나무에 대해 우선순위에 따른 최댓값을 찾는다
 */

fun main() {
    // 입력을 받는다
    val br = System.`in`.bufferedReader()
    val (N, M) = br.readLine().split(" ").map { it.toInt() }
    val (A, B, C) = br.readLine().split(" ").map { it.toInt() }

    val graph = Array<MutableList<Pair<Int, Int>>>(N + 1) { mutableListOf() }
    val degrees = IntArray(N + 1) { 0 }
    repeat(M) {
        val (i, j, k) = br.readLine().split(" ").map { it.toInt() }
        graph[i].add(Pair(j, k))
        graph[j].add(Pair(i, k))
        degrees[i]++
        degrees[j]++
    }

    // 한별 선배의 경로를 찾는다
    fun getLevels(): IntArray {
        val levels = IntArray(N + 1) { -1 }
        val dq = ArrayDeque<Int>()
        dq.addLast(C)
        levels[C] = 0
        var level = 1
        while (dq.isNotEmpty()) {
            repeat(dq.size) {
                val cur = dq.removeFirst()

                graph[cur]
                    .filter { (next, _) -> levels[next] == -1 }
                    .forEach { (next, _) ->
                        levels[next] = level
                        dq.addLast(next)
                    }
            }
            level++
        }
        return levels
    }

    val levels = getLevels()

    fun getRoute(): List<Int> {
        val route = mutableListOf<Int>()
        route.add(B)
        var cur = B
        while (cur != C) {
            cur = graph[cur]
                .minOfWith({ o1, o2 ->
                    when {
                        (levels[o1] != levels[o2]) -> levels[o1] - levels[o2]
                        (degrees[o1] != degrees[o2]) -> degrees[o2] - degrees[o1]
                        else -> o2 - o1
                    }
                }) { it.first }

            route.add(cur)
        }
        return route
    }

    val route = getRoute()

    // 토카가 기다릴 은행나무를 찾는다
    fun getDists(): LongArray {
        val dists = LongArray(N + 1) { -1L }
        val pq = PriorityQueue<Pair<Int, Long>>(Comparator.comparingLong { it.second})
        pq.offer(Pair(A, 0L))
        while (pq.isNotEmpty()) {
            val (cur, dist) = pq.poll()

            if (dists[cur] != -1L) {
                continue
            }
            dists[cur] = dist

            graph[cur]
                .filter { (next, _) -> dists[next] == -1L }
                .forEach { (next, weight) -> pq.offer(Pair(next, dist + weight)) }
        }
        return dists
    }

    val dists = getDists()

    fun getAnswer(): Int {
        return route.minWith { o1, o2 ->
            when {
                (dists[o1] != dists[o2]) -> dists[o1].compareTo(dists[o2])
                else -> o1 - o2
            }
        }
    }

    // 찾은 은행나무 번호를 출력한다
    println(getAnswer())
}