/**
 * 2
 * 1 2
 * 2 1
 *
 * 3
 * 1 3 2
 * 2 3 1
 * 2 1 3
 * 3 1 2
 *
 * 4
 * 1 3 2 4
 * 1 2 4 3
 *
 * 5
 * 1 5 3 4 2
 * 1 3 2 4 5
 *
 * 8
 * 1 3 2 4 5 7 6 8
 *
 * i) N % 4 == 0
 *      (4n+1) (4n+3) (4n+2) (4n+4) 의 반복만으로 가능
 * ii) N % 4 == 1
 *      i)의 마지막에 (N)만 붙이면 가능
 * iii) N % 4 == 2
 *      i)의 마지막에 (N-1) (N)만 붙이면 가능
 * iv) N % 4 == 3
 *      i)의 마지막에 (N-2) (N) (N-1)만 붙이면 가능
 */

fun main() = with(System.`in`.bufferedReader()) {
    // 입력을 받는다
    val N = readLine().toInt()

    // 모든 N에 대해 항상 수열 생성이 가능하다
    val sb = StringBuilder()
    sb.append("YES\n")

    // N을 4로 나눈 나머지에 맞게 수열을 생성하면서 정답 문자열에 추가한다
    repeat(N / 4) {
        sb.append("${4 * it + 1} ")
        sb.append("${4 * it + 3} ")
        sb.append("${4 * it + 2} ")
        sb.append("${4 * it + 4} ")
    }

    when (N % 4) {
        0 -> {

        }
        1 -> {
            sb.append(N)
        }
        2 -> {
            sb.append("${N - 1} ${N}")
        }
        else -> {
            sb.append("${N - 2} ${N} ${N - 1}")
        }
    }

    // 정답 문자열을 출력한다
    println(sb)
}

