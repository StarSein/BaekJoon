import kotlin.math.min

/**
 * 1. 완전 탐색
 *  - 격려를 어떻게 분배할 것인가
 *  - C = 5 인 경우
// *   (1) 5
// *   (2) 4 1
// *   (3) 3 2
// *   (4) 3 1 1
// *   (5) 2 2 1
// *   (6) 2 1 1 1
// *   (7) 1 1 1 1 1
// *  - 1명에게 분배 1가지
// *  - 2명에게 분배 2가지
// *  - 3명에게 분배 2가지
// *  - 4명에게 분배 1가지
// *  - 5명에게 분배 1가지
// *  - 따라서 전체 경우의 수는 1 * 10P1 + 2 * 10P2 + 2 * 10P3 + 1 * 10P4 + 1 * 10P5
// *    (순서가 중요하지 않은 케이스도 있지만 그 부분까지 최적화하면 너무 복잡해진다)
// *  - 완전 탐색의 케이스 수는 최대
// *  10 + 2 * 90 + 2 * 720 + 5040 + 30240 = 190 + 1440 + 35280 <= 40000
// *  - 대신 요리사의 음식 조리 시간을 1초 미만으로 줄일 수는 없으므로 최솟값이 1이 되게끔 강제로 처리한다
 * - 간단하게 N^C <= 10^5 의 경우의 수를 구현하자
 * 2. 매개 변수 탐색
 *  - 격려의 분배 이후 음식 조리를 위한 최소 시간 구하기
 *  - 음식 조리 시간의 범위는 [1, 10^12] 이므로 격려 분배 케이스 하나당 40번의 ok() 함수 호출 * 크기 10의 반복문
// * 3. 간단한 연산의 횟수가 400 * 40000 = 16000000 미만이므로 시간적으로 충분하다
 * 간단한 연산의 횟수는 400 * 100000 = 4000만 미만으로 시간적으로 충분
 */

fun main() = with (System.`in`.bufferedReader()) {
    // 입력을 받는다
    val (N, K, C) = readLine().split(" ").map { it.toInt() }
    val A = readLine().split(" ").map { it.toInt() }.toMutableList()

    val encourageCount = min(C, A.sum() - A.count())

    fun ok(time: Long): Boolean {
        return A.sumOf { time / it } >= K
    }

    // 격려 배분이 완료되고 매개 변수 탐색으로 음식 조리를 위한 최소 시간을 계산하여 정답을 최솟값으로 갱신한다
    fun parametricSearch(): Long {
        var minTime = Long.MAX_VALUE
        var s = 1L
        var e = 1_000_000_000_000L
        while (s <= e) {
            val mid = (s + e) / 2L
            if (ok(mid)) {
                minTime = mid
                e = mid - 1L
            } else {
                s = mid + 1L
            }
        }
        return minTime
    }

    // 격려를 배분한다
    fun recur(depth: Int): Long {
        if (depth == encourageCount) {
            return parametricSearch()
        }
        var minTime = Long.MAX_VALUE
        A.forEachIndexed { i, it ->
            if (it > 1) {
                A[i]--
                minTime = min(minTime, recur(depth + 1))
                A[i]++
            }
        }
        return minTime
    }

    // 정답을 출력한다
    println(recur(0))
}