/**
 * 직원이 일을 시작한 날짜와 그만둔 날짜를 마킹해두고
 * 하루씩 스위핑을 하면서 (일간 수익, 일하는 직원 수)를 관리한다
 * 하루가 지날 때마다 일하는 직원 수만큼 일간 수익을 증가시킨다
 * 일을 그만둔 날짜의 경우 일을 시작한 날짜와 매핑하여
 * 일간 수익에서 (일을 그만둔 날짜 - 일을 시작한 날짜)를 제외한다
 * 
 * 전체 시간 복잡도는 O(N + K)
 */

fun main() = with (System.`in`.bufferedReader()) {
    // 입력을 받는다
    val (N, K) = readLine().split(" ").map { it.toInt() }
    val starts = IntArray(K + 1) { 0 }
    val ends = Array(K + 1) { mutableListOf<Int>() }
    repeat(N) {
        val (s, e) = readLine().split(" ").map { it.toInt() }
        starts[s]++
        ends[e].add(s)
    }

    // 하루가 지날 때마다 그날의 수익 양을 정답 문자열에 추가한다
    val sb = StringBuilder()
    var profit = 0L
    var workerCount = 0
    (1..K).forEach { day ->
        workerCount += starts[day]
        profit += workerCount

        sb.append("$profit ")

        workerCount -= ends[day].size
        ends[day].forEach { start ->
            profit -= (day - start + 1)
        }
    }

    // 정답 문자열을 출력한다
    println(sb)
}