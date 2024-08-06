import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.min

/**
 * 임의의 k를 설정해 두고 그에 맞는 단어쌍의 조합 만들기를 시도해 본다고 하면
 * k 이상의 공통 접두사가 존재하는 단어쌍으로 가능한 경우의 수는 모두 서로 무차별하다
 * 따라서 매개 변수 탐색을 통해 해를 구할 수 있다
 *
 * 하지만 전체 문자의 개수가 최대 2 * 10^6 이므로
 * 매개 변수 탐색으로 약 20번의 ok() 함수 호출 시 시간 복잡도가 빡빡하다
 *
 * 단어들을 사전순으로 정렬하게 되면 가장 공통접두사가 긴 것들끼리 묶이게 된다
 * 한 쌍씩 묶었을 때 공통 접두사의 길이의 최솟값을 갱신하면 정답이다
 * 이때의 시간 복잡도도 매개 변수 탐색 풀이와 같지만 상수 시간이 절감되는 것으로 판단된다
 */

fun main() {
    // 입력을 받는다
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val words = Array<String>(n) { br.readLine() }

    // 단어들을 정렬한다
    Arrays.sort(words)

    // 단어 한 쌍씩 묶으면서 공통 접두사의 길이로 k의 최솟값을 갱신한다
    var k = 2_000_000_000
    repeat(n / 2) {
        val s1 = words[2 * it]
        val s2 = words[2 * it + 1]

        var prefixLength = 0
        for (i in 0..<s1.length) {
            if (s1[i] == s2[i]) {
                prefixLength++
            } else {
                break
            }
        }

        k = min(k, prefixLength)
    }

    // k의 최솟값을 출력한다
    println(k)
}