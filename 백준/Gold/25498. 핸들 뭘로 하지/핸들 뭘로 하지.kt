import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * 두 정점 사이의 우열을 정하려면 맨 뒤의 문자열까지 알아야 한다
 * 따라서 루트 노드에서 출발하면서 결정할 것이 아니라,
 * 리프 노드들부터 출발하면서 사전순으로 더 뒤에 오는 것을 상위 노드로 올리는 것이 맞다
// * 최악의 경우로 상정할 만한 트리의 개형은 이진 트리인데, 이때 시간 복잡도가 O(NlogN)이다
 * 시간 복잡도를 O(N^2)이라고 계산해야 맞다
 *
 * 결국 문자열을 통째로 비교하면서 올라가는 방법은 안 된다
 * 문자 하나만 비교하면서 최적 선택을 하려면 루트 노드에서 출발해야 한다
 * 특정 시점에서 다음에 올 문자로 사전 순으로 더 뒤에 오는 문자를 택하는 것이 최적임은 자명하다
 * 문제는 동점자가 발생하는 경우다
 * 너비 우선 탐색을 통해 동점자들 사이의 우열 가르기를 유보한다
 * 같은 너비에 해당하는 문자들끼리 비교하면서 각 너비의 최적의 문자를 택하면
 * 최적의 접두사를 만들어갈 수 있다
 */

fun main() {
    // 입력을 받는다
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()
    val s = br.readLine()

    val graph = Array<MutableList<Int>>(N) { mutableListOf() }
    repeat(N - 1) {
        val pair = br.readLine().split(" ")
        val u = pair[0].toInt() - 1
        val v = pair[1].toInt() - 1
        graph[u].add(v)
        graph[v].add(u)
    }

    // 너비 우선 탐색을 진행할 때마다 해당 너비에서 최적의 문자를 선택하면서
    // 최적의 접두사의 길이를 하나씩 늘려나간다
    val prefix = StringBuilder()

    val dq = ArrayDeque<Int>()
    val visited = BooleanArray(N) { false }
    dq.addLast(0)
    visited[0] = true

    while (dq.isNotEmpty()) {

        val size = dq.size
        var maxChar = 'a'
        val bestNodeList = mutableListOf<Int>()

        repeat(size) {
            val node = dq.removeFirst()
            val curChar = s[node]
            if (curChar > maxChar) {
                maxChar = curChar
                bestNodeList.clear()
                bestNodeList.add(node)
            } else if (curChar == maxChar) {
                bestNodeList.add(node)
            }
        }

        prefix.append(maxChar)

        bestNodeList.forEach { curNode ->
            graph[curNode].forEach { nextNode ->
                if (!visited[nextNode]) {
                    dq.addLast(nextNode)
                    visited[nextNode] = true
                }
            }
        }
    }

    // 최종 접두사를 출력한다
    println(prefix)
}