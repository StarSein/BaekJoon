"""
N이 최대 20억이다.
당연히 큐를 이용한 구현은 안 된다.
1~30까지의 운행 시간의 최소공배수를 구해서 약분을 해볼까? 그 최소공배수 역시 엄청 큰 수라서 안 된다.
매개 변수 탐색 말고는 이만큼 큰 N을 처리할 방법이 안 떠오른다.

0분: [3 2] -> 2명
1분: [] -> 2명
2분: [2] -> 3명
3분: [3] -> 4명
4분: [2] -> 5명
5분: [] -> 5명
6분: [3 2] -> 7명

[로직]
1. N번째 아이가 놀이기구를 타는 시간을 t(N) (분)이라고 하자.
2. t(N)을 매개 변수 탐색으로 구한다. (0분 <= t(N) <= 20억 명 * 30분)
3. t(N) - 1 시점에 타고 있는 아이의 수를 num 이라고 하면,
    N번째 아이가 타게 되는 놀이기구는
    t(N) - 1 시점에 남은 운행 시간이 1분인 놀이기구 중 (N - num)번째다.
"""
from sys import stdin
from typing import Tuple

input = lambda: stdin.readline().rstrip()


def solution(n: int, m: int, play_times: Tuple[int, ...]) -> int:
    def capacity(t: int) -> int:
        capa = 0
        for _pt in play_times:
            capa += (t // _pt) + 1
        return capa

    left, right = 0, int(6e10)
    t_n = -1
    while left <= right:
        mid = (left + right) // 2
        if capacity(mid) >= n:
            t_n = mid
            right = mid - 1
        else:
            left = mid + 1

    prev_num = capacity(t_n - 1)
    lack = n - prev_num
    cnt = 0
    for i, pt in enumerate(play_times, start=1):
        if pt - (t_n - 1) % pt == 1:
            cnt += 1
            if cnt == lack:
                return i
    return -1


if __name__ == '__main__':
    N, M = map(int, input().split())
    print(solution(N, M, tuple(map(int, input().split()))))
