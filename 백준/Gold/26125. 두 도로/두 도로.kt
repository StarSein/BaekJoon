import kotlin.math.min

/**
 * 1. 도로 건설 시나리오 이전 초기 상태의 그래프에서 각 교차로 사이의 최단 거리를 구한다
 *  - N번의 다익스트라: O(NMlogN)
 *  - 플로이드 워셜: O(N^3)
 * 2. 하나의 도로 건설 시나리오 a1, b1, c1, a2, b2, c2에 대해
 *  (1) 기존의 dist(S, T)
 *
 *  새로 생긴 도로 하나만 고려하는 경우 - 2가지
 *  (2) dist(S, a1) + dist(b1, T) + c1
 *  (3) dist(S, a2) + dist(b2, T) + c2
 *
 *  2) 새로 생긴 도로 둘 다 고려하는 경우 - 2가지
 *  (4) dist(S, a1) + dist(b1, a2) + dist(b2, T) + c1 + c2
 *  (5) dist(S, a2) + dist(b2, a1) + dist(b1, T) + c1 + c2
 *  위와 같은 5가지 경우 중 최솟값을 취하면 된다
 */

fun main() = with(System.`in`.bufferedReader()) {
    val INF = 1_000_000_000L
    // 입력에 대해 인접 행렬을 만든다
    val (N, M, S, T) = readLine().split(" ").map { it.toInt() }
    val dists = Array(N + 1) { LongArray(N + 1) { INF } }
    repeat(M) {
        val (u, v, w) = readLine().split(" ").map { it.toInt() }
        if (dists[u][v] > w) {
            dists[u][v] = w.toLong()
        }
    }
    
    // 모든 노드 사이의 최단 거리를 구한다
    repeat(N + 1) { dists[it][it] = 0L }
    (1..N).forEach { k ->
        (1..N)
            .filter { i -> dists[i][k] != INF }
            .forEach { i ->
            (1..N)
                .filter { j -> dists[k][j] != INF }
                .forEach { j -> 
                dists[i][j] = min(dists[i][j], dists[i][k] + dists[k][j])
            }
        }
    }
    
    // 도로 건설 시나리오가 주어질 때마다 고려할 모든 거리 케이스 중 최단 거리를 정답 문자열에 추가한다
    val sb = StringBuilder()
    val Q = readLine().toInt()
    repeat(Q) {
        val (a1, b1, c1, a2, b2, c2) = readLine().split(" ").map { it.toInt() }
        val minDist = listOf(
            dists[S][T],
            dists[S][a1] + dists[b1][T] + c1,
            dists[S][a2] + dists[b2][T] + c2,
            dists[S][a1] + dists[b1][a2] + dists[b2][T] + c1 + c2,
            dists[S][a2] + dists[b2][a1] + dists[b1][T] + c1 + c2,
        ).min()

        sb.append(if (minDist >= INF) -1 else minDist).append('\n')
    }
    
    // 정답 문자열을 출력한다
    print(sb)
}

private operator fun <E> List<E>.component6() = this[5]
