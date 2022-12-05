from sys import stdin
from typing import List, Tuple
import heapq


def input():
    return stdin.readline().rstrip()


def read_input():
    N, T, P = map(int, input().split())
    entries = list(tuple(input().split()) for _ in range(T))
    return N, T, P, entries


def solution(N: int, T: int, P: int, entries: List[Tuple[str, str]]) -> int:
    def time_str_to_int(time: str) -> int:
        return 60 * int(time[:2]) + int(time[2:])

    def assign_seats() -> int:
        INF = int(1e9)
        left_interval = [INF for _ in range(N + 1)]
        right_interval = [INF for _ in range(N + 1)]
        left_idx = 0
        for i in range(1, N + 1):
            if occupied[i]:
                left_idx = i
            if left_idx >= 1:
                left_interval[i] = i - left_idx
        right_idx = N + 1
        for i in range(N, 0, -1):
            if occupied[i]:
                right_idx = i
            if right_idx <= N:
                right_interval[i] = right_idx - i
        return max((x for x in range(1, N + 1)),
                   key=lambda x: min(left_interval[x], right_interval[x]))

    occupied = [False for _ in range(N + 1)]
    ready_q = [tuple(map(lambda x: time_str_to_int(x), entry)) for entry in entries]
    run_q = []
    heapq.heapify(ready_q)
    START = time_str_to_int("0900")
    END = time_str_to_int("2100")
    ans = 0
    for t in range(START, END):
        while run_q and run_q[0][0] == t:
            e, seat = heapq.heappop(run_q)
            occupied[seat] = False
        while ready_q and ready_q[0][0] == t:
            s, e = heapq.heappop(ready_q)
            if s == e:
                continue
            seat = assign_seats()
            occupied[seat] = True
            heapq.heappush(run_q, (e, seat))
        if not occupied[P]:
            ans += 1
    return ans


if __name__ == '__main__':
    print(solution(*read_input()))
    