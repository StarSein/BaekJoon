"""
여러 건물을 동시에 건설 가능하므로
지을 수 있는 건물은 모두 건설한다고 하면,
특정 시점에 건설 가능한 건물의 목록은 유일하다.

따라서 시간을 기준으로 오름차순 정렬이 되는 힙을 사용하자.
"""


import sys
import heapq


def input():
    return sys.stdin.readline().rstrip()


def solution():
    N, K = map(int, input().split())
    D = list(map(int, input().split()))

    precede_cnt = [0] * N
    follow_list = [[] for _ in range(N)]

    for _ in range(K):
        X, Y = map(lambda x: (int(x) - 1), input().split())
        precede_cnt[Y] += 1
        follow_list[X].append(Y)

    W = int(input()) - 1

    heap = [(D[i], i) for i in range(N) if precede_cnt[i] == 0]
    heapq.heapify(heap)

    ret = -1
    while heap:
        cur_time, cur_bld = heapq.heappop(heap)

        if cur_bld == W:
            ret = cur_time
            break

        for flw_bld in follow_list[cur_bld]:
            if precede_cnt[flw_bld] == 1:
                heapq.heappush(heap, (cur_time + D[flw_bld], flw_bld))
            else:
                precede_cnt[flw_bld] -= 1
    return ret


if __name__ == '__main__':
    T = int(input())
    for _ in range(T):
        print(solution())
