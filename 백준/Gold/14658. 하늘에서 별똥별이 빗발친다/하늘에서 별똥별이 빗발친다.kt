/**
 * 별똥별이 존재하는 x좌표값 100개, y좌표값 100개에 대해 트램펄린의 상하좌우 경계값으로 지정해보면서
 * 트램펄린으로 받을 수 있는 별똥별 개수의 최댓값을 구하면 된다
 * 완전 탐색의 경우의 수가 위와 같은 100 * 100 으로 커버되는 이유는
 * 어떤 별똥별을 트램펄린의 내부에 위치시키는 것보다 트램펄린의 경계선에 위치시키는 것이 더 이득이기 때문이다
 */


fun main() = with (System.`in`.bufferedReader()){
    // 입력을 받는다
    val (N, M, L, K) = readLine().split(" ").map { it.toInt() }
    val stars = Array(K) { readLine().let { line ->
        val (x, y) = line.split(" ").map { token -> token.toInt() }
        Pair(x, y)
    } }

    // 별똥별이 존재하는 x좌표 집합과 y좌표 집합을 구한다
    val xSet = mutableSetOf<Int>()
    val ySet = mutableSetOf<Int>()
    stars.forEach { (x, y) ->
        xSet.add(x)
        ySet.add(y)
    }

    // 모든 (x, y) (단, x, y는 각각 집합에 존재하는 값)에 대해
    // 트램펄린의 우상, 우하, 좌하, 좌상 꼭짓점 좌표값으로 지정해 보고 튕겨낼 수 있는 별똥별의 개수를 세면서
    // 최댓값을 갱신한다
    fun contains(sx: Int, sy: Int, ex: Int, ey: Int, x: Int, y: Int): Boolean {
        return x in sx..ex && y in sy..ey
    }

    var maxCoverage = 0
    xSet.forEach { tx ->
        ySet.forEach { ty ->
            // 오른쪽 위
            maxCoverage = Math.max(maxCoverage,
                stars.count { (x, y) -> contains(tx - L, ty - L, tx, ty, x, y) })
            // 오른쪽 아래
            maxCoverage = Math.max(maxCoverage,
                stars.count { (x, y) -> contains(tx - L, ty, tx, ty + L, x, y) })
            // 왼쪽 아래
            maxCoverage = Math.max(maxCoverage,
                stars.count { (x, y) -> contains(tx, ty, tx + L, ty + L, x, y) })
            // 왼쪽 위
            maxCoverage = Math.max(maxCoverage,
                stars.count { (x, y) -> contains(tx, ty - L, tx + L, ty, x, y) })
        }
    }

    // 정답을 출력한다
    println(K - maxCoverage)
}