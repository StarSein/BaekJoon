"""
1. N을 '운행 시간 M개의 최소공배수만큼의 시간 동안 탑승 가능한 아이의 수'로 나눈 나머지를 새로운 N으로 잡자.
2. 두 개의 힙을 사용하자.
    1) 현재 탑승 중인 놀이기구: (종료 시간, 인덱스)
    2) 현재 비어 있는 놀이기구: 인덱스

[1차 채점] TLE
엣지 케이스)
2000000000 30
1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30
최소공배수: 2329089562800
탑승 가능한 아이 수: 9304682830147
새로운 N: 그대로 20억

"f(t) = t만큼의 시간이 지났을 때, 몇 명이 놀이기구에 탑승했는가?"라는 코멘트에서 힌트를 얻자.
내가 떠올린 1차 풀이는 '모든 놀이기구가 비어 있는 최대한의 시간'을 찾은 것과 마찬가지이다.
f(t) < N을 만족하는 t의 최댓값을 구하고 N -= f(t)를 하면,
t가 운행시간으로 나누어 떨어지지 않는 놀이기구만 현재 운행중에 있을 것이고,
각각 '운행시간 - (t % 운행시간)'만큼의 시간이 흐른 뒤에 비어 있을 것이다.
t + 1부터 두 개의 힙을 사용해 풀면 된다.
"""
import sys
import heapq
from typing import Tuple


def input():
    return sys.stdin.readline().rstrip()


def main():
    n, m = map(int, input().split())
    runtimes = list(map(int, input().split()))

    def binary_search(t: int) -> Tuple[int, int]:
        lp, rp = 0, t
        best_time = 0
        while lp <= rp:
            mid = lp + (rp - lp) // 2
            num_user = sum(mid // runtime for runtime in runtimes)
            if num_user < n - m:
                best_time = max(best_time, mid)
                lp = mid + 1
            else:
                rp = mid - 1
        best_num_user = sum(best_time // runtime for runtime in runtimes)
        return best_time, best_num_user

    MAX_TIME = n * max(runtimes) * len(runtimes)
    time, cnt_use = binary_search(MAX_TIME)
    occupied_heap = [(runtime - time % runtime, idx) for idx, runtime in enumerate(runtimes) if time % runtime != 0]
    empty_heap = [idx for idx, runtime in enumerate(runtimes) if time % runtime == 0]
    heapq.heapify(occupied_heap)
    heapq.heapify(empty_heap)

    curr_time = 0
    cnt_use += len(occupied_heap) + 1
    while cnt_use <= n:
        if empty_heap:
            occupied_idx = heapq.heappop(empty_heap)
            heapq.heappush(occupied_heap, (curr_time + runtimes[occupied_idx], occupied_idx))
            cnt_use += 1
        else:
            curr_time = occupied_heap[0][0]
            while occupied_heap and occupied_heap[0][0] == curr_time:
                empty_idx = heapq.heappop(occupied_heap)[1]
                heapq.heappush(empty_heap, empty_idx)
    print(occupied_idx + 1)


if __name__ == '__main__':
    main()
