/**
 * 매개 변수 탐색을 통해 풀이하자
 * 정답이 여러 개일 경우 사전순으로 가장 늦는 것을 출력하므로,
 * 심판의 거리 조건을 만족하면서 배치할 수 있다면 곧바로 배치하도록 하자
 *
 * 0번 인덱스부터 고려할지, 1번 인덱스부터 고려할지, ... 50가지 경우를 다 해 봐야 한다
 */

fun main() = with(System.`in`.bufferedReader()) {
    // 입력을 받는다
    val (N, M, K) = readLine().split(" ").map { it.toInt() }
    val posList = readLine().split(" ").map { it.toInt() }

    fun simulate(x: Int, start: Int): Pair<Boolean, String> {
        var prev = posList[start]
        var refereeCount = 1
        val sb = StringBuilder()
        repeat(start) {
            sb.append("0")
        }
        sb.append("1")

        (start + 1..<K).forEach {
            val cur = posList[it]
            if (cur - prev >= x && refereeCount < M) {
                refereeCount++
                sb.append("1")
                prev = cur
            } else {
                sb.append("0")
            }
        }

        return Pair(refereeCount == M, sb.toString())
    }

    fun parametricSearch(start: Int): Pair<Int, String> {
        var ret = Pair(0, "")
        var s = 1
        var e = N
        while (s <= e) {
            val mid = (s + e) / 2
            val (able, result) = simulate(mid, start)
            if (able) {
                ret = Pair(mid, result)
                s = mid + 1
            } else {
                e = mid - 1
            }
        }
        return ret
    }

    // 매개 변수 탐색을 통해 K가지 경우 각각의 최댓값을 찾는다
    var answer = Pair(0, "")
    repeat(K) {
        val pair = parametricSearch(it)

        if (answer.first < pair.first) {
            answer = pair
        }
    }

    // K가지 경우의 결과값 중 최댓값을 출력한다
    println(answer.second)
}