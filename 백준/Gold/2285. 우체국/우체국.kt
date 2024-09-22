/**
 * 위치 x에 우체국을 세웠을 때 각 사람까지의 거리의 합을 f(x)라고 하자.
 * 그리고 위치 x보다 왼쪽(x 포함)에 위치한 사람의 수를 L(x), 오른쪽에 위치한 사람의 수를 R(x)라고 하자.
 *
 * 임의의 t에 대해
 * f(t + 1) = f(t) + L(t) - R(t)
 * f(t - 1) = f(t) - L(t) + R(t)
 * 가 성립한다
 *
 * y = f(x)는 아래로 볼록한 함수의 개형을 띠고
 * f(x)의 최솟값은 L(t) >= R(t) 가 성립하는 t의 최솟값에서 달성된다
 */

fun main() = with(System.`in`.bufferedReader()) {
    // 입력을 받고 마을을 왼쪽부터 순서대로 정렬한다
    val N = readLine().toInt()

    val towns = MutableList(N) {
        val (x, a) = readLine().split(" ").map { it.toInt() }
        x to a.toLong()
    }.sortedBy { it.first }


    // 왼쪽 마을부터 인구 수를 세면서 총 인구 수의 절반 이상이 되는 마을 좌표의 최솟값이 정답이다
    val totalPopulation = towns.sumOf { it.second }

    var population = 0L

    val answer = towns.first { (_, pop) ->
        population += pop
        2L * population >= totalPopulation
    }.first

    // 정답을 출력한다
    println(answer)
}