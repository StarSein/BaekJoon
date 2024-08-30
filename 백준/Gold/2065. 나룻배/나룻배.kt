fun main() = with(System.`in`.bufferedReader()) {

    // 입력을 받는다
    val (M, t, N) = readLine().split(" ").map { it.toInt() }
    val guests = List(N) {
        val (timeStr, posStr) = readLine().split(" ")
        Triple(it, timeStr.toInt(), posStr == "left")
    }.sortedBy { it.second } // 손님은 정박장 도착 시간의 오름차순으로 정렬한다

    // 시뮬레이션을 한다
    val arrivalTimes = Array(N) { 0 }
    val leftDq = ArrayDeque<Int>()
    val rightDq = ArrayDeque<Int>()
    val boatDq = ArrayDeque<Int>()
    var departFromLeft = true
    var curTime = 0
    var arrivalCount = 0
    var i = 0
    
    while (arrivalCount < N) {
        // 현재 시간에 정박장에 도착해 있을 손님들을 정박장 큐에 넣는다
        while (i < N) {
            val (guestId, time, isLeft) = guests[i]
            if (time <= curTime) {
                if (isLeft) {
                    leftDq.addLast(guestId)
                } else {
                    rightDq.addLast(guestId)
                }
                i++
            } else {
                break
            }
        }

        // 두 정박장 모두 손님이 없는 경우 현재 시간을 다음 손님이 존재하는 시간으로 바꾼다
        if (leftDq.isEmpty() && rightDq.isEmpty()) {
            curTime = guests[i].second
            continue
        }

        // 두 정박장 중 하나라도 손님이 있는 경우 배를 움직인다
        // 배가 현재 위치한 정박장에 손님이 있는 경우 최대 M명까지 태운다
        val curDq = if (departFromLeft) leftDq else rightDq
        repeat(M) {
            if (curDq.isEmpty()) {
                return@repeat
            }
            boatDq.addLast(curDq.removeFirst())
        }
        // 배가 도착하게 되면 현재 시간을 배의 도착 시간으로 바꾼다
        // 손님의 도착 시간을 현재 시간으로 저장한다
        curTime += t
        departFromLeft = departFromLeft.not()
        arrivalCount += boatDq.size
        while (boatDq.isNotEmpty()) {
            val guestId = boatDq.removeFirst()
            arrivalTimes[guestId] = curTime
        }
    }

    // 정답을 출력한다
    val sb = StringBuilder()
    arrivalTimes.forEach { sb.append(it).append("\n") }
    print(sb)
}