import java.util.*

/*
코사인 법칙을 이용하면 (b - c)(N^2 - a^2 - bc) = 0 을 유도할 수 있다
이는 모든 b, c 에 대해 성립되는 항등식이므로
N^2 = a^2 + bc 또한 항상 성립한다

따라서 정답은 N^2 * K 와 같다
 */

fun main(args: Array<String>) {
    // 입력을 받는다
    val reader = Scanner(System.`in`)
    val n = reader.nextInt()
    val k = reader.nextInt()

    // 정답을 출력한다
    println(n.toLong() * n * k)
}