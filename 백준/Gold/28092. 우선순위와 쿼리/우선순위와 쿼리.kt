import java.util.*

/**
 * 트리와 트리의 연결, 그리고 트리 사이의 비교를 위해
 * union & find 알고리즘 그리고 자료구조로 TreeSet을 이용하자
 *
 * TreeSet의 우선순위
 * 1) 정점의 개수 (연결요소의 크기)의 내림차순
 * 2) 가장 작은 정점 번호의 오름차순
 *
 * 각 연결 요소는
 * Tree(
 *  가장 작은 정점 번호: Int (연결 요소 및 트리의 루트 노드)
 *  정점의 개수: Int
 * )
 * 와 같은 형태로 TreeSet에 저장하고, union 연산을 할 때 두 연결 요소를 TreeSet에서 제거하고
 * 새로운 연결 요소를 TreeSet에 추가한다
 *
 * 시간 복잡도 O(QlogQ)에 해결할 수 있다
 */

data class Tree(
    val root: Int,
    val size: Int
) : Comparable<Tree> {
    override fun compareTo(other: Tree): Int {
        return compareValuesBy(this, other, { -it.size }, { it.root })
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val (N, Q) = readLine().split(" ").map { it.toInt() }
    val trees = Array(N + 1) { Tree(it, 1) }
    val treeSet = TreeSet<Tree>()
    (1..N).forEach { treeSet.add(trees[it]) }

    fun findTree(x: Int): Tree {
        if (trees[x].root == x) {
            return trees[x]
        }
        return findTree(trees[x].root).also {
            trees[x] = it
        }
    }

    fun merge(a: Int, b: Int) {
        var ta = findTree(a)
        var tb = findTree(b)

        /**
         * (1) 둘 중 하나가 트리가 아닌 경우 다른 하나도 이 연결로 인해 트리가 아닌 것이 되므로 삭제 작업만 진행한다
         *     트리셋에 존재하지 않는다면 트리가 아닌 것이다
         * (2) 같은 연결 요소에 속한 두 노드를 간선으로 연결하는 경우
         *     해당 연결 요소는 더 이상 트리가 아니게 되므로 삭제 작업만 진행한다
         *     이때 ta와 tb가 같으므로 두 번째 if문에서 함수 호출이 종료된다
         */
        if (!treeSet.remove(ta)) {
            treeSet.remove(tb)
            return
        }
        if (!treeSet.remove(tb)) {
            treeSet.remove(ta)
            return
        }

        if (ta.root > tb.root) {
            val temp = ta
            ta = tb
            tb = temp
        }

        val tc = Tree(ta.root, ta.size + tb.size)
        trees[ta.root] = tc
        trees[tb.root] = tc
        treeSet.add(tc)
    }

    val sb = StringBuilder()
    repeat(Q) {
        val query = readLine()
        when (query) {
            "2" -> {
                val bestTree = treeSet.pollFirst()
                sb.append(bestTree.root).append('\n')
            }
            else -> {
                val (_, u, v) = query.split(" ").map { it.toInt() }
                merge(u, v)
            }
        }
    }

    print(sb)
}