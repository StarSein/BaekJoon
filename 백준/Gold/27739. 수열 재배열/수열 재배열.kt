import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.max

/*

K개의 자유 재배열된 수들의 구간을 X 라고 하자
그 왼쪽에 존재하는 수를 a, 오른쪽에 존재하는 수를 b 라고 하면
A = [..., a, X, b, ...] 와 같은 형태다

1) a > b 인 경우
    a보다 큰 원소를 a쪽에 모으는 경우와 b보다 작은 원소를 b쪽에 모으는 경우를 비교해본다
2) a < b 인 경우
    (1) X의 모든 수가 a 초과 b 미만인 경우
        최적의 경우는 단 하나다
    (2) 그렇지 않은 수가 단 하나라도 존재하는 경우
        1)과 같이 두 경우를 비교해야 한다

필요한 연산은
a부터 왼쪽 방향 연속 감소 수열 최대 길이 구하기  - O(N)
b부터 오른쪽 방향 연속 증가 수열 최대 길이 구하기 - O(N)
K개의 수에 대해 a, b 와의 대소 비교 - O(K)
이 연산의 반복 횟수가 O(N)이므로

전체 시간 복잡도는 O(N^2)

 */


fun main(args: Array<String>) {
    // 입력을 받는다
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine(), " ")
    val N = st.nextToken().toInt()
    val K = st.nextToken().toInt()
    st = StringTokenizer(br.readLine(), " ")
    val A = IntArray(N + 2)
    A[0] = N + 1
    A[N + 1] = 0
    repeat(N) {
        A[it + 1] = st.nextToken().toInt()
    }

    var answer = K
    // 길이가 K인 모든 가능한 구간에 대해 연속 증가 부분 수열 최대 길이의 최댓값을 구한다
    for (s in 1..N - K + 1) {
        val e = s + K - 1
        val left = A[s - 1]
        val right = A[e + 1]

        // 왼쪽 바깥 부분의 길이을 구한다
        var leftCount = 1
        for (i in s - 1 downTo 1) {
            if (A[i - 1] > A[i]) {
                break
            }
            leftCount++
        }

        // 오른쪽 바깥 부분의 길이를 구한다
        var rightCount = 1
        for (i in e + 1..N) {
            if (A[i] > A[i + 1]) {
                break
            }
            rightCount++
        }

        // 왼쪽보다 큰 원소의 개수를 센다
        val interval = A.slice(s..e)
        val leftHigherCount = interval.count { it > left }
        // 오른쪽보다 작은 원소의 개수를 센다
        val rightLowerCount = interval.count { it < right }

        // K개의 원소 모두 왼쪽보다 크고 오른쪽보다 작은 경우
        if (leftHigherCount == K && rightLowerCount == K) {
            answer = max(answer, leftCount + rightCount + K)
            continue
        }

        // 그렇지 않은 경우
        answer = max(answer, leftCount + leftHigherCount)
        answer = max(answer, rightCount + rightLowerCount)
    }

    // 정답을 출력한다
    println(answer)
}