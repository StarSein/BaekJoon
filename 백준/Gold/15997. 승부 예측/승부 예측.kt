import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

/*

3^6가지의 모든 경우의 수를 완전 탐색해 본다
각 경우의 수에서 진출하는 팀은 해당 케이스의 확률값을 더한다

 */

fun main() {
    // 변수 선언
    data class Match(
        val team1: Int,
        val team2: Int,
        val winRate: Double,
        val drawRate: Double,
        val loseRate: Double
    )
    val emptyMatch = Match(-1, -1, 0.0, 0.0, 0.0)
    val nameMap = mutableMapOf<String, Int>()
    val matches = Array(6) { emptyMatch }
    val points = IntArray(4) { 0 }
    val answers = DoubleArray(4) { 0.0 }

    // 입력을 받는다
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    repeat(4) {
        val name = st.nextToken()
        nameMap.put(name, it)
    }
    repeat(6) {
        st = StringTokenizer(br.readLine())
        matches[it] = Match(
            team1 = nameMap.getOrDefault(st.nextToken(), -1),
            team2 = nameMap.getOrDefault(st.nextToken(), -1),
            winRate = st.nextToken().toDouble(),
            drawRate = st.nextToken().toDouble(),
            loseRate = st.nextToken().toDouble()
        )
    }

    // 재귀 함수 정의
    fun recur(depth: Int, p: Double) {
        // 기저 조건
        if (depth == 6) {
            // 점수 배열을 (인덱스, 점수)의 배열로 변환하고 점수의 내림차순으로 정렬한다
            val list = points.mapIndexed { index, point -> Pair(index, point) }
                .sortedByDescending { it.second }
                .toList()
            
            var randomTO: Int
            var randomCount: Int
            if (list[0].second > list[1].second) {
                // 1등이 추첨 대상이 아닌 경우
                answers[list[0].first] += p
                randomTO = 1
                randomCount = 1
            } else {
                // 1등이 추첨 대상인 경우
                randomTO = 2
                randomCount = 2
            }
            for (i in 1..2) {
                if (list[i].second > list[i + 1].second) {
                    break
                } else {
                    randomCount++
                }
            }
            
            // 추첨 대상자(들)에게 확률을 분배한다
            val startIndex = 2 - randomTO
            val np = randomTO.toDouble() / randomCount.toDouble()
            repeat(randomCount) {
                answers[list[startIndex + it].first] += p * np
            }

            // 재귀 호출을 종료한다
            return
        }
        
        val match = matches[depth]

        // team1 승리
        points[match.team1] += 3
        recur(depth + 1, p * match.winRate)
        points[match.team1] -= 3
        
        // 무승부
        points[match.team1]++
        points[match.team2]++
        recur(depth + 1, p * match.drawRate)
        points[match.team1]--
        points[match.team2]--
        
        // team1 패배
        points[match.team2] += 3
        recur(depth + 1, p * match.loseRate)
        points[match.team2] -= 3
    }

    // 재귀 함수를 이용해 완전 탐색을 진행한다
    recur(0, 1.0)

    // 각 국가별 본선 진출 확률을 출력한다
    repeat(4) {
        println(String.format("%.6f", answers[it]))
    }
}
