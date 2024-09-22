import kotlin.math.floor

/**
 * 일차항을 좌변, 상수항을 좌변에 배치하는 것으로 간주하자
 * 1. 일차항의 계수가 0이 아닌 경우 해는 반드시 하나 존재한다
 * 2. 일차항의 계수가 0인 경우
 *  1) 상수항이 0인 경우 해는 무한히 많다
 *  2) 상수항이 0이 아닌 경우 해는 존재하지 않는다
 */

fun main() = with(System.`in`.bufferedReader()) {
    val t = readLine().toInt()

    val sb = StringBuilder()
    repeat(t) {
        val equation = readLine()

        var buffer = 0
        var left = 0
        var right = 0

        var bufferWeight = 1
        var leftWeight = 1
        var rightWeight = -1

        fun addToRight() {
            if (buffer != 0) {
                right += rightWeight * bufferWeight * buffer
                buffer = 0
            }
        }

        equation.forEachIndexed { idx, char ->
            when (char) {
                'x' -> {
                    if (buffer == 0 && (idx == 0 || equation[idx - 1] != '0')) {
                        buffer = 1
                    }
                    left += leftWeight * bufferWeight * buffer
                    buffer = 0
                }
                '+' -> {
                    addToRight()
                    bufferWeight = 1
                }
                '-' -> {
                    addToRight()
                    bufferWeight = -1
                }
                '=' -> {
                    addToRight()
                    bufferWeight = 1
                    leftWeight = -1
                    rightWeight = 1
                }
                else -> {
                    buffer = 10 * buffer + char.digitToInt()
                }
            }
        }

        addToRight()

        if (left == 0) {
            if (right == 0) {
                sb.append("IDENTITY")
            } else {
                sb.append("IMPOSSIBLE")
            }
        } else {
            val solution = floor(right.toFloat() / left.toFloat()).toInt()
            sb.append(solution)
        }
        sb.append('\n')
    }

    print(sb)
}