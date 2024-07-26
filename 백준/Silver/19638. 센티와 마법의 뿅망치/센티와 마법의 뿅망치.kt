import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.PriorityQueue
import java.util.StringTokenizer

fun main(args: Array<String>) {
    // 입력을 받는다
    // 이때 각 거인의 키는 우선순위 큐에 넣는다
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val centiHeight = st.nextToken().toInt()
    val T = st.nextToken().toInt()

    val pq = PriorityQueue<Int>(Comparator.comparingInt { -it })
    repeat(N) {
        pq.offer(br.readLine().toInt())
    }

    // 내림차순 우선순위 큐의 최상단 원소 v를 제거하고 (v/2) 값을 우선순위 큐에 추가하는 작업을
    // T회 반복한다
    repeat(T) {
        val v = pq.poll()
        // 우선순위 큐의 최상단 원소 v의 값이 centiHeight 보다 낮은 경우
        if (v < centiHeight) {
            println("YES\n$it")
            return
        }

        val nv = if (v == 1) 1 else v / 2
        pq.offer(nv)
    }

    if (pq.peek() < centiHeight) {
        println("YES\n${ T }")
        return
    }

    // v의 값이 centiHeight 보다 높은 경우
    println("NO\n${ pq.peek() }")
}