/**
 * dist(i, j) 합을 최소화하려면 간선을 지나는 경로의 개수가 적은 간선에 수열의 큰 원소를 가중치로 매겨야 한다
 * 따라서 전체 과정은 아래와 같다
 * 1. 각 간선마다 해당 간선을 지나는 경로의 개수를 구한다
 *  - 트리의 리프노드에서부터 서브트리의 개수를 세면서 구한다
 * 2. 간선을 지나는 경로의 개수를 오름차순 정렬, 수열의 원소를 내림차순 정렬하여 일대일 대응시키고 그 합을 구한다
 */

fun main() = with (System.`in`.bufferedReader()) {
    // 입력을 받는다
    val N = readLine().toInt()
    val graph = Array(N + 1) { mutableListOf<Int>() }
    repeat(N - 1) {
        val (u, v) = readLine().split(" ").map { it.toInt() }
        graph[u].add(v)
        graph[v].add(u)
    }
    val a = readLine().split(" ").map { it.toInt() }

    // 각 간선마다 해당 간선을 지나는 경로의 개수를 구한다
    val routeCountList = mutableListOf<Long>()
    val mod = 1_000_000_007L
    fun dfs(cur: Int, prev: Int): Int {
        return (graph[cur].filter { next -> next != prev }
            .sumOf { next -> dfs(next, cur) } + 1)
            .also { subTreeSize ->
                val routeCount = (N - subTreeSize) * subTreeSize.toLong()
                if (routeCount != 0L) {
                    routeCountList.add(routeCount)
                }
            }
    }
    dfs(1, 0)

    // 경로의 개수 리스트를 오름차순 정렬한다
    routeCountList.sort()

    // 수열의 원소 리스트를 내림차순 정렬한다
    // 두 리스트의 원소들을 곱 연산으로 매핑한 뒤 총합을 출력한다
    val answer = a.sortedDescending()
        .mapIndexed { index, it -> (it * routeCountList[index]) % mod }
        .sum() % mod
    println(answer)
}