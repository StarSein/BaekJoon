/**
 * 각 왕국들 사이의 계층 구조를 트리의 형태로 완벽히 구현할 필요는 없다
 * 한 번 같은 연결 요소가 된 왕국들은 계속 같은 연결 요소에 속하게 된다
 * 따라서 각 연결 요소의 대표 왕국 관리에만 관심을 두면 된다
 */
fun main() = with(System.`in`.bufferedReader()) {
    val prefix = "Kingdom of "
    // 전쟁 이전의 입력을 받는다
    val (N, M) = readLine().split(" ").map { it.toInt() }
    val kingdoms = List(N) { readLine().substringAfter(prefix) }

    // 전쟁 처리를 위한 자료 구조 및 함수를 만든다
    val nameToIndex = mutableMapOf<String, Int>()
    kingdoms.forEachIndexed { index, name -> nameToIndex[name] = index }
    val roots = Array(N) { it }

    fun findRoot(x: Int): Int {
        if (roots[x] == x) {
            return x
        }
        roots[x] = findRoot(roots[x])
        return roots[x]
    }

    fun handleWar(winner: Int, loser: Int) {
        val rw = findRoot(winner)
        val rl = findRoot(loser)

        if (rw == rl) {
            if (rl == loser) {
                roots[rl] = winner
                roots[winner] = winner
            }
        } else {
            roots[rl] = rw
        }
    }

    // 전쟁을 입력받고 처리한다
    repeat(M) {
        val (a, b, c) = readLine().split(",")
        val (winner, loser) =
            if (c == "1") a to b
            else b to a

        handleWar(
            nameToIndex[winner.substringAfter(prefix)]!!,
            nameToIndex[loser.substringAfter(prefix)]!!
        )
    }

    // 모든 전쟁을 처리한 뒤에 속국이 아닌 왕국(각 연결 요소의 대표 노드)을 찾는다
    val rootKingdoms = (0..<N)
        .filter { findRoot(it) == it }
        .map { kingdoms[it] }
        .sorted()
        .map { prefix + it }

    // 대표 왕국의 수, 그리고 그 목록을 오름차순으로 출력한다
    val answer = StringBuilder()
    answer.append("${rootKingdoms.size}\n")
    rootKingdoms.forEach { answer.append("${it}\n") }

    print(answer)
}