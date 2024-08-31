/**
 * (1) 자식 노드의 목표 가중치가 부모 노드와 같을 경우
 *  두 노드를 항상 동일한 부분 연결 그래프에 포함시키면 되므로, 부모 노드의 가중치만 고려하면 된다
 * (2) 자식 노드의 목표 가중치가 부모 노드보다 작을 경우
 *  자식 노드의 가중치만큼 두 노드를 연결하면 되므로, 부모 노드의 가중치만 고려하면 된다
 * (3) 자식 노드의 목표 가중치가 부모 노드보다 클 경우
 *  부모 노드의 가중치만큼 두 노드를 연결하고 자식 노드의 남은 가중치 D만큼 따로 연산을 해야 하므로
 *  D만큼을 정답에 더하고 부모 노드의 가중치를 고려한다
 *
 *  이와 같은 방법으로 트리의 리프 노드부터 상위 노드로 거슬러 올라가면서 D의 총합을 집계하면
 *  최소 연산 횟수가 도출된다
 */

fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    val A = Array(N + 1) { 0 }
    readLine().split(" ").map { it.toInt() }
        .forEachIndexed { index, value -> A[index + 1] = value }
    val graph = Array(N + 1) { mutableListOf<Int>() }
    repeat(N - 1) {
        val (u, v) = readLine().split(" ").map { it.toInt() }
        graph[u].add(v)
        graph[v].add(u)
    }

    fun dfs(cur: Int, prev: Int): Long {
        var ret = 0L
        // 부모 노드보다 현재 노드의 가중치가 더 클 경우 그 차이만큼 반환값에 더한다
        // 루트 노드인 1번 노드의 경우에는 자기 자신의 가중치만큼 반환값에 더해야 하는데, 
        // 부모 노드가 가중치가 0인 가상의 0번 노드이므로, 이 조건에 의해 처리된다
        if (A[cur] > A[prev]) {
            ret += A[cur] - A[prev]
        }
        ret += graph[cur].filter { next -> next != prev }
            .sumOf { child -> dfs(child, cur) }
        return ret
    }

    val answer = dfs(1, 0)
    println(answer)
}