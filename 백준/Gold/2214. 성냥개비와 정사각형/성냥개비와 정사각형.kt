import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

/*

모든 (r, c)에 대해 모든 크기의 정사각형의 존재 여부를 체크해 보면 된다

가능한 정사각형의 개수 - O(N^3)
정사각형 존재 여부 검증 - O(N)
전체 시간 복잡도는 O(N^4) (N <= 20)

*/

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val sb = StringBuilder()
    while (true) {

        // 입력을 받는다. 이때 수평 성냥개비와 수직 성냥개비 배열을 따로 받는다
        var st = StringTokenizer(br.readLine(), " ")
        val R = st.nextToken().toInt()
        var C = st.nextToken().toInt()

        // 루프 종료 조건
        if (R == 0 && C == 0) {
            break
        }

        val horizons = Array(R + 1) { " " }
        val verticals = Array(R) { " " }
        repeat(2 * R + 1) {
            when (it % 2) {
                0 -> horizons[it / 2] = br.readLine()
                else -> verticals[it / 2] = br.readLine()
            }
        }

        // 어떤 (r, c)에서 시작하여 수평으로 길이가 k인 선분의 존재 여부를 반환하는 함수 정의
        fun isHorizonOk(r: Int, c: Int, k: Int): Boolean {
            for (i in 0 until k) {
                if (horizons[r][c + i] == '*') {
                    return false
                }
            }
            return true
        }

        // 어떤 (r, c)에서 시작하여 수직으로 길이가 k인 선분의 길이 존재 여부를 반환하는 함수 정의
        fun isVerticalOk(r: Int, c: Int, k: Int): Boolean {
            for (i in 0 until k) {
                if (verticals[r + i][c] == '*') {
                    return false
                }
            }
            return true
        }

        // 모든 (sr, sc, k) 에 대해 정사각형 존재 여부를 체크하여, 정사각형의 개수를 센다
        var squareCount = 0
        for (k in 1..20) {
            for (sr in 0..R - k) {
                for (sc in 0..C - k) {
                    if (!isHorizonOk(sr, sc, k)) {
                        continue
                    }
                    if (!isHorizonOk(sr + k, sc, k)) {
                        continue
                    }
                    if (!isVerticalOk(sr, sc, k)) {
                        continue
                    }
                    if (!isVerticalOk(sr, sc + k, k)) {
                        continue
                    }
                    squareCount++
                }
            }
        }

        // 정사각형의 개수를 정답 문자열에 추가한다
        sb.append("$squareCount squares\n")
    }

    // 정답 문자열을 출력한다
    print(sb)
}
